package com.michaelssss.account;

import com.michaelssss.SpringBootTestBasic;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AuthoritiesSetImplTest extends SpringBootTestBasic {
    @Autowired
    private AuthSetCatalog authSetCatalog;

    private static Authority getAuthority() {
        Random random = new Random();
        Authority authority = new Authority();
        authority.setPath("/home/test" + random.nextInt());
        return authority;
    }

    @Test
    public void testAddAuthoritiesSet() {
        AuthoritiesSetImpl authoritiesSet = new AuthoritiesSetImpl();
        authoritiesSet.setName("testAuthSet");
        authoritiesSet.addParentAuthSet(authoritiesSet);
        Set<Authority> authorities = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            authorities.add(getAuthority());
        }
        authoritiesSet.setAuthorities(authorities);
        authoritiesSet = authSetCatalog.saveAndFlush(authoritiesSet);
        AuthoritiesSetImpl sample = new AuthoritiesSetImpl();
        sample.setName("testAuthSet");
        Assert.assertTrue(authSetCatalog.count(Example.of(sample)) != 0);
        authSetCatalog.delete(authoritiesSet);
    }

    @Test
    public void testAddParentAuthSet() {
        AuthoritiesSetImpl authoritiesSet = new AuthoritiesSetImpl();
        authoritiesSet.setName("testAuthSet");
        authoritiesSet.addParentAuthSet(authoritiesSet);
        Set<Authority> authorities = new HashSet<>();
        for (int i = 0; i < 1; i++) {
            authorities.add(getAuthority());
        }
        authoritiesSet.setAuthorities(authorities);
        authoritiesSet = authSetCatalog.saveAndFlush(authoritiesSet);
        AuthoritiesSetImpl testSetParent = new AuthoritiesSetImpl();
        testSetParent.setName("testSetParent");
        testSetParent.addParentAuthSet(testSetParent);
        AuthoritiesSetImpl sample = new AuthoritiesSetImpl();
        sample.setName("testAuthSet");
        Assert.assertEquals(authoritiesSet, authSetCatalog.findOne(Example.of(sample)));
        Assert.assertEquals(authoritiesSet.getParent(), authSetCatalog.findOne(Example.of(sample)).getParent());
        authSetCatalog.delete(authoritiesSet);
        authSetCatalog.delete(testSetParent);
    }

    @Test
    public void testAddChildrenAuthSet() {
        AuthoritiesSetImpl authoritiesSet = new AuthoritiesSetImpl();
        authoritiesSet.setName("testAuthSet");
        authoritiesSet.addParentAuthSet(authoritiesSet);
        Set<Authority> authorities = new HashSet<>();
        for (int i = 0; i < 1; i++) {
            authorities.add(getAuthority());
        }
        authoritiesSet.setAuthorities(authorities);
        Assert.assertNotNull(authoritiesSet.getChildren());
        Assert.assertTrue(authoritiesSet.getChildren().size() == 0);
        authoritiesSet = authSetCatalog.saveAndFlush(authoritiesSet);
        AuthoritiesSetImpl testSetChildren = new AuthoritiesSetImpl();
        testSetChildren.setName("testSetChildren");
        testSetChildren.addParentAuthSet(testSetChildren);
        authoritiesSet.addChildrenAuthSet(testSetChildren);
        Assert.assertTrue(authoritiesSet.getChildren().size() != 0);
        Assert.assertEquals(authoritiesSet, testSetChildren.getParent());
        authSetCatalog.delete(authoritiesSet);
        authSetCatalog.delete(testSetChildren);
    }
}
