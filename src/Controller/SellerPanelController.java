package Controller;

import Model.*;
import View.LoginPanel;
import View.SellerInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class is the controller for SellerPanel view. It handles the action listeners from the view and interacts with the AccountList database and ProductList.
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

    private JFrame frame;
    private Session currentSession;
    private AccountList database = AccountList.getInstance();
    private Seller currentUser;


    /**
     * Contructor for SellerPanelController called when the object is created
     * @param userSession Session object for the user currently logged in
     * @param jFrame JFrame object to dispose current frame
     * @param inventoryJTable JTable object to display the seller inventory
     * @param productIDField JTextField object for productID
     * @param invoicePriceField JTextField object for invoice price
     * @param descriptionField JTextArea object for description
     * @param sellingPriceField JTextField object for selling price
     * @param quantityField JTextField object for quantity field
     * @param productNameField JTextField object for product name
     * @param productTypeField JTextField object for product type
     */
    public SellerPanelController(Session userSession, JFrame jFrame, JTable inventoryJTable, JTextField productIDField, JTextField invoicePriceField, JTextArea descriptionField, JTextField sellingPriceField,
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
        frame = jFrame;
        currentUser = (Seller) database.retrieve(currentSession.getUserInSession());

        loadList();
    }

    /**
     * Method to load the JTable with the seller inventory
     */
    private void loadList()
    {
        DefaultTableModel theListTable = (DefaultTableModel) inventoryTable.getModel();
        ArrayList<Product> allItems = currentUser.getList().getAllItems();

        for(Product item : allItems) {
            theListTable.addRow(new Object[]{item.getName(), item.getID(), item.getType(), item.getQuantity(),
                    item.getInvoicePrice(), item.getSellingPrice(), false});
        }
    }

    /**
     * Log Out Action for the SellerPanel View which reacts to LogOutButton
     */
    public void logOutButtonActionPerformed() {
        frame.dispose();
        LoginPanel backToLogin = new LoginPanel();
    }

    /**
     * My Account Action for the SellerPanel View which reacts to myAccountButton
     */
    public void myAccountButtonActionPerformed() {
        frame.dispose();
        SellerInfo sellerInfoPanel = new SellerInfo(currentSession);
    }

    /**
     * Add Product Action for the SellerPanel View which reacts to addProduct button and adds a product to the productlist
     */
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

    /**
     * Update Product Action for the SellerPanel View which reacts to updateProduct button and makes changes to the product
     */
    public void updateProductButtonActionPerformed() {

        DefaultTableModel theListTable = (DefaultTableModel) inventoryTable.getModel();
        String productID;
        Product product;
        ProductList productList = currentUser.getList();
        ArrayList<Product> allItems = currentUser.getList().getAllItems();

        if(inventoryTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "There are no products in the inventory to update", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            boolean isChecked = (boolean)inventoryTable.getValueAt(i, 6);
            if(isChecked) {
                break;
            }
            else if(i == inventoryTable.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "No products are selected to update", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }


        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            boolean isChecked = (boolean)inventoryTable.getValueAt(i, 6);

            if(isChecked) {
                productID = allItems.get(i).getID();
                product = productList.getProduct(productID);

                product.setName((String)theListTable.getValueAt(i, 0));
                product.setID((String)theListTable.getValueAt(i, 1));
                product.setType((String)theListTable.getValueAt(i, 2));
                product.setQuantity((Integer)theListTable.getValueAt(i, 3));
                product.setInvoicePrice((Double)theListTable.getValueAt(i, 4));
                product.setSellingPrice((Double)theListTable.getValueAt(i, 5));
            }
        }

        JOptionPane.showMessageDialog(null, "Selected Products Have Been Updated", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        resetCheckBoxes();
    }

    /**
     * Delete Product Action for the SellerPanel View which reacts to deleteProduct button and deletes a product from the productlist
     */
    public void deleteProductButtonActionPerformed() {
        DefaultTableModel theListTable = (DefaultTableModel) inventoryTable.getModel();
        String productID;
        ProductList productList = currentUser.getList();

        if(inventoryTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "There are no products in inventory to remove", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            boolean isChecked = (boolean)inventoryTable.getValueAt(i, 6);
            if(isChecked) {
                break;
            }
            else if(i == inventoryTable.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "No products are selected to remove", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            boolean isChecked = (boolean)inventoryTable.getValueAt(i, 6);

            if(isChecked) {
                productID = (String)theListTable.getValueAt(i, 1);
                productList.remove(productID);
                theListTable.removeRow(i);
                i--;
            }
        }

        JOptionPane.showMessageDialog(null, "Product Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method to reset checkboxes on the JTable
     */
    private void resetCheckBoxes() {
        DefaultTableModel theListTable = (DefaultTableModel) inventoryTable.getModel();
        for(int i = 0; i < inventoryTable.getRowCount(); i++) {
            theListTable.setValueAt(false, i, 6);
        }
    }
}
