package Controller;

import Model.*;
import View.CheckoutPanel;
import View.CustomerInformationPanel;
import View.LoginPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is the controller for CustomerPanel view. It handles the action listeners from the view and interacts with the AccountList database and ProductList.
 * Created by asaifbutt on 4/18/17.
 */
public class CustomerPanelController {

    AccountList database = AccountList.getInstance();
    private JTable productTable;
    private JTextArea cartTotalText;
    Customer customer;
    Session session;
    LinkedList<Integer> stock;
    JFrame frame;

    /**
     * Creates a CustomerPanelController object
     * @param table1 the product table to display products
     * @param cartTotal cartTotal text area to display the cart total
     * @param userSession current user in session
     */
    public CustomerPanelController(JFrame jFrame, JTable table1, JTextArea cartTotal, Session userSession) {
        customer = (Customer) database.retrieve(userSession.getUserInSession());
        productTable = table1;
        cartTotalText = cartTotal;
        session = userSession;
        frame =jFrame;
        loadInventoryFromAllSellers();
        loadCartTotal();
    }

    /**
     * My Account Action for the CustomerPanel View which reacts to MyAccount Button
     */
    public void myAccountButtonActionPerformed() {
        frame.dispose();
        CustomerInformationPanel customerInfo = new CustomerInformationPanel(session);
    }

    /**
     * Log Out Action for the CustomerPanel View which reacts to LogOutButton
     */
    public void logOutButtonActionPerformed() {
        frame.dispose();
        LoginPanel backToLogin = new LoginPanel();
    }

    /**
     * View Cart Action for the CustomerPanel View which reacts to ViewCartButton
     */
    public void viewCartButtonActionPerformed() {
        frame.dispose();
        CheckoutPanel checkoutPanel = new CheckoutPanel(session);
    }

    /**
     * Clear cart action for the CustomerPanel View which reacts to ClearCartButton
     */
    public void clearCartButtonActionPerformed() {
        customer.getShoppingCart().clear();
        DecimalFormat df = new DecimalFormat("#.##");
        cartTotalText.setText("$" + df.format(customer.getShoppingCart().calculateShoppingCartTotal()));
        resetCheckBoxes();

        stock.clear();
        ArrayList<Seller> sellerList = database.getAllSellers();
        ArrayList<Product> allItems = null;

        // adds back original stock quantities to array
        for(Seller seller : sellerList) {
            allItems = seller.getList().getAllItems();
            for(Product item : allItems) {
                stock.add(item.getQuantity());
            }
        }
    }

