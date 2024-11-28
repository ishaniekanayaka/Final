package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.DTO.tm.RegistrationTM;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RegistrationBO;
import lk.ijse.entity.Registration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaidFormController implements Initializable {

    @FXML
    private TableView<RegistrationTM> StudentTable;

    @FXML
    private TableColumn<Double, RegistrationTM> colPayment;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colRegId;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<Double, RegistrationTM> coldueAmonut;

    @FXML
    private TableColumn<String, RegistrationTM> colduration;

    @FXML
    private TableColumn<String, RegistrationTM> colsid;

    @FXML
    private TableColumn<String, RegistrationTM> colsname;

    @FXML
    private TableColumn<RegistrationTM, JFXButton> deleteBtn;

    @FXML
    private AnchorPane rootStudent;

    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REGISTRATION);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadallvalues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setValues();
    }

    private void setValues() {
        colRegId.setCellValueFactory(new PropertyValueFactory<>("regiId"));
        colsid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colsname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        coldueAmonut.setCellValueFactory(new PropertyValueFactory<>("dueAmount"));
        deleteBtn.setCellValueFactory(new PropertyValueFactory<RegistrationTM, JFXButton>("Delete"));
    }

    private void loadallvalues() throws IOException {
        List<Registration> alldetails = registrationBO.getAllRegistrationDetails();

        ObservableList<RegistrationTM> observableList = FXCollections.observableArrayList();

        for (Registration registration : alldetails) {
            RegistrationTM registrationTM = new RegistrationTM(
                    registration.getRegiId(),  // Set the registerId
                    registration.getStudent().getStudent_id(),
                    registration.getStudentName(),
                    registration.getProgramme().getProgrammeName(),
                    registration.getEnrollmentDate(),
                    registration.getDuration(),
                    registration.getPayment(),
                    registration.getDueAmount(),
                    new JFXButton("delete")
            );

            observableList.add(registrationTM);
        }

        StudentTable.setItems(observableList);
    }
}
