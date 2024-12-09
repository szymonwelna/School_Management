package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SettingsWindowController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Powrót do MainWindow
    public void switchToMainWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Pane clickBlocker;
    @FXML
    AnchorPane addUserPane;

    //region Adding user
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    TextField confirmPasswordTextField;


    public void showAddUserPane() {
        clickBlocker.setVisible(true);
        addUserPane.setVisible(true);
    }

    public void hideAddUserPane() {
        clickBlocker.setVisible(false);
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
            loadUsersIntoListView();
            hideAddUserPane();
        } else {
            System.out.println("Passwords do not match");
        }
    }
    //endregion

    //region Deleting user
    @FXML
    AnchorPane deleteUserPane;
    @FXML
    Label selectedUser;
    @FXML
    Button confirmDeleteButton, cancelDeleteButton;

    public void showDeleteUserPane() {
        loadUsersIntoListView();
        clickBlocker.setVisible(true);
        deleteUserPane.setVisible(true);
    }
    public void hideDeleteUserPane() {
        clickBlocker.setVisible(false);
        deleteUserPane.setVisible(false);
        selectedUser.setText("");
    }
    public void deleteUser() {
        String username = selectedUser.getText();
        if (username.isEmpty()) {
            selectedUser.setText("Nie wybrano użytkownika");
        } else {
            DataHolder dataHolder = DataHolder.getInstance();
            List<User> usersList = dataHolder.getUsers();
            User userToRemove = null;
            for (User user : usersList) {
                if (user.getLogin().equals(username)) {
                    userToRemove = user;
                    selectedUser.setText("Usunięto użytkownika");
                    hideDeleteUserPane();
                    break;
                }
            }
            if (userToRemove != null) {
                usersList.remove(userToRemove);
                loadUsersIntoListView();
            } else {
                System.out.println("Nie znaleziono użytkownika: " + username);
            }
        }
    }

    @FXML
    ListView<String> usersListView;
    private ObservableList<String> users = FXCollections.observableArrayList();

    public void initialize() {
        loadUsersIntoListView();
        usersListView.setOnMouseClicked(event -> handleUserSelection());
    }

    private void loadUsersIntoListView() {
        DataHolder dataHolder = DataHolder.getInstance();
        users.clear();
        for (User user : dataHolder.getUsers()) {
            users.add(user.getLogin());
        }
        usersListView.setItems(users);
    }

    private void handleUserSelection() {
        String selectedUsername = usersListView.getSelectionModel().getSelectedItem();
        if (selectedUsername != null) {
            selectedUser.setText(selectedUsername);
        }
    }
    //endregion
}
