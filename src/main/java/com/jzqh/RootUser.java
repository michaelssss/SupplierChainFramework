package com.jzqh;

import com.jzqh.account.UserCatalog;
import com.jzqh.account.UserImpl;
import com.jzqh.account.UserProfile;
import com.jzqh.daemon.BusinessInitialActionCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
    static {
        log.info("registered root user");
        BusinessInitialActionCenter.registeredAction(() -> {
            UserCatalog userCatalog = SpringContextHolder.getBean(UserCatalog.class);
            UserImpl sample = RootUser();
            if (0 == userCatalog.count(Example.of(sample)))
                RootUser().registered();
        });
        log.info("BusinessInitialActionCenter.status=" + BusinessInitialActionCenter.getStatus());
    }

    public static UserImpl RootUser() {
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
        UserProfile testUserProfile = new UserProfile();
        testUserProfile.setAge(23);
        testUserProfile.setName("testName");
        testUserProfile.setEmail("test@test.com");
        testUserProfile.setSexual("male");
        testUserProfile.setPhone("18124601060");
        UserImpl user = UserImpl.builder().
                username("8888").
                password(encoder.encode("1")).
                authorities(new ArrayList<>()).
                userProfile(testUserProfile).
                build();
        return user;
    }
}
