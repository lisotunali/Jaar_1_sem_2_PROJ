package Educational.Writing;

import BACKEND.Education.ImageWithName;
import BACKEND.Education.Writing;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WritingGameTest {
    void addQuestsToDatabase() {
        fakeDatabase.getImagesDatabase().add(new ImageWithName("bee", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/bee.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("doctor", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/doctor.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("dog", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/dog.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("elephant", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/elephant.png"))));
    }

    //Point system
    //Test if a point is awarded after the user answered correctly.
    @Test
    public void AddPointTest1() throws InterruptedException {
        addQuestsToDatabase();
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        Writing writingGame = new Writing();
        writingGame.nextQuestion();
        System.out.println("Current answer: " + writingGame.getQuestion().getName());
        writingGame.checkAnswer(writingGame.getCorrectAnswer());
        System.out.println("Current score: " + writingGame.getCurrentscore());
        assertEquals(1, writingGame.getCurrentscore());
    }

    //Test if a point is awarded after the user answered incorrectly.
    @Test
    public void AddPointTest2() throws InterruptedException {
        addQuestsToDatabase();
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        Writing writingGame = new Writing();
        writingGame.nextQuestion();
        System.out.println("Current answer: " + writingGame.getQuestion().getName());
        writingGame.checkAnswer("this will always be read as an incorrect answer");
        System.out.println("Current score: " + writingGame.getCurrentscore());
        assertNotEquals(1, writingGame.getCurrentscore());
    }
}
