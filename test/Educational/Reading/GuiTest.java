package Educational.Reading;

import BACKEND.Education.ImageWithName;
import BACKEND.Person.Person;
import GUI.Controllers.Educational.readingController;
import GUI.Controllers.Utility.singletonPerson;
import GUI.Main;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;
import java.util.ArrayList;

import static GUI.SceneController.getPrimaryStage;

public class GuiTest extends TestFXTestBase {
    @Test
    public void timerTest() throws IOException {
        SceneController.switchTo("reading");

        for (int i = 59; i > 50; i--) {
            FxAssert.verifyThat("#timerLabel", LabeledMatchers.hasText(Integer.toString(i)));
            sleep(1000);
        }
    }

    @Test
    public void textShouldChangeWhenClickedOnImage() throws IOException {
        Stage primaryStage = getPrimaryStage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("reading.fxml"));
        Parent parent = loader.load();
        readingController controller = loader.getController();
        primaryStage.getScene().setRoot(parent);

        for (int i = 0; i < 4; i++) {
            String before = controller.readingWord.getText();
            clickOn("#rImage4");
            String after = controller.readingWord.getText();
            Assertions.assertNotEquals(before, after, "Question should not be the same");
        }
    }

    @Test
    public void pointsShouldIncreaseWhenCorrectImage() throws IOException {
        Person testPerson = new Person("gameTester", "iAmTesting");
        singletonPerson.setPerson(testPerson);

        Stage primaryStage = getPrimaryStage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("reading.fxml"));
        Parent parent = loader.load();
        readingController controller = loader.getController();
        primaryStage.getScene().setRoot(parent);

        for (int i = 0; i < 10; i++) {
            String question = controller.readingWord.getText();
            ArrayList<ImageWithName> temps = controller.game.getTemps();

            for (int j = 0; j < temps.size(); j++) {
                ImageWithName img = temps.get(j);
                if (img.getName().equalsIgnoreCase(question)) {
                    clickOn("#rImage" + (j += 1));
                    break;
                }
            }
        }

        Assertions.assertEquals(controller.game.getCurrentscore(), 10);
    }
}
