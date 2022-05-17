import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import javafx.scene.control.Label;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ItemCardController extends AnchorPane {
    @FXML Label itemCardPriceLabel;
    @FXML Label itemCardNameLabel;
    @FXML Label itemCardBrandLabel;
    @FXML Label itemCardWeightLabel;
    @FXML Label itemCardReferencePriceLabel;
    @FXML ImageView itemCardImageImageView;
    @FXML Button reduceWareButton;
    @FXML Button increaseWareButton;
    @FXML TextField amountWareField;
    @FXML Button addWareButton;

    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;
    private Product product;
    private Model model = Model.getInstance();
    public ItemCardController(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        itemCardNameLabel.setText(product.getName());
        itemCardPriceLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        itemCardImageImageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
        if (!product.isEcological()) {
            itemCardBrandLabel.setText("");
        }
    }

    @FXML
    public void setAmountWareField(){
        String tempe = Integer.toString(model.getShoppingCart().getItems().size());
        amountWareField.setText(tempe);
    }

    @FXML
    public void addFirstWare(){
        model.addToShoppingCart(product);
        addWareButton.setVisible(false);
        setAmountWareField();
    }

    @FXML
    public void increaseWares(){
        model.addToShoppingCart(product);
        setAmountWareField();
    }

    @FXML
    public void reduceWares(){
        int temp = model.getShoppingCart().getItems().size();
        model.clearShoppingCart();
        for (int i = 0; i < temp - 1; i++) {
            model.addToShoppingCart(product);
        }
        setAmountWareField();
    }
}
