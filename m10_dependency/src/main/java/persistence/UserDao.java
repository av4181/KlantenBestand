package persistence;

import model.User;

// Info gaat van View naar UserService naar de database (tabel USERS) en vice versa
public interface UserDao {
    User getUserByName(String name);
    void addUser(User user);
}
