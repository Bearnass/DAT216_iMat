import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShoppingCartItemController extends Node{
    @FXML Label shoppingCartItemNameLabel;
    @FXML Label shoppingCartItemBrandLabel;
    @FXML Label shoppingCartItemWeightLabel;
    @FXML Label CartItemCardPriceLabel;
    @FXML ImageView shoppingCartItemImageView;
    @FXML Button reduceWareButton;
    @FXML Button increaseWareButton;
    @FXML TextField CartAmountWareField;
    private ShoppingItem shoppingItem;
    private Model model;
    public ShoppingCartItemController(ShoppingItem item){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        shoppingItem = item;
        shoppingCartItemNameLabel.setText(item.getProduct().getName());
        shoppingCartItemImageView.setImage(model.getImage(item.getProduct()));
        CartItemCardPriceLabel.setText(String.format("%.2f kr", shoppingItem.getTotal()));
        CartAmountWareField.setText((int) item.getAmount()+"");
    }
}
