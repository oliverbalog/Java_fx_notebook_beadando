module com.example.javafxbead {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;


    opens com.javafxbead to javafx.fxml;
    exports com.javafxbead;
}