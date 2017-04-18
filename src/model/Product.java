package Model;

import java.io.Serializable;
import java.text.DecimalFormat;
/**
 *
 * @author Antonio Zuniga
 */
public class Product implements Serializable 
{
  /**
   * Constructs a Product object
   * @param name a product name
   * @param ID a product ID
   * @param type a product type
   * @param quantity quantity of the product
   * @param invoicePrice the products invoice price
   * @param sellingPrice  the products selling price
   * @param seller seller String
   * @param productDescription shows product description
   */
  public Product(String name, String ID, String type, int quantity, 
          double invoicePrice, double sellingPrice, String seller,
          String productDescription) 
  {
    this.ID = ID;
    this.name = name;
    this.type = type;
    this.quantity = quantity;
    this.invoicePrice = invoicePrice;
    this.sellingPrice = sellingPrice;
    this.seller = seller;
    this.productDescription = productDescription;
  }

  /**
   * Creates a copy of a Product object
   * @param copyOfProduct 
   */
  public Product(Product copyOfProduct)
  {
    this.ID = copyOfProduct.ID;
    this.name = copyOfProduct.name;
    this.type = copyOfProduct.type;
    this.quantity = copyOfProduct.quantity;
    this.invoicePrice = copyOfProduct.invoicePrice;
    this.sellingPrice = copyOfProduct.sellingPrice;
    this.seller = copyOfProduct.seller;
  }

  /**
   * Returns the Product object ID
   * @return product ID code
   */
  public String getID()
  {
    return ID;
  }

  /**
   * Returns type of the Product object
   * @return product type in the form of a String object
   */
  public String getType() 
  {
    return type;
  }

  /**
   * Returns the name of the product 
   * @return the name of the product in the form of a String object
   */
  public String getName() 
  {
    return name;
  }

  /**
   * Returns the quantity of the Product object
   * @return quantity of product
   */
  public int getQuantity()
  {
    return quantity;
  }

  /**
   * Returns the invoice price of the product object
   * @return invoice price
   */
  public double getInvoicePrice() 
  {
    return invoicePrice;
  }

  /**
   * Returns the selling price of the Product object
   * @return selling price
   */
  public double getSellingPrice()
  {
    return sellingPrice;
  }

  /**
   * Returns the Seller associated with the customer the product
   * @return returns a String representing the sellers name
   */
  public String getSeller() 
  {
    return seller;
  }

  /**
   * Sets an ID for the Product object
   * @param ID an ID number for the product 
   */
  public void setID(String ID)
  {
    this.ID = ID;
  }

  /**
   * Sets an name for the Product object
   * @param name a name number for the product 
   */
  public void setName(String name) 
  {
    this.name = name;
  }

  /**
   * Sets a product type for the Product Object
   * @param type a product type
   */
  public void setType(String type)
  {
    this.type = type;
  }

  /**
   * Sets a quantity for the Product object
   * @param quantity a specified quantity 
   */
  public void setQuantity(int quantity) 
  {
    this.quantity = quantity;
  }

  /**
   * Sets an invoice price for the Product 
   * @param invoicePrice a specified price
   */
  public void setInvoicePrice(double invoicePrice) 
  {
    this.invoicePrice = invoicePrice;
  }

  /**
   * Sets a selling price for the Product
   * @param sellingPrice a selling price in the form of a type double 
   */
  public void setSellingPrice(double sellingPrice)
  {
    this.sellingPrice = sellingPrice;
  }

  /**
   * Sets the Seller to the Product object to keep an association between
   * the Product and its Seller
   * @param seller 
   */
  public void setSeller(String seller)
  {
    this.seller = seller;
  }

  /**
   * Returns the description of the product
   * @return a description of the product in the form of a String object
   */
  public String getProductDescription()
  {
    return productDescription;
  }

  /**
   * String representation of the Product object
   * @return Product object information
   */
  @Override
  public String toString()
  {
    DecimalFormat formatter = new DecimalFormat("#.##");

    return "Item: " + name + "\nInvoice Price: $" + formatter.format(invoicePrice) + 
           "\nSelling Price: $" + formatter.format(sellingPrice) + "\nQuantity: "  + 
            quantity + "\nID: " + ID + "\nType: " + type;
  }

  /**
   * Compares to products to see if they are the same
   * @param otherProduct Product object to compare with
   * @return returns true if the products are the same, returns false otherwise
   */
  @Override
  public boolean equals(Object otherProduct) 
  {
    if(otherProduct == null)
    {
      return false;
    }
    else if(getClass() != otherProduct.getClass())
    {
      return false;
    }
    else 
    {
      Product checkProduct = (Product)otherProduct;
      return (ID.equals(checkProduct.ID) && name.equals(checkProduct.name) 
              && type.equals(checkProduct.type)
              && (quantity == checkProduct.quantity) 
              && (invoicePrice == checkProduct.invoicePrice) 
              && (sellingPrice == checkProduct.sellingPrice));
    }
  }

  private String ID;
  private String name;
  private String type;
  private String productDescription;
  private int quantity;
  private double invoicePrice;
  private double sellingPrice;
  private String seller;
}
