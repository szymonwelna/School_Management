package com.demo.schoolmanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.demo.schoolmanagement.models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataSaver {

    private static final DataSaver instance = new DataSaver();

    public static DataSaver getInstance() {
        return instance;
    }


    public static void saveData() {
        DataHolder dataHolder = DataHolder.getInstance();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
        String json;

        // Zapis użytkowników do pliku
        if (dataHolder.getUsers() != null) {
            json = gson.toJson(dataHolder.getUsers());
            try (FileWriter writer = new FileWriter("./users.json")) {
                writer.write(json);
                System.out.println("Zapisano uczniów do pliku: users.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (dataHolder.getStudents() != null) {
            // Zapis uczniów do pliku
            json = gson.toJson(dataHolder.getStudents());

            try (FileWriter writer = new FileWriter("students.json")) {
                writer.write(json);
                System.out.println("Zapisano uczniów do pliku: students.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (dataHolder.getTeachers() != null) {
            // Zapis nauczycieli do pliku
            json = gson.toJson(dataHolder.getTeachers());

            try (FileWriter writer = new FileWriter("teachers.json")) {
                writer.write(json);
                System.out.println("Zapisano nauczycieli do pliku: teachers.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (dataHolder.getSchoolClasses() != null) {
            json = gson.toJson(dataHolder.getSchoolClasses());

            try (FileWriter writer = new FileWriter("schoolclasses.json")) {
                writer.write(json);
                System.out.println("Zapisano uczniów do pliku: schoolclasses.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Zapis wykładów do pliku
        if (dataHolder.getLectures() != null) {
            json = gson.toJson(dataHolder.getLectures());

            try (FileWriter writer = new FileWriter("lectures.json")) {
                writer.write(json);
                System.out.println("Zapisano lekcje do pliku: lectures.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deserializeUsers() {
        Gson gson = new Gson();

        // Określenie typu List<User> za pomocą TypeToken
        Type studentListType = new TypeToken<HashMap<Integer, User>>() {}.getType();

        // Odczyt JSON z pliku
        try (FileReader reader = new FileReader("users.json")) {
            HashMap<Integer, User> users = gson.fromJson(reader, studentListType);
            DataHolder dataHolder = DataHolder.getInstance();
            dataHolder.setUsers(users);
            System.out.println("Odczytano listę użytkowników z JSON:");
            for(User user : users.values()) {
                System.out.println(user.getLogin());
                System.out.println(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeStudents() {
        Gson gson = new Gson();

        // Określenie typu List<Student> za pomocą TypeToken
        Type studentListType = new TypeToken<HashMap<Integer, Student>>() {}.getType();

        // Odczyt JSON z pliku
        try (FileReader reader = new FileReader("students.json")) {
            HashMap<Integer, Student> students = gson.fromJson(reader, studentListType);
            DataHolder dataHolder = DataHolder.getInstance();
            dataHolder.setStudents(students);
            System.out.println("Odczytano listę studentów z JSON:");
            for(Student student : students.values()) {
                System.out.println(student);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeSchoolClasses() {
        Gson gson = new Gson();

        // Określenie typu List<User> za pomocą TypeToken
        Type studentListType = new TypeToken<List<SchoolClass>>() {}.getType();

        // Odczyt JSON z pliku
        try (FileReader reader = new FileReader("schoolclasses.json")) {
            ArrayList<SchoolClass> schoolClasses = gson.fromJson(reader, studentListType);
            DataHolder dataHolder = DataHolder.getInstance();
            dataHolder.setSchoolClasses(schoolClasses);
            System.out.println("Odczytano listę klas z JSON:");
            for(SchoolClass schoolClass : schoolClasses) {
                System.out.println(schoolClass.getSchoolClassId());
                System.out.println(schoolClass);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeTeachers() {
        Gson gson = new Gson();

        Type studentListType = new TypeToken<HashMap<Integer, Teacher>>() {}.getType();

        // Odczyt JSON z pliku
        try (FileReader reader = new FileReader("teachers.json")) {
            HashMap<Integer, Teacher> teachers = gson.fromJson(reader, studentListType);
            DataHolder dataHolder = DataHolder.getInstance();
            dataHolder.setTeachers(teachers);
            System.out.println("Odczytano listę nauczycieli z JSON:");
            for(Teacher teacher : teachers.values()) {
                System.out.println(teacher);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeLectures() {
        // Rejestracja adaptera LocalTime
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();

        Type lectureType = new TypeToken<HashMap<Integer, Lecture>>() {}.getType();

        // Odczyt JSON z pliku
        try (FileReader reader = new FileReader("lectures.json")) {
            HashMap<Integer, Lecture> lectures = gson.fromJson(reader, lectureType);
            DataHolder dataHolder = DataHolder.getInstance();
            dataHolder.setLectures(lectures);
            System.out.println("Odczytano lekcje z JSON:");
            for (Lecture lecture : lectures.values()) {
                System.out.println(lecture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
