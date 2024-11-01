package persist;

import model.Klant;

import java.util.List;

public interface KlantDao {
    boolean insert(Klant klant);
    boolean delete(String achternaam);
    boolean update(Klant klant);
    Klant retrieve(String achternaam);
    List<Klant> sortedOn(String query);
}
