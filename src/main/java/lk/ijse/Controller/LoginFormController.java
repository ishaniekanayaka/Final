package lk.ijse.Controller;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.DTO.LoginDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.entity.UserSession;
import lk.ijse.util.config.FactoryConfiguration;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private RadioButton btnRadio;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;


    String temporaryUserName = "admin";
    String temporaryPassword = "admin";
    LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);


  @FXML
  void btnLoginOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

      Session session = FactoryConfiguration.getInstance().getSession();

      String userName= txtUserName.getText();
      String password = txtPassword.getText();

      try {
          if (userName.equals(temporaryUserName)  && password.equals(temporaryPassword)) {

              session =  FactoryConfiguration.getInstance().getSession();
              UserSession.getInstance().setUser("U1234", "admin");

              navigateToTheDashboard((Stage) root.getScene().getWindow());

          } else {
              LoginDTO loginDTO = new LoginDTO(userName, password);

              boolean loginResult = loginBO.checkCredential(loginDTO);
              if (loginResult) {
                  navigateToTheDashboard((Stage)root.getScene().getWindow());

              } else {
                  // Show alert if credentials are incorrect
                  new Alert(Alert.AlertType.ERROR, "Invalid credentials! Please try again.").show();
              }
          }

      } catch (IOException | SQLException | ClassNotFoundException e) {
          throw new RuntimeException(e);
      }
  }


    private void navigateToTheDashboard(Stage window) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
            AnchorPane dashboardPane = loader.load();

            Scene dashboardScene = new Scene(dashboardPane);

            window.setScene(dashboardScene);

            window.centerOnScreen();
            window.setResizable(true);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load the dashboard.").show();
        }
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

    public void btnRadioOnAction(ActionEvent actionEvent) {
    }
}
