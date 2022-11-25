module com.example.javafxbead {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;


    opens com.javafxbead to javafx.fxml;
    exports com.javafxbead;
}