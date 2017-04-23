package View;

import Controller.RegistrationPanelController;

import javax.swing.*;

/**
 * This class is the view where a user can signup and register a new account.
 * Created by asaifbutt on 4/6/17.
 */
public class RegistrationPanel extends javax.swing.JFrame {
    private JPanel registration;
    private JComboBox accountTypeBox;
    private JTextField firstName;
    private JTextField userName;
    private JPasswordField password;
    private JPasswordField passwordConfirm;
    private JTextField creditCard;
    private JTextField ccv;
    private JButton registerButton;
    private JButton cancelButton;
    private JTextField lastName;
    private JTextField expDate;
    private RegistrationPanelController registrationPanelController;

    /**
     * Constructor for RegistrationPanel to create the JFrame when RegistrationPanel object is created
     */
    public RegistrationPanel(){

        JFrame frame = new JFrame();
        frame.setContentPane(registration);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.getRootPane().setDefaultButton(registerButton);

        registrationPanelController = new RegistrationPanelController(frame);

        cancelButton.addActionListener(e -> registrationPanelController.cancelButtonActionPerformed());
        registerButton.addActionListener(e -> registrationPanelController.registerButtonActionPerformed(accountTypeBox, firstName, lastName, userName, password, passwordConfirm, creditCard, ccv, expDate));

    }

}
