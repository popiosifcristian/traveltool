package travel.tool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import travel.tool.util.FxmlView;
import travel.tool.util.StageManager;
import util.IClientProtocol;

/**
 * @author ipop
 */
@Controller
public class LoginController {
    @Autowired
    private IClientProtocol server;
    @Lazy
    @Autowired
    private StageManager stageManager;
    @FXML
    public Label lblLogin;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public Button btnLogin;

    public void login(ActionEvent actionEvent) {
        if (server.authenticate(getUsername(), getPassword())) {
            stageManager.switchScene(FxmlView.BOOKING);
        } else {
            lblLogin.setText("Login Failed.");
        }
    }

    private String getPassword() {
        return password.getText();
    }

    private String getUsername() {
        return username.getText();
    }
}
