package Controller;

import Model.AccountList;
import Model.Session;
import View.LoginPanel;
import View.SellerInfo;

import javax.swing.*;

/**
 * Created by asaifbutt on 4/20/17.
 */
public class SellerPanelController {

    private JTable inventory;
    private JTextField productID;
    private JTextField invoicePrice;
    private JTextArea description;
    private JTextField sellingPrice;
    private JTextField quantity;
    private JTextField productName;
    private JTextField productType;

    private Session currentSession;
    private AccountList database = AccountList.getInstance();


    public SellerPanelController(Session userSession, JTable inventoryTable, JTextField productIDField, JTextField invoicePriceField, JTextArea descriptionField, JTextField sellingPriceField,
                                 JTextField quantityField, JTextField productNameField, JTextField productTypeField)
    {
        inventory = inventoryTable;
        productID = productIDField;
        invoicePrice = invoicePriceField;
        description = descriptionField;
        sellingPrice = sellingPriceField;
        quantity = quantityField;
        productName = productNameField;
        productType = productTypeField;
        currentSession = userSession;
    }

    public void logOutButtonActionPerformed(JFrame frame) {
        frame.dispose();
        LoginPanel backToLogin = new LoginPanel();
    }

    public void myAccountButtonActionPerformed(JFrame frame) {
        frame.dispose();
        SellerInfo sellerInfoPanel = new SellerInfo(currentSession);
    }
}
