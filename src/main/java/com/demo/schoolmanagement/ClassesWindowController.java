package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.SchoolClass;
import com.demo.schoolmanagement.models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassesWindowController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Pobranie instancji DataHolder
    private final DataHolder dataHolder = DataHolder.getInstance();

    // Mapa odwzorowująca nazwę klasy na obiekt SchoolClass
    private Map<String, SchoolClass> schoolClassesMap = new HashMap<>();

    // Lista do zarządzania widokiem klas
    private ObservableList<String> schoolClasses = FXCollections.observableArrayList();

    @FXML
    ListView<String> schoolClassesListView;

    @FXML
    Pane clickBlocker;

    //region Dodawanie nowej klasy
    @FXML
    AnchorPane addSchoolClassPane;
    @FXML
    TextField schoolClassIdTextField;
    @FXML
    ComboBox<String> teacherComboBox;
    @FXML
    ListView<String> studentsListView;
    @FXML
    Label addSchoolClassWarningLabel;

    public void showAddSchoolClassPane() {
        clickBlocker.setVisible(true);
        addSchoolClassPane.setVisible(true);
        initializeTeachersComboBox();
    }

    // Metoda inicjująca ComboBox nauczycieli
    private void initializeTeachersComboBox() {
        ObservableList<String> teacherNames = FXCollections.observableArrayList();

        for (Teacher teacher : dataHolder.getTeachers().values()) {
            String fullName = teacher.getName() + " " + teacher.getSurname();
            teacherNames.add(fullName);
        }
        teacherComboBox.setItems(teacherNames);
    }

    public void addNewSchoolClass() {
        if (isProvidedDataValid() && teacherComboBox.getSelectionModel().getSelectedItem() != null) {
            String selectedTeacherName = teacherComboBox.getSelectionModel().getSelectedItem();
            int teacherId = getTeacherIdByName(selectedTeacherName);

            SchoolClass schoolClass = new SchoolClass(schoolClassIdTextField.getText(), teacherId);
            schoolClassesMap.put(schoolClass.getSchoolClassId(), schoolClass);
            dataHolder.addSchoolClass(schoolClass);
            refreshSchoolClassesList(); // Odświeżenie listy klas po dodaniu nowej klasy
            hideAddSchoolClassPane();
        }
    }

    public void hideAddSchoolClassPane() {
        clickBlocker.setVisible(false);
        addSchoolClassPane.setVisible(false);
        schoolClassIdTextField.setText("");
        teacherComboBox.getSelectionModel().clearSelection();
        addSchoolClassWarningLabel.setText("");
    }

    private boolean isProvidedDataValid() {
        String classId = schoolClassIdTextField.getText();
        String teacher = teacherComboBox.getSelectionModel().getSelectedItem();

        // Sprawdzenie, czy oba pola są puste/niewypełnione
        if (classId.isEmpty() && teacher == null) {
            addSchoolClassWarningLabel.setText("Wprowadź dane");
            return false;
        }
        // Sprawdzanie poprawności identyfikatora klasy
        if (classId.isEmpty()) {
            addSchoolClassWarningLabel.setText("Proszę podać nazwę klasy");
            return false;
        }
        if (schoolClasses.contains(classId)) {
            addSchoolClassWarningLabel.setText("Klasa o podanej nazwie już istnieje");
            return false;
        }
        if (!(classId.length() == 2 && classId.matches("[1-8][a-zA-Z]"))) {
            addSchoolClassWarningLabel.setText("Wprowadź nazwę w formie, np: 1c ");
            return false;
        }
        // Sprawdzanie, czy nauczyciel został wybrany
        if (teacher == null) {
            addSchoolClassWarningLabel.setText("Wybierz nauczyciela");
            return false;
        }

        return true;
    }


    // Metoda do znalezienia ID nauczyciela po imieniu i nazwisku
    private int getTeacherIdByName(String teacherName) {
        for (Teacher teacher : dataHolder.getTeachers().values()) {
            String fullName = teacher.getName() + " " + teacher.getSurname();
            if (fullName.equals(teacherName)) {
                return teacher.getId();
            }
        }
        return -1;
    }

    //endregion

    //region Obsługa listy klas
    @FXML
    public void initialize() {
        // Wywołanie metody inicjalizującej widok listy klas
        initializeSchoolClassesListView();
        refreshSchoolClassesList();
    }

    private void initializeSchoolClassesListView() {
        schoolClassesListView.setOnMouseClicked(this::clickedOnSchoolClass);
    }

    private void clickedOnSchoolClass(MouseEvent event) {
        String chosenSchoolClassId = schoolClassesListView.getSelectionModel().getSelectedItem();
        if (chosenSchoolClassId != null) {
            SchoolClass selectedSchoolClass = schoolClassesMap.get(chosenSchoolClassId);
            if (selectedSchoolClass != null) {
                //showEditSchoolClassPane(selectedSchoolClass);
            }
        }
    }

    private void refreshSchoolClassesList() {
        schoolClasses.clear();
        schoolClassesMap.clear();

        schoolClasses.addAll(
                dataHolder.getSchoolClasses().stream()
                        .map(schoolClass -> {
                            String schoolClassId = schoolClass.getSchoolClassId();
                            schoolClassesMap.put(schoolClassId, schoolClass);
                            return schoolClassId;
                        })
                        .collect(Collectors.toList())
        );

        schoolClassesListView.setItems(schoolClasses);
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
