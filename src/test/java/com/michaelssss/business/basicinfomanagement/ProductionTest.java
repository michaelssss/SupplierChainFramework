package com.michaelssss.business.basicinfomanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.business.basicinfomanagement.domain.ProductionImpl;
import com.michaelssss.business.basicinfomanagement.domain.PropertyKeyValue;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Test;

public class ProductionTest extends SpringBootTestBasic {

  @Test
  public void testAddPropertyKeyValue() {
    Production production =
        ProductionImpl.builder().name("test").propertyKeyValues(new HashSet<>()).build();
    PropertyKeyValue propertyKeyValue = new PropertyKeyValue(null, "testKey", "testValue");
    production.addProperty(propertyKeyValue);
    Assert.assertTrue(production.getProperties().contains(propertyKeyValue));
  }
}
