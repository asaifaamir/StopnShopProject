package Controller;

import Model.*;
import View.CustomerPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by asaifbutt on 4/22/17.
 */
public class CheckoutPanelController {

    private Customer customer;
    private AccountList database = AccountList.getInstance();
    private ArrayList<Integer> stock;
    private JTable cartInventory;
    private Session customerSession;
    private JTextField cartTotalText;

    public CheckoutPanelController(JTable inventoryTable, Session currentSession, JTextField cartTotal) {
        cartInventory = inventoryTable;
        customerSession = currentSession;
        customer = (Customer) database.retrieve(currentSession.getUserInSession());
        cartTotalText = cartTotal;
        loadCart();
        resetCheckBoxes();
    }

    public void backButtonActionPerformed(JFrame frame) {
        frame.dispose();
        CustomerPanel backToCustomerPanel = new CustomerPanel(customerSession);
    }

    public void deleteButtonActionPerformed() {

        DefaultTableModel theCartTable = (DefaultTableModel) cartInventory.getModel();

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

    public void completePurchaseButtonActionPerformed() {
        int i = 0;
        DefaultTableModel theCartTable = (DefaultTableModel) cartInventory.getModel();

        if(cartInventory.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Cart is empty.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

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

    public void updateButtonActionPerformed() {
        DefaultTableModel theCartTable = (DefaultTableModel) cartInventory.getModel();
        Seller theSeller; String productID; Product itemPurchased; Integer quantitySold;
        Product productToAdd; Product itemInCart = null; int itemLimit;

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
                theSeller = (Seller)database.retrieve((String)theCartTable.getValueAt(i, 3));
                productID = (String)theCartTable.getValueAt(i, 1);
                itemPurchased = theSeller.getList().getProduct(productID);
                quantitySold = (Integer)theCartTable.getValueAt(i, 5);
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

                stock.set(i, itemLimit);
                theCartTable.setValueAt(itemInCart.getQuantity(), i, 4);
                theCartTable.setValueAt(0, i, 5);
                JOptionPane.showMessageDialog(null, "Quantity added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        loadCartTotal();
    }

    private void resetCheckBoxes() {
        DefaultTableModel cartTable = (DefaultTableModel) cartInventory.getModel();
        for(int i = 0; i < cartTable.getRowCount(); i++) {
            cartTable.setValueAt(false, i, 6);
        }
    }

    private void loadCart() {
        DefaultTableModel cartTable = (DefaultTableModel) cartInventory.getModel();
        ArrayList<Product> customerCart = customer.getShoppingCart().getAllItems();
        for(Product item: customerCart)
        {
            cartTable.addRow(new Object[]{item.getName(), item.getID(), item.getSellingPrice(), item.getSeller(), item.getQuantity(), 0, false});
        }
        loadCartTotal();

        stock = new ArrayList<>();
        for(int i = 0; i < cartTable.getRowCount(); i++) {
            String seller = (String)cartTable.getValueAt(i, 3);
            Seller theSeller = (Seller)database.retrieve(seller);
            String productID = (String)cartTable.getValueAt(i, 1);
            Product theItem = theSeller.getList().getProduct(productID);
            int quantityLeft = theItem.getQuantity() - (Integer)cartTable.getValueAt(i, 4);
            stock.add(quantityLeft);
        }

    }

    private void loadCartTotal() {
        DecimalFormat df = new DecimalFormat("#.##");
        cartTotalText.setText("$" + df.format(customer.getShoppingCart().calculateShoppingCartTotal()));
    }

    private void deleteAllItemsFromCart() {
        DefaultTableModel theCartTable = (DefaultTableModel) cartInventory.getModel();
        customer.getShoppingCart().clear();
        while(!(cartInventory.getRowCount() == 0))
            theCartTable.removeRow(0);
    }


}
