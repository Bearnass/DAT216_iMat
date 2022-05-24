import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class ShoppingCartController extends AnchorPane implements ShoppingCartListener {
    @FXML AnchorPane ShoppingCart;
    @FXML Button shoppingCartExitButton;
    @FXML ScrollPane cartScrollPane;
    @FXML FlowPane cartFlowPane;
    @FXML Label shoppingCartTotalLabel;
    @FXML Button shoppingCartCheckOutButton;

    private ShoppingCart shoppingCart;
    private iMatMiniController parentController;

    public ShoppingCartController(ShoppingCart shoppingCart, iMatMiniController iMatMiniController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.shoppingCart = shoppingCart;
        parentController = iMatMiniController;
        cartFlowPane.getChildren().clear();
        shoppingCartTotalLabel.setText("Totalt: " + (int)shoppingCart.getTotal() + "");
        shoppingCart.addShoppingCartListener(this);
        populateCart();
    }

    private void populateCart(){
        List<ShoppingItem>shoppingItems = shoppingCart.getItems();
        cartFlowPane.setVgap(20);
        for (ShoppingItem shoppingItem : shoppingItems){
            cartFlowPane.getChildren().add(new ShoppingCartItemController(shoppingItem));
        }
    }

    public void hideShoppingCart(){
        parentController.hideCart();
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        cartFlowPane.getChildren().clear();
        List<ShoppingItem>shoppingItems = shoppingCart.getItems();
        for (ShoppingItem shoppingItem : shoppingItems){
            cartFlowPane.getChildren().add(new ShoppingCartItemController(shoppingItem));
        }
        shoppingCartTotalLabel.setText(shoppingCart.getTotal() + "");
    }
}
