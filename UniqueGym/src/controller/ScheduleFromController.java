package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ScheduleBo;
import bo.custom.WorkOutBo;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.CoachDTO;
import dto.CustomerDTO;
import dto.ScheduleDTO;
import dto.WorkOutDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.ScheduleTM;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;



public class ScheduleFromController {
    public AnchorPane root;
    public ComboBox cmbRId;
    public ComboBox cmbWId;
    public TableView<ScheduleTM> tblShedule;
    public TableColumn colWid;
    public TableColumn colWName;
    public TableColumn colSet;
    public TableColumn colOperate;
    public JFXTextField txtSheduleId;
    public JFXTextField txtName;
    public JFXTextField txtSets;
    public DatePicker pckerDate;
    public TableColumn colShedule;
    public TableColumn colDate;
    public TableColumn colmember;

    ScheduleBo bo = BoFactory.getInstance().getBo(BoFactory.BOType.SHEDULE);
    WorkOutBo wbo = BoFactory.getInstance().getBo(BoFactory.BOType.WORKOUT);
    CustomerBo cbo = BoFactory.getInstance().getBo(BoFactory.BOType.MEMBER);
    ObservableList<ScheduleTM>tmList =FXCollections.observableArrayList();
    public void initialize() throws Exception {

       colShedule.setCellValueFactory(new PropertyValueFactory<>("schId"));
        colmember.setCellValueFactory(new PropertyValueFactory<>("mId"));
        colWid.setCellValueFactory(new PropertyValueFactory<>("wId"));
        colWName.setCellValueFactory(new PropertyValueFactory<>("wName"));
       colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colSet.setCellValueFactory(new PropertyValueFactory<>("setRound"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        getAllWorkOuts();
        getAllMemebers();
        loadAllSchedules();

        tblShedule.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData((ScheduleTM) newValue);
        });



    }
    private void setData(ScheduleTM tm) {
        txtSheduleId.setText(tm.getSchId());
        cmbRId.setValue(tm.getmId());
        cmbWId.setValue(tm.getwId());
        txtName.setText(tm.getwName());
        pckerDate.setValue(LocalDate.parse(tm.getDate()));
        txtSets.setText(tm.getSetRound());
    }


    private void loadAllSchedules() throws Exception {
        ObservableList<ScheduleTM> tmList = FXCollections.observableArrayList();
        List<ScheduleDTO> allSchedules = bo.getAllSchedules();
        for (ScheduleDTO dto : allSchedules) {

            Button btn = new Button("Delete");
            ScheduleTM tm = new ScheduleTM(dto.getSchId(), dto.getmId(), dto.getwId(), dto.getwName(), dto.getDate(),
                    dto.getSetRound(), btn);
            tmList.add(tm);

            btn.setOnAction((e) -> {
                try {

                    ButtonType ok = new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {
                        if (bo.deleteSchedule(tm.getSchId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllSchedules();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again", ButtonType.OK).show();
                    } else {

                    }


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }
        tblShedule.setItems(tmList);
    }


WorkOutBo workOutBo=BoFactory.getInstance().getBo(BoFactory.BOType.WORKOUT);

    public void SaveOnAction(ActionEvent actionEvent) throws Exception {
       if(Pattern.compile("^(S00)[1-9]{1}$").matcher(txtSheduleId.getText()).matches()) {
           if (Pattern.compile("([A-Z]|[0-9])[*,]{1,}$").matcher(txtSets.getText()).matches()) {
               boolean isSaved =bo.saveSchedule(
                       new ScheduleDTO(txtSheduleId.getText(),
                               cmbRId.getValue()+"",
                               cmbWId.getValue()+"",
                               workOutBo.getWorkOut(String.valueOf(cmbWId.getValue())).getName(),
                               pckerDate.getValue()+"",
                               txtSets.getText())
               );
           } else {
               txtSets.setFocusColor(Paint.valueOf("red"));
               txtSets.requestFocus();
           }
       }else {
           txtSheduleId.setFocusColor(Paint.valueOf("red"));
       }


        tblShedule.refresh();
        loadAllSchedules();

    }

    public void getAllWorkOuts() throws Exception {

        ObservableList<String>observableList =FXCollections.observableArrayList();
        ArrayList<WorkOutDTO>arrayList =wbo.getAllWorkOuts();
        for (WorkOutDTO  s:arrayList){
            observableList.add(s.getWid());
        }
        cmbWId.setItems(observableList);
    }

    public void getAllMemebers() throws Exception {
        ObservableList<String>observableList =FXCollections.observableArrayList();
        ArrayList<CustomerDTO>arrayList =cbo.getAllCustomers();
        for (CustomerDTO s:arrayList){
            observableList.add(s.getmId());
        }
        cmbRId.setItems(observableList);
    }


    public void DeleteOnAction(ActionEvent actionEvent) {
    }

    public void go(MouseEvent mouseEvent) throws IOException {
//        Stage stage =(Stage)root.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/UserFrom.fxml"))));

    }


    public void getDataOnAction(ActionEvent actionEvent) {



    }


    public void GEtReport(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/WorkOutReport.jasper");
            JasperPrint jp = JasperFillManager.fillReport(is, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, true);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void WIDOnAction(ActionEvent actionEvent) {
        String workoutId = String.valueOf(cmbWId.getValue());
        try {
            WorkOutDTO work = workOutBo.getWorkOut(workoutId);
            String name = work.getName();
            txtName.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


