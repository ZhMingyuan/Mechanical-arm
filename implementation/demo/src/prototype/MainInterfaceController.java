package prototype;

import arduino.Arduino;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public
class MainInterfaceController {

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

    final static Arduino arduino = new Arduino("COM5", 9600);

    // Launch   stop B terminate B reset R
    public
    void onButtonLaunchClick(ActionEvent actionEvent) throws IOException {//This is send a file to arduino
        arduino.openConnection();

        Platform.runLater(() -> {
            File file = new File("E:\\CGT\\COMP 3060 3070\\test\\demoTwo", "test1.txt");
            System.out.println(file);

            int count = 0;
            int sum = 0;

            try {
                FileReader fin = new FileReader(file);
                Scanner src = new Scanner(fin);
                // Read and sum numbers.
                while (src.hasNext()) {
                    if (src.hasNextDouble()) {
                        sum += src.nextInt();
                        count++;
                    } else {
                        String str = src.next();
                        if (str.equals("done"))
                            break;
                        else {
                            System.out.println("File format error.");
                            return;
                        }
                    }
                }
                src.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println(sum);

        });

        //  new Thread(() -> {}).start();


    }

    public
    void onButtonStopClick(ActionEvent actionEvent) {
        if (arduino.openConnection() == true) {
            arduino.serialWrite("B");
        } else {
            System.out.println("err");
        }
    }

    public
    void onButtonTerminateClick(ActionEvent actionEvent) {

    }

    public
    void onButtonResetClick(ActionEvent actionEvent) {
        if (arduino.openConnection() == true) {
            arduino.serialWrite("R");
        } else {
            System.out.println("err");
        }
    }


    public
    void onTopMenuButtonClick() {
        try {
            Parent menuButtonLoader = FXMLLoader.load(getClass().getResource("parameter-menu.fxml"));
            Stage menuInterface = new Stage();
            menuInterface.setScene(new Scene(menuButtonLoader));
            menuInterface.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //手动模式 自定义模式
    public
    void onModeChoice() {
        String str = ModeChoice.getValue();
        arduino.openConnection();
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        if (str.compareTo("手动模式") == 0) {

            Platform.runLater(() -> {
                ModeChoice.addEventHandler(KeyEvent.ANY, keyEventHandler);
            });

        } else if (str.compareTo("自定义模式") == 0) {
            arduino.closeConnection();//启动功能打开串口连接

        }
        // 监听选择框！！改变！！
        ModeChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public
            void changed(ObservableValue ov, Number value, Number new_value) {

                ModeChoice.removeEventHandler(KeyEvent.ANY, keyEventHandler);
                //模式改变
            }
        });

    }

    //键盘监听
    public
    class KeyEventHandler implements EventHandler<KeyEvent> {
        int lastKeyType = 0;//0释放 1按下 默认释放状态

        public
        KeyEventHandler() {
        }

        @Override
        public
        void handle(KeyEvent KeyEvent) {
            // 逻辑代码
            int key = KeyEvent.getCode().getCode();
            if (KeyEvent.getEventType() == javafx.scene.input.KeyEvent.KEY_PRESSED
                    && lastKeyType == 0) {//监听到键盘按下 且上一个为释放
                new Thread(() -> {
                    SwitchKey(key);
                },"Key").start();
                lastKeyType = 1;//保存当前键盘状态
            } else if (KeyEvent.getEventType() == javafx.scene.input.KeyEvent.KEY_RELEASED
                    && lastKeyType == 1) {//监听到键盘释放 且上一个为按下
                //this is send end to arduino
                System.out.println("STOP");
                arduino.serialWrite("B");//暂停功能的代码
                lastKeyType = 0;//保存当前键盘状态

            }


        }

        public
        void SwitchKey(int key) {
            try {
                switch (key) {
                    case 65://A
                        System.out.println("A-- ");
                        arduino.serialWrite("A");
                        break;
                    case 68://D
                        System.out.print("D--");
                        arduino.serialWrite("D");
                        break;
                    case 83://S
                        System.out.print("S--");
                        arduino.serialWrite("S");
                        break;
                    case 87://W
                        System.out.print("W--");
                        arduino.serialWrite("W");
                        break;
                    case 81://Q
                        System.out.print("Q--");
                        arduino.serialWrite("Q");
                        break;
                    case 69://D
                        System.out.print("E--");
                        arduino.serialWrite("E");
                        break;
                    case 73://I
                        System.out.print("I--");
                        arduino.serialWrite("I");
                        break;
                    case 75://K
                        System.out.print("K--");
                        arduino.serialWrite("K");
                        break;
                    case 74://J
                        System.out.print("J--");
                        arduino.serialWrite("J");
                        break;
                    case 76://L
                        System.out.print("L--");
                        arduino.serialWrite("L");
                        break;
                    case 85://U
                        System.out.print("U--");
                        arduino.serialWrite("U");
                        break;
                    case 79://O
                        System.out.print("O--");
                        arduino.serialWrite("O");
                        break;
                    default:
                        //
                        break;
 /*01000010	66	42	B
    01000011	67	43	C
    01000111	71	47	G
    01001000	72	48	H
    01001101	77	4D	M
    01001110	78	4E	N
    01010000	80	50	P
    01010011	83	53	S
    01010100	84	54	T
    01010110	86	56	V
    01011000	88	58	X
    01011010	90	5A	Z  */
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }

        }
    }


}




