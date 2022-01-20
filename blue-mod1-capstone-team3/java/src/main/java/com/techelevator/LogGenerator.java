package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogGenerator {

    public static void generateLog(String entryTitle, BigDecimal startingBalance, BigDecimal endBalance) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();

        File logFile = new File("Log.txt");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {
            writer.print(formatter.format(now));
            writer.print(" " + entryTitle);
            writer.print(" $" + startingBalance);
            writer.println(" $" + endBalance);

        } catch (FileNotFoundException ex) {
            System.out.println("Sorry, cannot create Log.txt file!");
        }

    }

    public static void refreshLog() {
        File logFile = new File("Log.txt");
        try (PrintWriter writer = new PrintWriter(logFile)) {

        } catch (FileNotFoundException ex) {
            System.out.println("Sorry, cannot create Log.txt file!");
        }
    }
}
