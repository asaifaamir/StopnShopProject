package View;

import Controller.LoginPanelController;

import javax.swing.*;

/**
 * Created by asaifbutt on 4/19/17.
 */
public class LoginPanel {
    private LoginPanelController loginPanelController;
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JButton loginButton;

    public LoginPanel()
    {
        loginPanelController = new LoginPanelController();

        JFrame frame = new JFrame();
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(e -> loginPanelController.loginButtonActionPerformed(e, frame ,usernameField, passwordField));
        signUpButton.addActionListener(e -> loginPanelController.registerButtonActionPerformed(e, frame));
    }

}
