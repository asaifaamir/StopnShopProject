package view;

import javax.swing.*;

/**
 * Created by asaifbutt on 4/6/17.
 */
public class registrationPanel {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel registration;
    private JButton registerButton;

    public registrationPanel()
    {

    }

    public static void main(String [] args)
    {
        JFrame frame = new JFrame();
        frame.setContentPane(new registrationPanel().registration);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);


    }
}
