package View;

import Controller.SellerPanelController;
import Model.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by asaifbutt on 4/19/17.
 */
public class SellerPanel {
    private JPanel sellerPanel;
    private JTable inventoryTable;
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
    private JScrollPane scrollPane;
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

        setUpTable();
        scrollPane.setColumnHeader(new JViewport() {
            @Override public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.width = 5;
                return d;
            }
        });

        scrollPane.setColumnHeaderView(inventoryTable);

        scrollPane.setViewportView(inventoryTable);

        session = userSession;

        sellerPanelController = new SellerPanelController(userSession, inventoryTable, productIDField, invoicePriceField, descriptionField, sellingPriceField, quantityField, productNameField, productTypeField);

        logOutButton.addActionListener(e -> sellerPanelController.logOutButtonActionPerformed(frame));
        myAccountButton.addActionListener(e -> sellerPanelController.myAccountButtonActionPerformed(frame));
        addProductButton.addActionListener(e -> sellerPanelController.addProductButtonActionPerformed());
        updateProductButton.addActionListener(e -> sellerPanelController.updateProductButtonActionPerformed());
        deleteProductButton.addActionListener(e -> sellerPanelController.deleteProductButtonActionPerformed());


    }

    private void setUpTable() {
        inventoryTable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String [] {"Product Name", "Product ID", "Product Type", "Quantity", "Invoice Price", "Selling Price", "Remove/Update"}
        )
        {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, Integer.class, Double.class,
                    Double.class, Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                    true, true, true, true, true, true, true
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
