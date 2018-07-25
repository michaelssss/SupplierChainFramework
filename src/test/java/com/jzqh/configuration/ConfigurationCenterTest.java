package com.jzqh.configuration;

import com.jzqh.rzzl2.SpringBootTestBasic;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfigurationCenterTest extends SpringBootTestBasic {
    @Autowired
    private ConfigurationCenter configurationCenter;

    @Test
    public void testAddKeyValue() {
        configurationCenter.addKeyValue("testSubSystem", "testKey", "testValue");
        Assert.assertNotNull(configurationCenter.getValue("testSubSystem", "testKey"));
        Assert.assertEquals("testValue", configurationCenter.getValue("testSubSystem", "testKey"));
    }
}
