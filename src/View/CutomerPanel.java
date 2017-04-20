package View;

import Controller.ProductBrowserPanelController;
import Model.Session;

import javax.swing.*;


/**
 * Created by asaifbutt on 4/10/17.
 */
public class CutomerPanel {

    private JPanel productBrowse;
    private JTable table1;
    private JButton myAccountButton;
    private JButton logOutButton;
    private JButton viewCartButton;
    private JButton clearCartButton;
    private JButton addToCartButton;
    private JTextArea cartTotal;
    private JLabel jLabel1;
    private ProductBrowserPanelController productBrowserPanelController;

    /**
     * Creates a productBrowserPanel object
     * @param userSession The current user that is loggedin
     */
    public CutomerPanel(Session userSession)
    {
        productBrowserPanelController = new ProductBrowserPanelController(table1, cartTotal, userSession);
        JFrame frame = new JFrame();
        frame.setContentPane(productBrowse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cartTotal.setEditable(false);

        createUIComponents();

        myAccountButton.addActionListener(e -> productBrowserPanelController.myAccountButtonActionPerformed(e, frame));
        logOutButton.addActionListener(e -> productBrowserPanelController.logOutButtonActionPerformed(e, frame));
        viewCartButton.addActionListener(e -> productBrowserPanelController.viewCartButtonActionPerformed(e, frame));
        clearCartButton.addActionListener(e -> productBrowserPanelController.clearCartButtonActionPerformed(e, frame));
        addToCartButton.addActionListener(e -> productBrowserPanelController.addToCartButtonActionPerformed(e, frame));

    }

    /**
     * Custom component creation for the JTable
     */
    private void createUIComponents() {
        table1 = new JTable();
        table1.setModel(new javax.swing.table.DefaultTableModel(
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
