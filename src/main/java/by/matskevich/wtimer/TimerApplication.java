package by.matskevich.wtimer;

import by.matskevich.wtimer.controller.TimerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TimerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimerApplication.class.getResource("timer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        stage.setTitle("WTimer v.0.5");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            TimerController timerController = fxmlLoader.getController();
            timerController.cancel();
            stage.close();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}