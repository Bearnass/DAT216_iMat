import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;

public class MenuBarController extends AnchorPane {
    @FXML ToggleGroup menuBar;
    @FXML ToggleButton menuBarHomeToggleButton;
    @FXML ToggleButton menuBarSaleToggleButton;
    @FXML ToggleButton menuBarFavouritesToggleButton;
    @FXML ToggleButton menuBarPurchasesToggleButton;
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
        System.out.println("/////");
        System.out.println(menuBar.getSelectedToggle());
    }

    @FXML
    public void showHomepage() {

    }
    @FXML
    public void showFavouritesPage(){}
    @FXML
    public void showPreviousPurchasesPage(){}
    @FXML
    public void logIn(){}
}
