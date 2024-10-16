module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.ejercicio1 to javafx.fxml;
    exports com.example.ejercicio1;
}
