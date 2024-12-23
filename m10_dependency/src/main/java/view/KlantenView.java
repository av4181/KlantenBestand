package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Klant;
import model.KlantType;

import java.time.LocalDate;
import java.util.List;

public class KlantenView extends BorderPane {

    private TableView<Klant> tableView;
    private TextField tfVoornaam;
    private TextField tfAchternaam;
    private TextField tfEmail;
    private ComboBox<KlantType> cbType;
    private TextField tfBtw;
    private DatePicker dpAanmaakDatum;
    private CheckBox cbRedflag;
    private Button btnAdd;

    public KlantenView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        tableView = new TableView<>();
        tfVoornaam = new TextField();
        tfAchternaam = new TextField();
        tfEmail = new TextField();
        cbType = new ComboBox<>();
        tfBtw = new TextField();
        dpAanmaakDatum = new DatePicker();
        cbRedflag = new CheckBox();
        btnAdd = new Button("Add");
    }

    private void layoutNodes() {
        // Java FX TableView fancy schmancy
        TableColumn<Klant, String> voornaamCol = new TableColumn<>("Voornaam");
        voornaamCol.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        TableColumn<Klant, String> achternaamCol = new TableColumn<>("Achternaam");
        achternaamCol.setCellValueFactory(new PropertyValueFactory<>("achternaam"));
        TableColumn<Klant, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Klant, KlantType> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Klant, Double> btwCol = new TableColumn<>("BTW");
        btwCol.setCellValueFactory(new PropertyValueFactory<>("btw"));
        TableColumn<Klant, LocalDate> aanmaakDatumCol = new TableColumn<>("Aanmaakdatum");
        aanmaakDatumCol.setCellValueFactory(new PropertyValueFactory<>("aanmaakDatum"));
        TableColumn<Klant, Boolean> redflagCol = new TableColumn<>("Redflag");
        redflagCol.setCellValueFactory(new PropertyValueFactory<>("redflag"));
        tableView.getColumns().addAll(voornaamCol, achternaamCol, emailCol, typeCol, btwCol, aanmaakDatumCol, redflagCol);

        // Invoer velden
        cbType.getItems().setAll(KlantType.values());
        HBox inputBox = new HBox(10,
                new Label("Voornaam:"), tfVoornaam,
                new Label("Achternaam:"), tfAchternaam,
                new Label("Email:"), tfEmail,
                new Label("Type:"), cbType,
                new Label("BTW:"), tfBtw,
                new Label("Aanmaakdatum:"), dpAanmaakDatum,
                new Label("Redflag:"), cbRedflag,
                btnAdd);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10));

        // Vertical grid layout
        VBox vBox = new VBox(10, tableView, inputBox);
        vBox.setPadding(new Insets(10));
        this.setCenter(vBox);
    }

    TableView<Klant> getTableView() {
        return tableView;
    }

    TextField getTfVoornaam() {
        return tfVoornaam;
    }

    TextField getTfAchternaam() {
        return tfAchternaam;
    }

    TextField getTfEmail() {
        return tfEmail;
    }

    ComboBox<KlantType> getCbType() {
        return cbType;
    }

    TextField getTfBtw() {
        return tfBtw;
    }

    DatePicker getDpAanmaakDatum() {
        return dpAanmaakDatum;
    }

    CheckBox getCbRedflag() {
        return cbRedflag;
    }

    Button getBtnAdd() {
        return btnAdd;
    }

    // Methode om de TableView te updaten met een lijst van klanten
    void updateTableView(List<Klant> klanten) {
        ObservableList<Klant> klantData = FXCollections.observableArrayList(klanten);
        tableView.setItems(klantData);
    }
}
