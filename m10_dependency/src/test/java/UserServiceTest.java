import exceptions.KlantException;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.UserDao;
import services.UserService;
import services.UserServiceImpl;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    class DummyUserDao implements UserDao {
        private Map<String, User> users = Map.of("duke", new User("duke","java"));

        @Override
        public User getUserByName(String userName) {
            return users.get(userName);
        }

        @Override
        public void addUser(User user) {
            // ??
        }
    }

    @BeforeEach
    public void setup() throws Exception {
        userService = new UserServiceImpl(new DummyUserDao());
    }

    @Test
    void addUser() {
        assertThrows(KlantException.class,
                () -> userService.addUser("jos", "12"),
        "Password not tested for strongness!");
    }

    @Test
    void login() {
        assertFalse(userService.login("niemand", "ongeldig"),
         "Login van onbestaande gebruiker moet falen");

        assertFalse(userService.login("duke", "ellington"),
        "Login met ongeldig paswoord moet falen");

        assertTrue(userService.login("duke", "java"),
        "Login van gebruiker duke met wachtwoord java moet lukken");

        assertFalse(userService.login( "Duke", "java"),
        "Login van gebruiker Duke (met hoofdletter) moet falen");
    }
}


