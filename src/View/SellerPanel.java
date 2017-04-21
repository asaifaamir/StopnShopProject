package View;

import Controller.SellerPanelController;
import Model.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by asaifbutt on 4/19/17.
 */
public class SellerPanel {
    private JPanel sellerPanel;
    private JTable inventory;
    private JTextField productIDField;
    private JTextField invoicePriceField;
    private JTextArea descriptionField;
    private JTextField sellingPriceField;
    private JTextField quantityField;
    private JTextField productNameField;
    private JTextField productTypeField;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private JButton myAccountButton;
    private JButton logOutButton;
    private JButton updateProductButton;
    private SellerPanelController sellerPanelController;

    private Session session;

    public SellerPanel(Session userSession)
    {
        JFrame frame = new JFrame();
        frame.setContentPane(sellerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        createUIComponents();

        session = userSession;

        sellerPanelController = new SellerPanelController(userSession, inventory, productIDField, invoicePriceField, descriptionField, sellingPriceField, quantityField, productNameField, productTypeField);

        logOutButton.addActionListener(e -> sellerPanelController.logOutButtonActionPerformed(frame));
        myAccountButton.addActionListener(e -> sellerPanelController.myAccountButtonActionPerformed(frame));


    }

    private void createUIComponents() {
        inventory = new JTable();
        inventory.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] {"Product Name", "Product ID", "Product Type", "Quantity", "Invoice Price", "Selling Price", "Quantity", "Remove/Update"}
        )
        {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                    true, true, true, true, true, true, true, true
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
