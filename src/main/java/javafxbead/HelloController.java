package javafxbead;

import database.models.Gep;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class HelloController {
    private SessionFactory factory;
    @FXML
    private Label welcomeText;
    private Pane contentPane;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onOlvasMenuClick() throws IOException {
        var table = new TableView<Gep>();
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
        table.getColumns().addAll(idCol,gyartoCol,tipusCol,kijelzoCol,
                memoriaCol,merevlemezCol,videovezerloCol,arCol,dbCol,processzorCol,oprendszerCol);

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Gep> gepList = session.createQuery("FROM Gep").list();
        session.close();

        for(Gep gep:gepList){
            System.out.println(gep.getTipus());
            table.getItems().add(new Gep(
                    gep.getId(),
                    gep.getGyarto(),
                    gep.getTipus(),
                    gep.getKijelzo(),
                    gep.getMemoria(),
                    gep.getMerevlemez(),
                    gep.getVideovezerlo(),
                    gep.getAr(),
                    gep.getDb(),
                    gep.getProcesszor(),
                    gep.getOprendszer()
            ));
        }

        factory.close();

    }
}