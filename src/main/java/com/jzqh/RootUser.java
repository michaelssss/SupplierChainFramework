package com.jzqh;

import com.jzqh.account.*;
import com.jzqh.daemon.Action;
import com.jzqh.daemon.BusinessInitialActionCenter;
import com.jzqh.utils.Sha256;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TreeSet;

/**
 * 本类用于返回一个用户对象
 * 可以用于业务新增用户参考
 *
 * @author Michaelssss
 * @see UserImpl
 */
@Component
@Slf4j
public class RootUser {
    public static UserImpl RootUser() {
        Sha256 sha256 = new Sha256();
        UserProfile testUserProfile = new UserProfile();
        testUserProfile.setAge(23);
        testUserProfile.setName("testName");
        testUserProfile.setEmail("test@test.com");
        testUserProfile.setSexual("male");
        testUserProfile.setPhone("18124601060");
        UserImpl user = UserImpl.builder().
                username("8888").
                password(sha256.getPwd("1")).
                authorities(new TreeSet<>()).
                userProfile(testUserProfile).
                build();
        return user;
    }

    @PostConstruct
    public void init() {
        log.info("registered root user");
        BusinessInitialActionCenter.registeredAction(new Action() {
            @Override
            public void act() {
                UserImpl root = RootUser();
                UserCatalog userCatalog = SpringContextHolder.getBean(UserCatalog.class);
                AuthorityCatalog authorityCatalog = SpringContextHolder.getBean(AuthorityCatalog.class);
                UserImpl sample = UserImpl.builder().username(root.getUsername()).build();
                if (0 == userCatalog.count(Example.of(sample))) {
                    root.registered();
                }
                root = userCatalog.findOne(Example.of(sample));
                for (Authority authority : authorityCatalog.findAll()) {
                    root.authority(authority);
                    log.debug("auth root authority: " + authority.getName());
                }
                userCatalog.saveAndFlush(root);
                log.info("auth root all authority");
            }

            @Override
            public int getOrder() {
                return 1;
            }
        });
        log.info("BusinessInitialActionCenter.status=" + BusinessInitialActionCenter.getStatus());
    }
}
