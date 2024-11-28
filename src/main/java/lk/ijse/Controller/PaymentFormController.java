/*
package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.DTO.PaymentDto;
import lk.ijse.DTO.tm.RegistrationTM;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.entity.Registration;
import lk.ijse.DTO.tm.PayementTm;

import java.io.IOException;
import java.util.List;

public class PaymentFormController {
   PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

    @FXML
    private ComboBox<Registration> cmb_Registr_ID;

    @FXML
    private TableColumn<?, ?> col_Amount;

    @FXML
    private TableColumn<?, ?> col_FullCourse_fee;

    @FXML
    private TableColumn<Double, PayementTm> col_balance;

    @FXML
    private TableColumn<Double, PayementTm> col_paid_Amount;

    @FXML
    private TableColumn<String, PayementTm> col_payment_ID;

    @FXML
    private TableColumn<String, RegistrationTM > col_registr_ID;

    @FXML
    private AnchorPane registrPane;

    @FXML
    private TableView<PayementTm> tbl_registr;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaidAmount;

    @FXML
    private TextField txt_Full_Course_Fee;

    @FXML
    private TextField txt_payment_ID;

    @FXML
    private TableColumn<?, ?> col_pay;

    private AnchorPane centerNode;

    @FXML
    private TextField txtCash;

    @FXML
    void btn_Payment_onAction(ActionEvent event) throws IOException {
        String paymentId = txt_payment_ID.getText();
        double paidAmount = Double.parseDouble(txtPaidAmount.getText());
        double fullPayment = Double.parseDouble(txt_Full_Course_Fee.getText());
        double pay = Double.parseDouble(txtCash.getText());
        double amount = fullPayment - paidAmount;
        double balance = pay - amount;

        Registration selectedRegistr = this.cmb_Registr_ID.getSelectionModel().getSelectedItem();
        System.out.println(selectedRegistr);
        if (pay >= amount) {
            boolean isSaved = paymentBO.save(new PaymentDto(paymentId, amount, paidAmount, fullPayment, pay, balance, selectedRegistr));
            if (isSaved) {
                clearValue();
                new Alert(Alert.AlertType.CONFIRMATION, "Payment successfully").show();
            } else {
                clearValue();
                new Alert(Alert.AlertType.ERROR, "Payment unsuccessfully").show();
            }
        } else {
            clearValue();
            new Alert(Alert.AlertType.ERROR, "Invalid payment, please try again").show();
        }
    }

    @FXML
    void btn_clear_onAction(ActionEvent event) {
        clearValue();
    }

    private void clearValue() {
        txtAmount.clear();
        txtPaidAmount.clear();
        txtCash.clear();
        txt_Full_Course_Fee.clear();
        txt_payment_ID.clear();
        cmb_Registr_ID.getSelectionModel().clearSelection();
    }

    public void initialize() {
        setComboRegistr();
        try {
            setTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
        generatePaymentId();
    }

    private String generatePaymentId() {
        try {
            String currentId = paymentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("Pay00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "Pay00" + ++idNum;
                txt_payment_ID.setText(availableId);
                return availableId;
            } else {
                txt_payment_ID.setText("Pay001");
                return "Pay001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setCellValueFactory() {
        col_payment_ID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        col_registr_ID.setCellValueFactory(new PropertyValueFactory<>("registration_regiId"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_paid_Amount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        col_FullCourse_fee.setCellValueFactory(new PropertyValueFactory<>("fullPayment"));
        col_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        col_pay.setCellValueFactory(new PropertyValueFactory<>("pay"));

    }

    private void setTable() throws IOException {
        ObservableList<PayementTm> payementTms = FXCollections.observableArrayList();
        List<PaymentDto> all = paymentBO.getAll();
        for (PaymentDto paymentDto : all) {
            PayementTm payementTm = new PayementTm(paymentDto.getPaymentId(), paymentDto.getAmount(), paymentDto.getPaidAmount(), paymentDto.getFullPayment(), paymentDto.getPay(), paymentDto.getBalance(), paymentDto.getRegistration().getRegiId());
            payementTms.add(payementTm);
        }
        tbl_registr.setItems(payementTms);
    }

   */
