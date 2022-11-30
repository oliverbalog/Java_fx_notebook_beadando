package javafxbead;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import database.models.Telepites;
import database.models.TelepitesViewModel;
import database.models.User;
import javafx.event.ActionEvent;
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

    @FXML
    protected void onIndexUsersClick() throws UnirestException {

        contentPane.getChildren().clear();

        var idCol = new TableColumn("ID");
        var nameCol = new TableColumn("Név");
        var emailCol = new TableColumn("Email");
        var genderCol = new TableColumn("Nem");
        var statusCol = new TableColumn("Státusz");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        contentTable = new TableView<User>();

        contentTable.getColumns().addAll(idCol, nameCol, emailCol, genderCol, statusCol);

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        HttpResponse<JsonNode> apiResponse = Unirest.get("https://gorest.co.in/public/v2/users").asJson();
        String responseJsonAsString = apiResponse.getBody().toString();
        System.out.println(responseJsonAsString);

        User []users = new Gson().fromJson(responseJsonAsString, User[].class);

        for (User user : users) {
            contentTable.getItems().add(user);
        }

        contentTable.setPrefWidth(1240);
        contentTable.setPrefHeight(600);
        contentTable.relocate(0,30);

        madeMenu.relocate(300,700);
        contentPane.getChildren().add(madeMenu);

        contentPane.getChildren().add(contentTable);
    }

    @FXML
    public void onCreateUserClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onReadUserClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onUpdateUserClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onDeleteUserClick(ActionEvent actionEvent) {
    }
}