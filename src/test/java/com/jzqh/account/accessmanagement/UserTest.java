package com.jzqh.account.accessmanagement;

import com.jzqh.account.User;
import com.jzqh.account.UserImpl;
import com.jzqh.account.accessmanagement.authority.Action;
import com.jzqh.account.accessmanagement.authority.Menu;
import com.jzqh.rzzl2.SpringBootTestBasic;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.TreeSet;

public class UserTest extends SpringBootTestBasic {
    @Test
    public void testRegistered() {
        Menu rootMenu = new Menu();
        rootMenu.setUrl("/HelloWorld");
        Action menuAction = new Action();
        menuAction.setUrl("/Menu/get");
        Action menuActionGet = new Action();
        menuActionGet.setUrl("/Menu/Action/get");
        TreeSet<Action> actions = new TreeSet<>();
        actions.add(menuAction);
        actions.add(menuActionGet);
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
        User user = UserImpl.builder().
                username("8888").
                password(encoder.encode("1")).
                actions(actions).rootMenu(rootMenu).
                build();
        user.registered();
    }
}
