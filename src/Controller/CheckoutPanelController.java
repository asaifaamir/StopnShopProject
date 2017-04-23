package Controller;

import Model.*;
import View.CustomerPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is the controller for CheckoutPanel view. It handles the action listeners from the view and interacts with the AccountList database and ProductList.
 * Created by asaifbutt on 4/22/17.
 */
public class CheckoutPanelController {

    private AccountList database = AccountList.getInstance();
    private Customer customer;
    private LinkedList<Integer> stock;
    private JTable cartInventory;
    private Session currentSession;
    private JTextField cartTotalText;
    private DefaultTableModel theCartTable;

    /**
     * A constructor called when CheckoutPanelController object is created
     * @param inventoryTable JTable object to display products added to cart by customer
     * @param session current user in session
     * @param cartTotal JTextField object to display the cart total
     */
    public CheckoutPanelController(JTable inventoryTable, Session session, JTextField cartTotal) {
        cartInventory = inventoryTable;
        currentSession = session;
        customer = (Customer) database.retrieve(currentSession.getUserInSession());
        cartTotalText = cartTotal;
        theCartTable = (DefaultTableModel) cartInventory.getModel();
        loadCart();
        resetCheckBoxes();
    }

    /**
     * Back Action for the CheckoutPanel View which reacts to Back Button
     * @param frame JFrame object to dispose the current frame
     */
    public void backButtonActionPerformed(JFrame frame) {
        frame.dispose();
        CustomerPanel backToCustomerPanel = new CustomerPanel(currentSession);
    }

