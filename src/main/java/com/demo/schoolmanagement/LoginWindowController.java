package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LoginWindowController {

    @FXML
    Label wrongPassword;

    @FXML
    TextField nametextfield;

    @FXML
    TextField passwordtextfield;

    @FXML
    Button loginbutton;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void Login(ActionEvent event) throws IOException {

        String username = nametextfield.getText();
        String password = passwordtextfield.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        root = loader.load();

        if (checkPassword(username, password) == true) {
            MainWindowController mainAppController = loader.getController();
            mainAppController.displayName(username);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            wrongPassword.setVisible(true);
        }
    }

    private boolean checkPassword(String username, String password) {
        DataHolder dataHolder = DataHolder.getInstance();
        HashMap<Integer, User> users = dataHolder.getUsers();
        if (users.isEmpty()) {
            System.out.println("Baza użytkowników jest pusta. Logowanie jako administrator.");
            return true;
        }
        if (username == null || password == null) {
            return false;
        }
        for (User user : users.values()) {
            if (username.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
