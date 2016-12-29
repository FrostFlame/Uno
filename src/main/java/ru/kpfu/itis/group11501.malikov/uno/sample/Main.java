package ru.kpfu.itis.group11501.malikov.uno.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String FXML = "/fxml/Start.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(FXML));
        Parent root = myLoader.load();
//        Parent root = FXMLLoader.load(getClass().getResource(FXML));
        primaryStage.setTitle("UNO");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
