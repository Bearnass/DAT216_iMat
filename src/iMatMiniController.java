/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Flow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;


/**
 *
 * @author oloft
 */
public class iMatMiniController implements Initializable, ShoppingCartListener {
    HashMap<String, ItemCardController> itemCardController = new HashMap<>();

    @FXML FlowPane changeFlowPane;
    // Shopping Pane
    @FXML
    private AnchorPane shopPane;
    @FXML
    private TextField searchBar;
    @FXML
    private Label itemsLabel;
    @FXML
    private Label costLabel;
    @FXML
    private FlowPane productsFlowPane;
    @FXML Label CartTotalLabel;

    
    // Account Pane
    @FXML
    private AnchorPane accountPane;
    @FXML 
    ComboBox cardTypeCombo;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField nameTextField;
    @FXML 
    private ComboBox monthCombo;
    @FXML
    private ComboBox yearCombo;
    @FXML
    private TextField cvcField;
    @FXML
    private Label purchasesLabel;

    @FXML Button VarukorgButton;
    @FXML AnchorPane iMatMain;



    // Other variables
    private final Model model = Model.getInstance();

    // Shop pane actions
    @FXML
    private void handleShowAccountAction(ActionEvent event) {
        openAccountView();
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event) {
        
        List<Product> matches = model.findProducts(searchBar.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    
    @FXML
    private void handleClearCartAction(ActionEvent event) {
        model.clearShoppingCart();
    }
    
    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        model.placeOrder();
        costLabel.setText("Köpet klart!");
    }


    
    // Account pane actions
     @FXML
    private void handleDoneAction(ActionEvent event) {
        closeAccountView();
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model.clearShoppingCart();
        model.getShoppingCart().addShoppingCartListener(this);
        initProducts();
        updateBottomPanel();

        //setupAccountPane();

    }

    void initProducts(){
        productsFlowPane.getChildren().clear();
        productsFlowPane.setHgap(20);
        productsFlowPane.setVgap(20);
        for (Product product : model.getProducts()){
            ItemCardController itemCardController1 = new ItemCardController(product, this);
            itemCardController.put(product.getName(), itemCardController1);
            productsFlowPane.getChildren().add(itemCardController1);
        }
    }
    
    // Navigation
    public void openAccountView() {
        updateAccountPanel();
        accountPane.toFront();
    }

    public void showCart(){
        changeFlowPane.toFront();
        changeFlowPane.getChildren().add(new ShoppingCartController(model.getShoppingCart(), this));
    }

    public void closeAccountView() {
        updateCreditCard();
        shopPane.toFront();
    }
    
    // Shope pane methods
    @Override
     public void shoppingCartChanged(CartEvent evt) {
        updateBottomPanel();
    }
   
    
    private void updateProductList(List<Product> products) {

        productsFlowPane.getChildren().clear();

        for (Product product : products) {
            if(itemCardController.containsKey(product.getName())){
                productsFlowPane.getChildren().add(itemCardController.get(product.getName()));
            }
        }

    }
    
    private void updateBottomPanel() {
        
        ShoppingCart shoppingCart = model.getShoppingCart();
        costLabel.setText(String.format("%.2f",shoppingCart.getTotal()));
        costLabel.setText(String.format("%.2f",shoppingCart.getTotal()));
        
    }
    
    private void updateAccountPanel() {
        
        CreditCard card = model.getCreditCard();
        
        numberTextField.setText(card.getCardNumber());
        nameTextField.setText(card.getHoldersName());
        
        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select(""+card.getValidMonth());
        yearCombo.getSelectionModel().select(""+card.getValidYear());

        cvcField.setText(""+card.getVerificationCode());
        
        purchasesLabel.setText(model.getNumberOfOrders()+ " tidigare inköp hos iMat");
        
    }
    
    private void updateCreditCard() {
        
        CreditCard card = model.getCreditCard();
        
        card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());
        
        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);
        
        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));
        
        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));
        
        card.setVerificationCode(Integer.parseInt(cvcField.getText()));

    }
    
    private void setupAccountPane() {
                
        cardTypeCombo.getItems().addAll(model.getCardTypes());
        
        monthCombo.getItems().addAll(model.getMonths());
        
        yearCombo.getItems().addAll(model.getYears());
        
    }
}
