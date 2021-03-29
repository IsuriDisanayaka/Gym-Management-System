package controller;

import bo.BoFactory;
import bo.custom.CoachBo;
import bo.custom.CustomerBo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.CoachDTO;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CustomerTM;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerFromController {
    public TableView<CustomerTM> tblCust;

    public TableColumn colid;
    public TableColumn colAddress;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colGender;
    public TableColumn colReport;
    public TableColumn colOperate;
    public TextField txtSearch;
    public AnchorPane root;
    public ComboBox cmbGender;
    public TextField txtGender;
    public ComboBox cmbCoachId;
    public TextField txtCName;
    public TextField txtDate;
    public TableColumn colCoach;
    public TableColumn colPayment;
    public TableColumn colDate;
    public JFXComboBox cmbPayment;
    public JFXTextField txtReport1;
    public DatePicker pickerDate;
    public Button newOnAction;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtReport;
    public TableColumn colCoachID;
    public TableColumn colDate1;
    CoachBo cbo = BoFactory.getInstance().getBo(BoFactory.BOType.COACH);
    CustomerBo bo = BoFactory.getInstance().getBo(BoFactory.BOType.MEMBER);

    public void initialize() throws Exception {
        selectGender();
        Payment();
        getAllCoaches();
        bo = BoFactory.getInstance().getBo(BoFactory.BOType.MEMBER);
        colid.setCellValueFactory(new PropertyValueFactory<>("mId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colReport.setCellValueFactory(new PropertyValueFactory<>("report"));
        colCoachID.setCellValueFactory(new PropertyValueFactory<>("cId"));
        colCoach.setCellValueFactory(new PropertyValueFactory<>("cname"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllCustomer();
        clearFields();

        tblCust.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            setData( newValue);
        }));

    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtReport.clear();
        txtCName.clear();

    }

    private void setData(CustomerTM tm) {
        txtId.setText(tm.getmId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        cmbGender.setValue(tm.getGender());
        txtReport.setText(tm.getReport());
        cmbCoachId.setValue(tm.getcId());
        txtCName.setText(tm.getCname());
        cmbPayment.setValue(tm.getPayment());
        pickerDate.setValue(LocalDate.parse(tm.getDate()));

    }

    public void getAllCoaches() {


        try {
            ArrayList<CoachDTO> arrayList = cbo.getAllCoaches();
            for (CoachDTO s : arrayList) {
                cmbCoachId.getItems().addAll(s.getCId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @FXML
    void cmbCoachIdOnAction(ActionEvent event) {
        String coachId = String.valueOf(cmbCoachId.getValue());
        try {
            CoachDTO coach = cbo.getCoach(coachId);
            String name = coach.getName();
            txtCName.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectGender() {
        ObservableList<String> observableList = FXCollections.observableArrayList("Female", "Male");
        cmbGender.setItems(observableList);
    }

    public void Payment() {
        ObservableList<String> observableList = FXCollections.observableArrayList("Month packege(1500)", "Six Month Package(5000)", "Year Package(9000");
        cmbPayment.setItems(observableList);
    }

    private void loadAllCustomer() throws Exception {
        ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
        List<CustomerDTO> allCustomers = bo.getAllCustomers();
        for (CustomerDTO dto : allCustomers) {
            Button btn = new Button("Delete");
            CustomerTM tm = new CustomerTM(dto.getmId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getGender(), dto.getReport(), dto.getcId(),
                    dto.getCname(), dto.getPayment(), dto.getDate(), btn);
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
                        if (bo.deleteCustomer(tm.getmId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllCustomer();
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
        tblCust.setItems(tmList);
    }


    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile("^(M00)[1-9]{1}$").matcher(txtId.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^[A-z,0-9 ,/-]{10,}|[ ]$").matcher(txtAddress.getText()).matches()) {
                    if (Pattern.compile("[0-9]{10}$").matcher(txtContact.getText()).matches()) {
                        if (Pattern.compile("[A-z]{1,}$").matcher(txtReport.getText()).matches()) {
                            boolean isSaved = bo.saveCustomer(
                                    new CustomerDTO(txtId.getText(),
                                            txtName.getText(),
                                            txtAddress.getText(),
                                            txtContact.getText(),
                                            cmbGender.getValue() + "",
                                            txtReport.getText(),
                                            cmbCoachId.getValue() + "",
                                            txtCName.getText(),
                                            cmbPayment.getValue() + "",
                                            pickerDate.getValue() + "")

                            );
                        } else {
                        txtReport.setFocusColor(Paint.valueOf("red"));
                        txtReport.requestFocus();
                    }
                } else {
                    txtContact.setFocusColor(Paint.valueOf("red"));
                    txtContact.requestFocus();

                }
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


        tblCust.refresh();
        loadAllCustomer();
        clearFields();
        // if (Pattern.compile(""))

    }


    public void newOnAction(ActionEvent actionEvent) throws IOException {
        txtId.setEditable(true);
    }

    public void SearchOnAction(ActionEvent actionEvent) throws Exception {
        CustomerDTO cudto=bo.getCustomer(txtSearch.getText());
        txtId.setText(cudto.getmId());
        txtName.setText(cudto.getName());
        txtAddress.setText(cudto.getAddress());
        txtContact.setText(cudto.getContact());
        cmbGender.setValue(cudto.getGender());
        txtReport.setText(cudto.getReport());
        cmbCoachId.setValue(cudto.getcId());
        txtCName.setText(cudto.getCname());
       cmbPayment.setValue(cudto.getPayment());
       pickerDate.setValue(LocalDate.parse(cudto.getDate()));
;


    }

    public void printOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/MemberReport.jasper");
            JasperPrint jp = JasperFillManager.fillReport(is,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, true);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }}



