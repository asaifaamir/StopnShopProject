package View;

import Controller.AccountInformationPanelController;
import Model.Session;

import javax.swing.*;

/**
 * This class is the view that displays customer account information and allows customer to edit their account.
 * Created by asaifbutt on 4/19/17.
 */
public class CustomerInformationPanel {
    private JPanel customerInfoPanel;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField userName;
    private JTextField password;
    private JTextField creditCard;
    private JTextField ccv;
    private JTextField expirationDate;
    private JButton updateAccountButton;
    private JButton backButton;
    private JLabel titleNameLabel;
    private AccountInformationPanelController customerInfoController;


    /**
     * Constructor for CustomerInformationPanel to create the JFrame when CustomerInformationPanel object is created
     * @param currentUser The user currently in session
     */
    public CustomerInformationPanel(Session currentUser) {
        JFrame frame = new JFrame();
        frame.setContentPane(customerInfoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        customerInfoController = new AccountInformationPanelController(currentUser, frame, firstName, lastName, userName, password, creditCard, ccv, expirationDate, titleNameLabel);

        updateAccountButton.addActionListener(e -> customerInfoController.updateAccountButtonActionPerformed(updateAccountButton, backButton));
        backButton.addActionListener(e -> customerInfoController.backButtonActionPerformed("customer"));


    }
}
