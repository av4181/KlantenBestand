package services;

import model.Klant;

import java.util.List;

// de service kan klanten ophalen uit de DB of kan klanten toevoegen aan de db
public interface KlantenService {
    List<Klant> getAllKlanten();
    void addKlant(Klant klant);
}
