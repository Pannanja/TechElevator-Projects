package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {
			MAIN_MENU_OPTION_DISPLAY_ITEMS,
			MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT
	};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {
			PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT,
			PURCHASE_MENU_OPTION_FINISH_TRANSACTION
	};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine vendingMachine = new VendingMachine();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				displayItem(vendingMachine.getInventory());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				while (true) {
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						Scanner input = new Scanner(System.in);

						feedMoney(input, vendingMachine);

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						displayItemSelection(vendingMachine.getInventory());
						selectItem(vendingMachine);
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						vendingMachine.finishTransaction();
						break;
					}
				}
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public void displayItem(Map<String, Item> inventory) {
		if (inventory != null) {
			for (Map.Entry<String, Item> entrySet : inventory.entrySet()) {
				String name = entrySet.getValue().getName();
				int amount = entrySet.getValue().getAmount();
				String dots = ".........................";
				final int NUMBER_OF_DOTS = 25;
				dots = dots.substring(0,NUMBER_OF_DOTS - name.length());
				if (amount == 0) {
					final int NUMBER_OF_CHARACTERS_IN_SOLD_OUT = 7;
					dots = dots.substring(0, dots.length() - NUMBER_OF_CHARACTERS_IN_SOLD_OUT);
					System.out.println(name + dots +  "SOLD OUT");
				} else {
					System.out.println(name + dots + amount);
				}
			}
		}
	}

	public void displayItemSelection(Map<String, Item> inventory) {
		System.out.println();
		if (inventory != null) {
			for (Map.Entry<String, Item> entrySet : inventory.entrySet()) {
				String slot = entrySet.getKey();
				String name = entrySet.getValue().getName();
				BigDecimal price = entrySet.getValue().getPrice();

				String dots = ".........................";
				final int NUMBER_OF_DOTS = 25;
				dots = dots.substring(0,NUMBER_OF_DOTS - name.length());

				if (entrySet.getValue().getAmount() == 0) {
					final int NUMBER_OF_CHARACTERS_IN_PRICE = 4;
					dots = dots.substring(0, dots.length() - NUMBER_OF_CHARACTERS_IN_PRICE + 1);
					System.out.println(slot + ": " + name + dots +  "SOLD OUT");
				} else {
					System.out.println(slot + ": " + name + dots+ "$" + price);
				}
			}
		}
	}

	public void feedMoney(Scanner input, VendingMachine vendingMachine) {

		boolean validInput = false;
		while (!validInput) {

			System.out.println("Please insert bills. When done, enter 0.");
			String line = input.nextLine();
			validInput = vendingMachine.deposit(line);
		}

	}

	public void selectItem(VendingMachine vendingMachine){
		Scanner input = new Scanner(System.in);
		System.out.println();
		System.out.print("Please enter an item ID:  ");
		String itemID = input.nextLine().toUpperCase(Locale.ROOT);

		vendingMachine.checkSelectedItem(itemID);
	}
}
