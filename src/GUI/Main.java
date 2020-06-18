package GUI;

import BACKEND.fakeDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Add fake persons

        fakeDatabase.setUpTestInstances();
        fakeDatabase.setUpGameData();

        SceneController.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("Views/Account/login.fxml"));
        primaryStage.setTitle("Kumbaya 5a");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
