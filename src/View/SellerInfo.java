package View;

import Controller.AccountInformationPanelController;
import Model.Session;

import javax.swing.*;

/**
 * This view is where the Seller can view their account information and edit their account. They can also view their account statistics
 * Created by asaifbutt on 4/19/17.
 */
public class SellerInfo {

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
    private JButton checkSalesButton;
    private JPanel sellerInfoPanel;
    private AccountInformationPanelController accountInformationPanelController;

    /**
     * Constructor for CheckoutPanel to create the JFrame when checkoutpanel object is created
     * @param currentSession the user currently in session
     */
    public SellerInfo(Session currentSession) {
        JFrame frame = new JFrame();
        frame.setContentPane(sellerInfoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        accountInformationPanelController = new AccountInformationPanelController(currentSession, frame, firstName, lastName, userName, password, creditCard, ccv, expirationDate, titleNameLabel);
        backButton.addActionListener(e -> accountInformationPanelController.backButtonActionPerformed("seller"));
        updateAccountButton.addActionListener((e -> accountInformationPanelController.updateAccountButtonActionPerformed(updateAccountButton, backButton)));
        checkSalesButton.addActionListener(e -> accountInformationPanelController.checkSalesButtonActionPerformed());

    }

}
