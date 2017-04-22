package Controller;

import Model.*;
import View.CheckoutPanel;
import View.CustomerInformationPanel;
import View.LoginPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by asaifbutt on 4/18/17.
 */
public class CustomerPanelController {

    AccountList database = AccountList.getInstance();
    private JTable productTable;
    private JTextArea cartTotalText;
    Customer customer;
    Session session;
    ArrayList<Integer> stockCount;

    /**
     * Creates a CustomerPanelController object
     * @param table1 the product table to display products
     * @param cartTotal cartTotal text aread to display the cart total
     */
    public CustomerPanelController(JTable table1, JTextArea cartTotal, Session userSession) {
        customer = (Customer) database.retrieve(userSession.getUserInSession());
        productTable = table1;
        cartTotalText = cartTotal;
        session = userSession;
        loadInventoryFromAllSellers();
        loadCartTotal();

    }

    /**
     * My Account Action for the CutomerPanel View which reacts to MyAccount Button
     * @param frame the frame that the action is being performed on
     */
    public void myAccountButtonActionPerformed(JFrame frame) {
        frame.dispose();
        CustomerInformationPanel customerInfo = new CustomerInformationPanel(session);
    }

    /**
     * Log Out Action for the CutomerPanel View which reacts to LogOutButton
     * @param frame the frame that the action is being performed on
     */
    public void logOutButtonActionPerformed(JFrame frame) {
        frame.dispose();
        LoginPanel backToLogin = new LoginPanel();
    }

    /**
     * View Cart Action for the CutomerPanel View which reacts to ViewCartButton
     * @param frame the frame that the action is being performed on
     */
    public void viewCartButtonActionPerformed(JFrame frame) {
        frame.dispose();
        CheckoutPanel checkoutPanel = new CheckoutPanel(session);
    }

    /**
     * Clear cart action for the CutomerPanel View which reacts to ClearCartButton
     */
    public void clearCartButtonActionPerformed() {
        customer.getShoppingCart().clear();
        DecimalFormat df = new DecimalFormat("#.##");
        cartTotalText.setText("$" + df.format(customer.getShoppingCart().calculateShoppingCartTotal()));
        resetCheckBoxes();

        stockCount.clear();
        ArrayList<Seller> sellerList = database.getAllSellers();
        ArrayList<Product> allItems = null;

        // adds back original stock quantites to array
        for(Seller seller : sellerList) {
            allItems = seller.getList().getAllItems();
            for(Product item : allItems) {
                stockCount.add(item.getQuantity());
            }
        }
    }

    /**
     * Add to Cart action for the CutomerPanel View which reacts to the Add to Cart Button
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
            boolean isChecked = (boolean)productTable.getValueAt(i, 6);
            if(isChecked) {
                break;
            }
            else if(i == productTable.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "No items are selected for purchase", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        for(int i = 0; i < productTable.getRowCount(); i++) {
            boolean isChecked = (boolean)productTable.getValueAt(i, 6);

            if(isChecked) {
                theSeller = (Seller)database.retrieve((String)productTableModel.getValueAt(i, 5));
                productID = (String)productTableModel.getValueAt(i, 1);
                itemPurchased = theSeller.getList().getProduct(productID);
                quantitySold = (Integer)productTableModel.getValueAt(i, 4);
                itemLimit = stockCount.get(i);

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

                stockCount.set(i, itemLimit);
                productTableModel.setValueAt(0, i, 4);

            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        cartTotalText.setText("$" + df.format(customer.getShoppingCart().calculateShoppingCartTotal()));
        resetCheckBoxes();

        JOptionPane.showMessageDialog(null, "Items added to Cart!", "Success", JOptionPane.INFORMATION_MESSAGE);

        System.out.println(session);
    }

    private void resetCheckBoxes() {
        DefaultTableModel theProductTable = (DefaultTableModel) productTable.getModel();
        for(int i = 0; i < productTable.getRowCount(); i++) {
            theProductTable.setValueAt(false, i, 6);
        }
    }

    private void loadInventoryFromAllSellers() {
        // this one is essentially pretty self explanatory. Each seller in the database and their
        // inventory is access and uploaded t othe jtable.
        DefaultTableModel theProductTable = (DefaultTableModel) productTable.getModel();
        ArrayList<Seller> sellerList = database.getAllSellers();
        ArrayList<Product> allItems = null;
        stockCount = new ArrayList<>();

        for(Seller seller : sellerList) {
            allItems = seller.getList().getAllItems();
            for(Product item : allItems) {
                theProductTable.addRow(new Object[]{item.getName(), item.getID(), item.getSellingPrice(), item.getQuantity(),
                        0, seller.getUserName(), false});

                stockCount.add(item.getQuantity());
            }
        }

        replaceItemStockCount();
    }

    private void replaceItemStockCount() {
        DefaultTableModel theProductTable = (DefaultTableModel) productTable.getModel();
        for(int i = 0; i < productTable.getRowCount(); i++) {
            String seller = (String)theProductTable.getValueAt(i, 5);
            Seller theSeller = (Seller)database.retrieve(seller);
            int quantity = (Integer)theProductTable.getValueAt(i, 3);
            String productID = (String)theProductTable.getValueAt(i, 1);

            if(customer.getShoppingCart().search(productID)) {
                stockCount.set(i, quantity -
                        customer.getShoppingCart().getProduct(productID).getQuantity());
            }
            else {
                stockCount.set(i, quantity);
            }
        }
    }

    private void loadCartTotal() {
        DecimalFormat df = new DecimalFormat("#.##");
        cartTotalText.setText("$" + df.format(customer.getShoppingCart().calculateShoppingCartTotal()));
    }
}
