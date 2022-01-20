package com.techelevator;
import java.util.Scanner;

/*
 Write a command line program which prompts the user for the total bill, and the amount tendered. It should then
 display the change required.

 $ java MakeChange
 Please enter the amount of the bill: 23.65
 Please enter the amount tendered: 100.00
 The change required is 76.35
 */
public class MakeChange {

	public static void main(String[] args) {
		double tenderPaid = 0;
		double billTotal = 0;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the amount of the bill: ");
		String enteredAmount = keyboard.nextLine();
		billTotal = Double.parseDouble(enteredAmount);

		System.out.println("Enter the amount tendered: ");
		String tenderedAmount = keyboard.nextLine();
		tenderPaid = Double.parseDouble(tenderedAmount);
		double changeRequired = tenderPaid - billTotal;
		System.out.println("Change require is: " + changeRequired);
		

	}
}
