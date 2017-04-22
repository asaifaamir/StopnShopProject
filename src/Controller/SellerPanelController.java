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

        for(Product item : allItems) {
            theListTable.addRow(new Object[]{item.getName(), item.getID(), item.getType(), item.getQuantity(),
                    item.getInvoicePrice(), item.getSellingPrice(), false});
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

    public void addProductButtonActionPerformed() {

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
                productName.getText(), productID.getText(), productType.getText(), Integer.parseInt(quantity.getText()), Double.parseDouble(invoicePrice.getText()),
                Double.parseDouble(sellingPrice.getText()), false
        });

        ProductList userList = currentUser.getList();
        userList.add(new Product(productName.getText(),productID.getText(), productType.getText(), Integer.parseInt(quantity.getText()),
                Double.parseDouble(invoicePrice.getText()), Integer.parseInt(sellingPrice.getText()), currentUser.getUserName(), description.getText()));

        productID.setText("");
        invoicePrice.setText("");
        description.setText("");
        sellingPrice.setText("");
        quantity.setText("");
        productName.setText("");
        productType.setText("");

        JOptionPane.showMessageDialog(null, "Product " + productName.getText() + " Added!", null, JOptionPane.INFORMATION_MESSAGE);

    }

    public void updateProductButtonActionPerformed() {

        DefaultTableModel theListTable = (DefaultTableModel) inventoryTable.getModel();
        String productID;
        Product productToEdit;
        ProductList userList = currentUser.getList();
        ArrayList<Product> allItems = currentUser.getList().getAllItems();

        if(inventoryTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "There are no items in the inventory to update", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            boolean isChecked = (boolean)inventoryTable.getValueAt(i, 6);
            if(isChecked) {
                break;
            }
            else if(i == inventoryTable.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "No items are selected to update", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }


        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            boolean isChecked = (boolean)inventoryTable.getValueAt(i, 6);

            if(isChecked) {
                productID = allItems.get(i).getID();
                productToEdit = userList.getProduct(productID);

                productToEdit.setName((String)theListTable.getValueAt(i, 0));
                productToEdit.setID((String)theListTable.getValueAt(i, 1));
                productToEdit.setType((String)theListTable.getValueAt(i, 2));
                productToEdit.setQuantity((Integer)theListTable.getValueAt(i, 3));
                productToEdit.setInvoicePrice((Double)theListTable.getValueAt(i, 4));
                productToEdit.setSellingPrice((Double)theListTable.getValueAt(i, 5));
            }
        }

        JOptionPane.showMessageDialog(null, "Selected Items Have Been Updated", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        resetCheckBoxes();
    }

    public void deleteProductButtonActionPerformed() {
        DefaultTableModel theListTable = (DefaultTableModel) inventoryTable.getModel();
        String productID;
        ProductList userList = currentUser.getList();

        if(inventoryTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "There are no items in inventory to remove", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            boolean isChecked = (boolean)inventoryTable.getValueAt(i, 6);
            if(isChecked) {
                break;
            }
            else if(i == inventoryTable.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "No items are selected to remove", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            boolean isChecked = (boolean)inventoryTable.getValueAt(i, 6);

            if(isChecked) {
                productID = (String)theListTable.getValueAt(i, 1);
                userList.remove(productID);
                theListTable.removeRow(i);
                i--;
            }
        }

        JOptionPane.showMessageDialog(null, "Product Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetCheckBoxes() {
        DefaultTableModel theListTable = (DefaultTableModel) inventoryTable.getModel();
        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            theListTable.setValueAt(false, i, 6);
        }
    }
}
