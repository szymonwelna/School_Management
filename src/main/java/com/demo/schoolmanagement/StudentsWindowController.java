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
import javafx.scene.control.Label;
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
    @FXML
    ListView<String> schoolClassesListView1;

    @FXML
    ListView<String> schoolClassesListView2;

    private Map<String, SchoolClass> schoolClassMap = new HashMap<>();

    private ObservableList<String> schoolClasses = FXCollections.observableArrayList();

    private SchoolClass studentSchoolClass;

    private void initializeSchoolClassesListViews() {
        schoolClassesListView1.setOnMouseClicked(event -> clickedOnSchoolClass(schoolClassesListView1));
        schoolClassesListView2.setOnMouseClicked(event -> clickedOnSchoolClass(schoolClassesListView2));
    }

    private void clickedOnSchoolClass(ListView<String> listView) {
        String chosenSchoolClassId = listView.getSelectionModel().getSelectedItem();
        if (chosenSchoolClassId != null) {
            studentSchoolClass = schoolClassMap.get(chosenSchoolClassId);
        }
    }

    private void refreshSchoolClassesListViews() {
        schoolClasses.clear();
        schoolClassMap.clear();

        dataHolder.getSchoolClasses().forEach((schoolClassId, schoolClass) -> {
            String schoolClassName = schoolClass.getSchoolClassName();

            schoolClassMap.put(schoolClassName, schoolClass);

            schoolClasses.add(schoolClassName);
        });

        // Ustawienie listy klas w obu ListView
        schoolClassesListView1.setItems(schoolClasses);
        schoolClassesListView2.setItems(schoolClasses);
    }
    //endregion

    //region Dodawanie ucznia
    @FXML
    private VBox addstudentvbox;
    @FXML
    private TextField firstnamefield, surnameField;

    public void showAddStudentPane(ActionEvent event) {
        refreshSchoolClassesListViews();
        clickblocker.setVisible(true);
        addstudentvbox.setVisible(true);
    }

    private void hideAddStudentPane() {
        firstnamefield.clear();
        surnameField.clear();
        studentSchoolClass = null;
        clickblocker.setVisible(false);
        addstudentvbox.setVisible(false);
    }

    public void addStudentConfirm(ActionEvent event) {
        String firstName = firstnamefield.getText();
        String surname = surnameField.getText();

        if (!firstName.isEmpty() && !surname.isEmpty()) {
            Student student = new Student(firstName, surname, dataHolder.getLastStudentId());
            dataHolder.addStudent(student);
            if (studentSchoolClass != null) {
                student.setSchoolClassId(studentSchoolClass.getSchoolClassId());
            }
            studentSchoolClass = null;
            refreshStudentList();
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

        // Pobranie klasy ucznia
        studentSchoolClass = dataHolder.getSchoolClass(student.getSchoolClassId());
        firstNameTextField.setText(student.getName());
        lastNameTextField.setText(student.getSurname());

        refreshSchoolClassesListViews();

        // Jeśli uczeń ma przypisaną klasę, zaznacz ją w obu listach
        if (studentSchoolClass != null) {
            String selectedClassName = studentSchoolClass.getSchoolClassName();

            // Ustaw zaznaczenie w obu listach
            schoolClassesListView1.getSelectionModel().select(selectedClassName);
            schoolClassesListView2.getSelectionModel().select(selectedClassName);
        }

        clickblocker.setVisible(true);
        editUserPane.setVisible(true);
    }


    public void hideEditUserPane() {
        currentStudent = null;
        firstNameTextField.clear();
        lastNameTextField.clear();
        
        schoolClassesListView1.getSelectionModel().clearSelection();
        schoolClassesListView2.getSelectionModel().clearSelection();

        clickblocker.setVisible(false);
        editUserPane.setVisible(false);
    }


    public void confirmChanges() {
        if (currentStudent != null) {
            // Aktualizacja imienia i nazwiska
            currentStudent.changeName(firstNameTextField.getText());
            currentStudent.changeSurname(lastNameTextField.getText());
            // Klasa pobrana z listview
            String selectedClassName = schoolClassesListView2.getSelectionModel().getSelectedItem();
            SchoolClass selectedSchoolClass = schoolClassMap.get(selectedClassName);
            currentStudent.setSchoolClassId(selectedSchoolClass.getSchoolClassId());
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

        initializeSchoolClassesListViews();
        refreshSchoolClassesListViews();
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
