public class StoreTracker {
    public String reorderNotif(String product, int amount)
    {
        // there will be 2 notifications 1) an alert that you are low on said item and need to order more 2) you are out of said item and need to reorder said item
        // this will only be called if you are low or out of said item in your inventory not the shelf 
    }
     public String restockNotif(String product, int amount)
    {
        // this will have 2 notifications 1) an alert that you are low on said item and need to restock the shelves 2) you are out of said item and need to restock the shelves
        // on the 2nd condition restock notif
    }

    public static void main (String [] args)
    {
        
    }
}
public class Store
{
    //insert here
}

public class  ConfigStore extends Store
{
    // this needs to config the store so it will set the number of aisles, number of shelves, and what type of food will be placed in each section of the aisle
    // this will also configure the inventory aswell
    /* String[][][][] storeStuff = new int[Aisles][Shelves][Sections][Product (always 1)]; */
    /* to grab an item use storeStuff[0][0][0][0 (always 0)] */
    /* the 4d array is used to get the items name and then a 2d to get and store the amount that is a) currently on the shelf b) the amount of product originally on the shelf and c) the amout left in inventory */
}

public class Aisle extends Store
{
    public int getAisle (int num) // this will get the aisle number
    {
 
    }
}

public class Shelf extends Aisle
{
    public String getShelf() //this will get the 
    {

    }
}
public class Section extends Shelf
{
    public String getSection()
    {
        // returns the item and amount on the shelf like this Apples: 10
        /* if the amount on the shelf is low it will call on restockNotif and will call check inventory if there is any more of said product to restock.
        If the amount of said product is low or you are out of said product than it will also call on reorderNotif */
    }
}

public class Inventory extends Shelf
{
    public int checkInventory (String product) // if called it will check if the store has more of said product in their inventory
    {

    }
}
