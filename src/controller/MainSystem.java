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

    /**
     * an AccountList object 
     * This variable is essentially the data model of this application
     */
    AccountList stopNShopDatabase;

    // Creates a new file object 
    File f = new File("stopNShopDatabase.dat");

    // Checks to see if the file object f is a file and if it can be read
    // essentially this is here to check if the file exists in the directory
    // it is suppose to be in. If the file is not in the directory it belongs
    // in it just pulls an existing instance of the kitteCreationDataBase from
    // the AccountList class. If the file does exist in the directory it
    // belongs in, it will deserialize the file into the variable. The
    // AccountList class was implemented using the Singleton design pattern.
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

    // Create a new instance of the LandingScreen Jframe
    SignInScreen startScreen = new SignInScreen(stopNShopDatabase);

    // Set default close operation to exiting if window closes
    // and set the display location 
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
          ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("stopNShopDatabase.dat"));
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
