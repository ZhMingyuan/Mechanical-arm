package prototype;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainInterfaceController {

    @FXML
    private AnchorPane TopAnchorPane;

    @FXML
    private Button ButtonStop;

    @FXML
    private AnchorPane MiddleAnchorPane;

    @FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private AnchorPane BottomAnchorPane;

    @FXML
    private Label ModeLabel;

    @FXML
    private Button ButtonLaunch;

    @FXML
    private Button TopMenuButton;

    @FXML
    private VBox CustomVBox;

    @FXML
    private Button ButtonReset;

    @FXML
    private VBox MainVBox;

    @FXML
    private ChoiceBox<String> ModeChoice;

    @FXML
    private HBox ModeSelectionHBox;

    @FXML
    private ScrollPane CustomScrollPane;

    @FXML
    private ImageView Icon;

    @FXML
    private Button ButtonTerminate;

    @FXML
    private HBox ButtonHBox;


    public void onTopMenuButtonClick() {
        try {
            Parent menuButtonLoader = FXMLLoader.load(getClass().getResource("parameter-menu.fxml"));
            Stage menuInterface = new Stage();
            menuInterface.setScene(new Scene(menuButtonLoader));
            menuInterface.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
