package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// Login schermpje
public class LoginView extends HBox {

    private TextField tfUser;
    private PasswordField pfPassword;
    private Button btnAdd;
    private Button btnLogin;

    public LoginView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        tfUser = new TextField();
        pfPassword = new PasswordField();
        btnAdd = new Button("Add");
        btnLogin = new Button("Login");
    }

    private void layoutNodes() {
        VBox inputBox = new VBox(10,
                new Label("User:"), tfUser,
                new Label("Password:"), pfPassword,
                new HBox(10, btnAdd, btnLogin));
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(20));

        this.getChildren().add(inputBox);
        this.setAlignment(Pos.CENTER);
    }

    TextField getTfUser() {
        return tfUser;
    }

    PasswordField getPfPassword() {
        return pfPassword;
    }

    Button getBtnAdd() {
        return btnAdd;
    }

    Button getBtnLogin() {
        return btnLogin;
    }
}
