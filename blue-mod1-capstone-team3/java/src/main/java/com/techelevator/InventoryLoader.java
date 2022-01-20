package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class InventoryLoader {

    private static final File dataFile = new File("vendingmachine.csv");


    public static Map<String, Item> loadInventory(){
        Map<String, Item> inventory = new LinkedHashMap<>();
        try (Scanner in = new Scanner(dataFile)) {
            String line;
            String slot;
            String name;
            BigDecimal price;
            String type;
            while(in.hasNextLine()){
                line = in.nextLine();
                String[] splitLine = line.split("\\|");
                slot = splitLine[0];
                name = splitLine[1];
                double priceDouble = Double.parseDouble(splitLine[2]);
                price = BigDecimal.valueOf(priceDouble).setScale(2);
                type = splitLine[3];
                inventory.put(slot, new Item(slot, name, price, type));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong loading our inventory. Sorry for the invoncenience.");
        }
        return inventory;
    }

}
