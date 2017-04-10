package view;

import javax.swing.*;

/**
 * Created by asaifbutt on 4/6/17.
 */
public class loginPanel {
    private JPanel loginPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private JButton loginButton;

    public loginPanel() {


    }


    public static void main(String [] args)
    {
        JFrame frame = new JFrame();
        frame.setContentPane(new loginPanel().loginPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);


    }

}

