package Model;

import java.io.Serializable;
/**
 *
 * @author Antonio Zuniga
 */
public class Seller extends Account implements Serializable
{
    /**
     * Constructs a Seller object
     * @param firstName a String representing the first name of the account holder
     * @param lastName a String representing the last name of the account holder
     * @param userName a String representing the user name of the account
     * @param password a String representing the password of the account
     * @param creditCard a String representing the credit card number on the account
     * @param ccvNumber a String representing the CCV number of the credit card on the account
     * @param expDate a String representing the expiration date of the credit card on the account
     */
    public Seller(String firstName, String lastName, String userName, 
            String password, String creditCard, String ccvNumber, String expDate)
    {
      // Call Account constructor to make Seller a "Seller Account"
      super(firstName, lastName, userName, password, creditCard, ccvNumber, expDate);
      sellersList = new ProductList();
    }
    
    /**
     * Constructs a deep copy of a Seller object
     * @param copySellerAccount a Seller object
     */
    public Seller(Seller copySellerAccount)
    {
      super(copySellerAccount);
      sellersList = new ProductList(copySellerAccount.sellersList);
    }
    
    /**
     * Retrieves the inventory of the Seller
     * @return the inventory of the Seller 
     */
    public ProductList getList() 
    {
      return sellersList;
    }
    
    /**
     * Retrieves the revenue made by the Seller
     * @return the revenue made by the seller
     */
    public double getRevenue()
    {
      return revenue;
    }
    
    /**
     * Retrieves the profit made by the Seller
     * @return the profit made by the seller
     */
    public double getProfit() 
    {
      return profit;
    }
    
    /**
     * Adds to the total revenue made by the Seller
     * @param totalOfSoldItems an amount of revenue made by Seller
     */
    public void calculateRevenue(double totalOfSoldItems)
    {
      revenue += totalOfSoldItems;
    }
    
    /**
     * Calculates the total profit made by the Seller
     * @return the total profit made by the seller
     */
    public void calculateProfit()
    {
      profit = revenue - getList().getLifeTimeListCosts();
    }
    
    /**
     * Returns a String representation of the Seller which contains the
     * Sellers full name, user name, password, credit card number, CCV number,
     * account type, and all the items inside the Sellers inventory
     * @return 
     */
    @Override
    public String toString() 
    {
      String sellerInfo = "Stop N Shop Account" + "\n\nFirst Name: "
              + super.getFirstName() + super.getLastName() + "\nUser Name: " 
              + super.getUserName() + "\nPassword: " + super.getPassword() 
              + "\nCredit Card Number: " + super.getCreditCard() 
              + "\nCCV Number: " + super.getCCV() + "\nExpiration Date: " 
              + super.getExpDate() + "\nAccount: Seller\n\n"
              + super.getFirstName() + "'s List\n\n" + sellersList.toString();

      return sellerInfo;
    }
    
    /**
     * Checks to see if two Sellers are identical, this checks to see
     * all instances of what comprises the Seller object are identical
     * All Seller fields, and inventory and products inside the inventory
     * must be identical, this even includes the fields of the products themselves
     * @param otherSeller an instance of a Seller object
     * @return true or false depending if the seller objects are equal
     */
    @Override
    public boolean equals(Object otherSeller)
    {
      if(otherSeller == null)
      {
        return false;
      }
      else if(getClass() != otherSeller.getClass())
      {
        return false;
      }
      else
      {
        Seller checkSeller = (Seller)otherSeller;
        return (super.getFirstName().equals(checkSeller.getFirstName()) 
                && super.getLastName().equals(checkSeller.getLastName())
                && super.getUserName().equals(checkSeller.getUserName()) 
                && super.getPassword().equals(checkSeller.getPassword()) 
                && super.getCCV().equals(checkSeller.getCCV())
                && super.getCreditCard().equals(checkSeller.getCreditCard())
                && super.getExpDate().equals(checkSeller.getExpDate())
                && sellersList.equals(checkSeller.sellersList));
      }
    }
    
    private final ProductList sellersList;
    private double revenue;
    private double profit;
}
