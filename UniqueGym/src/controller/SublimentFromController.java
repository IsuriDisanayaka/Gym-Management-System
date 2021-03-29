package controller;

import bo.BoFactory;

import bo.custom.SupplementBo;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.SupplementDTO;
import entity.Supplement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import view.tm.CustomerTM;
import view.tm.SupplementTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SublimentFromController {
    public AnchorPane root;

    public Button btnDelete;
    public Button btnSave;
    public TableColumn<SupplementTM,String> colId;
    public TableColumn<SupplementTM,String> colName;
    public TableColumn<SupplementTM,String> colMDate;
    public TableColumn<SupplementTM,String> colEDate;
    public TableColumn<SupplementTM,Button> colOperate;
    public TableColumn<SupplementTM,Double> colPrice;

    public TableView<SupplementTM> supplementTbl;
    public JFXTextField txtQty;
    public TableColumn <SupplementTM,String> colQty;
    public DatePicker pickerMdate;
    public DatePicker pickerEdate;
    public TextField txtSearch;
    public JFXTextField txtId;
    public JFXTextField txtPrice;
    public JFXTextField txtName;

    SupplementBo bo;


    public void initialize() throws Exception {
       bo =BoFactory.getInstance().getBo(BoFactory.BOType.SUPPLEMENT);
       colId.setCellValueFactory(new PropertyValueFactory<>("sid"));
       colName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
       colMDate.setCellValueFactory(new PropertyValueFactory<>("mDate"));
       colEDate.setCellValueFactory(new PropertyValueFactory<>("exDate"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

  loadAllSupplements();

  supplementTbl.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
      setData((SupplementTM)newValue);
  }));


    }



    private void setData(SupplementTM tm) {
       txtId.setText(tm.getSid());
        txtName.setText(tm.getName());
      txtPrice.setText(tm.getPrice()+"");
      txtQty.setText(tm.getQty());
      pickerMdate.setValue(LocalDate.parse(tm.getmDate()));
        pickerEdate .setValue(LocalDate.parse(tm.getExDate()));

   }


    private void loadAllSupplements() throws Exception {
        ObservableList<SupplementTM> tmList = FXCollections.observableArrayList();
        List<SupplementDTO> allSupplements = bo.getAllSupplement();
        for (SupplementDTO dto : allSupplements) {

            Button btn = new Button("Delete");
            SupplementTM tm = new SupplementTM(dto.getSid(),dto.getName(),dto.getPrice(),dto.getQty(),dto.getmDate(),dto.getExDate(),btn);
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
                        if (bo.deleteSupplement(tm.getSid())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllSupplements();
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
        supplementTbl.setItems(tmList);
    }
    public void SaveOnAction(ActionEvent actionEvent) throws Exception {

        if (Pattern.compile("^(S00)[1-9]{1}$").matcher(txtId.getText()).matches()) {
            if(Pattern.compile("^[A-z]{1,}$").matcher(txtName.getText()).matches()) {
                    if (Pattern.compile("[0-9]{1,}$").matcher(txtPrice.getText()).matches()) {
                        if (Pattern.compile("([A-z]|[0-9])$").matcher(txtQty.getText()).matches()) {
                            boolean isSaved =bo.saveSupplement(
                                    new SupplementDTO(txtId.getText(),
                                            txtName.getText(),
                                            Double.parseDouble(txtPrice.getText()),
                                            txtQty.getText(),
                                            pickerMdate.getValue()+"",
                                            pickerEdate.getValue()+"")
                            );

                    } else {
                        txtQty.setFocusColor(Paint.valueOf("red"));
                        txtQty.requestFocus();
                    }
                }else{
                    txtPrice.setFocusColor(Paint.valueOf("red"));
                    txtPrice.requestFocus();

                }
            }else{
                txtName.setFocusColor(Paint.valueOf("red"));
                txtName.requestFocus();
            }

        } else {
            txtId.setFocusColor(Paint.valueOf("red"));
            txtId.requestFocus();
        }



        supplementTbl.refresh();
        loadAllSupplements();
    }


    public void getDataOnAction(ActionEvent actionEvent) {
    }

    public void SearchOnAction(ActionEvent actionEvent) throws Exception {

        SupplementDTO sDTO = bo.getSupplement(txtSearch.getText());
        txtId.setText(sDTO.getSid());
        txtName.setText(sDTO.getName());
        txtPrice.setText(String.valueOf(sDTO.getPrice()));
        txtQty.setText(sDTO.getQty());
        pickerMdate.setValue(LocalDate.parse(sDTO.getmDate()));
        pickerEdate.setValue(LocalDate.parse(sDTO.getExDate()));
    }

    public void Get(ActionEvent actionEvent) {
        txtId.setEditable(true);
    }
}
