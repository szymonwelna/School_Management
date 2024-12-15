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

public class ClassesWindowController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Pobranie instancji DataHolder
    private final DataHolder dataHolder = DataHolder.getInstance();
    private final DataSorter dataSorter = new DataSorter();

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
    TextField schoolClassNameTextField;
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


            SchoolClass schoolClass = new SchoolClass(dataHolder.getLastSchoolClassId() + 1, schoolClassNameTextField.getText().toUpperCase(), teacherId);
            schoolClassesMap.put(schoolClass.getSchoolClassName(), schoolClass);
            dataHolder.addSchoolClass(schoolClass);
            dataSorter.sortSchoolClasses();
            refreshSchoolClassesList(); // Odświeżenie listy klas po dodaniu nowej klasy
            hideAddSchoolClassPane();
        }
    }

    public void hideAddSchoolClassPane() {
        clickBlocker.setVisible(false);
        addSchoolClassPane.setVisible(false);
        schoolClassNameTextField.setText("");
        teacherComboBox.getSelectionModel().clearSelection();
        addSchoolClassWarningLabel.setText("");
    }

    private boolean isProvidedDataValid() {
        String schoolClassName = schoolClassNameTextField.getText();
        String teacher = teacherComboBox.getSelectionModel().getSelectedItem();

        // Sprawdzenie, czy oba pola są puste/niewypełnione
        if (schoolClassName.isEmpty() && teacher == null) {
            addSchoolClassWarningLabel.setText("Wprowadź dane");
            return false;
        }
        // Sprawdzanie poprawności identyfikatora klasy
        if (schoolClassName.isEmpty()) {
            addSchoolClassWarningLabel.setText("Proszę podać nazwę klasy");
            return false;
        }
        if (schoolClasses.contains(schoolClassName)) {
            addSchoolClassWarningLabel.setText("Klasa o podanej nazwie już istnieje");
            return false;
        }
        if (!(schoolClassName.length() == 2 && schoolClassName.matches("[1-8][a-zA-Z]"))) {
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

        // Pobierz wszystkie klasy ze źródła danych (dataHolder)
        dataHolder.getSchoolClasses().forEach((currentSchoolClassId, currentSchoolClass) -> {
            String schoolClassName = currentSchoolClass.getSchoolClassName();
            schoolClassesMap.put(schoolClassName, currentSchoolClass);
            schoolClasses.add(schoolClassName);
        });

        // Ustawiamy elementy w ListView
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
