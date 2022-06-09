package prototype;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
            String cmdDir[] = {"explorer.exe", directory.getCanonicalPath() + "\\resource"};
            Runtime.getRuntime().exec(cmdDir);
            Stage menuInterface = (Stage) MenuAnchorPane.getScene().getWindow();
            menuInterface.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onParameterButton(ActionEvent actionEvent) throws IOException {
        try {
            Parent parameterInterfaceLoader = FXMLLoader.load(ParameterMenuController.class.getResource("parameter-interface.fxml"));
            Stage parameterInterface = new Stage();
            Stage menuInterface = (Stage) MenuAnchorPane.getScene().getWindow();
            parameterInterface.setScene(new Scene(parameterInterfaceLoader));
            parameterInterface.setOnShown(event -> {
                menuInterface.close();
            });
            parameterInterface.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
