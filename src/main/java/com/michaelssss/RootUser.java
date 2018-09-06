package com.michaelssss;

import com.michaelssss.account.*;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
import com.michaelssss.utils.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;

/**
 * 本类用于返回一个用户对象
 * 可以用于业务新增用户参考
 *
 * @author Michaelssss
 * @see UserImpl
 */
@Component
@Slf4j
public class RootUser extends Action {
    @Override
    public void act() {
        UserImpl root = RootUser.RootUser();
        UserCatalog userCatalog = SpringContextHolder.getBean(UserCatalog.class);
        AuthorityCatalog authorityCatalog = SpringContextHolder.getBean(AuthorityCatalog.class);
        UserImpl sample = UserImpl.builder().username(root.getUsername()).build();
        if (0 == userCatalog.count(Example.of(sample))) {
            log.info("create root user" + JSON.toJSONString(root));
            root.registered();
        }
        root = userCatalog.findOne(Example.of(sample));
        for (FunctionName functionName : authorityCatalog.findAll()) {
            root.authority(functionName);
            log.debug("auth root functionName: " + functionName.getFunctionName());
        }
        userCatalog.saveAndFlush(root);
        log.info("auth root all authority");
    }

    public static UserImpl RootUser() {
        UserProfile testUserProfile = new UserProfile();
        testUserProfile.setAge("23");
        testUserProfile.setName("testName");
        testUserProfile.setEmail("test@test.com");
        testUserProfile.setSexual("male");
        testUserProfile.setPhone("18124601060");
        UserImpl user = UserImpl.builder().
                username("8888").
                password("1").
                functionNames(new HashSet<>()).
                userProfile(testUserProfile).
                build();
        return user;
    }

    @PostConstruct
    public void init() {
        log.info("registered root user");
        BusinessInitialActionCenter.registeredAction(this);
    }
}
