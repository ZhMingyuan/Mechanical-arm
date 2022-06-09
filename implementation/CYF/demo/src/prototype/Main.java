package prototype;

import arduino.Arduino;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main extends Application {
    AtomicBoolean result = new AtomicBoolean(false);
    CountDownLatch countDownLatch = new CountDownLatch(1);

    public void arduinoConnection() {
        new Thread(() -> {//新建arduino自检线程
            Arduino arduino = new Arduino("COM3", 9600);
            if (true) {
                //arduino.serialWrite("2000013000150008500800013500");
                //String arduinoRead = arduino.serialRead();
                String arduinoRead = "SelfInspectionSucceed";
                if (arduinoRead.equals("SelfInspectionSucceed")) {//检测arduino自检是否完成
                    result.set(true);
                }
                arduino.closeConnection();
            }
            countDownLatch.countDown();
        }).start();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent launchPage = FXMLLoader.load(getClass().getResource("LaunchPage.fxml"));
        primaryStage.setScene(new Scene(launchPage));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("TamakiIroha.ico")));
        primaryStage.setTitle("机械臂操作软件");
        primaryStage.setOnShowing(event -> {//设置自检线程启动事件
            arduinoConnection();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        primaryStage.show();//设置启动页面


        if (result.get()) {//判断自检是否完成
            Platform.runLater(() -> {//加载主界面
                try {
                    Thread.sleep(1500);
                    if (result.get()) {
                        Parent mainInterfaceLoader = FXMLLoader.load(Main.class.getResource("main-interface.fxml"));
                        Stage mainInterface = new Stage();
                        mainInterface.setScene(new Scene(mainInterfaceLoader));
                        mainInterface.setTitle("机械臂操作软件");
                        mainInterface.getIcons().add(new Image(Main.class.getResourceAsStream("TamakiIroha.ico")));
                        mainInterface.show();
                        primaryStage.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
