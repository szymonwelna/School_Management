package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.ListsHolder;
import com.demo.schoolmanagement.models.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("School Management");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    // Tymczasowa funkcja służąca wypełnieniu listy studentów
    public void initStudentsList() {
        ListsHolder listsHolder = ListsHolder.getInstance();

        String[] studentsData = {
                "Jan Kowalski", "Anna Nowak", "Piotr Zieliński", "Katarzyna Wiśniewska",
                "Tomasz Wójcik", "Agnieszka Kaczmarek", "Michał Mazur", "Monika Adamczyk",
                "Paweł Dąbrowski", "Ewa Jabłońska", "Krzysztof Zawadzki", "Maria Wojciechowska",
                "Grzegorz Kamiński", "Joanna Walczak", "Łukasz Sikora", "Sylwia Baran",
                "Damian Sadowski", "Natalia Dudek", "Adrian Walczak", "Iwona Kwiatkowska",
                "Rafał Głowacki", "Dominika Zielińska", "Jakub Wojtaszek", "Karolina Nowicka",
                "Mateusz Król", "Aleksandra Bąk", "Sebastian Majewski", "Patrycja Malinowska",
                "Grzegorz Brzęczyszczykiewicz"
        };

        for (String studentData : studentsData) {
            String[] nameParts = studentData.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];

            // Tworzenie studenta i dodawanie do globalnego ListsHolder
            Student student = new Student(listsHolder.getLastId(), firstName, lastName);
            listsHolder.addStudentToList(student);
        }
    }

}