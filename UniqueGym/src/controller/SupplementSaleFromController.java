package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.PlaceOrderBo;
import bo.custom.SupplementBo;
import bo.custom.SupplementSaleBo;
import bo.custom.impl.SupplementBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.*;
import entity.Customer;
import entity.Supplement;
import entity.SupplementSale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.SupplementSaleTM;
import view.tm.SupplementTM;
import view.tm.WorkOutTM;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class SupplementSaleFromController {
    
    public TextField txtPrice;
    public TableView<SupplementSaleTM> tblSale;
    public TableColumn<SupplementSaleTM,String> colSupId;
    public TableColumn <SupplementSaleTM,String>colSupName;
    public TableColumn <SupplementSaleTM,String>colDate;
    public JFXTextField txtQty;
    public DatePicker pickerDate;
    public TableColumn<SupplementSaleTM,String> colOrder;
    public TableColumn <SupplementSaleTM,String>colQty;
    public TextField txtTotal;
    public JFXComboBox cmbCust;
    public JFXComboBox cmbSupplement;
    public TableColumn <SupplementSaleTM,String>colCustId;
    public TableColumn <SupplementSaleTM,Double>colPrice;
    public JFXTextField txtOrder;
    public JFXTextField txtSupName;
    public AnchorPane root;
    SupplementBo supplementBo=BoFactory.getInstance().getBo(BoFactory.BOType.SUPPLEMENT);;
    CustomerBo customerBo= BoFactory.getInstance().getBo(BoFactory.BOType.MEMBER);
    PlaceOrderBo placeOrderBo= BoFactory.getInstance().getBo(BoFactory.BOType.PLACEORDER);
SupplementSaleBo bo = BoFactory.getInstance().getBo(BoFactory.BOType.SUPPLEMENTSALE);
    ObservableList<SupplementSaleDTO> allSupplementSale = FXCollections.observableArrayList();

    private double total;

    public void initialize() throws Exception {

       loadMembers();
       loadSupplement();
     loadAllSupplementSales();
        colOrder.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tblSale.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            setData((SupplementSaleTM) newValue);
        }));
    }

    public void loadSupplement() {
        try {
            ArrayList<SupplementDTO> arrayList = supplementBo.getAllSupplement();
            for(SupplementDTO s :arrayList) {
                cmbSupplement.getItems().addAll(s.getSid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void setData(SupplementSaleTM tm) {
        txtOrder.setText(tm.getOrderId());
        cmbSupplement.setValue(tm.getsId());
        txtSupName.setText(tm.getsNmae());
        txtQty.setText(tm.getQty());
        txtPrice.setText(String.valueOf(tm.getPrice()));
        pickerDate.setValue(LocalDate.parse(tm.getDate()));
        cmbCust.setValue(tm.getCustID());
    }
    public void loadAllSupplementSales() throws Exception {
        ObservableList<SupplementSaleTM> tmList = FXCollections.observableArrayList();
        List<SupplementSaleDTO> allSupplementsales = bo.getAllSupplementSale();
        for (SupplementSaleDTO dto : allSupplementsales) {
           tmList.add(new SupplementSaleTM(dto.getOrderId(),dto.getsId(),dto.getsNmae(),dto.getQty(),dto.getPrice(),dto.getDate(),dto.getCustID()));

        tblSale.setItems(tmList);
    }
}


    @FXML
  public   void getPrice(ActionEvent actionEvent) {
        String suppName = String.valueOf(cmbSupplement.getValue());

        try {
            SupplementDTO supplement = supplementBo.getSupplement(suppName);
            double price = supplement.getPrice();
            txtPrice.setText(String.valueOf(price));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void getName(ActionEvent actionEvent) {
        String suppId = String.valueOf(cmbSupplement.getValue());
        String suppName = String.valueOf(cmbSupplement.getValue());
        try {
            SupplementDTO supplement = supplementBo.getSupplement(suppId);
            String name = supplement.getName();
            txtSupName.setText(name);
            supplement = supplementBo.getSupplement(suppName);
            double price = supplement.getPrice();
            txtPrice.setText(String.valueOf(price));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void loadMembers() throws Exception {

            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<CustomerDTO>arrayList =customerBo.getAllCustomers();
            for (CustomerDTO  c:arrayList){
                observableList.add(c.getmId());
            }
            cmbCust.setItems(observableList);
        }






    private List<SupplementSaleDTO> getAllSupplementDetail;
    private List<SupplementDTO> getAllSupplement;

    public void SaveOnAction(ActionEvent actionEvent) {
    }

    public void getTotal(ActionEvent actionEvent) {
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        total= (price * qty);
        txtTotal.setText(total+"");
    }


    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/AdminFrom.fxml"))));
    }

    public void Again(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/UserFrom.fxml"))));
    }

    public void placeOrder(ActionEvent actionEvent) throws Exception {
        try {
            String supplimentID=cmbSupplement.getValue().toString();
            SupplementSaleDTO dto=new SupplementSaleDTO(txtOrder.getText(),cmbSupplement.getValue().toString(),txtSupName.getText(),txtQty.getText(), parseDouble(txtPrice.getText()),pickerDate.getValue()+"",cmbCust.getValue().toString());

            boolean isSaved=placeOrderBo.updateSupplementSale(dto,supplimentID);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Schedule details saved successfully!!!").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Please select a valid Date!!!").show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        tblSale.refresh();
       loadAllSupplementSales();
    }


    public void print(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/SupplementReport.jasper");
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


}

