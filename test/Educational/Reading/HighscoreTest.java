package Educational.Reading;

import BACKEND.Education.Highscores;
import BACKEND.Education.ImageWithName;
import BACKEND.Education.Reading;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HighscoreTest {
    void init() {
        IPerson testIPerson = new Person("gameTester", "iAmTesting");
        singletonPerson.setPerson(testIPerson);

        fakeDatabase.getImagesDatabase().add(new ImageWithName("bee", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/bee.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("doctor", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/doctor.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("dog", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/dog.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("elephant", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/elephant.png"))));
    }

    @Test
    void newHighScoreShouldBeSavedInAccount() {
        init();

        Reading game = new Reading();

        game.nextQuestion();
        game.checkAnswer(game.getCorrectAnswer());

        game.nextQuestion();
        game.checkAnswer(game.getCorrectAnswer());

        game.endGame();

        Assertions.assertEquals(singletonPerson.getInstance().getHS("reading").getHighScore(), 2);
    }

    @Test
    void higherScoreShouldBeSavedInAccount() {
        init();

        Reading game = new Reading();

        Highscores hsCurrentPerson = singletonPerson.getInstance().getHS("reading");
        hsCurrentPerson.setHighscore(10);

        for (int i = 0; i < 20; i++) {
            game.nextQuestion();
            game.checkAnswer(game.getCorrectAnswer());
        }

        game.endGame();

        Assertions.assertEquals(singletonPerson.getInstance().getHS("reading").getHighScore(), 20);
    }

    @Test
    void tooLowHighScoreShouldNotBeSavedInAccount() {
        init();

        Reading game = new Reading();

        Highscores hsCurrentPerson = singletonPerson.getInstance().getHS("reading");
        hsCurrentPerson.setHighscore(10);

        game.nextQuestion();
        game.checkAnswer(game.getCorrectAnswer());

        game.nextQuestion();
        game.checkAnswer(game.getCorrectAnswer());

        game.endGame();

        Assertions.assertEquals(hsCurrentPerson.getHighScore(), 10);
    }
}