    /**
     * Add to Cart action for the CustomerPanel View which reacts to the Add to Cart Button
     */
    public void addToCartButtonActionPerformed() {

        DefaultTableModel productTableModel = (DefaultTableModel) productTable.getModel();
        Seller theSeller;
        String productID;
        Product itemPurchased;
        Integer quantitySold;
        Product productToAdd;
        Product itemInCart;
        int itemLimit;

        if(productTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "There are no items to purchase", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for(int i = 0; i < productTable.getRowCount(); i++) {
            boolean isChecked = (boolean)productTable.getValueAt(i, 7);
            if(isChecked) {
                break;
            }
            else if(i == productTable.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "No items are selected for purchase", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        for(int i = 0; i < productTable.getRowCount(); i++) {
            boolean isChecked = (boolean)productTable.getValueAt(i, 7);

            if(isChecked) {
                theSeller = (Seller)database.retrieve((String)productTableModel.getValueAt(i, 5));
                productID = (String)productTableModel.getValueAt(i, 1);
                itemPurchased = theSeller.getList().getProduct(productID);
                quantitySold = (Integer)productTableModel.getValueAt(i, 4);
                itemLimit = stock.get(i);

                if(quantitySold > itemPurchased.getQuantity()) {
                    JOptionPane.showMessageDialog(null, "Cannot purchase more than whats in stock.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(quantitySold > itemLimit) {
                    JOptionPane.showMessageDialog(null, "Cannot purchase more than whats in stock.", "Error", JOptionPane.WARNING_MESSAGE);

                    if(itemLimit == 0) {
                        JOptionPane.showMessageDialog(null, "Purchase limit has been reached for this item.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }

                if(quantitySold <= 0) {
                    if(quantitySold == 0)
                        JOptionPane.showMessageDialog(null, "You have no specified a quantity", "Error", JOptionPane.WARNING_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "You cannot purchase negative amounts", "Error", JOptionPane.WARNING_MESSAGE);

                    return;
                }

                if(customer.getShoppingCart().search(productID)) {
                    itemInCart = customer.getShoppingCart().getProductInstance(productID);
                    itemInCart.setQuantity(itemInCart.getQuantity() + quantitySold);
                    itemLimit -= quantitySold;
                }
                else {
                    productToAdd = new Product(itemPurchased);
                    productToAdd.setQuantity(quantitySold);
                    customer.getShoppingCart().add(productToAdd);
                    itemLimit -= quantitySold;
                }

                stock.set(i, itemLimit);
                productTableModel.setValueAt(0, i, 4);

            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        cartTotalText.setText("$" + df.format(customer.getShoppingCart().calculateShoppingCartTotal()));
        resetCheckBoxes();

        JOptionPane.showMessageDialog(null, "Items added to Cart!", "Success", JOptionPane.INFORMATION_MESSAGE);

        System.out.println(session);
    }

    /**
     * Method to reset the checkboxes on the table
     */
    private void resetCheckBoxes() {
        DefaultTableModel theProductTable = (DefaultTableModel) productTable.getModel();
        for(int i = 0; i < productTable.getRowCount(); i++) {
            theProductTable.setValueAt(false, i, 7);
        }
    }

    /**
     * Method to load the inventory from each seller into the table
     */
    private void loadInventoryFromAllSellers() {
        DefaultTableModel theProductTable = (DefaultTableModel) productTable.getModel();
        ArrayList<Seller> sellerList = database.getAllSellers();
        ArrayList<Product> allItems = null;
        stock = new LinkedList<>();

        for(Seller seller : sellerList) {
            allItems = seller.getList().getAllItems();
            for(Product item : allItems) {
                theProductTable.addRow(new Object[]{item.getName(), item.getID(), item.getSellingPrice(), item.getQuantity(),
                        0, seller.getUserName(), item.getProductDescription(), false});

                stock.add(item.getQuantity());
            }
        }

        replaceItemStockCount();
    }

    /**
     * Method to keep a count of the stock
     */
    private void replaceItemStockCount() {
        DefaultTableModel theProductTable = (DefaultTableModel) productTable.getModel();
        for(int i = 0; i < productTable.getRowCount(); i++) {
            String seller = (String)theProductTable.getValueAt(i, 5);
            Seller theSeller = (Seller)database.retrieve(seller);
            int quantity = (Integer)theProductTable.getValueAt(i, 3);
            String productID = (String)theProductTable.getValueAt(i, 1);

            if(customer.getShoppingCart().search(productID)) {
                stock.set(i, quantity -
                        customer.getShoppingCart().getProduct(productID).getQuantity());
            }
            else {
                stock.set(i, quantity);
            }
        }
    }

    /**
     * Method to update the cart total on the cart total text panel on the frame
     */
    private void loadCartTotal() {
        DecimalFormat df = new DecimalFormat("#.##");
        cartTotalText.setText("$" + df.format(customer.getShoppingCart().calculateShoppingCartTotal()));
    }

    public void productDescriptionAction(MouseEvent e) {

            JTable target = (JTable)e.getSource();
            int row = target.getSelectedRow();
            int column = target.getSelectedColumn();
            Seller theSeller = (Seller)database.retrieve(productTable.getValueAt(productTable.getSelectedRow(), 5).toString());
            String productId = (productTable.getValueAt(productTable.getSelectedRow(), 1).toString());
            String description = theSeller.getList().getProduct(productId).getProductDescription();
            String productName = theSeller.getList().getProduct(productId).getName();
            JOptionPane.showMessageDialog(null, description, productName,JOptionPane.INFORMATION_MESSAGE);
    }
}
