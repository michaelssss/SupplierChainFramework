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

    private static FunctionName getAuthority() {
        Random random = new Random();
        FunctionName functionName = new FunctionName();
        functionName.setFunctionName(Integer.toString(random.nextInt()));
        return functionName;
    }

    @Test
    public void testAddAuthoritiesSet() {
        AuthoritiesSetImpl authoritiesSet = new AuthoritiesSetImpl();
        authoritiesSet.setName("testAuthSet");
        Set<FunctionName> authorities = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            authorities.add(getAuthority());
        }
        authoritiesSet.setFunctionNames(authorities);
        authoritiesSet = authSetCatalog.saveAndFlush(authoritiesSet);
        AuthoritiesSetImpl sample = new AuthoritiesSetImpl();
        sample.setName("testAuthSet");
        Assert.assertTrue(authSetCatalog.count(Example.of(sample)) != 0);
        authSetCatalog.delete(authoritiesSet);
    }
}
