package View;

import Controller.AccountInformationPanelController;
import Model.Session;

import javax.swing.*;

/**
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

    public SellerInfo(Session currentSession) {
        JFrame frame = new JFrame();
        frame.setContentPane(sellerInfoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        accountInformationPanelController = new AccountInformationPanelController(currentSession, firstName, lastName, userName, password, creditCard, ccv, expirationDate, titleNameLabel);
        backButton.addActionListener(e -> accountInformationPanelController.backButtonActionPerformed(frame, "seller"));
        updateAccountButton.addActionListener((e -> accountInformationPanelController.updateAccountButtonActionPerformed(frame, updateAccountButton, backButton)));
        checkSalesButton.addActionListener(e -> accountInformationPanelController.checkSalesButtonActionPerformed());

    }

}
