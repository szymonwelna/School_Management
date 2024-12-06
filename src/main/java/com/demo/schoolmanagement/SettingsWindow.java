package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsWindow {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Powrót do MainWindow
    public void switchToMainWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    AnchorPane addUserPane;
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    TextField confirmPasswordTextField;


    public void showAddUserPane() {
        addUserPane.setVisible(true);
    }

    public void hideAddUserPane() {
        addUserPane.setVisible(false);
        usernameTextField.setText("");
        passwordTextField.setText("");
        confirmPasswordTextField.setText("");
    }

    public void addNewUser() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (password.equals(confirmPassword) && !username.isEmpty()) {
            User newUser = new User(1, username, password);
            DataHolder dataHolder = DataHolder.getInstance();
            dataHolder.addUser(newUser);
            hideAddUserPane();
        } else {
            System.out.println("Passwords do not match");
        }
    }

}
