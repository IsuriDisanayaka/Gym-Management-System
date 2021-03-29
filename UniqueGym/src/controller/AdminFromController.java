package controller;

import bo.BoFactory;
import bo.custom.CoachBo;
import bo.custom.CustomerBo;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminFromController   {

    public AnchorPane mainRoot;
    public Button btnBack;
    public AnchorPane Lroot;
    public Label lblDate;
    public AnchorPane rootFrom;
    public Label LblTime;
    public Label lblTotCustomer;
    public Label lblTotCustomer1;
    public Label lblTotCoach;
    private LineChart<?, ?> chrt;

//    @FXML
//    private Label lblTotCustomer;
//    @FXML
//    private Label lblTotCoach;

    CustomerBo bo = BoFactory.getInstance().getBo(BoFactory.BOType.MEMBER);
    CoachBo cbo =BoFactory.getInstance().getBo(BoFactory.BOType.COACH);

    public void initialize() throws IOException {
       // setChart();
       // initUi("AdminFrom.fxml");
       // setChart();
        lblDate.setText((String.valueOf(LocalDate.now())));
        setLblTime();
        getTot();
      //  getTotal();
    }
  /*  private void setChart() {
        try {
            XYChart.Series s = new XYChart.Series<>();
            s.setName("Customer");
            // ArrayList<Integer>qty =
            s.getData().add(new XYChart.Data<>("1", 20));
            s.getData().add(new XYChart.Data<>("2", 30));
            s.getData().add(new XYChart.Data<>("3", 20));
            s.getData().add(new XYChart.Data<>("4", 10));
            s.getData().add(new XYChart.Data<>("5", 18));
            s.getData().add(new XYChart.Data<>("6", 13));
            s.getData().add(new XYChart.Data<>("7", 18));

            XYChart.Series s2 = new XYChart.Series<>();
            s2.setName("Subliment");
            s2.getData().add(new XYChart.Data<>("1", 10));
            s2.getData().add(new XYChart.Data<>("2", 20));
            s2.getData().add(new XYChart.Data<>("3", 15));
            s2.getData().add(new XYChart.Data<>("4", 10));
            s2.getData().add(new XYChart.Data<>("5", 13));
            s2.getData().add(new XYChart.Data<>("6", 10));
            s2.getData().add(new XYChart.Data<>("7", 24));

            chrt.getData().addAll(s, s2);
        }catch (Exception e){
            System.out.println(e);
        }
//
//        chrt.getData().addAll(s, s2);
//    }catch (Exception e){
//        System.out.println(e);
    }*/




    public void getTot(){
        try {
            lblTotCustomer.setText(String.valueOf(bo.getCustomerCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {lblTotCoach.setText(String.valueOf(cbo.getCoachCount()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getTotal(){
        try {lblTotCoach.setText(String.valueOf(cbo.getCoachCount()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setLblTime() {
        Timeline timeline =new Timeline(new KeyFrame(Duration.ZERO,e -> {
            DateTimeFormatter formatter =DateTimeFormatter.ofPattern("hh:mm:ss");
            LblTime.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }




    public void initUi(String location) {

        try {
            this.mainRoot.getChildren().clear();
            this.mainRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/"+location)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getMemberFrom(ActionEvent actionEvent) throws IOException {
        initUi("CustomerFrom.fxml");


    }


    public void getSupplementFrom(ActionEvent actionEvent) throws IOException {
        initUi("SublimentFrom.fxml");
    }

    public void getSellSupForm(ActionEvent actionEvent) throws IOException {
        initUi("SupplementSaleFrom.fxml");
    }

    public void getCoachForm(ActionEvent actionEvent) throws IOException {
        initUi("CoachFrom.fxml");
    }

    public void getScheduleFrom(ActionEvent actionEvent) throws IOException {
        initUi("ScheduleFrom.fxml");
    }



    public void back(MouseEvent mouseEvent) throws IOException {
    Stage stage =(Stage)Lroot.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginFrom.fxml"))));
    }

    public void getWorkOutFrom(ActionEvent actionEvent) throws IOException {
        initUi("WorkOutFrom.fxml");
    }


    public void getOrderFrom(ActionEvent actionEvent) {
        initUi("OrderFrom.fxml");
    }
}
