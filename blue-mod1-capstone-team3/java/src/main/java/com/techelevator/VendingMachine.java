package com.techelevator;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    private Map<String, Item> inventory;
    private BigDecimal balance = BigDecimal.ZERO.setScale(2);

    private static final BigDecimal QUARTER = BigDecimal.valueOf(0.25);
    private static final BigDecimal DIME = BigDecimal.valueOf(0.10);
    private static final BigDecimal NICKEL = BigDecimal.valueOf(0.05);


    public VendingMachine() {
        inventory = InventoryLoader.loadInventory();
        LogGenerator.refreshLog();
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addBalance(int moneyAdded){
        BigDecimal moneyBigDecimal = BigDecimal.valueOf(moneyAdded);
        balance = balance.add(moneyBigDecimal);
    }

    public boolean deposit(String line){
        try {
            int amountEntered = Integer.parseInt(line);
            if (amountEntered == 1 || amountEntered == 2 || amountEntered == 5 || amountEntered == 10) {
                BigDecimal startBalance = getBalance();
                addBalance(amountEntered);
                System.out.println("Current Balance: $" + getBalance());
                LogGenerator.generateLog("FEED MONEY:", startBalance, getBalance());
            } else if (amountEntered == 0) return true;
            else System.out.println("Sorry, that is not a valid dollar amount.");
        } catch (Exception ex) {
            System.out.println("Sorry, that is not a valid dollar amount.");
        }

        return false;
    }


    public void checkSelectedItem(String itemID){

            if (getInventory().containsKey(itemID)) {
                if (getInventory().get(itemID).getAmount() == 0) {
                    System.out.println("The item you selected is sold out!");

                } else {
                    if (getInventory().get(itemID).getPrice().compareTo(getBalance()) == -1 ||
                            getInventory().get(itemID).getPrice().compareTo(getBalance()) == 0) {
                        dispenseItem(itemID);

                    } else {
                        System.out.println("Insufficient funds!");

                    }
                }
            } else {
                System.out.println("Item ID not valid!");

            }
    }

    private void dispenseItem(String itemID) {
        Item item = inventory.get(itemID);
        String format = String.format("%s | %s | %s", item.getName(), item.getPrice(), getBalance().subtract(item.getPrice()));
        System.out.println(format + "\n" + item.getMessage());

        BigDecimal startBalance = getBalance();
        balance = balance.subtract(item.getPrice());
        item.updateAmount();

        LogGenerator.generateLog(item.getName() + " " + itemID, startBalance, balance);
    }

    public void finishTransaction(){
        BigDecimal total = getBalance();
        BigDecimal numberOfQuarters = getBalance().divideToIntegralValue(QUARTER).setScale(0);
        BigDecimal startBalance = getBalance();
        balance = getBalance().remainder(QUARTER);
        BigDecimal numberOfDimes = getBalance().divideToIntegralValue(DIME).setScale(0);
        balance = getBalance().remainder(DIME);
        BigDecimal numberOfNickels = getBalance().divideToIntegralValue(NICKEL).setScale(0);
        balance = getBalance().remainder(NICKEL);
        if (balance.compareTo(BigDecimal.ZERO) != 0)System.out.println("SOMETHING WENT WRONG!!");
        System.out.println("Your change is:" + "\nQuarters: " + numberOfQuarters + "\nDimes: " + numberOfDimes + "\nNickels: " + numberOfNickels);
        System.out.println("Total: $" + total);

        LogGenerator.generateLog("GIVE CHANGE: ", startBalance, getBalance());
    }
}
