package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.SchoolClass;
import com.demo.schoolmanagement.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentsWindowController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Pobranie instancji DataHolder
    private final DataHolder dataHolder = DataHolder.getInstance();

    // Mapa odwzorowująca pełne imię ucznia na obiekt Student
    private final Map<String, Student> studentMap = new HashMap<>();

    // Lista do zarządzania widokiem uczniów
    private final ObservableList<String> students = FXCollections.observableArrayList();

    @FXML
    private ListView<String> studentslistview;

    @FXML
    private Pane clickblocker;

    //region Obsługa ListView z klasami szkolnymi
    // Mapa odwzorowująca nazwę klasy na obiekt
    @FXML
    ListView<String> schoolClassesListView;

    private Map<String, SchoolClass> schoolClassMap = new HashMap<>();

    private ObservableList<String> schoolClasses = FXCollections.observableArrayList();

    private SchoolClass selectedSchoolClass;

    private void initializeSchoolClassesListView() {
        schoolClassesListView.setOnMouseClicked(this::clickedOnSchoolClass);
    }

    private void clickedOnSchoolClass(MouseEvent event) {
        String chosenSchoolClassId = schoolClassesListView.getSelectionModel().getSelectedItem();
        if (chosenSchoolClassId != null) {
            selectedSchoolClass = schoolClassMap.get(chosenSchoolClassId);
        }
    }

    private void refreshSchoolClassesListView() {
        schoolClasses.clear();
        schoolClassMap.clear();

        schoolClasses.addAll(
                dataHolder.getSchoolClasses().stream()
                        .map(schoolClass -> {
                            String schoolClassId = schoolClass.getSchoolClassId();
                            schoolClassMap.put(schoolClassId, schoolClass);
                            return schoolClassId;
                        })
                        .collect(Collectors.toList())
        );

        schoolClassesListView.setItems(schoolClasses);
    }

    //endregion

    //region Dodawanie ucznia
    @FXML
    private VBox addstudentvbox;
    @FXML
    private TextField firstnamefield, surnameField;

    public void showAddStudentPane(ActionEvent event) {
        clickblocker.setVisible(true);
        addstudentvbox.setVisible(true);
    }

    private void hideAddStudentPane() {
        firstnamefield.clear();
        surnameField.clear();
        clickblocker.setVisible(false);
        addstudentvbox.setVisible(false);
    }



    public void addStudentConfirm(ActionEvent event) {
        String firstName = firstnamefield.getText();
        String surname = surnameField.getText();

        if (!firstName.isEmpty() && !surname.isEmpty()) {
            Student student = new Student(firstName, surname, dataHolder.getLastStudentId());
            dataHolder.addStudent(student);
            refreshStudentList(); // Aktualizacja listy uczniów
            hideAddStudentPane();
        } else {
            System.out.println("Błąd dodawania ucznia. Proszę uzupełnić dane.");
        }
    }

    public void addStudentCancel(ActionEvent event) {
        hideAddStudentPane();
    }
    //endregion

    //region Edytowanie ucznia
    @FXML
    private AnchorPane editUserPane;
    @FXML
    private TextField firstNameTextField, lastNameTextField;

    private Student currentStudent;

    public void showEditUserPane(Student student) {
        this.currentStudent = student;
        selectedSchoolClass = dataHolder.getSchoolClass(student.getSchoolClassId());
        firstNameTextField.setText(student.getName());
        lastNameTextField.setText(student.getSurname());
        refreshSchoolClassesListView();
        clickblocker.setVisible(true);
        editUserPane.setVisible(true);
    }

    public void hideEditUserPane() {
        currentStudent = null;
        firstNameTextField.clear();
        lastNameTextField.clear();
        clickblocker.setVisible(false);
        editUserPane.setVisible(false);
    }

    public void confirmChanges() {
        if (currentStudent != null) {
            currentStudent.changeName(firstNameTextField.getText());
            currentStudent.changeSurname(lastNameTextField.getText());
            refreshStudentList(); // Aktualizacja listy uczniów
            hideEditUserPane();
        }
    }

    public void deleteStudent() {
        if (currentStudent != null) {
            dataHolder.removeStudent(currentStudent.getId());
            refreshStudentList(); // Aktualizacja listy uczniów
            hideEditUserPane();
        }
    }
    //endregion

    //region Obsługa listy uczniów
    @FXML
    public void initialize() {
        // Inicjalizacja danych do widoku
        refreshStudentList();

        // Obsługa kliknięcia na liście
        studentslistview.setOnMouseClicked(this::clickedOnStudent);

        initializeSchoolClassesListView();
        refreshSchoolClassesListView();
    }

    private void clickedOnStudent(MouseEvent event) {
        String chosenStudentName = studentslistview.getSelectionModel().getSelectedItem();
        if (chosenStudentName != null) {
            Student chosenStudent = studentMap.get(chosenStudentName);
            if (chosenStudent != null) {
                showEditUserPane(chosenStudent);
            }
        }
    }

    private void refreshStudentList() {
        students.clear();
        studentMap.clear();

        students.addAll(
                dataHolder.getStudents().values().stream()
                        .map(student -> {
                            String fullName = student.getName() + " " + student.getSurname();
                            studentMap.put(fullName, student);
                            return fullName;
                        })
                        .collect(Collectors.toList())
        );

        studentslistview.setItems(students);
    }
    //endregion

    //region Powrót do MainWindow
    public void switchToMainWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //endregion
}