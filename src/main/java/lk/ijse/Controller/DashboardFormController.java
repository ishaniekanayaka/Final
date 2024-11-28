package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.entity.UserSession;
import lk.ijse.util.timeDate.TimeDate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {


    public JFXButton btnStudent;
    public JFXButton btnProgramme;
    public JFXButton btnPayment;
    public JFXButton btnUser;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane mainroot;

    @FXML
    private AnchorPane root;

    String userId = UserSession.getInstance().getUserId();
    String role = UserSession.getInstance().getRole();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TimeDate.localDateAndTime(lblDate,lblTime);
        checkLoggedUser();
    }

    private void checkLoggedUser() {
        if (role.equals("Coordinator")){
            btnUser.setVisible(false);
            btnProgramme.setVisible(false);
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage)mainroot .getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/login_form.fxml"))));
        stage.setTitle("DashBoard ");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/Payment_form.fxml"));
            loadWindow(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnProgrammeOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/Programme_form.fxml"));
            loadWindow(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
            loadWindow(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUserOnAction(ActionEvent event) {

        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/user_form.fxml"));
            loadWindow(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWindow(AnchorPane anchorPane) {
        root.getChildren().clear();
        root.getChildren().add(anchorPane);
    }


}