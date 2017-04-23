package Controller;

import Model.AccountList;
import Model.Customer;
import Model.Seller;
import Model.Session;
import View.CustomerPanel;
import View.RegistrationPanel;
import View.SellerPanel;

import javax.swing.*;
import java.util.Arrays;

/**
 * This class is the controller for LoginPanel view. It handles the action listeners from the view and interacts with the AccountList database.
 * Created by asaifbutt on 4/17/17.
 */
public class LoginPanelController {

    private AccountList database = AccountList.getInstance();
    JFrame frame;

    /**
     * Constructor for LoginPanelController called when loginPanelController object is created
     * @param jFrame JFrame object to dispose current frame
     */
    public LoginPanelController(JFrame jFrame)
    {
        frame = jFrame;
    }

    /**
     * Login Action for the LoginPanel View which reacts to Login Button
     * @param userNameField the username field to get the username
     * @param passwordField1 the password field to get the password
     */
    public void loginButtonActionPerformed(JTextField userNameField, JPasswordField passwordField1)
    {
        String username = userNameField.getText();
        char[] password = passwordField1.getPassword();

        if (database.search(username))
        {
            if (isPasswordCorrect(username, password))
            {
                if (database.retrieve(username).getClass() == Customer.class)
                {
                    Session userSession = new Session(username);
                    JOptionPane.showMessageDialog(null, "WELCOME " + (database.retrieve(username).getFirstName()).toUpperCase() + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    CustomerPanel customerScreen = new CustomerPanel(userSession);
                }
                else if (database.retrieve(username).getClass() == Seller.class)
                {
                    Session userSession = new Session(username);
                    JOptionPane.showMessageDialog(null, "WELCOME " + (database.retrieve(username).getFirstName()).toUpperCase() + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    SellerPanel sellerScreen = new SellerPanel(userSession);

                }
                else {
                    JOptionPane.showMessageDialog(null, "Please verify that the username and password are correct", "Incorrect Login", JOptionPane.WARNING_MESSAGE);

                }
            }else
            {
                JOptionPane.showMessageDialog(null, "Please verify that the username and password are correct", "Incorrect Login", JOptionPane.WARNING_MESSAGE);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Username not found. Please create an account", "Username Not Found", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * Method to check the password for the username
     * @param username username to check the password for
     * @param password password entered
     * @return true is password is correct else return false
     */
    private boolean isPasswordCorrect(String username, char[] password) {
        boolean isCorrect;
        char[] correctPassword = (database.retrieve(username).getPassword()).toCharArray();

        if (password.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (password, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }

    /**
     * Register Action for the LoginPanel View which reacts to RegisterationButton
     */
    public void registerButtonActionPerformed() {
        frame.dispose();
        RegistrationPanel registration = new RegistrationPanel();

    }
}
