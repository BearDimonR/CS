package com.miedviediev.cs.Homework2;

import com.miedviediev.cs.Homework2.Commands.ECommands;
import com.miedviediev.cs.Homework2.Models.Storage;
import com.miedviediev.cs.Homework2.Receivers.FakeReceiver;
import org.junit.Assert;
import org.junit.Test;

public class HomeworkTest {


    private void doWaitPool(int n) {
        try {
            Thread.sleep(n * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("DoWait");
    }

    @Test
    public void testAddingAmount() {
        String expected = String.valueOf(Double.valueOf(Storage.getInstance().getAmount(1)) + 8 * 10);
        FakeReceiver.getInstance().generateMsg(ECommands.ADD_AMOUNT_TO_PRODUCT.getId(),"1,8");
        for(int i = 0; i < 10; ++i)
            FakeReceiver.getInstance().receivePackage();
        doWaitPool(10);
        Assert.assertEquals(expected, Storage.getInstance().getAmount(1));
    }

    @Test
    public void testGettingAmount() {
        FakeReceiver.getInstance().generateMsg(ECommands.AMOUNT_OF_PRODUCT.getId(),"2");
        for(int i = 0; i < 5; ++i)
            FakeReceiver.getInstance().receivePackage();
        doWaitPool(5);
    }

    @Test
    public void testReducingAmount() {
        String expected = String.valueOf(Double.valueOf(Storage.getInstance().getAmount(3)) - 30 * 10);
        FakeReceiver.getInstance().generateMsg(ECommands.REDUCE_AMOUNT_OF_PRODUCT.getId(), "3,10");
        for(int i = 0; i < 30; ++i)
            FakeReceiver.getInstance().receivePackage();
        doWaitPool(30);
        Assert.assertEquals(expected, Storage.getInstance().getAmount(3));
    }

    @Test
    public void testAddingGroup() {
        FakeReceiver.getInstance().generateMsg(ECommands.ADD_GROUP_PRODUCT_TO_PRODUCT.getId(),"5,0");
        for(int i = 0; i < 20; ++i)
            FakeReceiver.getInstance().receivePackage();
        FakeReceiver.getInstance().generateMsg(ECommands.ADD_GROUP_PRODUCT_TO_PRODUCT.getId(),"5,2");
        FakeReceiver.getInstance().receivePackage();
        doWaitPool(20);
        Assert.assertEquals(2, Storage.getInstance().getProductById(5).getGroupProduct());

    }

    @Test
    public void testCreatingGroup() {
        FakeReceiver.getInstance().generateMsg(ECommands.ADD_GROUP.getId(),"newGroup,newGroupDesc");
        for(int i = 0; i < 20; ++i)
            FakeReceiver.getInstance().receivePackage();
        doWaitPool(20);
        Assert.assertEquals(23,Storage.getInstance().getGroupProducts().size());
    }

    @Test
    public void testSettingPrice() {
        FakeReceiver.getInstance().generateMsg(ECommands.SET_PRICE_TO_PRODUCT.getId(),"4,0");
        for(int i = 0; i < 20; ++i)
            FakeReceiver.getInstance().receivePackage();
        FakeReceiver.getInstance().generateMsg(ECommands.SET_PRICE_TO_PRODUCT.getId(),"4,228");
        FakeReceiver.getInstance().receivePackage();
        doWaitPool(20);
        Assert.assertTrue(Double.valueOf(228.0).compareTo(
                Storage.getInstance().getProductById(4).getPrice()) == 0);
    }
}
