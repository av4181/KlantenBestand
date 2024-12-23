package view;

import exceptions.KlantException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import persistence.KlantDao;
import persistence.hsql.HSQLKlantDao;
import services.KlantenService;
import services.KlantenServiceImpl;
import services.UserServiceImpl;

import java.util.logging.Logger;

// Login Schermpje
public class LoginPresenter {

    private static final Logger L = Logger.getLogger(LoginPresenter.class.getName());
    private LoginView loginView;
    private UserServiceImpl userService;
    private Scene scene;

    public LoginPresenter(Scene scene, LoginView loginView, UserServiceImpl userService) {
        this.scene = scene;
        this.loginView = loginView;
        this.userService = userService;
        addEventHandlers();
    }

    private void addEventHandlers() {
        loginView.getBtnAdd().setOnAction(event -> {
            L.info("Button add clicked...");
            try {
                userService.addUser(loginView.getTfUser().getText(), loginView.getPfPassword().getText());
            } catch (KlantException e) { // Gebruik KlantException
                new Alert(Alert.AlertType.ERROR,
                        "Er was een probleem bij het toevoegen van de gebruiker:\n" + e.getMessage()).showAndWait();
            }
        });

        loginView.getBtnLogin().setOnAction(event -> {
            L.info("Button login clicked...");
            try {
                boolean result = userService.login(loginView.getTfUser().getText(), loginView.getPfPassword().getText());
                L.info("Result of login: " + result);
                if (result) {
                    KlantDao klantDao = HSQLKlantDao.getInstance("db/klantentabel");
                    KlantenService klantenService = new KlantenServiceImpl(klantDao);
                    KlantenView klantenView = new KlantenView();
                    new KlantenPresenter(klantenView, klantenService);

                    scene.setRoot(klantenView);
                    scene.getWindow().sizeToScene();
                }
            } catch (KlantException e) {
                new Alert(Alert.AlertType.ERROR,
                        "Er was een probleem bij het inloggen:\n" + e.getMessage()).showAndWait();
            }
        });
    }
}
