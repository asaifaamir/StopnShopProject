package Main;

import Model.AccountList;
import View.LoginPanel;

import java.io.*;
/**
 *
 * @author Antonio Zuniga
 */
public class MainSystem
{
  public static void main(String args[]) throws ClassNotFoundException, IOException 
  {
    // Our main object that holds pretty much all of the data
    AccountList stopNShopDatabase;

    // Creates a new file object so we can store a local database 
    File f = new File("stopNShopDatabase.ser");

    // Check to see if the file (database) exists. If it does, read from that
    // file. If it doesn't then get a new instance.
    if(f.isFile() && f.canRead())
    {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
      stopNShopDatabase = (AccountList) in.readObject();
        AccountList.replaceInstance(stopNShopDatabase);
      in.close();
    }
    else 
    {
      stopNShopDatabase = AccountList.getInstance();
    }

    // The starting JFrame of login screen
    LoginPanel loginPanel = new LoginPanel();

    // This runs a thread when the compiler detects that the program is exiting
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() 
    {
      @Override
      public void run() 
      {
        try 
        {
          ObjectOutputStream out = new ObjectOutputStream(
                  new FileOutputStream("stopNShopDatabase.ser"));
          out.writeObject(stopNShopDatabase);
          out.close();
        }
        catch(Exception e) 
        {
        }
      }
  }));
 }
}
