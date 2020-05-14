package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        SceneController.primaryStage = primaryStage;
    }

    // Changes the current scene to the given template name
    public static void switchTo(String templateName) throws IOException {
        Stage primaryStage = getPrimaryStage();
        Parent root = FXMLLoader.load(Main.class.getResource(templateName + ".fxml"));
        primaryStage.getScene().setRoot(root);
    }
}
