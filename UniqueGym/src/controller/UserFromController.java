package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class UserFromController {

    public AnchorPane secondRoot;
    public AnchorPane UserRoot;
    public AnchorPane root;

    public void initUi(String location) throws IOException {
        this.UserRoot.getChildren().clear();
        this.UserRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/"+location)));

    }

    public void getMember(ActionEvent actionEvent) throws IOException {
        initUi("CustomerFrom.fxml");
    }

    public void sellsub(ActionEvent actionEvent) throws IOException {
        initUi("SupplementSaleFrom.fxml");
    }

    public void getSchedule(ActionEvent actionEvent) throws IOException {
        initUi("ScheduleFrom.fxml");
    }

    public void getWorkout(ActionEvent actionEvent) throws IOException {
        initUi("WorkOutFrom.fxml");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage)UserRoot.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginFrom.fxml"))));
    }
}
