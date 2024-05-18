public class StoreTracker {
    public String reorderNotif(String product, int amount) // this will only be called if you are low or out of said item in your inventory not the shelf
    {      
        if (amount == 0) //an alert that you are out of said item and need to reorder said item
        {
            return "You are out of " + product + " and need to reorder.";
        }
        else; //an alert that you are low on said item and need to order more
        {
            return "You are low on " + product + " and need to order more.";
        }
    }
     public String restockNotif(String product, int amount)
    {
        if (amount == 0) //an alert that you are out of said item and need to restock the shelves
        {
            return "You are out of " + product + " on the shelves and need to restock.";
        }
        else //an alert that you are low on said item and need to restock the shelves
        { 
            return "You are low on " + product + " on the shelves and need to restock.";
        }
        // on the 2nd condition restock notif
    }
    public String refillInventory (String product, int amount)
    {
        //this is called by the user to submit a refill into the program that will update the amount of said product in inventory
    }

    public Boolean restock (String product)
    {
        //this will be called by the user to restock the shelves, if the shelves were successfully restocked then it will return True however if you are out of said item in your inventory than it will return as False
    }

    public String productBought ()
    {
        /*the user will call this and it will ask what was bought and how much of it was it bought.
        After this it will ask anything else and if yes it will repeat asking what was bought and how much, if the user replys no than it will stop asking and then update the shelves
        since a costumer can only purchase the amount of said product that is on the shelf there won't be an overflow error*/
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
    public String getShelf()
    {

    }
}
public class Section extends Shelf
{
    public String getSection()
    {
        // returns the item and amount on the shelf like this Apples: 10
        /* if the amount on the shelf is low it will call on restockNotif and will call check inventory if there is any more of said product to restock.
        If the amount of said product is low or you are out of said product than it will only restock the remaining amount in inventory*/
    }
}

public class Inventory extends Shelf
{
    public int checkInventory (String product) // if called it will check if the store has more of said product in their inventory
    {

    }
    
}
