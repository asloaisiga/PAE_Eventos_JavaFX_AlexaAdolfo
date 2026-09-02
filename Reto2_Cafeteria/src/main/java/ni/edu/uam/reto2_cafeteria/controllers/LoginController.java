package ni.edu.uam.reto2_cafeteria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnEnter;

    private static final String USUARIO_VALIDO = "admin";
    private static final String CLAVE_VALIDA = "admin";

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        String usuario = txtUser.getText();
        String clave = txtPassword.getText();

        if(usuario.equals(USUARIO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/ni/edu/uam/reto2_cafeteria/cafeteria-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Recepción de café");
            stage.show();
        }

    }
}
