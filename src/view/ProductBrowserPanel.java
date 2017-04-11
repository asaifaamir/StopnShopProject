package view;

import javax.swing.*;

/**
 * Created by asaifbutt on 4/10/17.
 */
public class ProductBrowserPanel {
    private JPanel productBrowse;
    private JTable table1;
    private JButton myAccountButton;
    private JButton logOutButton;
    private JButton viewCartButton;
    private JButton clearCartButton;
    private JButton addToCartButton;

    public ProductBrowserPanel()
    {

    }

    public static void main(String [] args)
    {
        JFrame frame = new JFrame();
        frame.setContentPane(new ProductBrowserPanel().productBrowse);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);


    }
}
