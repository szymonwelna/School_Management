package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.demo.schoolmanagement.DataSaver.*;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            loadData();

            Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("School Management");
            stage.setScene(scene);
            stage.setResizable(false);

            // Dodanie handlera na zdarzenie zamknięcia okna
            stage.setOnCloseRequest(event -> {
                saveData();
            });

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loadData() {
        deserializeUsers();
        deserializeStudents();
        deserializeTeachers();
        deserializeSchoolClasses();
        deserializeLectures();
    }


}
