import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StoreManagement {
    // Configuration and inventory file names
    private static final String CONFIG_FILE = "store_config.txt"; // sets up the Store Configuration file
    private static final String INVENTORY_FILE = "inventory.txt"; // sets up the Inventory file

    // Store configuration parameters
    private static int aisles; // Number of aisles in the store
    private static int shelvesPerAisle; // Number of shelves per aisle
    private static int restockAmount; // Amount to restock items to

    // Inventory data structure
    private static Map<String, Integer> inventory = new HashMap<>(); // Map to store item names and their quantities

    public static void main(String[] args) {
        loadConfiguration(); // Load store configuration from file
        loadInventory(); // Load inventory from file
        checkLowInventory(); // Check and alert for items with low inventory
        
        Scanner scanner = new Scanner(System.in);

        while (true) { // Main loop for user interaction
            System.out.println("Choose an option: configure store, update inventory, check inventory, restock shelves, exit");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "configure store": //when user enters configure store or cs it will call configureStore to configure the store
                    configureStore(scanner); 
                    break;
                case "cs":
                    configureStore(scanner);
                    break;
                case "update inventory": //when user enters update inventory or ui it will call updateInventory to update the inventory
                    updateInventory(scanner);
                    break;
                case "ui":
                    updateInventory(scanner);
                    break;
                case "check inventory": //when user enters check inventory of ci it will call checkInventory to display the inventory to the user
                    checkInventory();
                    break;
                case "ci":
                    checkInventory();
                    break;
                case "restock shelves": //when user enters restock shelves or rs it will call restockShelves and it will restock the shelves to the restock amount and notifies the user if they are out of or low on a product
                    restockShelves();
                    break;
                case "rs":
                    restockShelves();
                    break;
                case "exit": //when user enters exit or e it will call savesaveConfiguration and saveInventory and then it will close the loop and end the program
                    saveConfiguration();
                    saveInventory();
                    System.out.println("Exiting the program...");
                    return;
                case "e":
                    saveConfiguration();
                    saveInventory();
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to configure store settings
    private static void configureStore(Scanner scanner) {
        System.out.println("Enter the number of aisles:");
        aisles = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the number of shelves per aisle:");
        shelvesPerAisle = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the restock amount:");
        restockAmount = Integer.parseInt(scanner.nextLine());

        System.out.println("Store configuration updated.");
    }
    
    // Method to update inventory (add or remove items)
    private static void updateInventory(Scanner scanner) {
        while (true) {
            System.out.println("Choose an option: add item, remove item, exit");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "add item": //when the user enters add item or ai it will call addItem
                    addItem(scanner);
                    break;
                case "ai":
                    addItem(scanner);
                    break;
                case "remove item": //when the user enters remove item or ri it will call removeItem
                    removeItem(scanner);
                    break;
                case "ri":
                    removeItem(scanner);
                    break;
                case "exit": //when the user enters exit or e it will stop this loop an go back to the user interface loop
                    return;
                case "e":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to add an item to inventory
    private static void addItem(Scanner scanner) {
        System.out.println("Enter the name of the item:"); //asks for the item you are adding
        String itemName = scanner.nextLine();

        System.out.println("Enter the quantity:"); //asks for the quantity/amount you have
        int quantity = Integer.parseInt(scanner.nextLine());

        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + quantity); //loads the item and amount into inventory.txt
        System.out.println("Item added/updated.");
    }

    // Method to remove an item from inventory
    private static void removeItem(Scanner scanner) {
        System.out.println("Enter the name of the item:");//asks for the item you are removing
        String itemName = scanner.nextLine();

        if (!inventory.containsKey(itemName)) {
            System.out.println("Item not found."); //checks if the item youve entered is actually in inventory.txt
            return;
        }

        System.out.println("Enter the quantity to remove (or 'all' to remove all):"); //asks for the amount you are removing and to enter "all" if you are removing all of said item
        String input = scanner.nextLine().toLowerCase();

        if (input.equals("all")) {
            inventory.remove(itemName);
            System.out.println("All items removed.");
        } else {
            int quantity = Integer.parseInt(input);
            int currentQuantity = inventory.get(itemName);
            if (quantity >= currentQuantity) {
                inventory.remove(itemName);
                System.out.println("All items removed.");
            } else {
                inventory.put(itemName, currentQuantity - quantity);
                System.out.println("Item quantity updated.");
            }
        }
    }

    private static void checkInventory() { // Method to check and display current inventory
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty."); //reply to an empty inventory
        } else {
            System.out.println("Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue()); // Display each item and its quantity
            }
        }
    }

    // Method to restock shelves to the restock amount
    private static void restockShelves() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() < restockAmount) {
                inventory.put(entry.getKey(), restockAmount); // Restock item to the restock amount
            }
        }
        System.out.println("Shelves have been restocked");

    }

    // Method to check for items with low inventory and alert
    private static void checkLowInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() <= 0) {
                System.out.println("Low inventory alert: " + entry.getKey()); // Alert for low inventory items
            }
        }
    }

        // Method to load store configuration from store_config.txt
    private static void loadConfiguration() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            aisles = Integer.parseInt(reader.readLine());
            shelvesPerAisle = Integer.parseInt(reader.readLine());
            restockAmount = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            aisles = 0; // Default values if configuration file not found or invalid
            shelvesPerAisle = 0;
            restockAmount = 0;
        }
    }

    // Method to save store configuration to file
    private static void saveConfiguration() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            writer.write(aisles + "\n");
            writer.write(shelvesPerAisle + "\n");
            writer.write(restockAmount + "\n");
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage()); // Handle file write errors
        }
    }

    // Method to load store configuration from inventory.txt
    private static void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String itemName = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                inventory.put(itemName, quantity); // Load each item into the inventory map
            }
        } catch (IOException | NumberFormatException e) {
            inventory.clear(); // Clear inventory if file not found or invalid
        }
    }

    // Method to save inventory to file
    private static void saveInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n"); // Save each item to file
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage()); // Handle file write errors
        }
    }
}
