package com.michaelssss.bpm;

import com.michaelssss.account.Group;
import com.michaelssss.account.GroupCatalog;
import com.michaelssss.account.UserCatalog;
import com.michaelssss.account.UserImpl;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 本类用于同步自有账号系统与Activiti账号系统
 */
@Service
@Slf4j
public class UserSyncService extends Action {

  private IdentityService identityService;
  private UserCatalog userCatalog;
  private GroupCatalog groupCatalog;

  @Autowired
  public UserSyncService(
      IdentityService identityService, UserCatalog userCatalog, GroupCatalog groupCatalog) {
    this.identityService = identityService;
    this.userCatalog = userCatalog;
    this.groupCatalog = groupCatalog;
  }

  @PostConstruct
  public void init() {
    log.info("User sync Service Start");
    BusinessInitialActionCenter.registeredAction(this);
  }

  /** 部门应该先同步至Activiti的账号体系 然后再同步用户信息 接着判断在我们的体系中遍历Group将Group与User在Activiti的体系中建立关系 */
  @Override
  public void act() {
    List<UserImpl> users = userCatalog.findAll();
    for (UserImpl user : users) {
      if (identityService.createUserQuery().userId(user.getUsername()).count() == 0) {
        List<Group> groups = new ArrayList<>(groupCatalog.findAll());
        User user1 = identityService.newUser(user.getUsername());
        identityService.saveUser(user1);
        for (Group group : groups) {
          if (group.getUsers().contains(user)) {
            identityService.createMembership(user.getUsername(), group.getName());
          }
        }
        log.info("User " + user.getUsername() + " sync to Activiti");
      }
    }
  }

  @Override
  public List<Class<? extends Action>> getDepends() {
    List<Class<? extends Action>> list = super.getDepends();
    list.add(GroupSyncService.class);
    return list;
  }
}
