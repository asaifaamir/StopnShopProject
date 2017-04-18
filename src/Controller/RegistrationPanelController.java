package Controller;

import View.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by asaifbutt on 4/18/17.
 */
public class RegistrationPanelController {
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
     * @param fullName full name of the user
     * @param userName username for the account
     * @param password password for the account
     * @param passwordConfirm confirmed password for the account
     * @param creditCard credit card information for the account
     * @param ccv ccv for credit card
     */
    public void registerButtonActionPerformed(ActionEvent e, JFrame frame, JTextField fullName, JTextField userName, JPasswordField password, JPasswordField passwordConfirm, JTextField creditCard, JTextField ccv) {
    }
}
