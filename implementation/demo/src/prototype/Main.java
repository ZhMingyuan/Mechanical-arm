package prototype;

import arduino.Arduino;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main extends Application {
    AtomicBoolean result = new AtomicBoolean(false);


    @Override
    public void start(Stage primaryStage) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Parent launchPage = FXMLLoader.load(getClass().getResource("LaunchPage.fxml"));
        primaryStage.setScene(new Scene(launchPage));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("TamakiIroha.ico")));
        primaryStage.setTitle("机械臂操作软件");
        primaryStage.show();

        new Thread(() -> {
            Arduino arduino = new Arduino("COM5", 9600);
            if (true) {
                //arduino.serialWrite("2000013000150008500800013500");
                // String arduinoRead = arduino.serialRead();
                String arduinoRead = "SelfInspectionSucceed";
                if (arduinoRead.equals("SelfInspectionSucceed")) {
                    result.set(true);
                }
                arduino.closeConnection();
            }
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();


        if (result.get()) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(5000);
                    if (result.get()) {
                        Parent mainInterfaceLoader = FXMLLoader.load(Main.class.getResource("main-interface.fxml"));
                        Stage mainInterface = new Stage();
                        mainInterface.setScene(new Scene(mainInterfaceLoader));
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
