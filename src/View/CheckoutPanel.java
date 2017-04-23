package View;

import Controller.CheckoutPanelController;
import Model.Session;

import javax.swing.*;

/**
 * The CheckoutPanel class is the view for the customer to checkout the items in the cart
 * Created by asaifbutt on 4/19/17.
 */
public class CheckoutPanel {

    private JPanel checkOutPanel;
    private JTextField cartTotal;
    private JButton backButton;
    private JTable productTable;
    private JButton deleteButton;
    private JButton completePurchaseButton;
    private JButton updateButton;


    /**
     * Constructor for CheckoutPanel to create the JFrame when checkoutpanel object is created
     * @param currentSession The current user is session
     */
    public CheckoutPanel(Session currentSession)
    {
        JFrame frame = new JFrame();
        frame.setContentPane(checkOutPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setUpTable();

        CheckoutPanelController checkoutPanelController = new CheckoutPanelController(productTable, currentSession, cartTotal);
        backButton.addActionListener(e -> checkoutPanelController.backButtonActionPerformed(frame));
        deleteButton.addActionListener(e -> checkoutPanelController.deleteButtonActionPerformed());
        completePurchaseButton.addActionListener(e -> checkoutPanelController.completePurchaseButtonActionPerformed());
        updateButton.addActionListener(e -> checkoutPanelController.updateButtonActionPerformed());


    }

    /**
     * Method to setup the JTable in the Checkout Panel
     */
    private void setUpTable()
    {
        productTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Product", "ProductID", "Price", "Seller", "Quantity", "Quantity to update", "Delete/Update"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

        });
    }



}
