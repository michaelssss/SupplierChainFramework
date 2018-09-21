package com.michaelssss.account;

import com.michaelssss.SpringBootTestBasic;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

public class GroupImplTest extends SpringBootTestBasic {
    @Autowired
    private GroupCatalog groupCatalog;

    @Test
    public void testAddGroup() {
        GroupImpl authoritiesSet = new GroupImpl();
        authoritiesSet.setName("testAuthSet");
        authoritiesSet = groupCatalog.saveAndFlush(authoritiesSet);
        GroupImpl sample = new GroupImpl();
        sample.setName("testAuthSet");
        Assert.assertTrue(groupCatalog.count(Example.of(sample)) != 0);
        groupCatalog.delete(authoritiesSet);
    }

    @Test
    public void testAddUser() {
        Group group = new GroupImpl();
        User user = new UserImpl();
        group.setName("testGroup");
        group.addUser(user);
        Assert.assertTrue(group.getUsers().contains(user));
    }
}
