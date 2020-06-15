package Educational.Reading;

import Education.ImageWithName;
import Education.Reading;
import GUI.fakeDatabase;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Testen of een punt wordt gegeven wanneer het juiste plaatje wordt aangeklikt bij het bijbehorend woord. (Decision + Operational Profiles)
class PointsTest {
    void addQuestsToDatabase() {
        fakeDatabase.getImagesDatabase().add(new ImageWithName("bee", new Image(getClass().getResourceAsStream("/Education/Images/bee.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("doctor", new Image(getClass().getResourceAsStream("/Education/Images/doctor.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("dog", new Image(getClass().getResourceAsStream("/Education/Images/dog.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("elephant", new Image(getClass().getResourceAsStream("/Education/Images/elephant.png"))));
    }

    @Test
    void correctAnswerShouldGivePoint() {
        addQuestsToDatabase();

        Reading game = new Reading();

        game.nextQuestion();
        game.checkAnswer(game.getCorrectAnswer());

        game.nextQuestion();
        game.checkAnswer(game.getCorrectAnswer());

        Assertions.assertEquals(game.getCurrentscore(), 2);
    }

    @Test
    void incorrectAnswerShouldNotGivePoint() {
        addQuestsToDatabase();

        Reading game = new Reading();

        game.nextQuestion();
        game.checkAnswer("Wrong answer");

        game.nextQuestion();
        game.checkAnswer("Wrong answer");

        Assertions.assertEquals(game.getCurrentscore(), 0);
    }
}
