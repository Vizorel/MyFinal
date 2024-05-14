public class StoreTracker {
    public String reorderNotif(String product, int amount)
    {
        //there will be 2 notifications 1) an alert that you are low on said item and need to order more and 2) you are out of said item and need to reorder said item
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
    // this needs to config the store so it will set the number of aisles and what type of food will be placed in each section of the isle
    // all though 3d arrays are hard a 4d array will need to be used in this program 
    /* String[][][][] storeStuff = new int[rows][colums][sections][Product (always 1)]; */
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
    public int getSection()
}

public class Inventory extends Store
{
    public int checkInventory (String product, int amount) // if called it will check if the store has more of said product in their inventory
    {

    }
}