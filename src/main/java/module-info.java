module com.demo.schoolmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.demo.schoolmanagement to com.google.gson, javafx.fxml;
    exports com.demo.schoolmanagement;
    exports com.demo.schoolmanagement.models;
    opens com.demo.schoolmanagement.models to com.google.gson, javafx.fxml;
}