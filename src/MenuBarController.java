import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuBarController extends AnchorPane {
    @FXML
    Button menuBarHomeButton;
    @FXML Button menuBarSaleButton;
    @FXML Button menuBarFavouriteButton;
    @FXML Button menuBarPurchaseButton;
    @FXML Button menuBarServiceButton;
    @FXML Button menuBarAccountButton;

    public MenuBarController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void showHomepage(){}
    @FXML
    public void showOffers(){}
    @FXML
    public void showFavourites(){}
    @FXML
    public void showPreviousPurchases(){}
    @FXML
    public void showCustomerService(){}
    @FXML
    public void logIn(){}
}
