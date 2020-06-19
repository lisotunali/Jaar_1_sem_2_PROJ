package GUI.Controllers.Utility;


import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

//Alle alerts/dialog popups zijn hiernaartoe verplaatst
public class AlertClass {

    public static void showAlert(AlertType type, String text) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void showAlert(AlertType type, String text, String buttontype) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(type, buttontype);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static String showText(String[] textArray) {
        TextInputDialog dialog = new TextInputDialog(textArray[0]);
        dialog.setTitle(textArray[1]);
        dialog.setHeaderText(textArray[2]);
        dialog.setContentText(textArray[3]);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(textArray[4] + result.get());
            return result.get();
        }
        return null;
    }
}
