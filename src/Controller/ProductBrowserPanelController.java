package Controller;

import Model.AccountList;
import Model.Session;
import View.CustomerInformationPanel;
import View.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by asaifbutt on 4/18/17.
 */
public class ProductBrowserPanelController {

    AccountList database = AccountList.getInstance();
    private JTable productTable;
    private JTextArea cartTotalText;
    Session currentUser;

    /**
     * Creates a ProductBrowserPanelController object
     * @param table1 the product table to display products
     * @param cartTotal cartTotal text aread to display the cart total
     */
    public ProductBrowserPanelController(JTable table1, JTextArea cartTotal, Session userSession) {
        productTable = table1;
        cartTotalText = cartTotal;
        currentUser = userSession;
    }

    /**
     * My Account Action for the ProductBrowserPanel View which reacts to MyAccount Button
     * @param e an ActionEvent object
     * @param frame the frame that the action is being performed on
     */
    public void myAccountButtonActionPerformed(ActionEvent e, JFrame frame) {
        frame.dispose();
        CustomerInformationPanel customerInfo = new CustomerInformationPanel(currentUser);
    }

    /**
     * Log Out Action for the ProductBrowserPanel View which reacts to LogOutButton
     * @param e an ActionEvent object
     * @param frame the frame that the action is being performed on
     */
    public void logOutButtonActionPerformed(ActionEvent e, JFrame frame) {
        frame.dispose();
        LoginPanel backToLogin = new LoginPanel();
    }

    /**
     * View Cart Action for the ProductBrowserPanel View which reacts to ViewCartButton
     * @param e an ActionEvent object
     * @param frame the frame that the action is being performed on
     */
    public void viewCartButtonActionPerformed(ActionEvent e, JFrame frame) {
    }

    /**
     * Clear cart action for the ProductBrowserPanel View which reacts to ClearCartButton
     * @param e an ActionEvent object
     * @param frame the frame that the action is being performed on
     */
    public void clearCartButtonActionPerformed(ActionEvent e, JFrame frame) {
    }

    /**
     * Add to Cart action for the ProductBrowserPanel View which reacts to the Add to Cart Button
     * @param e an ActionEvent object
     * @param frame the frame that the action is being performed on
     */
    public void addToCartButtonActionPerformed(ActionEvent e, JFrame frame) {
    }
}
