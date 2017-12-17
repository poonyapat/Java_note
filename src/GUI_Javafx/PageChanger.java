package GUI_Javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PageChanger {
    public static void changePage(Object object, Node node, String url) {
        try {
            Parent root = FXMLLoader.load(object.getClass().getResource(url));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.printf("IOException: loader load [%s] fail\n", url);
        }
    }
}
