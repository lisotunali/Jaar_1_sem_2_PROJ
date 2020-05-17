package GUI;


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

    public static String showText(String defaultValue, String title, String header, String context, String output) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(context);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(output + result.get());
            return result.get();
        }
        return null;
    }
}
