package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class OrderFromController {
    public AnchorPane root;
    public JFXTextField txtOrderId;
    public JFXComboBox cmbRegId;
    public JFXTextField txtDate;
    public TableView tblSale;
    public TableColumn colOrderid;
    public TableColumn colReg;
    public TableColumn colSupName;
    public TableColumn colSupPrice;
    public TableColumn colDate;

    public void PlaceOrder(ActionEvent actionEvent) {
    }

    public void getTotal(ActionEvent actionEvent) {
    }
}
