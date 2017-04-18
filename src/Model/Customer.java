package Model;

import java.io.Serializable;
/**
 *
 * @author Antonio Zuniga
 */
public class Customer extends Account implements Serializable
{
  /**
   * Constructs a Customer object
   * @param firstName a String representing the first name of the account holder
   * @param lastName a String representing the last name of the account holder
   * @param userName a String representing the user name of the account
   * @param password a String representing the password of the account
   * @param creditCard a String representing the credit card number on the account
   * @param ccvNumber a String representing the CCV number of the credit card on the account
   * @param expDate a String representing the expiration date of the credit card on the account
   */
  public Customer(String firstName, String lastName, String userName,
          String password, String creditCard, String ccvNumber, String expDate)
  {
    // Call Account constructor to make Customer a "Customer Account"
    super(firstName, lastName, userName, password, creditCard, ccvNumber, expDate);
    customersShoppingCart = new ShoppingCart();
  }

  /**
   * Creates a copy of a Customer object
   * @param copyCustomer 
   */
  public Customer(Customer copyCustomer) 
  {
    super(copyCustomer);
    customersShoppingCart = new ShoppingCart(copyCustomer.customersShoppingCart);
  }

  /**
   * Returns the customers cart
   * @return a reference to the Customer cart
   */
  public ShoppingCart getShoppingCart() 
  {
    return customersShoppingCart;
  }

  /**
   * A string representation of the  Customer object
   * @return a String containing all information about the Customer object
   */
  @Override
  public String toString() 
  {
    String customerInfo = "Stop N Shop Account" + "\n\nFirst Name: "
            + super.getFirstName() + "\n\nLast Name: " + super.getLastName()
            + "\nUser Name: " + super.getUserName() + "\nPassword: " 
            + super.getPassword() + "\nCredit Card Number: " 
            + super.getCreditCard() + "\nCCV Number: " + super.getCCV() 
            + "\nExpiration Date: " + super.getExpDate()
            + "\nAccount:Customer\n\n" + super.getFirstName() 
            + "'s ShoppingCart\n\n" + customersShoppingCart.toString();

    return customerInfo;
  }

  /**
   * Checks to see if two Customer objects are the same
   * @param otherCustomer aCustomer object
   * @return returns true or false depending ifCustomer objects are equal or not
   */
  @Override
  public boolean equals(Object otherCustomer)
  {
    if(otherCustomer == null)
    {
      return false;
    }
    else if(getClass() != otherCustomer.getClass())
    {
      return false;
    }
    else 
    {
      Customer checkCustomer = (Customer)otherCustomer;
      //Complete check of all accout entities
      return (super.getFirstName().equals(checkCustomer.getFirstName()) 
              && super.getLastName().equals(checkCustomer.getLastName())
              && super.getUserName().equals(checkCustomer.getUserName())
              && super.getPassword().equals(checkCustomer.getPassword()) 
              && super.getCCV().equals(checkCustomer.getCCV()) 
              && super.getCreditCard().equals(checkCustomer.getCreditCard()) 
              && super.getExpDate().equals(checkCustomer.getExpDate())
              && customersShoppingCart.equals(checkCustomer.customersShoppingCart));
    }
  }

  private final ShoppingCart customersShoppingCart;
}
