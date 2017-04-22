package View;

import Controller.CustomerPanelController;
import Model.Session;

import javax.swing.*;


/**
 * Created by asaifbutt on 4/10/17.
 */
public class CutomerPanel {

    private JPanel productBrowse;
    private JTable productTable;
    private JButton myAccountButton;
    private JButton logOutButton;
    private JButton viewCartButton;
    private JButton clearCartButton;
    private JButton addToCartButton;
    private JTextArea cartTotal;
    private JLabel jLabel1;
    private CustomerPanelController customerPanelController;
    private JButton descriptionButton;

    /**
     * Creates a productBrowserPanel object
     * @param userSession The current user that is loggedin
     */
    public CutomerPanel(Session userSession)
    {
        JFrame frame = new JFrame();
        frame.setContentPane(productBrowse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setUpTable();
        customerPanelController = new CustomerPanelController(productTable, cartTotal, userSession);
        cartTotal.setEditable(false);


        myAccountButton.addActionListener(e -> customerPanelController.myAccountButtonActionPerformed(frame));
        logOutButton.addActionListener(e -> customerPanelController.logOutButtonActionPerformed(frame));
        viewCartButton.addActionListener(e -> customerPanelController.viewCartButtonActionPerformed(frame));
        clearCartButton.addActionListener(e -> customerPanelController.clearCartButtonActionPerformed());
        addToCartButton.addActionListener(e -> customerPanelController.addToCartButtonActionPerformed());

    }

    private void setUpTable()
    {
        productTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Product", "ProductID", "Price", "In Stock", "Quantity", "Seller", "Purchase"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, true, false, true
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
