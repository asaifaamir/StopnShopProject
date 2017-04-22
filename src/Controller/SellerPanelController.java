package Controller;

import Model.*;
import View.LoginPanel;
import View.SellerInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by asaifbutt on 4/20/17.
 */
public class SellerPanelController {

    private JTable inventoryTable;
    private JTextField productID;
    private JTextField invoicePrice;
    private JTextArea description;
    private JTextField sellingPrice;
    private JTextField quantity;
    private JTextField productName;
    private JTextField productType;

    private Session currentSession;
    private AccountList database = AccountList.getInstance();
    private Seller currentUser;


    public SellerPanelController(Session userSession, JTable inventoryJTable, JTextField productIDField, JTextField invoicePriceField, JTextArea descriptionField, JTextField sellingPriceField,
                                 JTextField quantityField, JTextField productNameField, JTextField productTypeField)
    {
        inventoryTable = inventoryJTable;
        productID = productIDField;
        invoicePrice = invoicePriceField;
        description = descriptionField;
        sellingPrice = sellingPriceField;
        quantity = quantityField;
        productName = productNameField;
        productType = productTypeField;
        currentSession = userSession;
        currentUser = (Seller) database.retrieve(currentSession.getUserInSession());
        loadList();
    }

    private void loadList()
    {
        DefaultTableModel theListTable = (DefaultTableModel) inventoryTable.getModel();
        ArrayList<Product> allItems = currentUser.getList().getAllItems();

        // adds rows to the JTable on the screen using the attributes of the Product objects from the Sellers
        // inventory
        for(Product item : allItems) {
            theListTable.addRow(new Object[]{item.getName(), item.getID(), item.getType(), item.getQuantity(),
                    item.getInvoicePrice(), item.getSellingPrice(), 0, false});
        }
    }

    public void logOutButtonActionPerformed(JFrame frame) {
        frame.dispose();
        LoginPanel backToLogin = new LoginPanel();
    }

    public void myAccountButtonActionPerformed(JFrame frame) {
        frame.dispose();
        SellerInfo sellerInfoPanel = new SellerInfo(currentSession);
    }

    public void addProductButtonActionPerformed(JFrame frame) {

        if(productName.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Product Name Field Cannot Be Empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(productID.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Product ID Field Cannot Be Empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(quantity.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Quantity Field Cannot Be Empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(invoicePrice.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Invoice Price Field Cannot Be Empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(sellingPrice.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Selling Price Field Cannot Be Empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(productType.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Product Type Field Cannot Be Empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(description.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Product Description Field Cannot Be Empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }



        DefaultTableModel listTable = (DefaultTableModel) inventoryTable.getModel();
        listTable.addRow(new Object[]{
                productName.getText(), productID.getText(), productType.getText(), quantity.getText(), invoicePrice.getText(), sellingPrice.getText(), quantity.getText(), false
        });

        ProductList userList = currentUser.getList();
        userList.add(new Product(productName.getText(),productID.getText(), productType.getText(), Integer.parseInt(quantity.getText()),
                Double.parseDouble(invoicePrice.getText()), Integer.parseInt(sellingPrice.getText()), currentUser.getUserName(), description.getText()));

        JOptionPane.showMessageDialog(null, "Product Added!");

        productID.setText("");
        invoicePrice.setText("");
        description.setText("");
        sellingPrice.setText("");
        quantity.setText("");
        productName.setText("");
        productType.setText("");

    }
}
