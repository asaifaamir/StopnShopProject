package Interface;

import Model.Account;
/**
 *
 * @author Antonio Zuniga
 */
public interface AccountListWrapper
{ 
  public void insert(Account data);
  public void remove(String userName);
  public Account retrieve(String userName);
  public boolean search(String userName);
  public int size();
}
