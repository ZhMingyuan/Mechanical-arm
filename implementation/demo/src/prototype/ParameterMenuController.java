package prototype;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

public class ParameterMenuController {

    @FXML
    private Button FileButton;

    @FXML
    private Button ThreeDButton;

    @FXML
    private Button ParameterButton;

    @FXML
    private VBox MenuVBox;

    @FXML
    private AnchorPane MenuAnchorPane;

    public void onFileButton() {
        File directory = new File("");
        try {
            String cmdDir[] = { "explorer.exe", directory.getCanonicalPath() };
            Runtime.getRuntime().exec(cmdDir);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
