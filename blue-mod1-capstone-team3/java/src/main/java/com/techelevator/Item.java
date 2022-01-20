package com.techelevator;

import java.math.BigDecimal;

public class Item {

    private String slot;
    private String name;
    private BigDecimal price;
    private String type;
    private int amount;

    public Item(String slot, String name, BigDecimal price, String type) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
        amount = 5;
    }

    public String getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }


    public String getMessage() {
        switch (type) {
            case "Chip":
                return "Crunch Crunch, Yum!";
            case "Candy":
                return "Munch Munch, Yum!";
            case "Drink":
                return "Glug Glug, Yum!";
            case "Gum":
                return "Chew Chew, Yum!";
            default:
                return "Yummy";
        }
    }

    public void updateAmount() {
        if (amount > 0) amount--;
    }
}
