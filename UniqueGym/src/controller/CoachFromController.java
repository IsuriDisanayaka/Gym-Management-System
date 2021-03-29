package controller;

import bo.BoFactory;
import bo.custom.CoachBo;
import com.jfoenix.controls.JFXTextField;
import dto.CoachDTO;
import dto.ScheduleDTO;
import dto.SupplementDTO;
import dto.WorkOutDTO;
import entity.Coach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import view.tm.CoachTM;
import view.tm.ScheduleTM;
import view.tm.WorkOutTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Filter;
import java.util.regex.Pattern;

public class CoachFromController {
    public AnchorPane root;

    public Button btnSave;
    public TableColumn<CoachTM, String> colId;
    public TableColumn<CoachTM, String> colName;
    public TableColumn<CoachTM, String> colBd;
    public TableColumn<CoachTM, String> colQualify;
    public TableColumn<CoachTM, Button> colOperate;
    public Button btnUpdate;
    public TableColumn<CoachTM, Double> colSalary;

    public TableColumn<CoachTM, String> colAddress;
    public TableColumn<CoachTM, String> colcontact;
    public TableView tableCoach;
    public JFXTextField txtContact;
    public JFXTextField txtId;
    public JFXTextField txtbd;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtSalary;
    public JFXTextField txtQualification;
    public TextField txtSearch;


    CoachBo bo;
    CoachBo coachBo = BoFactory.getInstance().getBo(BoFactory.BOType.COACH);
    ObservableList<CoachDTO> allCoach = FXCollections.observableArrayList();

    public void initialize() throws Exception {
        bo = BoFactory.getInstance().getBo(BoFactory.BOType.COACH);

        colId.setCellValueFactory(new PropertyValueFactory("cId"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colBd.setCellValueFactory(new PropertyValueFactory("birthday"));
        colcontact.setCellValueFactory(new PropertyValueFactory("contact"));
        colQualify.setCellValueFactory(new PropertyValueFactory("qualification"));
        colSalary.setCellValueFactory(new PropertyValueFactory("salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));
        loadAllCoaches();
        clearFields();


        tableCoach.getSelectionModel().selectedItemProperty().
                addListener(((observable, oldValue, newValue) -> {
                    setData((CoachTM) newValue);
                }));
    }



    private void setData(CoachTM tm) {

        txtId.setText(tm.getCId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtbd.setText(tm.getBirthday());
        txtContact.setText(tm.getContact());
        txtQualification.setText(tm.getQualification());
        txtSalary.setText(tm.getSalary() + "");
    }

    private void loadAllCoaches() throws Exception {

        ObservableList<CoachTM> tmList = FXCollections.observableArrayList();
        List<CoachDTO> allCoaches = bo.getAllCoaches();
        for (CoachDTO dto : allCoaches) {

            Button btn = new Button("Delete");
            CoachTM tm = new CoachTM(dto.getCId(), dto.getName(), dto.getAddress(), dto.getBirthday(), dto.getContact(),
                    dto.getQualification(), dto.getSalary(), btn);
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
                        if (bo.deleteCoach(tm.getCId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllCoaches();
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
        tableCoach.setItems(tmList);

    }


    public void SaveCoach(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile("^(C00)[1-9]{1}$").matcher(txtId.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^[A-z,0-9 ,/-]{10,}|[ ]$").matcher(txtAddress.getText()).matches()) {
                //    if (Pattern.compile("^[0-9 -]{8}$").matcher(txtbd.getText()).matches()) {
                        if (Pattern.compile("[0-9]{10}$").matcher(txtContact.getText()).matches()) {
                            if (Pattern.compile("[A-z ]{1,}$").matcher(txtQualification.getText()).matches()) {
                                if (Pattern.compile(("[0-9][.00]{1,}$")).matcher(txtSalary.getText()).matches()) {
                                    boolean isSaved = bo.saveCoach(
                                            new CoachDTO(txtId.getText(),
                                                    txtName.getText(),
                                                    txtAddress.getText(),
                                                    txtbd.getText(),
                                                    txtContact.getText(),
                                                    txtQualification.getText(),
                                                    Double.parseDouble(txtSalary.getText()))
                                    );


                                } else {
                                    txtSalary.setFocusColor(Paint.valueOf("red"));
                                    txtSalary.requestFocus();
                                }
                            } else {
                                txtQualification.setFocusColor(Paint.valueOf("red"));
                                txtQualification.requestFocus();

                            }
                        } else {
                            txtContact.setFocusColor(Paint.valueOf("red"));
                            txtContact.requestFocus();
                        }
//                    } else {
//                        txtbd.setFocusColor(Paint.valueOf("red"));
//                        txtbd.requestFocus();
//                    }
                } else {
                    txtAddress.setFocusColor(Paint.valueOf("red"));
                    txtAddress.requestFocus();
                }
            } else {
                txtName.setFocusColor(Paint.valueOf("red"));
                txtName.requestFocus();
            }

        } else {

            txtId.setFocusColor(Paint.valueOf("red"));
            txtId.requestFocus();
        }


        tableCoach.refresh();
        loadAllCoaches();



    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtbd.clear();
        txtContact.clear();
        txtQualification.clear();
        txtSalary.clear();
    }
    public void getDataOnAction(ActionEvent actionEvent) {
    }

    public void SearchOnAction(ActionEvent actionEvent) throws Exception {
CoachDTO cDTO = bo.getCoach(txtSearch.getText());
        txtId.setText(cDTO.getCId());
        txtName.setText(cDTO.getName());
        txtAddress.setText(cDTO.getAddress());
        txtbd.setText(cDTO.getBirthday());
        txtContact.setText(cDTO.getContact());
        txtQualification.setText(cDTO.getQualification());
        txtSalary.setText(String.valueOf(cDTO.getSalary()));




    }

    public void Get(ActionEvent actionEvent) {
        clearFields();
        txtId.setEditable(true);
    }
}