package View;

import Model.AccountList;
import Model.Session;

import javax.swing.*;

/**
 * Created by asaifbutt on 4/10/17.
 */
public class ProductBrowserPanel extends javax.swing.JFrame{
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

    public ProductBrowserPanel(AccountList database, Session newSession) {
    }


}
