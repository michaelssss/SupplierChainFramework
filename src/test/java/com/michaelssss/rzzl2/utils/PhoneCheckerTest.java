package com.michaelssss.rzzl2.utils;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.utils.PhoneChecker;
import org.junit.Assert;
import org.junit.Test;

public class PhoneCheckerTest extends SpringBootTestBasic {

    @Test
    public void testIsValid() {
        PhoneChecker phoneChecker = new PhoneChecker();
        final String actualPhone = "18124601060";
        final String fakePhone = "12345678901";
        Assert.assertTrue(phoneChecker.isValid(actualPhone, null));
        Assert.assertFalse(phoneChecker.isValid(fakePhone, null));
    }
}
