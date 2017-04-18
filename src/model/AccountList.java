package Model;

import StopNShop_Interface.AccountListWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
/**
 *
 * @author Antonio Zuniga
 */
public class AccountList implements AccountListWrapper, Serializable 
{   

  /**
   * Constructs an AccountList object with a Map to hold all accounts
   */
  private AccountList() 
  {
    accountList = new TreeMap<>();
  }

  /**
   * Inserts Account objects into the account list
   * @param data a Account object to insert into list
   */
  @Override
  public void insert(Account data) 
  {
    if(data.getClass() == Customer.class)
    {
      Customer customer = (Customer)data;
      accountList.put(accountNumberID, new Customer(customer));
      accountNumberID++;
    }
    else if(data.getClass() == Seller.class) 
    {
      Seller seller = (Seller)data;
      accountList.put(accountNumberID, new Seller(seller));
      accountNumberID++;
    }
  }

  /**
   * Removes an account from the list
   * @param userName a String representing the user name of the account to remove
   */
  @Override
  public void remove(String userName)
  {
    for(Map.Entry<Integer, Account> entry : accountList.entrySet()) 
    {
      if(entry.getValue().getUserName().equals(userName))
      {
        accountList.remove(entry.getKey());
        break;
      }
    }
  }

  /**
   * Searches for an account in the list
   * @param userName a String representing the user name of the account to search for
   * @return if the account is found return true, otherwise return false
   */
  @Override
  public boolean search(String userName)
  {
    boolean nameFound = false;
    for(Account account : accountList.values()) 
    {
      if(account.getUserName().equals(userName)) 
      {
        nameFound = true;
        break;
      }
    }

    return nameFound;
  }

  /**
   * Retrieves an account from the list
   * @param userName a String representing the user name of the account to 
   * retrieve from the list
   * @return the account specified by user name
   */
  @Override
  public Account retrieve(String userName) 
  {
    Account retrievedAccount = null;
    for(Account account : accountList.values())
    {
      if(account.getUserName().equals(userName)) 
      {
        retrievedAccount = account;
      }
    }

    return retrievedAccount;
  }

  /**
   * Retrieves all the Seller account objects from the DataBase
   * @return returns an ArrayList of all Sellers within the AccountList
   */
  public ArrayList<Seller> getAllSellers() 
  {
    ArrayList<Seller> sellerList = new ArrayList<>();

    for(Account account : accountList.values())
    {
      if(account.getClass() == Seller.class) 
      {
        sellerList.add((Seller)account);
      }
    }

    return sellerList;
  }

  /**
   * Retrieves all the Customer account objects from the DataBase
   * @return returns an ArrayList of all Customers within the AccountList
   */
  public ArrayList<Customer> getAllCustomers()
  {
    ArrayList<Customer> customerList = new ArrayList<>();

    for(Account account : accountList.values())
    {
      if(account.getClass() == Customer.class)
      { 
        customerList.add((Customer)account);
      }
    }

    return customerList;
  }

  /**
   * Returns a reference to the AccountList object
   * @return a reference to the AccountList 
   */
  public static AccountList getInstance()
  {
    return instance;
  }

  /**
   * Returns the current size of the list
   * @return an integer value specifying list size
   */
  @Override
  public int size()
  {
    return accountList.size();
  }

  private final TreeMap <Integer, Account> accountList;
  private int accountNumberID = 0;
  private static final AccountList instance = new AccountList(); 
}
