import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;

import javafx.scene.control.Label;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class ItemCardController extends AnchorPane implements ShoppingCartListener {
    @FXML Label itemCardPriceLabel;
    @FXML Label itemCardNameLabel;
    @FXML Label itemCardBrandLabel;
    @FXML Label itemCardWeightLabel;
    @FXML Label itemCardReferencePriceLabel;
    @FXML ImageView itemCardImageImageView;
    @FXML ImageView HeartView;
    @FXML Button reduceWareButton;
    @FXML Button increaseWareButton;
    @FXML TextField amountWareField;
    @FXML Button addWareButton;

    private Image filledHeart = new Image("imgs/icons8-basket-30.png");
    private Image unfilledHeart = new Image("imgs/icons8-basket-30.png");

    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;
    private ShoppingItem shoppingItem;
    private Model model = Model.getInstance();
    public ItemCardController(Product product, iMatMiniController parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.shoppingItem = new ShoppingItem(product, 0);
        itemCardNameLabel.setText(product.getName());
        itemCardPriceLabel.setText(String.format("%.2f", product.getPrice()) + " kr");
        itemCardReferencePriceLabel.setText(product.getUnit());
        itemCardImageImageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
        if (!product.isEcological()) {
            itemCardBrandLabel.setText("");
        }

        model.getShoppingCart().addShoppingCartListener(this);
    }

    @FXML
    public void setAmountWareField(){
        amountWareField.setText((int) shoppingItem.getAmount() + "");
    }

    @FXML
    public void toggleHeart(){
        if (HeartView.getImage() == filledHeart){
            HeartView.setImage(unfilledHeart);

        }else {
            HeartView.setImage(filledHeart);
        }
    }
    @FXML
    public void addFirstWare(){
        increaseWares();
        addWareButton.setVisible(false);
    }

    @FXML
    public void increaseWares(){
        shoppingItem.setAmount((int) shoppingItem.getAmount() + 1);
        setAmountWareField();

        if (shoppingItem.getAmount() < 2){
            model.getShoppingCart().addItem(shoppingItem);
        }
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }

    @FXML
    public void reduceWares(){
        if (shoppingItem.getAmount() > 0){
            shoppingItem.setAmount((int) shoppingItem.getAmount() - 1);
        }
        if (shoppingItem.getAmount() == 0){
            model.getShoppingCart().removeItem(shoppingItem);
        }
        setAmountWareField();
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }

    @FXML
    public void changeAmount(){
        if (Integer.parseInt(amountWareField.getText()) >= 0){
            int changeAmount = Integer.parseInt(amountWareField.getText());
            System.out.println(changeAmount);
            shoppingItem.setAmount(changeAmount);
            if (!model.getShoppingCart().getItems().contains(shoppingItem)){
                model.getShoppingCart().addItem(shoppingItem);
            }
            else if (changeAmount == 0){
                model.getShoppingCart().removeItem(shoppingItem);
            }
            model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        }else {
            amountWareField.setText("0");
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        amountWareField.setText((int)shoppingItem.getAmount() + "");
    }
}
