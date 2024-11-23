package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.ListsHolder;
import com.demo.schoolmanagement.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;

public class StudentsWindowController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Pobranie instancji ListsHolder
    ListsHolder listsHolder = ListsHolder.getInstance();

    // Zainicjowanie blokera kliknięć, który będzie uniemożliwiał klikanie poza pop-upami
    @FXML
    private Pane clickblocker;

    // Powrót do MainWindow
    public void switchToMainWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





    @FXML
    private VBox addstudentvbox;
    @FXML
    private TextField firstnamefield;
    @FXML
    private TextField lastnamefield;
    @FXML
    private ListView<String> classeslistview;


    // Przycisk dodania ucznia
    public void addStudent(ActionEvent event) throws IOException {
        clickblocker.setVisible(true);
        addstudentvbox.setVisible(true);
        // Tutaj wyląduje cały kod związany z dodaniem ucznia do listy
    }

    public void addStudentConfirm(ActionEvent event) throws IOException {
        String firstName = firstnamefield.getText();
        String lastName = lastnamefield.getText();

        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            Student student = new Student(listsHolder.getLastId(), firstName, lastName);
            listsHolder.addStudentToList(student);
            students.add(firstName+" "+lastName);
            clickblocker.setVisible(false);
            addstudentvbox.setVisible(false);
        } else {
            System.out.println("Błąd dodawania ucznia. Proszę uzupełnić dane.");
        }
    }

    public void addStudentCancel(ActionEvent event) throws IOException {
        firstnamefield.clear();
        lastnamefield.clear();
        clickblocker.setVisible(false);
        addstudentvbox.setVisible(false);
    }



    // Lista przechowująca uczniów
    @FXML
    private ListView<String> studentslistview;

    private ObservableList<String> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Przekształcenie listy uczniów na ObservableList<String>
        students.setAll(
                listsHolder.getStudents().values().stream()
                        .map(student -> student.getFirstName() + " " + student.getLastName())
                        .collect(Collectors.toList())
        );

        // Dodanie listy uczniów do ListView
        studentslistview.setItems(students);

        // Obsługa kliknięcia na elemencie listy
        studentslistview.setOnMouseClicked(this::clickedOnStudent);
    }

    // Funkcja wywoływana po kliknięciu na ucznia
    private void clickedOnStudent(MouseEvent event) {
        String chosenStudent = studentslistview.getSelectionModel().getSelectedItem();
        if (chosenStudent != null) {
            System.out.println("Wybrano ucznia: " + chosenStudent);
            // Tutaj mogę potem wywołać okienko do manipulowania danymi ucznia
        }
    }

}
