module com.example.javafxbead {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens com.javafxbead to javafx.fxml;
    exports com.javafxbead;
}