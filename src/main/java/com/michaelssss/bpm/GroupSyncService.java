package com.michaelssss.bpm;

import com.michaelssss.RootGroup;
import com.michaelssss.account.Group;
import com.michaelssss.account.GroupCatalog;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupSyncService extends Action {

  private GroupCatalog groupCatalog;
  private IdentityService identityService;

  @Autowired
  public GroupSyncService(GroupCatalog groupCatalog, IdentityService identityService) {
    this.groupCatalog = groupCatalog;
    this.identityService = identityService;
  }

  @PostConstruct
  public void init() {
    log.debug("Group Sync Service Initial");
    BusinessInitialActionCenter.registeredAction(this);
  }

  @Override
  public void act() {
    List<Group> groups = new ArrayList<>(groupCatalog.findAll());
    for (Group group : groups) {
      if (identityService.createGroupQuery().groupId(group.getName()).count() == 0l) {
        identityService.saveGroup(identityService.newGroup(group.getName()));
        log.info("group :" + group.getName() + " had been sync");
      }
    }
  }

  @Override
  public List<Class<? extends Action>> getDepends() {
    List<Class<? extends Action>> father = super.getDepends();
    father.add(RootGroup.class);
    return father;
  }
}
