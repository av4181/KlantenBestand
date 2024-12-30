package services;

import model.Klant;

import java.util.List;

// de service kan klanten ophalen uit de DB of kan klanten toevoegen aan de db
public interface KlantenService {
    List<Klant> getAllKlanten();
    void addKlant(Klant klant);
}

// zowel Klanten als User service klassen kunnen in 1 generieke klasse gestoken worden

/*
public interface Service<T> {
    List<T> getAll();
    void add(T object);
}

public class ServiceImpl<T> implements Service<T> {
    // ...
}
 */