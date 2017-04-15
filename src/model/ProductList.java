package Model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Antonio Zuniga
 */
public class ProductList implements Serializable
{
    /**
     * Constructs an List object
     */
    public ProductList() 
    {
      inventoryList = new ArrayList<>();
    }
    
    /**
     * Constructs a copy of a List object
     * @param copyList 
     */
    public ProductList(ProductList copyList)
    {
      inventoryList = new ArrayList<>(copyList.inventoryList);
    }
    
    /**
     * Adds an item to the list and updates listTotal
     * @param item Product object to add to list
     */
    public void add(Product item)
    {
      inventoryList.add(item);
      listTotal += item.getQuantity() * item.getInvoicePrice();
    }
    
    /**
     * Removes an item from the list
     * @param IDnum String ID to locate proper item
     */
    public void remove(String IDnum)
    {
      for(Product item : inventoryList)
      {
        if(IDnum.equals(item.getID())) 
        {
          inventoryList.remove(item);
          break;
        }
      }
    }
    
    /**
     * Clears out entire list
     */
    public void clear()
    {
      inventoryList.clear();
    }
    
    /**
     * Checks to see if the list is empty
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() 
    {
      return inventoryList.isEmpty();
    }
    
    /**
     * Retrieves a specified product in the Sellers list
     * @param IDnum a Product ID Number
     * @return the Product specified via the ID Number (as a reference) 
     */
    public Product getProduct(String IDnum) 
    {
      Product itemToGet = null;

      for(Product item : inventoryList)
      {
        if(item.getID().equals(IDnum)) 
        {
          itemToGet = item;
          break;
        }
      }

      return itemToGet;
    }
    
    /**
     * Returns a list of all items in the list
     * @return an ArrayList of Product objects
     */
    public ArrayList<Product> getAllItems() 
    {
      return new ArrayList<>(inventoryList);
    }
    
    /**
     * Adds incurred costs to the Sellers lifetime spending 
     * @param incurredCost an incurred cost
     */
    public void addToLifeTimeListCosts(double incurredCost) 
    {
      listTotal += incurredCost;
    }
    
    /**
     * Retrieves the total lifetime spending of the Seller
     * @return lifetime spending of seller
     */
    public double getLifeTimeListCosts()
    {
      return listTotal;
    }
    
    public void changeProductOwnerShip(String userName) 
    {
      for(Product item : inventoryList) 
      {
        item.setSeller(userName);
      }
    }
    
    /**
     * Returns a String representation of all Products in the list
     * @return a String representation of all the products found in the sellers list
     */
    @Override
    public String toString()
    {
      String listOutput = "";

      for(Product item : inventoryList)
          listOutput += item.toString() + "\n\n";

      return listOutput;
    }
    
    /** 
     * Checks to see the equality of two List objects
     * @param otherList List object to compare
     * @return true if equal, otherwise false
     */
    public boolean equals(ProductList otherList)
    {
      if(otherList == null)
      {
        return false;
      }
      else if(getClass() != otherList.getClass())
      {
        return false;
      }
      else 
      {
        ProductList checkList = (ProductList)otherList;
        return (inventoryList.equals(checkList.inventoryList));
      }
    }
    
    private final ArrayList<Product> inventoryList;
    private double listTotal;
}