    /**
     * Delete Action for the CheckoutPanel View which interacts the JTable and deletes the item from the cart on delete button press
     */
    public void deleteButtonActionPerformed() {

        for(int i = 0; i < cartInventory.getRowCount(); i++) {
            boolean isChecked = (boolean)cartInventory.getValueAt(i, 6);
            if(isChecked) {
                break;
            }
            else if(i == cartInventory.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "No items are selected to remove", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        for(int i = 0; i < cartInventory.getRowCount(); i++) {
            boolean isChecked = (boolean)cartInventory.getValueAt(i, 6);

            if(isChecked) {
                String productID = (String)theCartTable.getValueAt(i, 1);
                customer.getShoppingCart().remove(productID);
                theCartTable.removeRow(i);
                i--;
            }
        }

        loadCartTotal();

    }

    /**
     *Complete Purchase Action for the CheckoutPanel View. It completes a transaction when the customer click complete purchase button.
     */
    public void completePurchaseButtonActionPerformed() {

        //Checks if the cart is empty
        if(cartInventory.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Cart is empty.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Goes through the items in the shopping cart and makes changes to the seller database in the Model.
        int i = 0;
        for(Product item : customer.getShoppingCart().getAllItems()) {
            Seller theSeller = (Seller)database.retrieve((String)theCartTable.getValueAt(i, 3));
            String productID = (String)theCartTable.getValueAt(i, 1);
            Product product = theSeller.getList().getProduct(productID);
            double revenueMade = item.getQuantity() * item.getSellingPrice();
            theSeller.calculateRevenue(revenueMade);
            product.setQuantity(product.getQuantity() - item.getQuantity());
            i++;
        }

        JOptionPane.showMessageDialog(null, "Thank you for shopping with us :)", "Success", JOptionPane.INFORMATION_MESSAGE);
        deleteAllItemsFromCart();
        loadCartTotal();
    }

    /**
     * Update Action for the CheckoutPanel View. It makes updates(if any) to the quantity of the item in the cart
     */
    public void updateButtonActionPerformed() {

        Seller seller;
        String productID;
        Product product;
        Integer updatedQuantity;
        Product itemInCart = null;
        int itemLimit;

        if(cartInventory.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "There are no items", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for(int i = 0; i < cartInventory.getRowCount(); i++) {
            boolean isChecked = (boolean)cartInventory.getValueAt(i, 6);
            if(isChecked) {
                break;
            }
            else if(i == cartInventory.getRowCount() - 1) {
                JOptionPane.showMessageDialog(null, "No items are selected in the cart", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        for(int i = 0; i < cartInventory.getRowCount(); i++) {
            boolean isChecked = (boolean)cartInventory.getValueAt(i, 6);

            if(isChecked) {
                seller = (Seller)database.retrieve((String)theCartTable.getValueAt(i, 3));
                productID = (String)theCartTable.getValueAt(i, 1);
                product = seller.getList().getProduct(productID);
                updatedQuantity = (Integer)theCartTable.getValueAt(i, 5);
                itemLimit = stock.get(i);

                if(updatedQuantity > product.getQuantity()) {
                    JOptionPane.showMessageDialog(null, "Cannot purchase more than whats in stock.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(updatedQuantity > itemLimit) {
                    JOptionPane.showMessageDialog(null, "Cannot purchase more than whats in stock.", "Error", JOptionPane.WARNING_MESSAGE);

                    if(itemLimit == 0) {
                        JOptionPane.showMessageDialog(null, "Purchase limit has been reached for this item.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }

                if(updatedQuantity <= 0) {
                    if(updatedQuantity == 0)
                        JOptionPane.showMessageDialog(null, "You have no specified a quantity", "Error", JOptionPane.WARNING_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "You cannot purchase negative amounts", "Error", JOptionPane.WARNING_MESSAGE);

                    return;
                }

                if(customer.getShoppingCart().search(productID)) {
                    itemInCart = customer.getShoppingCart().getProductInstance(productID);
                    itemInCart.setQuantity(itemInCart.getQuantity() + updatedQuantity);
                    itemLimit -= updatedQuantity;
                }

                stock.set(i, itemLimit);
                theCartTable.setValueAt(itemInCart.getQuantity(), i, 4);
                theCartTable.setValueAt(0, i, 5);
                JOptionPane.showMessageDialog(null, "Quantity added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        loadCartTotal();
    }

    /**
     * Method to reset checkboxes in the JTable
     */
    private void resetCheckBoxes() {
        DefaultTableModel cartTable = (DefaultTableModel) cartInventory.getModel();
        for(int i = 0; i < cartTable.getRowCount(); i++) {
            cartTable.setValueAt(false, i, 6);
        }
    }

    /**
     * Method to load the cart with the products added in the cart
     */
    private void loadCart() {
        DefaultTableModel cartTable = (DefaultTableModel) cartInventory.getModel();
        ArrayList<Product> customerCart = customer.getShoppingCart().getAllItems();
        for(Product item: customerCart)
        {
            cartTable.addRow(new Object[]{item.getName(), item.getID(), item.getSellingPrice(), item.getSeller(), item.getQuantity(), 0, false});
        }
        loadCartTotal();

        stock = new LinkedList<>();
        for(int i = 0; i < cartTable.getRowCount(); i++) {
            String seller = (String)cartTable.getValueAt(i, 3);
            Seller theSeller = (Seller)database.retrieve(seller);
            String productID = (String)cartTable.getValueAt(i, 1);
            Product theItem = theSeller.getList().getProduct(productID);
            int quantityLeft = theItem.getQuantity() - (Integer)cartTable.getValueAt(i, 4);
            stock.add(quantityLeft);
        }

    }

    /**
     * Method to set the JTextField for cart total
     */
    private void loadCartTotal() {
        DecimalFormat df = new DecimalFormat("#.##");
        cartTotalText.setText("$" + df.format(customer.getShoppingCart().calculateShoppingCartTotal()));
    }

    /**
     * Method to clear the cart
     */
    private void deleteAllItemsFromCart() {
        DefaultTableModel theCartTable = (DefaultTableModel) cartInventory.getModel();
        customer.getShoppingCart().clear();
        while(!(cartInventory.getRowCount() == 0))
            theCartTable.removeRow(0);
    }


}
