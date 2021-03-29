package controller;

import com.jfoenix.controls.JFXTextField;
import bo.BoFactory;
import bo.custom.WorkOutBo;
import dto.CustomerDTO;
import dto.WorkOutDTO;
import javafx.scene.paint.Paint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;;
import view.tm.WorkOutTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class WorkOutFromController {
    public AnchorPane root;

    public TableColumn<WorkOutTM, String> colId;
    public TableColumn<WorkOutTM, String> colName;
    public TableColumn<WorkOutTM, Button> colOperate;
    public TextField txtSearch;
    public TableColumn<WorkOutTM, String> colDes;
    public JFXTextField txtDes;
    public TableView tblWorkOut;
    public JFXTextField txtName;
    public JFXTextField txtId;
    WorkOutBo bo;
    ObservableList<WorkOutTM> tmList = FXCollections.observableArrayList();

    public void initialize() throws Exception {
        bo = BoFactory.getInstance().getBo(BoFactory.BOType.WORKOUT);
        colId.setCellValueFactory(new PropertyValueFactory<>("wid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllWorkOuts();

        tblWorkOut.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            setData((WorkOutTM) newValue);
        }));
        Search();


    }

    private void setData(WorkOutTM tm) {
        txtId.setText(tm.getWid());
        txtName.setText(tm.getName());
        txtDes.setText(tm.getDescription());
    }

    private void loadAllWorkOuts() throws Exception {
        ObservableList<WorkOutTM> tmList = FXCollections.observableArrayList();
        List<WorkOutDTO> allWorkOuts = bo.getAllWorkOuts();
        for (WorkOutDTO dto : allWorkOuts) {

            Button btn = new Button("Delete");
            WorkOutTM tm = new WorkOutTM(dto.getWid(), dto.getName(),
                    dto.getDescription(), btn);
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
                        if (bo.deleteWorkOut(tm.getWid())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllWorkOuts();
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
        tblWorkOut.setItems(tmList);
    }

    private void Search() {
        FilteredList<WorkOutTM> filteredListdata = new FilteredList<>(tmList, e -> true);
        txtSearch.setOnKeyReleased(e -> {
            txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListdata.setPredicate((Predicate<? super WorkOutTM>) part -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowercasefilter = newValue.toLowerCase();
                    if (part.getName().contains(newValue)) {

                        return true;
                    } else if (part.getWid().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    } else if (part.getName().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    }
                    return false;
                });
            }));
            SortedList<WorkOutTM> sortedList = new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblWorkOut.comparatorProperty());
            tblWorkOut.setItems(sortedList);
        });
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile("^(W00)[1-9]{1}$").matcher(txtId.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtName.getText()).matches()) {
                if(Pattern.compile("^[A-z,0-9 ,/-]{10,}|[ ]$").matcher(txtDes.getText()).matches()) {
                    boolean isSaved = bo.saveWorkOut(
                            new WorkOutDTO(txtId.getText(),
                                    txtName.getText(),
                                    txtDes.getText())
                    );
                }else{
                    txtDes.setFocusColor(Paint.valueOf("red"));
                    txtDes.requestFocus();
                }
            } else {
                txtName.setFocusColor(Paint.valueOf("red"));
                txtName.requestFocus();
            }

        } else {

            txtId.setFocusColor(Paint.valueOf("red"));
            txtId.requestFocus();
        }
        tblWorkOut.refresh();
        loadAllWorkOuts();


    }

    public void getDataOnAction(ActionEvent actionEvent) {
    }

    public void SearchoNAction(ActionEvent actionEvent) throws Exception {
        WorkOutDTO dto = bo.getWorkOut(txtSearch.getText());
        txtId.setText(dto.getWid());
        txtName.setText(dto.getName());
        txtDes.setText(dto.getDescription());

    }

}