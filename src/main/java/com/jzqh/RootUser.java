package com.jzqh;

import com.jzqh.account.User;
import com.jzqh.account.UserImpl;
import com.jzqh.account.UserProfile;
import com.jzqh.account.accessmanagement.authority.Action;
import com.jzqh.account.accessmanagement.authority.Menu;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.TreeSet;

/**
 * 本类用于返回一个用户对象
 * 可以用于业务新增用户参考
 *
 * @author Michaelssss
 * @see UserImpl
 */
public class RootUser {
    public static void addUser() {
        Menu rootMenu = new Menu();
        rootMenu.setUrl("/HelloWorld");
        Action menuAction = new Action();
        Menu menu = new Menu();
        menu.setUrl("/Menu");
        rootMenu.addMenu(menu);
        menuAction.setUrl("/Menu/get");
        Action menuActionGet = new Action();
        menuAction.setOrder(0);
        menuActionGet.setOrder(1);
        menuActionGet.setUrl("/Menu/Action/get");
        TreeSet<Action> actions = new TreeSet<>();
        actions.add(menuAction);
        actions.add(menuActionGet);
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
        UserProfile testUserProfile = new UserProfile();
        testUserProfile.setAge(23);
        testUserProfile.setName("testName");
        testUserProfile.setEmail("test@test.com");
        testUserProfile.setSexual("male");
        testUserProfile.setPhone("18124601060");
        User user = UserImpl.builder().
                username("8888").
                password(encoder.encode("1")).
                actions(actions).
                userProfile(testUserProfile).
                rootMenu(rootMenu).
                build();
        user.registered();
    }
}
