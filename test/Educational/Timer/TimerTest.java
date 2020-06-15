package Educational.Timer;

import BACKEND.Person;
import Education.Game;
import Education.Math;
import Education.Reading;
import Education.Writing;
import GUI.singletonPerson;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimerTest {

    //Timer tests
    @Test
    public void TimerTestMath() throws InterruptedException {
        Person testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        Game mathGame = new Math();
        mathGame.setSecondsLeft(3);
        mathGame.startTimer();
        sleep(3000);
        assertEquals(0, mathGame.getSecondsLeft());
    }

    @Test
    public void TimerTestReading() throws InterruptedException {
        Person testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        Game readingGame = new Reading();
        readingGame.setSecondsLeft(3);
        readingGame.startTimer();
        sleep(3000);
        assertEquals(0, readingGame.getSecondsLeft());
    }

    @Test
    public void TimerTestWriting() throws InterruptedException {
        Person testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        Game writingGame = new Writing();
        writingGame.setSecondsLeft(3);
        writingGame.startTimer();
        sleep(3000);
        assertEquals(0, writingGame.getSecondsLeft());
    }
}
