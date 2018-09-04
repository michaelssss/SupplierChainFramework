package com.michaelssss.daemon;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderBusinessArrayListTest {
    @Test
    public void testSort() {
        OrderBusinessArrayList orderBusinessArrayList = new OrderBusinessArrayList();
        Action action1 = new Action1();
        Action action2 = new Action2();
        orderBusinessArrayList.add(action2);
        orderBusinessArrayList.add(action1);
        Assert.assertEquals(action2, orderBusinessArrayList.get(0));
        Assert.assertEquals(action1, orderBusinessArrayList.get(1));
        orderBusinessArrayList.sorted();
        Assert.assertEquals(action2, orderBusinessArrayList.get(1));
        Assert.assertEquals(action1, orderBusinessArrayList.get(0));
    }

    private static class Action1 extends Action {
        @Override
        public void act() {
            System.out.println("Action1 act");
        }

        @Override
        public List<Class<? extends Action>> getDepends() {
            return new ArrayList<>();
        }
    }

    private static class Action2 extends Action {
        @Override
        public void act() {
            System.out.println("Action2 act");
        }

        @Override
        public List<Class<? extends Action>> getDepends() {
            List<Class<? extends Action>> actions = new ArrayList<>();
            actions.add(Action1.class);
            return actions;
        }
    }
}
