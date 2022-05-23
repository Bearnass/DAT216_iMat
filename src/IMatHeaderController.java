import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.*;
import java.io.IOException;

public class IMatHeaderController {
    @FXML AnchorPane Header;
    @FXML Label iMatLogga;
    @FXML Button varukorgButton;
    @FXML AnchorPane ShoppingCart;
    private iMatMiniController iMatController;

    public IMatHeaderController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Header.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void displayCart(){
        iMatController.showCart();
    }

    @FXML
    void showShoppingCart(){
        ShoppingCart.setVisible(true);
        ShoppingCart.toFront();
    }

}