/* private void setComboRegistr() {
        List<Registration> registrationIds = paymentBO.getRegistrationIds();
        cmb_Registr_ID.getItems().addAll(registrationIds);

        cmb_Registr_ID.setOnAction(event -> {
            Registration selectedRegistrationId = cmb_Registr_ID.getValue();

            if (selectedRegistrationId != null) {
                double paidAmount = paymentBO.getPaidAmountByRegistrationId(selectedRegistrationId);
                txtPaidAmount.setText(String.valueOf(paidAmount));

                double fullFee = paymentBO.getFullFeeRegistrationId(selectedRegistrationId);
                txt_Full_Course_Fee.setText(String.valueOf(fullFee));

                double amount = paymentBO.getAmounteRegistrationId(selectedRegistrationId);
                txtAmount.setText(String.valueOf(amount));
            }
        });
    }*//*


    private void setComboRegistr() {
        List<Registration> registrationIds = paymentBO.getRegistrationIds();
        System.out.println("Registrations: " + registrationIds); // Debugging print

        cmb_Registr_ID.getItems().addAll(registrationIds);

        cmb_Registr_ID.setOnAction(event -> {
            Registration selectedRegistrationId = cmb_Registr_ID.getValue();
            System.out.println("Selected Registration: " + selectedRegistrationId); // Debugging print

            if (selectedRegistrationId != null) {
                double paidAmount = paymentBO.getPaidAmountByRegistrationId(selectedRegistrationId);
                txtPaidAmount.setText(String.valueOf(paidAmount));

                double fullFee = paymentBO.getFullFeeRegistrationId(selectedRegistrationId);
                txt_Full_Course_Fee.setText(String.valueOf(fullFee));

                double amount = paymentBO.getAmounteRegistrationId(selectedRegistrationId);
                txtAmount.setText(String.valueOf(amount));
            }
        });
    }


    public void setCenterNode(AnchorPane centerNode) {
        this.centerNode = centerNode;
    }
}
*/
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.DTO.PaymentDto;
import lk.ijse.DTO.tm.RegistrationTM;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.entity.Registration;
import lk.ijse.DTO.tm.PayementTm;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaymentFormController {

    public JFXButton btnPaid;
    private PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

    @FXML
    private ComboBox<Registration> cmb_Registr_ID;

    @FXML
    private TableColumn<?, ?> col_Amount;

    @FXML
    private TableColumn<?, ?> col_FullCourse_fee;

    @FXML
    private TableColumn<Double, PayementTm> col_balance;

    @FXML
    private TableColumn<Double, PayementTm> col_paid_Amount;

    @FXML
    private TableColumn<String, PayementTm> col_payment_ID;

    @FXML
    private TableColumn<String, RegistrationTM> col_registr_ID;

    @FXML
    private AnchorPane registrPane;

    @FXML
    private TableView<PayementTm> tbl_registr;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaidAmount;

    @FXML
    private TextField txt_Full_Course_Fee;

    @FXML
    private TextField txt_payment_ID;

    @FXML
    private TableColumn<?, ?> col_pay;

    private AnchorPane centerNode;

    @FXML
    private TextField txtCash;

    // Set to keep track of selected registration IDs
    private Set<String> selectedRegistrations = new HashSet<>();

    @FXML
    void btn_Payment_onAction(ActionEvent event) throws IOException {
        String paymentId = txt_payment_ID.getText();
        double paidAmount = Double.parseDouble(txtPaidAmount.getText());
        double fullPayment = Double.parseDouble(txt_Full_Course_Fee.getText());
        double pay = Double.parseDouble(txtCash.getText());
        double amount = fullPayment - paidAmount;
        double balance = pay - amount;

        Registration selectedRegistr = this.cmb_Registr_ID.getSelectionModel().getSelectedItem();
        if (selectedRegistr != null && pay >= amount) {
            boolean isSaved = paymentBO.save(new PaymentDto(paymentId, amount, paidAmount, fullPayment, pay, balance, selectedRegistr));
            if (isSaved) {
                clearValue();
                new Alert(Alert.AlertType.CONFIRMATION, "Payment successful").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Payment unsuccessful").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid payment, please try again").show();
        }
    }

    @FXML
    void btn_clear_onAction(ActionEvent event) {
        clearValue();
    }

    private void clearValue() {
        txtAmount.clear();
        txtPaidAmount.clear();
        txtCash.clear();
        txt_Full_Course_Fee.clear();
        txt_payment_ID.clear();
        cmb_Registr_ID.getSelectionModel().clearSelection();
        selectedRegistrations.clear();
    }

    public void initialize() {
        setComboRegistr();
        try {
            setTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
        generatePaymentId();
    }

    private String generatePaymentId() {
        try {
            String currentId = paymentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("Pay00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "Pay00" + ++idNum;
                txt_payment_ID.setText(availableId);
                return availableId;
            } else {
                txt_payment_ID.setText("Pay001");
                return "Pay001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setCellValueFactory() {
        col_payment_ID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        col_registr_ID.setCellValueFactory(new PropertyValueFactory<>("registration_regiId"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_paid_Amount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        col_FullCourse_fee.setCellValueFactory(new PropertyValueFactory<>("fullPayment"));
        col_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        col_pay.setCellValueFactory(new PropertyValueFactory<>("pay"));
    }

    private void setTable() throws IOException {
        ObservableList<PayementTm> payementTms = FXCollections.observableArrayList();
        List<PaymentDto> all = paymentBO.getAll();
        for (PaymentDto paymentDto : all) {
            PayementTm payementTm = new PayementTm(paymentDto.getPaymentId(), paymentDto.getAmount(), paymentDto.getPaidAmount(), paymentDto.getFullPayment(), paymentDto.getPay(), paymentDto.getBalance(), paymentDto.getRegistration().getRegiId());
            payementTms.add(payementTm);
        }
        tbl_registr.setItems(payementTms);
    }

    private void setComboRegistr() {
        List<Registration> registrationIds = paymentBO.getRegistrationIds();
        cmb_Registr_ID.getItems().addAll(registrationIds);

        cmb_Registr_ID.setOnAction(event -> {
            Registration selectedRegistration = cmb_Registr_ID.getValue();

            if (selectedRegistration != null) {
                String selectedId = selectedRegistration.getRegiId();

                // Check if this registration ID has already been selected
                if (selectedRegistrations.contains(selectedId)) {
                    // Show alert if already selected
                    new Alert(Alert.AlertType.WARNING, "This registration ID has already been selected!").show();

                    // Clear selection
                    cmb_Registr_ID.getSelectionModel().clearSelection();
                } else {
                    // Add the registration ID to selected set if not previously selected
                    selectedRegistrations.add(selectedId);

                    // Fetch and display payment details
                    double paidAmount = paymentBO.getPaidAmountByRegistrationId(selectedRegistration);
                    txtPaidAmount.setText(String.valueOf(paidAmount));

                    double fullFee = paymentBO.getFullFeeRegistrationId(selectedRegistration);
                    txt_Full_Course_Fee.setText(String.valueOf(fullFee));

                    double amount = paymentBO.getAmounteRegistrationId(selectedRegistration);
                    txtAmount.setText(String.valueOf(amount));
                }
            }
        });
    }

    public void setCenterNode(AnchorPane centerNode) {
        this.centerNode = centerNode;
    }

    public void btn_Paid_onAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/paid_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.setTitle("Register Form");

        // Set the popup window to be modal (block input to other windows)
        popupStage.initModality(Modality.APPLICATION_MODAL);

        // Center the popup on the screen
        popupStage.centerOnScreen();

        // Show the popup
        popupStage.showAndWait();
    }
}
