package services;

import exceptions.KlantException;
import model.User;
import persistence.UserDao;

import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger L = Logger.getLogger(UserServiceImpl.class.getName());
    private static final int MIN_PASSWORD_LENGTH = 3;

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(String name, String password) throws KlantException {
        L.info("Trying to add user: " + name);

        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            L.info("Password not strong enough");
            throw new KlantException("Password not strong enough");
        }

        if (userDao.getUserByName(name) != null) {
            L.info("Username already in use!");
            throw new KlantException("Username already in use!");
        }

        userDao.addUser(new User(name, password));
    }

    public boolean login(String username, String password) throws KlantException {
        L.info("Trying to login user: " + username);

        User user = userDao.getUserByName(username);

        if (user == null) {
            return false;
        } else {
            return user.getPassword() != null && user.getPassword().equals(password);
        }
    }
}
