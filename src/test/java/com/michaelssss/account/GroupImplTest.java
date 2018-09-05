package com.michaelssss.account;

import com.michaelssss.SpringBootTestBasic;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

public class GroupImplTest extends SpringBootTestBasic {
    @Autowired
    private AuthSetCatalog authSetCatalog;

    @Test
    public void testAddGroup() {
        GroupImpl authoritiesSet = new GroupImpl();
        authoritiesSet.setName("testAuthSet");
        authoritiesSet = authSetCatalog.saveAndFlush(authoritiesSet);
        GroupImpl sample = new GroupImpl();
        sample.setName("testAuthSet");
        Assert.assertTrue(authSetCatalog.count(Example.of(sample)) != 0);
        authSetCatalog.delete(authoritiesSet);
    }

    @Test
    public void testAddUser() {
        Group group = new GroupImpl();
        User user = new UserImpl();
        group.setGroupName("testGroup");
        group.addUser(user);
        Assert.assertTrue(group.getUsers().contains(user));
    }
}
