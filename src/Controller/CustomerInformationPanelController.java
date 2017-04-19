package Controller;

import Model.Account;
import Model.AccountList;
import Model.Session;
import View.ProductBrowserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by asaifbutt on 4/19/17.
 */
public class CustomerInformationPanelController {

    private AccountList database = AccountList.getInstance();
    private  Session currentUserSession;
    private JTextField firstNameField;
    private final Account currentUserInfo;
    private JTextField lastNameField;
    private JTextField userNameField;
    private JTextField passwordField;
    private JTextField creditCardField;
    private JTextField ccvField;
    private JTextField expirationDateField;
    private boolean edit = false;


    /**
     * Creates a CustomerInformationPanelController object
     * @param currentUser the current user logged in
     */
    public CustomerInformationPanelController(Session currentUser, JTextField firstName, JTextField lastName, JTextField userName, JTextField password, JTextField creditCard, JTextField ccv, JTextField expirationDate) {
        currentUserSession = currentUser;
        currentUserInfo = database.retrieve(currentUser.getUserInSession());
        firstNameField = firstName;
        lastNameField = lastName;
        userNameField = userName;
        passwordField = password;
        creditCardField = creditCard;
        ccvField = ccv;
        expirationDateField = expirationDate;

        getAccountInfo();
    }

    /**
     * Retrieves the information for the user currently logged in
     */
    private void getAccountInfo()
    {
        firstNameField.setText(currentUserInfo.getFirstName());
        lastNameField.setText(currentUserInfo.getLastName());
        userNameField.setText(currentUserInfo.getUserName());
        passwordField.setText(currentUserInfo.getPassword());
        creditCardField.setText(currentUserInfo.getCreditCard());
        ccvField.setText(currentUserInfo.getCCV());
        expirationDateField.setText(currentUserInfo.getExpDate());
    }

    /**
     * Update Account Action for the CustomerInformationPanel View which reacts to Update Account Button
     * @param e ActionEvent object
     * @param frame The frame where the action takes place
     */
    public void updateAccountButtonActionPerformed(ActionEvent e, JFrame frame, JButton updateAccountButton, JButton backButton) {
        if (!edit) {
            edit = true;
            backButton.setEnabled(false);
            updateAccountButton.setText("Update Changes");
            firstNameField.setEditable(true);
            lastNameField.setEditable(true);
            passwordField.setEditable(true);
            creditCardField.setEditable(true);
            ccvField.setEditable(true);
        }
        else if (edit)
        {
            String[] allUserInput = new String[5];
            allUserInput[0] = firstNameField.getText().trim();
            allUserInput[1] = lastNameField.getText().trim();
            allUserInput[2] = passwordField.getText().trim();
            allUserInput[3] = creditCardField.getText().trim();
            allUserInput[4] = ccvField.getText().trim();

        }


        /*if(!editState) {
            editState = true;
            goBackButton.setEnabled(false);
            editButton.setText("Save Changes");
            nameField.setEditable(true);
            passwordField.setEditable(true);
            creditcardField.setEditable(true);
            ccvField.setEditable(true);
        }
        else if(editState) {
            String[] allUserInput = new String[5];
            allUserInput[0] = nameField.getText().trim();
            allUserInput[2] = passwordField.getText().trim();
            allUserInput[3] = creditcardField.getText().trim();
            allUserInput[4] = ccvField.getText().trim();

            if(allUserInput[0].length() < 1) {
                JOptionPane.showMessageDialog(null, "Cannot leave full name field blank", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(allUserInput[2].length() < 6) {
                JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(allUserInput[3].length() < 16 || allUserInput[3].length() >= 17) {
                JOptionPane.showMessageDialog(null, "Incorrect Credit Card Length", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else {
                for(int i = 0; i < allUserInput[3].length(); i++) {
                    if(Character.isDigit(allUserInput[3].charAt(i))) {

                    }
                    else {
                        JOptionPane.showMessageDialog(null, "credit card number cannot contains characters or symbols", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }

            if(allUserInput[4].length() < 3 || allUserInput[4].length() >= 4) {
                JOptionPane.showMessageDialog(null, "CCV number has incorrect length", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            editState = false;
            currentUser.setFullName(allUserInput[0]);
            currentUser.setPassword(allUserInput[2]);
            currentUser.setCreditCard(allUserInput[3]);
            currentUser.setCCV(allUserInput[4]);

            editButton.setText("Edit Account");
            nameField.setEditable(false);
            passwordField.setEditable(false);
            creditcardField.setEditable(false);
            ccvField.setEditable(false);
            goBackButton.setEnabled(true);

            currentSession.setUserInSession(currentUser.getUserName());

            JOptionPane.showMessageDialog(null, "Changes were successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        }*/
    }

    /**
     * * Back Action for the CustomerInformationPanel View which reacts to BackButton
     * @param e ActionEvent object
     * @param frame The frame where the action takes place
     * @param updateAccountButton
     * @param backButton
     */
    public void backButtonActionPerformed(ActionEvent e, JFrame frame) {
        frame.dispose();
        ProductBrowserPanel backToProductPanel = new ProductBrowserPanel(currentUserSession);
    }
}
