package ru.kpfu.itis.group11501.malikov.uno.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Дамир on 11.12.2016.
 */
public class HelpController implements Initializable {

    @FXML
    private Button btnHelpBack;

    public void goToMainMenu(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnHelpBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Start.fxml"));
        Scene scene = new Scene(root );
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
