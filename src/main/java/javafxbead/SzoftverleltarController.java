package javafxbead;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import database.models.Telepites;
import database.models.TelepitesViewModel;
import database.models.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

public class SzoftverleltarController {
    private SessionFactory factory;
    @FXML
    private Pane contentPane;
    @FXML
    private Pane madeMenu;

    private TableView contentTable;


    @FXML
    protected void onOlvasMenuClick(Filter filter) throws IOException {
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
        Query q;

        if(filter == null) {
            q = session.createQuery("FROM Telepites");
        }
        else{
            var hql = "FROM Telepites T";
            if(!filter.getPcType().isEmpty() || !filter.getSoftName().isEmpty() || !filter.getPcPlace().isEmpty() || )
            if(!filter.getPcPlace().isEmpty()){

            }
            q = session.createQuery(hql);
        }
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
        hbox.relocate(20,5);
        hbox.setPrefSize(contentPane.getWidth(),800);

        var vbox1 = new VBox();
        vbox1.setPrefSize(300,300);

        var vbox2 = new VBox();
        vbox2.setPrefSize(300,300);

        var vbox3 = new VBox();
        vbox3.setPrefSize(300,300);

        var vbox4 = new VBox();
        vbox4.setPrefSize(300,300);

        var label = new Label();
        label.setText("Gép helye");
        label.relocate(40,0);
        vbox1.getChildren().add(label);
        var textb = new TextField();
        textb.relocate(40,0);
        textb.setPrefWidth(100);
        textb.setMaxWidth(100);
        vbox1.getChildren().add(textb);

        var label2 = new Label();
        label2.setText("Válasszon egy szoftvert!");
        vbox2.getChildren().add(label2);
        var combobox = new ComboBox<>();
        combobox.getItems().addAll(szoftverList);
        vbox2.getChildren().add(combobox);

        String radioVal="";
        var labelRadio = new Label();
        labelRadio.setText("Válasszon számítógép típust!");
        vbox3.getChildren().add(labelRadio);
        var toggleGroup = new ToggleGroup();
        var radioButton1 = new RadioButton();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setText("Notebook");
        vbox3.getChildren().add(radioButton1);
        var radioButton2 = new RadioButton();
        radioButton2.setToggleGroup(toggleGroup);
        radioButton2.setText("Asztali");
        vbox3.getChildren().add(radioButton2);


        var labelCheck = new Label();
        labelCheck.setText("Szoftver fajták");
        var toolsCheck = new CheckBox();
        toolsCheck.setText("Segédszoftverek");
        var hangCheck = new CheckBox();
        hangCheck.setText("Hang szoftverek");
        var pluginCheck = new CheckBox();
        pluginCheck.setText("Pluginok");
        var fajlCheck = new CheckBox();
        fajlCheck.setText("Fájlkezelők");
        var mindCheck = new CheckBox();
        mindCheck.setText("Mind");
        vbox4.getChildren().addAll(labelCheck,toolsCheck,hangCheck,pluginCheck,fajlCheck,mindCheck);
        mindCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                toolsCheck.setSelected(t1);
                hangCheck.setSelected(t1);
                pluginCheck.setSelected(t1);
                fajlCheck.setSelected(t1);
            }
        });

        var srchBtn = new Button();
        srchBtn.setText("Keresés");
        srchBtn.relocate(500,100);

        hbox.getChildren().addAll(vbox1,vbox2,vbox3,vbox4);
        textb.autosize();

        contentPane.getChildren().clear();

        contentPane.getChildren().addAll(madeMenu,hbox,srchBtn);




        srchBtn.setOnAction(event ->{
            var selRadBtn =(RadioButton)toggleGroup.getSelectedToggle();
            var selRadVal = selRadBtn.getText();
            searchWithFiltersResult(new Filter(textb.getText(), combobox.getValue().toString(),
                    selRadVal,toolsCheck.isSelected(),hangCheck.isSelected(),pluginCheck.isSelected(),
                    fajlCheck.isSelected()));
        });
    }

    private void searchWithFiltersResult(Filter filter){

    }

    @FXML
    protected void onIndexUsersClick() throws UnirestException {

        contentPane.getChildren().clear();

        var idCol = new TableColumn("ID");
        var nameCol = new TableColumn("Név");
        var emailCol = new TableColumn("Email");
        var genderCol = new TableColumn("Nem");
        var statusCol = new TableColumn("Státusz");
        var actionCol = new TableColumn("Action");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("id"));


        contentTable = new TableView<User>();

        contentTable.getColumns().addAll(idCol, nameCol, emailCol, genderCol, statusCol, actionCol);
        actionCol.setCellFactory(param -> new TableCell<Integer, Integer>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);

                if (id == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);

                deleteButton.setOnAction(
                        event -> {
                            try {

                                HttpResponse<JsonNode> response = Unirest.delete("https://gorest.co.in/public/v2/users/" + id.toString())
                                        .header("Authorization", "Bearer dad5a814f147a1aeabd5a64c90bc36f3c6ac42a5c6b05b36d4e5c4bc2c54542c")
                                        .asJson();

                                contentTable.getItems().clear();
                                setContentTable(contentTable);

                            } catch (UnirestException e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
        });

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        setContentTable(contentTable);

        contentTable.setPrefWidth(1240);
        contentTable.setPrefHeight(600);
        contentTable.relocate(0,30);

        madeMenu.relocate(300,700);
        contentPane.getChildren().add(madeMenu);

        contentPane.getChildren().add(contentTable);

        // Double click
        contentTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    User user = (User) contentTable.getSelectionModel().getSelectedItem();
                    System.out.println(user.getName());
                }
            }
        });
    }

    private void setContentTable(TableView contentTable)
    {
        try {

            HttpResponse<JsonNode> apiResponse = Unirest.get("https://gorest.co.in/public/v2/users").asJson();
            String responseJsonAsString = apiResponse.getBody().toString();

            //System.out.println(responseJsonAsString);

            User []users = new Gson().fromJson(responseJsonAsString, User[].class);

            for (User user : users) {
                contentTable.getItems().add(user);
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onCreateUserClick(ActionEvent actionEvent) {

        contentPane.getChildren().clear();

        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Defining the Name text field
        final TextField name = new TextField();
        name.setPromptText("Name");
        name.setPrefColumnCount(10);
        name.getText();
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);

        //Defining the Last Name text field
        final TextField email = new TextField();
        email.setPromptText("Email");
        GridPane.setConstraints(email, 0, 1);
        grid.getChildren().add(email);

        // gender
        final String[] genders = new String[]{"male", "female"};
        ChoiceBox gender = new ChoiceBox(FXCollections.observableArrayList(genders));
        gender.setPrefWidth(100);
        GridPane.setConstraints(gender, 0, 2);
        grid.getChildren().add(gender);
        final TextField genderHelper = new TextField();

        gender.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number value, Number newValue) {
                genderHelper.setText(genders[newValue.intValue()]);
            }
        });

        //status
        final String[] statuses = new String[]{"active", "inactive"};
        ChoiceBox status = new ChoiceBox(FXCollections.observableArrayList(statuses));
        status.setPrefWidth(100);
        GridPane.setConstraints(status, 0, 3);
        grid.getChildren().add(status);
        final TextField statusHelper = new TextField();

        status.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number value, Number newValue) {
                statusHelper.setText(statuses[newValue.intValue()]);
            }
        });

        //Defining the Submit button
        Button submit = new Button("Mentés");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        //Defining the Clear button
        Button clear = new Button("Törlés");
        GridPane.setConstraints(clear, 1, 1);
        grid.getChildren().add(clear);

        //Adding a Label
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 4);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);

        submit.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent actionEvent) {
               //https://gorest.co.in/public/v2/users

               if (
                       !name.getText().isEmpty() &&
                       !email.getText().isEmpty() &&
                       !genderHelper.getText().isEmpty() &&
                       !statusHelper.getText().isEmpty()
               ) {

                   try {
                       HttpResponse<JsonNode> response = Unirest.post("https://gorest.co.in/public/v2/users")
                               .header("Authorization", "Bearer dad5a814f147a1aeabd5a64c90bc36f3c6ac42a5c6b05b36d4e5c4bc2c54542c")
                               .field("name", name.getText())
                               .field("email", email.getText())
                               .field("gender", genderHelper.getText())
                               .field("status", statusHelper.getText())
                               .asJson();

                       System.out.println(response.getStatus());

                       label.setText(response.getStatusText() + " (" + response.getStatus() + "):" + response.getBody().toString());

                   } catch (UnirestException e) {
                        e.printStackTrace();
                   }

               } else {
                   label.setText("Minden mező kötelező");
               }
           }
        });

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                name.clear();
                email.clear();
                label.setText(null);
            }
        });

        contentPane.getChildren().add(grid);
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