package View;

import Controller.CustomerInformationPanelController;
import Model.Session;

import javax.swing.*;

/**
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
    private CustomerInformationPanelController customerInfoController;


    /**
     * Create a CustomerInformationPanel Object
     * @param currentUser The user currently logged in
     */
    public CustomerInformationPanel(Session currentUser) {
        JFrame frame = new JFrame();
        frame.setContentPane(customerInfoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        customerInfoController = new CustomerInformationPanelController(currentUser, firstName, lastName, userName, password, creditCard, ccv, expirationDate, titleNameLabel);

        updateAccountButton.addActionListener(e -> customerInfoController.updateAccountButtonActionPerformed(e, frame, updateAccountButton, backButton));
        backButton.addActionListener(e -> customerInfoController.backButtonActionPerformed(e, frame));


    }
}
