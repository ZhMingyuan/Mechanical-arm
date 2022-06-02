package prototype;

import arduino.Arduino;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;


public
class MainInterfaceController {
    @FXML
    private HBox customButtonHBox;

    @FXML
    private AnchorPane TopAnchorPane;

    @FXML
    private Button ButtonStop;

    @FXML
    private AnchorPane MiddleAnchorPane;

    @FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private Button ButtonLaunch;

    @FXML
    private Button ButtonReset;

    @FXML
    private ChoiceBox<String> ModeChoice;

    @FXML
    private ScrollPane CustomScrollPane;

    @FXML
    private HBox ButtonHBox;

    @FXML
    private Label actionGroup5;

    @FXML
    private Label actionGroup6;

    @FXML
    private Label actionGroup7;

    @FXML
    private Label actionGroup8;

    @FXML
    private Label actionGroup1;

    @FXML
    private Label actionGroup2;

    @FXML
    private Label actionGroup3;

    @FXML
    private Label actionGroup4;

    @FXML
    private AnchorPane BottomAnchorPane;

    @FXML
    private Label ModeLabel;

    @FXML
    private Button TopMenuButton;

    @FXML
    private VBox CustomVBox;

    @FXML
    private VBox MainVBox;

    @FXML
    private Label actionGroup10;

    @FXML
    private Label actionGroup11;

    @FXML
    private HBox ModeSelectionHBox;

    @FXML
    private Label actionGroup12;

    @FXML
    private ImageView Icon;

    @FXML
    private Label actionGroup9;

    @FXML
    private Button ButtonTerminate;

    @FXML
    private Button ButtonChoose;

    @FXML
    private HBox CustomButtonHBox;

    @FXML
    private Button ButtonDelete;

    private final static Arduino arduino = new Arduino("COM5", 9600);
    private AtomicReferenceArray<String> arduinoFileList = new AtomicReferenceArray<String>(12);

