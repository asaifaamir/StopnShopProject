package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Antonio Zuniga
 */
public class ShoppingCart implements Serializable
{
    /**
     * Constructs an empty ShoppingCart object
     */
    public ShoppingCart()
    {
      items = new ArrayList<>();
      cartTotal = 0.00;
      isEmpty = true;
    }
    
    /**
     * Constructs a copy of an existing ShoppingCart object
     * @param copyShoppingCart a ShoppingCart object
     */
    public ShoppingCart(ShoppingCart copyShoppingCart)
    {
      items = new ArrayList<>(copyShoppingCart.items);
    }
    
    /**
     * Adds an item to the ShoppingCart
     * @param item a product to add to shopping cart
     * @precondition a ShoppingCart object must exist
     * @postcondition an item is added to the cart
     */
    public void add(Product item)
    {
      items.add(item);

      if(!items.isEmpty())
      {
        isEmpty = false;
      }
    }
    
    /**
     * Removes an item from the cart via item ID
     * @param itemID a Product ID
     * @precondition ShoppingCart must not be empty
     * @postcondition Selected item is removed from the cart
     */
    public void remove(String itemID) 
    {
      if(!items.isEmpty())
      {
        for(Product item : items)
        {
          if(itemID.equals(item.getID()))
          {
            items.remove(item);
            break;
          }
        }

        if(items.isEmpty())
        {
          isEmpty = true;
        }
      }
    }
    
    /**
     * Searches for a product in the ShoppingCart via an ID
     * @param itemID a product ID number
     * @return true or false depending if the item exists in the cart
     */
    public boolean search(String itemID) 
    {
      if(items.isEmpty())
      {
        return false;
      }
      
      for(Product item : items)
      {
        if(itemID.equals(item.getID()))
        {
          return true;
        }
      }

      return false;
    }
    
    /**
     * Returns the total of all items in the cart
     * @return total value of items in cart
     */
    public double calculateShoppingCartTotal() 
    {
      cartTotal = 0;
      for(Product item : items)
      {
        cartTotal += (double)(item.getQuantity() * item.getSellingPrice());
      }

      return cartTotal;
    }
    
    /**
     * Empties the ShoppingCart
     */
    public void clear() 
    {
      items.clear();
      isEmpty = true;
    }
    
    /**
     * Checks to see if the cart is completely empty
     * @return true or false if the cart is empty or not empty
     */
    public boolean isEmpty() 
    {
      return isEmpty;
    }
    
    /**
     * Retrieves a specified product in the shopping cart
     * @param itemID a Product ID number
     * @return the Product specified via the ID number
     */
    public Product getProduct(String itemID)
    {
      Product itemToGet = null;

      for(Product item : items) 
      {
        if(item.getID().equals(itemID)) 
        {
            itemToGet = item;
            break;
        }
      }

      return new Product(itemToGet);
    }
    
    /**
     * Retrieves a specified product in the shopping cart
     * @param itemID a Product ID Number
     * @return the Product specified via the ID Number (as a reference) 
     */
    public Product getProductInstance(String itemID)
    {
      Product itemToGet = null;

      for(Product item : items) 
      {
        if(item.getID().equals(itemID)) 
        {
          itemToGet = item;
          break;
        }
      }

      return itemToGet;
    }
    
    /**
     * Retrieves all items in the cart
     * @return an ArrayList of items from the cart
     */
    public ArrayList<Product> getAllItems()
    {
      return new ArrayList<>(items);
    }
    
    
    /**
     * Checks the size of the cart
     * @return the current size of the cart
     */
    public int size() 
    {
      return items.size();
    }
    
    /**
     * Returns a string representation of all items in the ShoppingCart
     * @return string representation of everything in the ShoppingCart
     */
    public String toString() 
    {
      String cartOutput = "";

      cartIterator = items.iterator();

      while(cartIterator.hasNext()) 
      {
        cartOutput += cartIterator.next().toString() + "\n\n";
      }

       return cartOutput;
    }
    
    /**
     * Checks to see if the cart is the same as another cart
     * @param otherShoppingCart another ShoppingCart object to check with
     * @return returns true if carts are the same, or returns false if carts are different
     */
    @Override
    public boolean equals(Object otherShoppingCart)
    {
      if(otherShoppingCart == null)
      {
        return false;
      }
      else if(getClass() != otherShoppingCart.getClass())
      {
        return false;
      }
      else 
      {
        ShoppingCart checkShoppingCart = (ShoppingCart)otherShoppingCart;
        return (items.equals(checkShoppingCart.items));
      }
    }
       
    private final ArrayList<Product> items;
    Iterator<Product> cartIterator;
    private double cartTotal;
    private boolean isEmpty;
}
