package View;

import Controller.LoginPanelController;

import javax.swing.*;

/**
 * This class is the view where the user logs in to the shopping cart. They can signup or login, and based on the account type are taken to the respective view.
 * Created by asaifbutt on 4/19/17.
 */
public class LoginPanel {
    private LoginPanelController loginPanelController;
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JButton loginButton;

    /**
     * Constructor for LoginPanel to create the JFrame when LoginPanel object is created
     */
    public LoginPanel()
    {
        JFrame frame = new JFrame();
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(loginButton);

        loginPanelController = new LoginPanelController(frame);

        loginButton.addActionListener(e -> loginPanelController.loginButtonActionPerformed(usernameField, passwordField));
        signUpButton.addActionListener(e -> loginPanelController.registerButtonActionPerformed());
    }

}
