module com.example.javafxbead {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    //needed for HTTP
    requires unirest.java;
    //needed for JSON
    requires gson;

    opens javafxbead to javafx.fxml;
    exports javafxbead;
    exports database.models;
    opens database.models to org.hibernate.orm.core;
}