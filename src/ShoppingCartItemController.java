import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShoppingCartItemController extends AnchorPane {
    @FXML Label shoppingCartItemNameLabel;
    @FXML Label shoppingCartItemBrandLabel;
    @FXML Label shoppingCartItemWeightLabel;
    @FXML Label CartItemCardPriceLabel;
    @FXML ImageView shoppingCartItemImageView;
    @FXML Button reduceWareButton;
    @FXML Button increaseWareButton;
    @FXML TextField CartAmountWareField;
    private ShoppingItem shoppingItem;
    private Model model = Model.getInstance();
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
        shoppingCartItemNameLabel.setText(shoppingItem.getProduct().getName());
        shoppingCartItemBrandLabel.setText("iMat");
        shoppingCartItemWeightLabel.setText(shoppingItem.getProduct().getUnit());
        shoppingCartItemImageView.setImage(model.getImage(shoppingItem.getProduct()));
        CartItemCardPriceLabel.setText(String.format("%.2f kr", shoppingItem.getTotal()));
        CartAmountWareField.setText((int) shoppingItem.getAmount()+"");
    }

    @FXML
    public void cartIncreaseWares(){
        shoppingItem.setAmount((int) shoppingItem.getAmount() + 1);
        setCartAmountWareField();

        if (shoppingItem.getAmount() < 2){
            model.getShoppingCart().addItem(shoppingItem);
        }
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }

    @FXML
    public void setCartAmountWareField(){
        CartAmountWareField.setText((int) shoppingItem.getAmount() + "");
    }
    @FXML
    public void cartReduceWares(){
        if (shoppingItem.getAmount() > 0){
            shoppingItem.setAmount((int) shoppingItem.getAmount() - 1);
        }
        if (shoppingItem.getAmount() == 0){
            model.getShoppingCart().removeItem(shoppingItem);
        }
        setCartAmountWareField();
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }
}
