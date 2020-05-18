package com.miedviediev.cs.Practice1;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private String INPUT = "123456";

    @Test
    public void testHex() {
        Assert.assertEquals(INPUT, App.test(INPUT));
    }
}
