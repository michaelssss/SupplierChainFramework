package com.michaelssss.business.utils;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.annotation.PhoneNumberValidator;
import org.junit.Assert;
import org.junit.Test;

public class PhoneCheckerTest extends SpringBootTestBasic {

  @Test
  public void testIsValid() {
    PhoneNumberValidator phoneChecker = new PhoneNumberValidator();
    final String actualPhone = "18124601060";
    final String fakePhone = "12345678901";
    Assert.assertTrue(phoneChecker.isValid(actualPhone, null));
    Assert.assertFalse(phoneChecker.isValid(fakePhone, null));
  }
}
