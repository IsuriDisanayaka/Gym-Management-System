package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.beans.AppletInitializer;
import java.io.IOException;
import java.net.URL;

public class LoginFromController{
    public AnchorPane root;
    public TextField txtPassword;
    public TextField txtEmail;
    public Button btnExit;
    private Button btnLogin;

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();
        if (email.length() > 0 && password.length() > 0) {
            if (email.equalsIgnoreCase("i") &&
                    password.equalsIgnoreCase("1234")) {
                URL resourse = this.getClass().getResource("/view/AdminFrom.fxml");
                Parent load = FXMLLoader.load(resourse);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }else if (email.length() > 0 && password.length() > 0) {
                if (email.equalsIgnoreCase("j") &&
                        password.equalsIgnoreCase("5678")) {
                    URL resourse = this.getClass().getResource("/view/UserFrom.fxml");
                    Parent load = FXMLLoader.load(resourse);
                    Scene scene = new Scene(load);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!!",
                            ButtonType.OK, ButtonType.NO).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!!",
                        ButtonType.OK, ButtonType.NO).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty!!",
                    ButtonType.OK, ButtonType.NO).show();
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }



}

