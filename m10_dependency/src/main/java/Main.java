import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.hsql.HSQLUserDao;
import services.UserServiceImpl;
import view.LoginView;
import view.LoginPresenter;

// enkel login, de tabel met klanten volgt op het login scherm (zie loginpresenter)
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();
        UserServiceImpl userService = new UserServiceImpl(new HSQLUserDao());
        Scene scene = new Scene(loginView);
        new LoginPresenter(scene, loginView, userService);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}