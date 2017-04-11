package view;

import javax.swing.*;

/**
 * Created by asaifbutt on 4/6/17.
 */
public class RegistrationPanel {
    private JPanel registration;
    private JComboBox comboBox1;
    private JTextField fullName;
    private JTextField userName;
    private JPasswordField password;
    private JPasswordField passwordConfirm;
    private JTextField creditCard;
    private JTextField ccv;
    private JButton registerButton;

    public RegistrationPanel()
    {

    }

    public static void main(String [] args)
    {
        JFrame frame = new JFrame();
        frame.setContentPane(new RegistrationPanel().registration);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);


    }
}
