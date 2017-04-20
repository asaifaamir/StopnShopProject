package Controller;

import Model.AccountList;
import Model.Customer;
import Model.Seller;
import View.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by asaifbutt on 4/18/17.
 */
public class RegistrationPanelController {

    AccountList database = AccountList.getInstance();

    /**
     * Cancel action for the RegistrationPanel to go back to the login screen
     * @param e Takes an action event object
     * @param frame The frame on which action is performed
     */
    public void cancelButtonActionPerformed(ActionEvent e, JFrame frame) {
        frame.dispose();
        LoginPanel backToLogin = new LoginPanel();
    }

    /**
     * Registration action for the RegistrationPanel to add a new Account
     * @param e Takes an action event object
     * @param frame The frame on which action is performed
     * @param firstName full name of the user
     * @param userName username for the account
     * @param password password for the account
     * @param passwordConfirm confirmed password for the account
     * @param creditCard credit card information for the account
     * @param ccv ccv for credit card
     */
    public void registerButtonActionPerformed(ActionEvent e, JFrame frame, JComboBox accountTypeBox, JTextField firstName, JTextField lastName, JTextField userName,
                                              JPasswordField password, JPasswordField passwordConfirm, JTextField creditCard, JTextField ccv, JTextField expDate)
    {
        String[] input = new String[8];
        int accountType = 2;

        if(accountTypeBox.getSelectedIndex() == 0)
        {
            accountType = 0;
        }
        else if(accountTypeBox.getSelectedIndex() == 1)
        {
            accountType = 1;
        }

        input[0] = firstName.getText().trim();
        input[1] = lastName.getText().trim();
        input[2] = userName.getText().trim();
        input[3] = new String(password.getPassword());
        input[4] = new String(passwordConfirm.getPassword());
        input[5] = creditCard.getText().trim();
        input[6] = ccv.getText().trim();
        input[7] = expDate.getText().trim();

        //Error checking and storing it in the database

        if(input[0].length() < 1) {
            JOptionPane.showMessageDialog(null, "Cannot leave first name field blank", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(input[1].length() < 1) {
            JOptionPane.showMessageDialog(null, "Cannot leave last name field blank", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(input[2].length() < 5 || input[1].length() > 16) {
            JOptionPane.showMessageDialog(null, "Username must be between 5 and 16 characters", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(input[3].length() < 6) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(!(input[3].equals(input[4]))) {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(input[5].length() < 16 || input[5].length() >= 17) {
            JOptionPane.showMessageDialog(null, "Incorrect Credit Card Length", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else {
            for(int i = 0; i < input[5].length(); i++) {
                if(Character.isDigit(input[5].charAt(i))) {
                    // do nothing
                }
                else {
                    JOptionPane.showMessageDialog(null, "credit card number cannot contains characters or symbols",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }

        if(input[6].length() < 3 || input[6].length() >= 4) {
            JOptionPane.showMessageDialog(null, "CCV number has incorrect length.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(input[7].length() > 5)
        {
            JOptionPane.showMessageDialog(null, "Expiration date is incorrect.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(database.search(input[2])) {
            JOptionPane.showMessageDialog(null, "The username: " + input[2] + " is already taken",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            if(accountType == 0) {
                Customer aNewCustomer = new Customer(input[0], input[1], input[2], input[3], input[5], input[6], input[7]);
                database.insert(aNewCustomer);
                JOptionPane.showMessageDialog(null, "\tAccount Created! Please log in to start shopping!", "Success!", JOptionPane.PLAIN_MESSAGE);
                frame.dispose();
                LoginPanel backToLogin = new LoginPanel();
            }
            else if(accountType == 1) {
                Seller aNewSeller = new Seller(input[0], input[1], input[2], input[3], input[5], input[6], input[7]);
                database.insert(aNewSeller);
                JOptionPane.showMessageDialog(null, "\tAccount Created! Please log in to start selling!", "Success!", JOptionPane.PLAIN_MESSAGE);
                frame.dispose();
                LoginPanel backToLogin = new LoginPanel();
            }
        }



    }
}