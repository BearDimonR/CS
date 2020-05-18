package com.miedviediev.cs.Practice1;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private String INPUT = "123456";
    private String INPUT_TEXT = "Hi, i've written my own decoder and it is super! I like it very much!...";

    @Test
    public void testHex() {
        Assert.assertEquals(INPUT, App.test(INPUT));
    }

    @Test
    public void testRandomInput() {
        double n = new Random().nextGaussian();
        Assert.assertEquals(String.valueOf(n), App.test(String.valueOf(n)));
    }

    @Test
    public void testTextInput() {
        Assert.assertEquals(INPUT_TEXT, App.test(INPUT_TEXT));
    }
}
