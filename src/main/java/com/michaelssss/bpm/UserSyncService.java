package com.michaelssss.bpm;

import com.michaelssss.account.UserCatalog;
import com.michaelssss.account.UserImpl;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
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

  @Autowired
  public UserSyncService(IdentityService identityService, UserCatalog userCatalog) {
    this.identityService = identityService;
    this.userCatalog = userCatalog;
  }

  @PostConstruct
  public void init() {
    log.info("User sync Service Start");
    BusinessInitialActionCenter.registeredAction(this);
  }

  @Override
  public void act() {
    List<UserImpl> users = userCatalog.findAll();
    for (UserImpl user : users) {
      if (identityService.createUserQuery().userId(user.getUsername()).count() == 0) {
        User user1 = identityService.newUser(user.getUsername());
        identityService.saveUser(user1);
        log.info("User " + user.getUsername() + " sync to Activiti");
      }
    }
  }
}
