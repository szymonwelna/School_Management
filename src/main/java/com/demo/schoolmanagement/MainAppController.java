package com.demo.schoolmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    // Etykieta z nazwą zalogowanego użytkownika
    @FXML
    Label usernameLabel;

    public void displayName(String value) {
        usernameLabel.setText(value);
    }

    // Można przenieść do innego kotrolera w celu utworzenia przycisku cofającego do głównego okna programu
    public void switchToMainWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToStudentsWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StudentsWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTeachersWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TeachersWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLecturesWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LecturesWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToClassesWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ClassesWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}