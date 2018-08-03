package com.jzqh.account;

import com.jzqh.SpringBootTestBasic;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.TreeSet;

@Slf4j
public class UserTest extends SpringBootTestBasic {
    Authority page;
    Authority pageload;
    @Autowired
    private UserCatalog catalog;
    @Autowired
    private AuthorityCatalog authorityCatalog;

    @Before
    public void before() {
        page = new Authority();
        page.setUrl("/Pages");
        pageload = new Authority();
        pageload.setUrl("/Pages/load");
        page = authorityCatalog.saveAndFlush(page);
        pageload = authorityCatalog.saveAndFlush(pageload);
    }

    @Test
    public void testStartAddRootUser() {
        UserImpl sample = UserImpl.builder().username("8888").build();
        Assert.assertNotNull(catalog.findOne(Example.of(sample)));
    }

    @Test
    public void testAuthority() {
        Authority authority = new Authority();
        authority.setUrl("/Pages/load");
        UserImpl user = UserImpl.builder().username("8888").build();
        user = catalog.findOne(Example.of(user));
        user.authority(authority);
        Assert.assertTrue(user.hasAuthority(authority));
    }

    @Test
    public void testAuthorityOther() {
        User mockUser = UserImpl.builder().username("9999").password("1").authorities(new TreeSet<>()).build();
        Authority authority = new Authority();
        authority.setUrl("/Pages/load");
        UserImpl user = UserImpl.builder().username("8888").build();
        user = catalog.findOne(Example.of(user));
        user.authority(authority);
        user.authority(mockUser, authority);
        Assert.assertTrue(mockUser.hasAuthority(authority));
    }

    @Test
    public void testGetMenus() {
        UserImpl user = UserImpl.builder().username("8888").build();
        user = catalog.findOne(Example.of(user));
        user.authority(page);
        user.authority(pageload);
        Assert.assertTrue(user.getMenus().contains(page));
    }

    @Test
    public void testGetActions() {
        UserImpl user = UserImpl.builder().username("8888").build();
        user = catalog.findOne(Example.of(user));
        user.authority(page);
        user.authority(pageload);
        Assert.assertTrue(user.getActions().contains(pageload));
    }
}
