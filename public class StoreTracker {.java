import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StoreManagement {

    private static final String CONFIG_FILE = "store_config.txt"; // sets up the Store Configuration file
    private static final String INVENTORY_FILE = "inventory.txt"; // sets up the Inventory file

    private static int aisles;
    private static int shelvesPerAisle;
    private static int restockAmount;
    private static Map<String, Integer> inventory = new HashMap<>();

    public static void main(String[] args) {
        loadConfiguration();//sets up a defualt for the store configuration
        loadInventory();//loads the 
        checkLowInventory();
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option: configure store, update inventory, check inventory, restock shelves, exit");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "configure store":
                    configureStore(scanner);
                    break;
                case "cs":
                    configureStore(scanner);
                    break;
                case "update inventory":
                    updateInventory(scanner);
                    break;
                case "ui":
                    updateInventory(scanner);
                    break;
                case "check inventory":
                    checkInventory();
                    break;
                case "ci":
                    checkInventory();
                    break;
                case "restock shelves":
                    restockShelves();
                    break;
                case "rs":
                    restockShelves();
                    break;
                case "exit":
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

    private static void configureStore(Scanner scanner) {
        System.out.println("Enter the number of aisles:");
        aisles = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the number of shelves per aisle:");
        shelvesPerAisle = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the restock amount:");
        restockAmount = Integer.parseInt(scanner.nextLine());

        System.out.println("Store configuration updated.");
    }

    private static void updateInventory(Scanner scanner) {
        while (true) {
            System.out.println("Choose an option: add item, remove item, exit");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "add item":
                    addItem(scanner);
                    break;
                case "remove item":
                    removeItem(scanner);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addItem(Scanner scanner) {
        System.out.println("Enter the name of the item:");
        String itemName = scanner.nextLine();

        System.out.println("Enter the quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + quantity);
        System.out.println("Item added/updated.");
    }

    private static void removeItem(Scanner scanner) {
        System.out.println("Enter the name of the item:");
        String itemName = scanner.nextLine();

        if (!inventory.containsKey(itemName)) {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Enter the quantity to remove (or 'all' to remove all):");
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

    private static void checkInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    private static void restockShelves() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() < restockAmount) {
                inventory.put(entry.getKey(), restockAmount);
            }
        }
        System.out.println("Shelves have been restocked");

    }

    private static void checkLowInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() <= 0) {
                System.out.println("Low inventory alert: " + entry.getKey());
            }
        }
    }

    private static void loadConfiguration() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            aisles = Integer.parseInt(reader.readLine());
            shelvesPerAisle = Integer.parseInt(reader.readLine());
            restockAmount = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            aisles = 0;
            shelvesPerAisle = 0;
            restockAmount = 0;
        }
    }

    private static void saveConfiguration() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            writer.write(aisles + "\n");
            writer.write(shelvesPerAisle + "\n");
            writer.write(restockAmount + "\n");
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    private static void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String itemName = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                inventory.put(itemName, quantity);
            }
        } catch (IOException | NumberFormatException e) {
            inventory.clear();
        }
    }

    private static void saveInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}
