package com.michaelssss.scanner;

import com.michaelssss.account.FunctionName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

public class RouterScannerTest {

  @Test
  public void testHasAnnotations() throws Exception {
    RouteScanner routeScanner = new RouteScanner();
    Method hasAnnotations =
        routeScanner.getClass().getDeclaredMethod("hasAnnotations", Method.class, Class[].class);
    hasAnnotations.setAccessible(true);
    Assert.assertTrue(
        (Boolean)
            hasAnnotations.invoke(
                routeScanner,
                TestClass.class.getMethod("hasApiOperationMethod"),
                new Class[]{ApiOperation.class, RequestMapping.class}));
    Method classHasAnnotations =
        routeScanner.getClass().getDeclaredMethod("hasAnnotations", Class.class, Class[].class);
    classHasAnnotations.setAccessible(true);
    Assert.assertTrue(
        (Boolean)
            classHasAnnotations.invoke(
                routeScanner, TestClass.class, new Class[]{Api.class, RequestMapping.class}));
    Assert.assertFalse(
        (Boolean)
            hasAnnotations.invoke(
                routeScanner,
                TestClass.class.getMethod("noApiOperationMethod"),
                new Class[]{ApiOperation.class, RequestMapping.class}));
  }

  @Test
  public void testGetFunctionNames() throws Exception {
    RouteScanner routeScanner = new RouteScanner();
    Method getFunctionNames =
        routeScanner.getClass().getDeclaredMethod("getFunctionNames", Collection.class);
    getFunctionNames.setAccessible(true);
    FunctionName assertHasFunctionName = new FunctionName();
    assertHasFunctionName.setFunctionName("testClass.test");
    assertHasFunctionName.setUrl("/testMapping/methodRequestMapping");
    Collection<FunctionName> result =
        (Collection)
            getFunctionNames.invoke(routeScanner, Arrays.asList(new Class[]{TestClass.class}));
    boolean flag = false;
    for (FunctionName functionName1 : result) {
      if (functionName1.equals(assertHasFunctionName)) {
        flag = true;
      }
    }
    Assert.assertTrue(flag);
  }

  @Api(value = "testClass")
  @RequestMapping(value = "testMapping")
  private static class TestClass {

    @ApiOperation(value = "test")
    @RequestMapping(value = "methodRequestMapping")
    public String hasApiOperationMethod() {
      return "";
    }

    public String noApiOperationMethod() {
      return "";
    }
  }
}
