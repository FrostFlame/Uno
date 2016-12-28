package ru.kpfu.itis.Malikov.uno.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Дамир on 11.12.2016.
 */
public class StartMenuController implements Initializable {
    private static final String HELP_FXML = "../fxml/Help.fxml";
    private static final String LOBBY_LIST_FXML = "../fxml/lobbyList.fxml";
    private static String resource;

    @FXML
    private Button exit;
    @FXML
    private Button game;
    @FXML
    private Button help;


    @Override
    public void initialize(URL location, ResourceBundle resources) {}


    public void goTo(ActionEvent actionEvent) {


        if (actionEvent.getSource().equals(exit)) {
            System.exit(0);
        }
        else if (actionEvent.getSource().equals(game)) {
            resource = LOBBY_LIST_FXML;
        }
        else if (actionEvent.getSource().equals(help)) {
            resource = HELP_FXML;
        }

        try {
            Stage stage = (Stage) game.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}