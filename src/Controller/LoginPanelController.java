package Controller;

import Model.AccountList;
import Model.Customer;
import Model.Seller;
import Model.Session;
import View.NewProductPanel;
import View.ProductBrowserPanel;
import View.RegistrationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Created by asaifbutt on 4/17/17.
 */
public class LoginPanelController {

    AccountList database;

    /**
     * Login Action for the LoginPanel View which reacts to Login Button
     * @param e takes an Action Event object
     * @param frame the frame on which the action is performed
     * @param userNameField the username field to get the username
     * @param passwordField1 the password field to get the password
     */
    public void loginButtonActionPerformed(ActionEvent e, JFrame frame, JTextField userNameField, JPasswordField passwordField1)
    {
        String username = userNameField.getText();
        char[] password = passwordField1.getPassword();

        if ((username.equals("Hello")) || password.equals("123"))
        {
            JOptionPane.showMessageDialog(null, "Welcome: " + username, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please verify that the username and password are correct", "Incorrect Login", JOptionPane.WARNING_MESSAGE);
        };
/*
        if (database.search(username))
        {
            if (password.equals(database.retrieve(username).getPassword()))
            {
                if (database.retrieve(username).getClass() == Customer.class)
                {
                    Session newSession = new Session(username);
                    JOptionPane.showMessageDialog(null, "Welcome: " + username, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    ProductBrowserPanel customerScreen = new ProductBrowserPanel(database, newSession);
                    customerScreen.setVisible(true);
                }

                else if (database.retrieve(username).getClass() == Seller.class)
                {
                    Session newSession = new Session(username);
                    JOptionPane.showMessageDialog(null, "Welcome: " + username, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    NewProductPanel sellerScreen = new NewProductPanel(newSession);
                    sellerScreen.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please verify that the username and password are correct", "Incorrect Login", JOptionPane.WARNING_MESSAGE);

                }
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Please verify that the username and password are correct", "Incorrect Login", JOptionPane.WARNING_MESSAGE);
        }*/

    }

    /*private boolean isPasswordCorrect(String username, char[] password) {
        boolean isCorrect = true;
        char[] correctPassword = (database.retrieve(username).getPassword()).toCharArray();

        if (password.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (password, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }*/

    /**
     * Register Action for the LoginPanel View which reacts to RegisterationButton
     * @param e Takes an Action event object
     * @param frame The frame on which the action is performed
     */
    public void registerButtonActionPerformed(ActionEvent e, JFrame frame) {
        frame.dispose();
        RegistrationPanel registration = new RegistrationPanel();

    }
}
