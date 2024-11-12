package persist;

import model.Klant;

import java.util.List;

public interface KlantDao {
    boolean insert(Klant klant);
    boolean verwijder(String achternaam, String voornaam);
    boolean update(Klant klant);
    Klant retrieve(String achternaam);
    List<Klant> sortedOn(String query);
    List<Klant> sortedOnAchternaam();
    List<Klant> sortedOnAanmaakDatum();
    List<Klant> sortedOnBtw();

}
