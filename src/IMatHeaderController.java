import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

public class IMatHeaderController {
    @FXML AnchorPane Header;
    @FXML Label iMatLogga;
    @FXML Button varukorgButton;

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

}
