package com.michaelssss;

import com.michaelssss.account.FunctionName;
import com.michaelssss.account.FunctionNameCatalog;
import com.michaelssss.account.UserCatalog;
import com.michaelssss.account.UserImpl;
import com.michaelssss.account.UserProfile;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
import com.michaelssss.utils.JSON;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * 本类用于系统初始化时候，自动注册一个根用户 账号为8888，密码为1
 *
 * @author Michaelssss
 * @see UserImpl
 */
@Component
@Slf4j
public class RootUser extends Action {

  private static UserImpl root() {
    UserProfile testUserProfile = new UserProfile();
    testUserProfile.setAge("23");
    testUserProfile.setName("testName");
    testUserProfile.setEmail("test@test.com");
    testUserProfile.setSexual("male");
    testUserProfile.setPhone("18124601060");
    UserImpl user =
        UserImpl.builder()
            .username("8888")
            .password("1")
            .functionNames(new HashSet<>())
            .userProfile(testUserProfile)
            .build();
    return user;
  }

  @Override
  public void act() {
    UserImpl root = RootUser.root();
    UserCatalog userCatalog = SpringContextHolder.getBean(UserCatalog.class);
    FunctionNameCatalog authorityCatalog = SpringContextHolder.getBean(FunctionNameCatalog.class);
    UserImpl sample = UserImpl.builder().username(root.getUsername()).build();
    if (!userCatalog.exists(Example.of(sample))) {
      log.info("create root user" + JSON.toJSONString(root));
      root.registered();
    }
    root = userCatalog.findOne(Example.of(sample)).get();
    for (FunctionName functionName : authorityCatalog.findAll()) {
      root.authority(functionName);
      log.debug("auth root functionName: " + functionName.getFunctionName());
    }
    userCatalog.saveAndFlush(root);
    log.info("auth root all authority");
  }

  @PostConstruct
  public void init() {
    log.info("registered root user");
    BusinessInitialActionCenter.registeredAction(this);
  }
}
