package Interface;

import Model.Account;
/**
 *
 * @author Antonio Zuniga
 */
public interface AccountListWrapper
{ 
  void insert(Account data);
  void remove(String userName);
  Account retrieve(String userName);
  boolean search(String userName);
  int size();
}
