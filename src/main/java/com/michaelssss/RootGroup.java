package com.michaelssss;

import com.michaelssss.account.GroupCatalog;
import com.michaelssss.account.GroupImpl;
import com.michaelssss.account.UserCatalog;
import com.michaelssss.account.UserImpl;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * 本类用于初始化时候创建一个组织 本类由Spring管理其实例
 *
 * @author Michaelssss
 * @date 2018年10月11日
 */
@Component
@Slf4j
public class RootGroup extends Action {

  private UserCatalog userCatalog;
  private GroupCatalog groupCatalog;

  @Autowired
  public RootGroup(UserCatalog userCatalog, GroupCatalog groupCatalog) {
    this.userCatalog = userCatalog;
    this.groupCatalog = groupCatalog;
  }

  @PostConstruct
  public void init() {
    BusinessInitialActionCenter.registeredAction(this);
  }

  @Override
  public void act() {
    log.info("create root group");
    UserImpl root = UserImpl.builder().username("8888").build();
    root = userCatalog.findOne(Example.of(root)).get();
    GroupImpl rootGroup = new GroupImpl();
    rootGroup.setName("root");
    if (!groupCatalog.exists(Example.of(rootGroup))) {
      rootGroup.addUser(root);
      groupCatalog.save(rootGroup);
    }
    log.info("create root group finish");
  }

  @Override
  public List<Class<? extends Action>> getDepends() {
    List<Class<? extends Action>> ac = super.getDepends();
    ac.add(RootUser.class);
    return ac;
  }
}
