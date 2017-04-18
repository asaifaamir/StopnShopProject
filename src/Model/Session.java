package Model;

import java.io.Serializable;
/**
 *
 * @author Antonio Zuniga
 */
public class Session implements Serializable
{
  /**
   * Constructs a Session object which contains the user in a session
   * @param userInSession a String object representing the users username
   */
  public Session(String userInSession)
  {
    this.userInSession = userInSession;
  }

  /**
   * Retrieves the user that is in a session
   * @return the username of the user that is in a session
   */
  public String getUserInSession()
  {
    return userInSession;
  }

  /**
   * Sets a user to a session 
   * @param user a String object representing the users username
   */
  public void setUserInSession(String user) 
  {
    userInSession = user;
  }

  private String userInSession;
}
