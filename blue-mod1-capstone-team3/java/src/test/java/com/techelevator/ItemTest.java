package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ItemTest {
    private Item item;


    @Before
    public void setUp(){
        item = new Item("testSlot", "testSlot", BigDecimal.valueOf(2.05).setScale(2), "Drink");
    }

    @Test
    public void test_item_constructor(){
        String expectedSlot = "testSlot";
        String expectedName = "testSlot";
        BigDecimal expectedPrice = BigDecimal.valueOf(2.05).setScale(2);
        String expectedType = "Drink";

        Assert.assertEquals(expectedSlot, item.getSlot());
        Assert.assertEquals(expectedName, item.getName());
        Assert.assertEquals(expectedPrice, item.getPrice());
        Assert.assertEquals(expectedType, item.getType());

        item.updateAmount();
        Assert.assertEquals(4, item.getAmount());
    }

    @Test
    public void item_amount_is_not_negative(){
        item.updateAmount();
        item.updateAmount();
        item.updateAmount();
        item.updateAmount();
        item.updateAmount();
        item.updateAmount();

        Assert.assertEquals(0, item.getAmount());
    }

    @Test
    public void get_message_test() {
        String expected = "Glug Glug, Yum!";
        String result = item.getMessage();

        Assert.assertEquals(expected, result);
    }
}
