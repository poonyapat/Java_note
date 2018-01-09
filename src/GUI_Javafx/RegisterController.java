package GUI_Javafx;

import Storage.ObjectIOStream;
import Storage.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    TextField usernameTextField, passwordTextField, confirmPwdTextField, firstNameTextField, lastNameTextField, emailTextField;

    @FXML
    void handleCancel() {
        PageChanger.changePage(this, usernameTextField, "loginPage.fxml");
    }

    @FXML
    void handleSubmit() {
        ObjectIOStream oios = new ObjectIOStream();
        if (verifyIdentity()) {
            UserData newUserData = new UserData(
                    usernameTextField.getText(),
                    passwordTextField.getText(),
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    emailTextField.getText(),
                    NoteGUISystem.accountSystem
            );
            oios.writeObject(newUserData, newUserData.getUsername() + ".ser");
            oios.writeObject(NoteGUISystem.accountSystem, "as.ser");
        } else
            return;
        PageChanger.changePage(this, usernameTextField, "loginPage.fxml");
    }

    private boolean verifyIdentity() {
        String alertText = "";
        if (NoteGUISystem.accountSystem.contains(usernameTextField.getText()))
            alertText += "This username is already used in System.\n";
        if (usernameTextField.getText().length() < 3)
            alertText += "This username is too short. At least 3 letters.\n";
        if (passwordTextField.getText().length() < 4)
            alertText += "This password is too short. At least 4 letters.\n";
        if (!confirmPwdTextField.getText().equals(passwordTextField.getText()))
            alertText += "Confirm password and password have to be same.\n";
        if (!firstNameTextField.getText().matches("^[a-zA-Z]+$"))
            alertText += "First name have to be character only.\n";
        if (!lastNameTextField.getText().matches("^[a-zA-Z]+$"))
            alertText += "Last name have to be character only.\n";
        if (!emailTextField.getText().matches("^[^@]+@[a-zA-Z]+[.][a-zA-Z]+$"))
            alertText += "Your email is wrong pattern.";
        if (alertText.isEmpty())
            return true;
        System.err.println(alertText);
        Alert alert = new Alert(Alert.AlertType.ERROR, alertText);
        alert.show();
        return false;
    }
}