    // Launch   stop B terminate B reset R
    public void onButtonLaunchClick(ActionEvent actionEvent) throws IOException {//This is send a file to arduino
        arduino.openConnection();

        Task<Void> launch = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                boolean[] arduinoFileListUsed = new boolean[12];
                int countArduinoFileListUsed = 0;
                for (int i = 0; i < arduinoFileList.length(); i++) {
                    if (arduinoFileList.get(i) != null) {
                        arduinoFileListUsed[i] = true;
                        countArduinoFileListUsed++;
                    } else {
                        continue;
                        // arduinoFileListUsed[i] = false;
                    }
                }

                for (int i = 0; i < 11; i++) {
                    if (arduinoFileList.get(i) != null) {
                        switch (arduinoFileList.get(i)) {
                            case "01":
                                actionGroup1.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "02":
                                actionGroup2.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "03":
                                actionGroup3.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "04":
                                actionGroup4.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "05":
                                actionGroup5.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "06":
                                actionGroup6.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "07":
                                actionGroup7.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "08":
                                actionGroup8.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "09":
                                actionGroup9.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "10":
                                actionGroup10.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "11":
                                actionGroup11.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            case "12":
                                actionGroup12.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                break;
                            default:
                                break;
                        }
                    }
                }


                File getDirectory = new File("");
                String dir = null;
                try {
                    dir = String.format("%s\\resource", getDirectory.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Object[] fileList = new Object[countArduinoFileListUsed];
                StringBuffer stringBuffer = new StringBuffer();

                for (int i = 0; i < arduinoFileList.length(); i++) {
                    if (arduinoFileListUsed[i]) {
                        int countFileList = 0;
                        String tempString = arduinoFileList.get(i) + ".txt";
                        arduinoFileList.set(i, null);
                        File tempFile = new File(dir, tempString);
                        fileList[countFileList] = tempFile;

                        try {

                            FileReader fileReader = new FileReader((File) fileList[countFileList]);
                            BufferedReader bufferedReader = new BufferedReader(fileReader);

                            while (true) {
                                String str = bufferedReader.readLine();
                                if (str.compareTo("done") != 0) {
                                    stringBuffer.append(str);
                                } else {
                                    break;
                                }
                            }
                            //fileReader.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        stringBuffer.append("\n");
                        countFileList++;
                    }

                }
                System.out.println(stringBuffer);


                return null;
            }
        };

        new Thread(launch).start();


    }


    public void onButtonStopClick(ActionEvent actionEvent) {
        if (arduino.openConnection() == true) {
            arduino.serialWrite("B");
        } else {
            System.out.println("err");
        }
    }

    public void onButtonTerminateClick(ActionEvent actionEvent) {

    }

    public void onButtonResetClick(ActionEvent actionEvent) {
        if (arduino.openConnection()) {
            arduino.serialWrite("R");
        } else {
            System.out.println("err");
        }
    }


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

    //手动模式 自定义模式
    public void onModeChoice() throws ExecutionException, InterruptedException {
        String str = ModeChoice.getValue();
        arduino.openConnection();
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        AtomicReference<String> labelText = new AtomicReference<>("");

        Task<String[]> getFileList = new Task<String[]>() {
            @Override
            protected String[] call() throws Exception {

                File getDirectory = new File("");
                String dir = null;
                try {
                    dir = String.format("%s\\resource", getDirectory.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                File resource = new File(dir);
                File[] getResourceList = resource.listFiles();//获取文件列表
                String[] resourceList = new String[getResourceList.length];

                if (getResourceList != null && getResourceList.length < 13) {
                    for (int i = 0; i < getResourceList.length; i++) {
                        resourceList[i] = getResourceList[i].getName().replace(".txt", "");
                    }
                } else {
                    for (int i = 0; i < 12; i++) {
                        resourceList[i] = getResourceList[i].getName().replace(".txt", "");
                    }
                }
                return resourceList;
            }
        };

        Thread getFileListThread = new Thread(getFileList);

        if (str.compareTo("手动模式") == 0) {
            CustomScrollPane.visibleProperty().set(false);
            ButtonLaunch.visibleProperty().set(false);
            ButtonStop.visibleProperty().set(false);
            ButtonTerminate.visibleProperty().set(false);
            ButtonTerminate.visibleProperty().set(false);
            ButtonReset.visibleProperty().set(false);
            ButtonChoose.visibleProperty().set(false);
            ButtonDelete.visibleProperty().set(false);
            ModeChoice.addEventHandler(KeyEvent.ANY, keyEventHandler);
        } else if (str.compareTo("自定义模式") == 0) {
            arduino.closeConnection();//启动功能打开串口连接
            CustomScrollPane.visibleProperty().set(true);
            ButtonLaunch.visibleProperty().set(true);
            ButtonStop.visibleProperty().set(true);
            ButtonTerminate.visibleProperty().set(true);
            ButtonTerminate.visibleProperty().set(true);
            ButtonReset.visibleProperty().set(true);
            ButtonChoose.visibleProperty().set(true);
            ButtonDelete.visibleProperty().set(true);
            getFileListThread.start();
            String[] fileList = getFileList.get();

            Platform.runLater(() -> {
                for (int i = 0; i < fileList.length; i++) {
                    switch (i) {
                        case 0:
                            actionGroup1.setText(fileList[i]);
                            actionGroup1.visibleProperty().set(true);
                            actionGroup1.setOnMouseClicked(event -> {
                                actionGroup1.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(0, actionGroup1.getText());
                            });

                            break;
                        case 1:
                            actionGroup2.setText(fileList[i]);
                            actionGroup2.visibleProperty().set(true);
                            actionGroup2.setOnMouseClicked(event -> {
                                actionGroup2.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(1, actionGroup2.getText());
                            });
                            break;
                        case 2:
                            actionGroup3.setText(fileList[i]);
                            actionGroup3.visibleProperty().set(true);
                            actionGroup3.setOnMouseClicked(event -> {
                                actionGroup3.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(2, actionGroup3.getText());
                            });
                            break;
                        case 3:
                            actionGroup4.setText(fileList[i]);
                            actionGroup4.visibleProperty().set(true);
                            actionGroup4.setOnMouseClicked(event -> {
                                actionGroup4.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(3, actionGroup4.getText());
                            });
                            break;
                        case 4:
                            actionGroup5.setText(fileList[i]);
                            actionGroup5.visibleProperty().set(true);
                            actionGroup5.setOnMouseClicked(event -> {
                                actionGroup5.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(4, actionGroup5.getText());
                            });
                            break;
                        case 5:
                            actionGroup6.setText(fileList[i]);
                            actionGroup6.visibleProperty().set(true);
                            actionGroup6.setOnMouseClicked(event -> {
                                actionGroup6.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(5, actionGroup6.getText());
                            });
                            break;
                        case 6:
                            actionGroup7.setText(fileList[i]);
                            actionGroup7.visibleProperty().set(true);
                            actionGroup7.setOnMouseClicked(event -> {
                                actionGroup7.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(6, actionGroup7.getText());
                            });
                            break;
                        case 7:
                            actionGroup8.setText(fileList[i]);
                            actionGroup8.visibleProperty().set(true);
                            actionGroup8.setOnMouseClicked(event -> {
                                actionGroup8.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(7, actionGroup8.getText());
                            });
                            break;
                        case 8:
                            actionGroup9.setText(fileList[i]);
                            actionGroup9.visibleProperty().set(true);
                            actionGroup9.setOnMouseClicked(event -> {
                                actionGroup9.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(8, actionGroup9.getText());
                            });
                            break;
                        case 9:
                            actionGroup10.setText(fileList[i]);
                            actionGroup10.visibleProperty().set(true);
                            actionGroup10.setOnMouseClicked(event -> {
                                actionGroup10.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(9, actionGroup10.getText());
                            });
                            break;
                        case 10:
                            actionGroup11.setText(fileList[i]);
                            actionGroup11.visibleProperty().set(true);
                            actionGroup11.setOnMouseClicked(event -> {
                                actionGroup11.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(10, actionGroup11.getText());
                            });
                            break;
                        case 11:
                            actionGroup12.setText(fileList[i]);
                            actionGroup12.visibleProperty().set(true);
                            actionGroup12.setOnMouseClicked(event -> {
                                actionGroup12.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9999ef"), CornerRadii.EMPTY, null)));
                                arduinoFileList.set(11, actionGroup12.getText());
                            });
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        // 监听选择框！！改变！！
        Platform.runLater(() -> {
            ModeChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {
                    ModeChoice.removeEventHandler(KeyEvent.ANY, keyEventHandler);
                    //模式改变
                }
            });
        });
    }

    public void onButtonChoose(ActionEvent actionEvent) {
        Task<Void> change = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                String[] fileChosenList = new String[12];

                File getDirectory = new File("");
                String dir = null;
                try {
                    dir = String.format("%s\\resource", getDirectory.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String finalDir = dir;

                Platform.runLater(() -> {
                    for (int i = 0; i < arduinoFileList.length(); i++) {
                        fileChosenList[i] = arduinoFileList.get(i);
                    }

                    int listTime = fileChosenList.length;

                    for (int i = 0; i < listTime; i++) {
                        if (fileChosenList[i] != null) {

                            try {
                                Desktop.getDesktop().open(new File(finalDir + "\\" + fileChosenList[i] + ".txt"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            switch (fileChosenList[i]) {
                                case "01":
                                    actionGroup1.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "02":
                                    actionGroup2.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "03":
                                    actionGroup3.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "04":
                                    actionGroup4.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "05":
                                    actionGroup5.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "06":
                                    actionGroup6.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "07":
                                    actionGroup7.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "08":
                                    actionGroup8.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "09":
                                    actionGroup9.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "10":
                                    actionGroup10.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "11":
                                    actionGroup11.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                case "12":
                                    actionGroup12.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f4f4f4"), CornerRadii.EMPTY, null)));
                                    break;
                                default:
                                    break;
                            }
                            fileChosenList[i] = null;
                            arduinoFileList.set(i, null);
                        }
                    }
                });
                return null;
            }
        };
        new Thread(change).start();
    }

    //键盘监听
    public
    class KeyEventHandler implements EventHandler<KeyEvent> {
        int lastKeyType = 0;//0释放 1按下 默认释放状态

        public KeyEventHandler() {
        }

        @Override
        public void handle(KeyEvent KeyEvent) {
            // 逻辑代码
            int key = KeyEvent.getCode().getCode();
            if (KeyEvent.getEventType() == javafx.scene.input.KeyEvent.KEY_PRESSED
                    && lastKeyType == 0) {//监听到键盘按下 且上一个为释放
                new Thread(() -> {
                    SwitchKey(key);
                }, "Key").start();
                lastKeyType = 1;//保存当前键盘状态
            } else if (KeyEvent.getEventType() == javafx.scene.input.KeyEvent.KEY_RELEASED
                    && lastKeyType == 1) {//监听到键盘释放 且上一个为按下
                //this is send end to arduino
                System.out.println("STOP");
                arduino.serialWrite("B");//暂停功能的代码
                lastKeyType = 0;//保存当前键盘状态
            }
        }

        public void SwitchKey(int key) {
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




