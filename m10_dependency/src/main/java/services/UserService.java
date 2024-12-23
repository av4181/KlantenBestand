package services;

import exceptions.KlantException;

// de service kan (info over) users ophalen uit de DB of kan users toevoegen aan de db
public interface UserService {
    void addUser(String name, String password) throws KlantException;
    boolean login(String username, String password) throws KlantException;
}
