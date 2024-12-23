package view;

import exceptions.KlantException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Klant;
import model.KlantType;
import services.KlantenService;

import java.time.LocalDate;
import java.util.List;

public class KlantenPresenter {

    private final KlantenView view;
    private final KlantenService klantenService;

    public KlantenPresenter(KlantenView view, KlantenService klantenService) {
        this.view = view;
        this.klantenService = klantenService;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getBtnAdd().setOnAction(event -> {
            try {
                klantenService.addKlant(createKlant());
                updateView();
            } catch (KlantException e) {
                showAlert(e.getMessage());
            }
        });
    }

    private Klant createKlant() {
        String voornaam = view.getTfVoornaam().getText();
        String achternaam = view.getTfAchternaam().getText();
        String email = view.getTfEmail().getText();
        KlantType type = view.getCbType().getValue();
        double btw = Double.parseDouble(view.getTfBtw().getText());
        LocalDate aanmaakDatum = view.getDpAanmaakDatum().getValue();
        boolean redflag = view.getCbRedflag().isSelected();

        return new Klant(voornaam, achternaam, email, type, btw, aanmaakDatum, redflag);
    }

    private void updateView() {
        try {
            List<Klant> klanten = klantenService.getAllKlanten();
            ObservableList<Klant> klantData = FXCollections.observableArrayList(klanten);
            view.getTableView().setItems(klantData);
        } catch (KlantException e) {
            showAlert(e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
