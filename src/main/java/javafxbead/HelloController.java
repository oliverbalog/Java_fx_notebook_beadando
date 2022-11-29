package javafxbead;

import database.models.Gep;
import database.models.GepViewModel;
import database.models.Oprendszer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class HelloController {
    private SessionFactory factory;
    @FXML
    private Pane contentPane;
    @FXML
    private Pane madeMenu;

    private TableView contentTable;


    @FXML
    protected void onOlvasMenuClick() throws IOException {
        contentPane.getChildren().clear();

        var idCol = new TableColumn("Id");
        var gyartoCol = new TableColumn("Gyarto");
        var tipusCol = new TableColumn("Típus");
        var kijelzoCol = new TableColumn("Kijelző méret");
        var memoriaCol = new TableColumn("Memória");
        var merevlemezCol = new TableColumn("Merevlemez");
        var videovezerloCol = new TableColumn("Videóvezérlő");
        var arCol = new TableColumn("Ár");
        var dbCol = new TableColumn("Db");
        var processzorCol = new TableColumn("Processzor");
        var oprendszerCol = new TableColumn("Operációs rendszer");

        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        gyartoCol.setCellValueFactory(new PropertyValueFactory<>("Gyarto"));
        tipusCol.setCellValueFactory(new PropertyValueFactory<>("Tipus"));
        kijelzoCol.setCellValueFactory(new PropertyValueFactory<>("Kijelzo"));
        memoriaCol.setCellValueFactory(new PropertyValueFactory<>("Memoria"));
        merevlemezCol.setCellValueFactory(new PropertyValueFactory<>("Merevlemez"));
        videovezerloCol.setCellValueFactory(new PropertyValueFactory<>("Videovezerlo"));
        arCol.setCellValueFactory(new PropertyValueFactory<>("Ar"));
        dbCol.setCellValueFactory(new PropertyValueFactory<>("Db"));
        processzorCol.setCellValueFactory(new PropertyValueFactory<>("Processzor"));
        oprendszerCol.setCellValueFactory(new PropertyValueFactory<>("Oprendszer"));

        contentTable = new TableView<>();

        contentTable.getColumns().addAll(idCol, gyartoCol, tipusCol, kijelzoCol,
                memoriaCol, merevlemezCol, videovezerloCol, arCol, dbCol, processzorCol, oprendszerCol);

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Gep> gepList = session.createQuery("FROM Gep").list();
        session.close();

        for (Gep gep : gepList) {
            contentTable.getItems().add(new GepViewModel(
                    gep.getId(),
                    gep.getGyarto(),
                    gep.getTipus(),
                    gep.getKijelzo(),
                    gep.getMemoria(),
                    gep.getMerevlemez(),
                    gep.getVideovezerlo(),
                    gep.getAr(),
                    gep.getDb(),
                    gep.getProcesszor().getGyarto() + " - " + gep.getProcesszor().getTipus(),
                    gep.getOprendszer().getNev()
                    )
            );
        }

        contentTable.setPrefWidth(1240);
        contentTable.setPrefHeight(600);
        contentTable.relocate(0,30);

        madeMenu.relocate(300,700);
        contentPane.getChildren().add(madeMenu);

        contentPane.getChildren().add(contentTable);

        factory.close();

    }

    @FXML
    protected void onOlvasFilterMenuClick() throws IOException{
            contentPane.getChildren().clear();

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<String> oprendszerList = session.createQuery("SELECT Nev FROM Oprendszer").list();
        session.close();

        var hbox = new HBox();
        hbox.relocate(0,5);
        hbox.setPrefSize(contentPane.getWidth(),800);

        var vbox1 = new VBox();
        vbox1.setPrefSize(300,300);

        var vbox2 = new VBox();
        vbox2.setPrefSize(300,300);


        var label = new Label();
        label.setText("Márka és/vagy típus");
        label.relocate(10,0);
        var textb = new TextField();
        textb.relocate(10,0);
        textb.setPrefWidth(100);
        textb.setMaxWidth(100);

        var label2 = new Label();
        label2.setText("Válasszon egy operációs rendszert!");
        var combobox = new ComboBox<>();
        combobox.getItems().addAll(oprendszerList);

        contentPane.getChildren().clear();



        vbox1.getChildren().add(label);
        vbox1.getChildren().add(textb);

        vbox2.getChildren().add(label2);
        vbox2.getChildren().add(combobox);


        hbox.getChildren().add(vbox1);
        hbox.getChildren().add(vbox2);
        textb.autosize();

        contentPane.getChildren().add(madeMenu);

        contentPane.getChildren().add(hbox);
    }
}