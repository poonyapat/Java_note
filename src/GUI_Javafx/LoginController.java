package GUI_Javafx;

import Storage.ObjectIOStream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordField;

    @FXML
    public void actionSignIn() {
        if (!verifyAccountPassword()) {
            alertSignInFail();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("noteManagePage.fxml"));
            Parent root = loader.load();
            NoteManageController controller = loader.getController();
            controller.setUser(new ObjectIOStream().readUserDataFile(usernameTextField.getText() + ".ser"));
            controller.actionReadNote();
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.printf("IOException: loader load [noteManagePage] fail\n");
        }
    }

    @FXML
    public void actionSignUp() {
        PageChanger.changePage(this, usernameTextField, "registerPage.fxml");
    }

    private boolean verifyAccountPassword() {
        return NoteGUISystem.accountSystem.verifyAccount(usernameTextField.getText(), passwordField.getText());
    }

    private void alertSignInFail() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password. Please Try again.");
        alert.show();
    }
}
