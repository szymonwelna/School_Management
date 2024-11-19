module com.demo.schoolmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.demo.schoolmanagement to javafx.fxml;
    exports com.demo.schoolmanagement;
}