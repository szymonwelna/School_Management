package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.ListsHolder;
import com.demo.schoolmanagement.models.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Inicjalizacja listy studentów przed załadowaniem okna
            initStudentsList();

            Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("School Management");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            // Weryfikacja w terminalu, czy uczniowie zostali poprawnie dodani
            verifyStudentsInHolder();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // #temporary
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

    // #temporary
    // Funkcja weryfikująca, czy uczniowie zostali poprawnie dodani do ListsHolder
    public void verifyStudentsInHolder() {
        ListsHolder listsHolder = ListsHolder.getInstance();

        System.out.println("Lista uczniów w ListsHolder:");
        listsHolder.getStudents().forEach((id, student) -> {
            System.out.println("ID: " + id + ", Imię: " + student.getFirstName() + ", Nazwisko: " + student.getLastName());
        });
    }
}
