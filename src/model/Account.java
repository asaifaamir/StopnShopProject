package Model;

import java.io.Serializable;
/**
 *
 * @author Antonio Zuniga
 */
public abstract class Account implements Serializable
{
  /**
   * Constructs an Account object
   * @param firstName a first name for the account
   * @param lastName a last name for the account
   * @param userName a user name for the account
   * @param password a password for the account
   * @param creditCard a credit card number for the account
   * @param ccvNumber the credit card CCV number
   * @param expDate the credit card expiration date
   */
  public Account(String firstName, String lastName, String userName, String password, 
                 String creditCard, String ccvNumber, String expDate)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.creditCard = creditCard;
    this.ccv = ccvNumber;
    this.expDate = expDate;
  }

  /**
   * Constructs a copy of an existing object
   * @param copyAccount Account object to copy
   */
  public Account(Account copyAccount)
  {
    firstName = copyAccount.firstName;
    lastName = copyAccount.lastName;
    userName = copyAccount.userName;
    password = copyAccount.password;
    creditCard = copyAccount.creditCard;
    ccv = copyAccount.ccv;
    expDate = copyAccount.expDate;
  }

  /**
   * Returns the first name of the account holder
   * @return a String representation of the account holders first name
   */
  public String getFirstName()
  {
    return firstName;
  }
  
  /**
   * Returns the last name of the account holder
   * @return a String representation of the account holders last name
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Returns the user name of the account holder
   * @return a String representation of the account holders user name
   */
  public String getUserName()
  {
    return userName;
  }

  /**
   * Returns the password of the account holder
   * @return a String representation of the account holders password
   */
  public String getPassword() 
  {
    return password;
  }

  /**
   * Returns the credit card number of the account holder
   * @return a String representation of the account holders credit card number
   */
  public String getCreditCard() 
  {
    return creditCard;
  }

  /**
   * Returns the CCV of the credit card associated with the account holder
   * @return a String representation of the account holders credit card CCV number
   */
  public String getCCV() 
  {
    return ccv;
  }
  
  /**
   * Returns the expiration date of the credit card associated with the account holder
   * @return a String representation of the account holders credit card expiration date
   */
  public String getExpDate() 
  {
    return expDate;
  }

  /**
   * Sets a first name for the account holder
   * @param firstName the first name of the account holder
   */
  public void setFirstName(String firstName) 
  {
    this.firstName = firstName;
  }
  
  /**
   * Sets a last name for the account holder
   * @param lastName the last name of the account holder
   */
  public void setLastName(String lastName) 
  {
    this.lastName = lastName;
  }

  /**
   * Sets a user name for the account holder
   * @param userName the user name of the account holder
   */
  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  /**
   * Sets a password for the account holder
   * @param password a password for the account holder
   */
  public void setPassword(String password) 
  {
    this.password = password;
  }

  /**
   * Sets a credit card number for the account holder
   * @param creditCard a credit card number for the account holder
   */
  public void setCreditCard(String creditCard)
  {
    this.creditCard = creditCard;
  }

  /**
   * Sets a CCV number for the credit card on the account holder
   * @param ccv a CCV number for the account holder
   */
  public void setCCV(String ccv)
  {
    this.ccv = ccv;
  }
  
  /**
   * Sets an expiration date for the credit card of the account holder
   * @param expDate an expiration date for the account holder
   */
  public void setExpDate(String expDate)
  {
    this.expDate = expDate;
  }

  private String firstName;
  private String lastName;
  private String userName;
  private String password;
  private String creditCard;
  private String expDate;
  private String ccv;
}


