package View;

import Controller.LoginPanelController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by asaifbutt on 4/17/17.
 */
public class LoginPanel {
    private final LoginPanelController loginPanelController;
    private JPanel loginPanel;
    private JButton signUpButton;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel title;
    private JPanel titlePanel;
    private JLabel iconLabel;

    /**
     * Creates a LoginPanel object.
     * The object creates a new frame for login panel
     */
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

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("../Image/icon.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        iconLabel.setIcon(imageIcon);

        loginButton.addActionListener(e -> loginPanelController.loginButtonActionPerformed(e, frame ,usernameField, passwordField));
        signUpButton.addActionListener(e -> loginPanelController.registerButtonActionPerformed(e, frame));

    }


}
