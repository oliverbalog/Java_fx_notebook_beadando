package javafxbead;

import database.models.Telepites;
import database.models.TelepitesViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
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

        var idCol = new TableColumn("ID");
        var verzioCol = new TableColumn("Verzió");
        var datumCol = new TableColumn("Dátum");
        var gepIdCol = new TableColumn("Gép ID");
        var helyCol = new TableColumn("Gép helye");
        var tipusCol = new TableColumn("Gép típusa");
        var ipcimCol = new TableColumn("Gép IP címe");
        var szoftverIdCol = new TableColumn("Szoftver ID");
        var nevCol = new TableColumn("Szoftver neve");
        var kategoriaCol = new TableColumn("Szoftver kategóriája");

        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        verzioCol.setCellValueFactory(new PropertyValueFactory<>("Verzio"));
        datumCol.setCellValueFactory(new PropertyValueFactory<>("Datum"));
        gepIdCol.setCellValueFactory(new PropertyValueFactory<>("GepId"));
        helyCol.setCellValueFactory(new PropertyValueFactory<>("GepHely"));
        tipusCol.setCellValueFactory(new PropertyValueFactory<>("GepTipus"));
        ipcimCol.setCellValueFactory(new PropertyValueFactory<>("GepIpcim"));
        szoftverIdCol.setCellValueFactory(new PropertyValueFactory<>("SzoftverId"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("SzoftverNev"));
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("SzoftverKategoria"));

        contentTable = new TableView<TelepitesViewModel>();

        contentTable.getColumns().addAll(idCol, verzioCol, datumCol, gepIdCol,
                helyCol, tipusCol, ipcimCol, szoftverIdCol, nevCol, kategoriaCol);

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("FROM Telepites");
        q.setFirstResult(0);
        q.setMaxResults(100);
        List<Telepites> telepitesList = q.getResultList();
        session.close();

        for (Telepites telepites : telepitesList) {
            System.out.println(telepites.getId());
            contentTable.getItems().add(new TelepitesViewModel(
                    telepites.getId(),
                    telepites.getVerzio(),
                    telepites.getDatum(),
                    telepites.getGep().getId(),
                    telepites.getGep().getHely(),
                    telepites.getGep().getTipus(),
                    telepites.getGep().getIpcim(),
                    telepites.getSzoftver().getId(),
                    telepites.getSzoftver().getNev(),
                    telepites.getSzoftver().getKategoria()
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
        List<String> szoftverList = session.createQuery("SELECT Nev FROM Szoftver ").list();
        session.close();
//fweanfpwenfianwef
        var hbox = new HBox();
        hbox.relocate(0,5);
        hbox.setPrefSize(contentPane.getWidth(),800);

        var vbox1 = new VBox();
        vbox1.setPrefSize(300,300);

        var vbox2 = new VBox();
        vbox2.setPrefSize(300,300);


        var label = new Label();
        label.setText("Szoftver kategóriája");
        label.relocate(10,0);
        var textb = new TextField();
        textb.relocate(10,0);
        textb.setPrefWidth(100);
        textb.setMaxWidth(100);

        var label2 = new Label();
        label2.setText("Válasszon egy szoftvert!");
        var combobox = new ComboBox<>();
        combobox.getItems().addAll(szoftverList);

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