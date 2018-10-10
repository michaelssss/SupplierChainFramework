package com.michaelssss.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.michaelssss.SpringContextHolder;
import com.michaelssss.configuration.ConfigurationCenter;
import com.michaelssss.utils.Sha256;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(
    name = "sys_user",
    indexes = {@Index(name = "idx_username", columnList = "username", unique = true)})
public class UserImpl implements User, Serializable {

  private static final long serialVersionUID = -2426342993719284587L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  @Column(length = 64)
  private String username;

  @JsonIgnore
  private String password;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private UserProfile userProfile;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<FunctionName> functionNames = new HashSet<>();

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean validatePassword(String password) {
    Sha256 encoder = new Sha256(SpringContextHolder.getBean(ConfigurationCenter.class));
    return encoder.isPwd(this.password, password);
  }

  @Override
  public void authority(User other, FunctionName functionName) {
    if (null != functionName && hasAuthority(functionName.getUrl())) {
      other.authority(functionName);
    } else {
      throw new AuthorityException("you have no Authority");
    }
  }

  @Override
  public void authority(FunctionName functionName) {
    this.functionNames.add(functionName);
  }

  @Override
  public Set<FunctionName> getHasAuthorityFunctionName() {
    return Collections.unmodifiableSet(this.functionNames);
  }

  @Override
  public boolean hasAuthority(String functionUrl) {
    Collection<FunctionName> functionNames = getHasAuthorityFunctionName();
    for (FunctionName functionName : functionNames) {
      if (functionName.getUrl().equals(functionUrl)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void registered() {
    if (StringUtils.isNotEmpty(this.username) && StringUtils.isNotEmpty(this.password)) {
      PlatformTransactionManager platformTransactionManager =
          SpringContextHolder.getBean(PlatformTransactionManager.class);
      TransactionStatus transactionStatus =
          platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
      Sha256 encoder = new Sha256(SpringContextHolder.getBean(ConfigurationCenter.class));
      FunctionName functionName = new FunctionName();
      functionName.setFunctionName("用户相关.获取功能列表");
      functionName =
          SpringContextHolder.getBean(FunctionNameCatalog.class)
              .findOne(Example.of(functionName))
              .get();
      this.functionNames.add(functionName);
      this.password = encoder.getPwd(this.password);
      SpringContextHolder.getBean(UserCatalog.class).saveAndFlush(this);
      platformTransactionManager.commit(transactionStatus);
    }
  }

  @Override
  public Token login(String password, Date outdate) {
    if (validatePassword(password)) {
      Token token = new Token();
      token.setToken(UUID.randomUUID().toString());
      token.setOutdate(outdate);
      token.setUser(this);
      token = SpringContextHolder.getBean(TokenCatalog.class).saveAndFlush(token);
      return token;
    }
    return null;
  }

  @Override
  public void logout(String token) {
    TokenCatalog tokenCatalog = SpringContextHolder.getBean(TokenCatalog.class);
    Token sample = new Token();
    sample.setToken(token);
    Token token1 = tokenCatalog.findOne(Example.of(sample)).get();
    if (token1.getUser().uid.equals(this.uid)) {
      tokenCatalog.delete(token1);
    }
  }

  @Override
  public void updatePassword(String password) {
    Sha256 encoder = new Sha256(SpringContextHolder.getBean(ConfigurationCenter.class));
    this.password = encoder.getPwd(password);
    SpringContextHolder.getBean(UserCatalog.class).saveAndFlush(this);
  }

  @Override
  public UserProfile getProfile() {
    return this.userProfile;
  }

  @Override
  public List<Task> getTasks() {
    IdentityService identityService = SpringContextHolder.getBean(IdentityService.class);
    TaskService taskService = SpringContextHolder.getBean(TaskService.class);
    org.activiti.engine.identity.User user =
        identityService.createUserQuery().userId(this.username).singleResult();
    return taskService.createTaskQuery().taskCandidateOrAssigned(user.getId()).list();
  }

  @Override
  public void completeTask(String taskId) {
    // ?或许可以增加一层很薄的Service以完成事务处理
    for (Task task : getTasks()) {
      if (task.getId().equals(taskId)) {
        SpringContextHolder.getBean(TaskService.class).complete(task.getId());
        break;
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserImpl)) {
      return false;
    }
    UserImpl user = (UserImpl) o;
    return Objects.equals(username, user.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }
}
