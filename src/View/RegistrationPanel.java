package View;

import Controller.RegistrationPanelController;

import javax.swing.*;
import java.awt.*;

/**
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

    public RegistrationPanel() throws HeadlessException {
        registrationPanelController = new RegistrationPanelController();
        JFrame frame = new JFrame();
        frame.setContentPane(registration);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cancelButton.addActionListener(e -> registrationPanelController.cancelButtonActionPerformed(e, frame));
        registerButton.addActionListener(e -> registrationPanelController.registerButtonActionPerformed(e, frame, accountTypeBox, firstName, lastName, userName, password, passwordConfirm, creditCard, ccv, expDate));

    }

}
