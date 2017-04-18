package Controller;

import View.SignInScreen;
import Model.AccountList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author Antonio Zuniga
 */
public class MainSystem
{
  public static void main(String args[]) throws ClassNotFoundException, IOException 
  {
    /* To set Nimbus look and feel */
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
    {
      try
      {
        if("Nimbus".equals(info.getName())) 
        {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
      catch(ClassNotFoundException | InstantiationException 
              | IllegalAccessException | UnsupportedLookAndFeelException e)
      {
      }
    }

    // Our main object that holds pretty much all of the data
    AccountList stopNShopDatabase;

    // Creates a new file object so we can store a local database 
    File f = new File("stopNShopDatabase.dat");

    // Check to see if the file (database) exists. If it does, read from that
    // file. If it doesn't then get a new instance.
    if(f.isFile() && f.canRead())
    {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
      stopNShopDatabase = (AccountList) in.readObject();
      in.close();
    }
    else 
    {
      stopNShopDatabase = AccountList.getInstance();
    }

    // The landing page JFrame to the sign in screen
    SignInScreen startScreen = new SignInScreen(stopNShopDatabase);

    // Set default close operation to exiting if window closes
    startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    startScreen.setLocationRelativeTo(null); 
    startScreen.setVisible(true); 
    startScreen.setResizable(false);

    // This runs a thread when the compiler detects that the program is exiting
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() 
    {
      @Override
      public void run() 
      {
        try 
        {
          ObjectOutputStream out = new ObjectOutputStream(
                  new FileOutputStream("stopNShopDatabase.dat"));
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
