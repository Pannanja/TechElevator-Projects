package com.techelevator;

import com.techelevator.view.Menu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

public class VendingMachineTest {

    VendingMachine vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void finish_transaction_sets_balance_to_zero() {
        vendingMachine.addBalance(10);
        Assert.assertTrue("The balance should be $10 here", vendingMachine.getBalance().compareTo(BigDecimal.TEN) == 0);
        vendingMachine.finishTransaction();
        Assert.assertTrue("The balance should be $0 here", (vendingMachine.getBalance().compareTo(BigDecimal.ZERO)) == 0);
    }

    @Test
    public void add_balance_test() {
        BigDecimal expected = BigDecimal.TEN;
        vendingMachine.addBalance(10);
        Assert.assertTrue(vendingMachine.getBalance().compareTo(expected) == 0);
        vendingMachine.addBalance(3);
        expected = BigDecimal.valueOf(13.00);
        Assert.assertTrue(vendingMachine.getBalance().compareTo(expected) == 0);
    }

    @Test
    public void deposit_valid_input_test() {
        boolean result = vendingMachine.deposit("5");

        Assert.assertFalse(result);

        Assert.assertEquals(BigDecimal.valueOf(5.00).setScale(2), vendingMachine.getBalance());
    }

    @Test
    public void deposit_invalid_input_4_test() {
        boolean result = vendingMachine.deposit("4");

        Assert.assertFalse(result);

        Assert.assertEquals(BigDecimal.ZERO.setScale(2), vendingMachine.getBalance());
    }

    @Test
    public void deposit_valid_input_0_test() {
        boolean result = vendingMachine.deposit("0");

        Assert.assertTrue(result);

        Assert.assertEquals(BigDecimal.ZERO.setScale(2), vendingMachine.getBalance());
    }

    @Test
    public void deposit_invalid_input_null_test() {
        boolean result = vendingMachine.deposit(null);

        Assert.assertFalse(result);

        Assert.assertEquals(BigDecimal.ZERO.setScale(2), vendingMachine.getBalance());
    }

    @Test
    public void check_selected_item_valid_with_enough_balance_test() {
        int expectedAmount = 4;

        vendingMachine.addBalance(5);
        vendingMachine.checkSelectedItem("D2");
        int result = vendingMachine.getInventory().get("D2").getAmount();

        Assert.assertEquals(expectedAmount, result);
    }

}

