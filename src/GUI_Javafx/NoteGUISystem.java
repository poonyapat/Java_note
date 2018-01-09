package GUI_Javafx;

import Storage.AccountSystem;
import Storage.ObjectIOStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NoteGUISystem extends Application {

    public static AccountSystem accountSystem;

    public static void run() {
        initAccountSystem();
        launch();
    }

    private static void initAccountSystem() {
        ObjectIOStream oios = new ObjectIOStream();
        accountSystem = oios.readAccountSystemFile("as.ser");
        if (accountSystem == null) {
            accountSystem = new AccountSystem();
            oios.writeObject(accountSystem, "as.ser");
            System.err.println("Cannot find as.ser : Create New Account System");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Note V.1.0");
        stage.show();
    }
}
