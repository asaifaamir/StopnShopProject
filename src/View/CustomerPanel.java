package View;

import Controller.CustomerPanelController;
import Model.Session;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * This class is the view where customer can see the items in the seller product list. Customer can add those items in their cart
 * Created by asaifbutt on 4/10/17.
 */
public class CustomerPanel {

    private JPanel productBrowse;
    private JTable productTable;
    private JButton myAccountButton;
    private JButton logOutButton;
    private JButton viewCartButton;
    private JButton clearCartButton;
    private JButton addToCartButton;
    private JTextArea cartTotal;
    private CustomerPanelController customerPanelController;

    /**
     * Constructor for CustomerPanel to create the JFrame when CustomerPanel object is created
     * @param userSession The current user in session
     */
    public CustomerPanel(Session userSession)
    {
        JFrame frame = new JFrame();
        frame.setContentPane(productBrowse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setUpTable();

        customerPanelController = new CustomerPanelController(frame, productTable, cartTotal, userSession);

        cartTotal.setEditable(false);

        myAccountButton.addActionListener(e -> customerPanelController.myAccountButtonActionPerformed());
        logOutButton.addActionListener(e -> customerPanelController.logOutButtonActionPerformed());
        viewCartButton.addActionListener(e -> customerPanelController.viewCartButtonActionPerformed());
        clearCartButton.addActionListener(e -> customerPanelController.clearCartButtonActionPerformed());
        addToCartButton.addActionListener(e -> customerPanelController.addToCartButtonActionPerformed());

    }

    /**
     * Method to setup the JTable in CustomerPanel
     */
    private void setUpTable()
    {
        productTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Product", "ProductID", "Price", "In Stock", "Quantity to buy", "Seller", "Description", "Purchase"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        productTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    customerPanelController.productDescriptionAction(e);
                }
            }
        });

    }

}
