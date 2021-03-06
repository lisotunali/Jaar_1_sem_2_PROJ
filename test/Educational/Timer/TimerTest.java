package Educational.Timer;

import BACKEND.Education.BaseGame;
import BACKEND.Education.Math;
import BACKEND.Education.Reading;
import BACKEND.Education.Writing;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import GUI.Controllers.Utility.singletonPerson;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimerTest {

    //Timer tests
    @Test
    public void TimerTestMath() throws InterruptedException {
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        BaseGame mathGame = new Math();
        mathGame.setSecondsLeft(3);
        mathGame.startTimer();
        sleep(3000);
        mathGame.stopTimer();
        assertEquals(0, mathGame.getSecondsLeft());
    }

    @Test
    public void TimerTestReading() throws InterruptedException {
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        BaseGame readingGame = new Reading();
        readingGame.setSecondsLeft(3);
        readingGame.startTimer();
        sleep(3000);
        readingGame.stopTimer();
        assertEquals(0, readingGame.getSecondsLeft());
    }

    @Test
    public void TimerTestWriting() throws InterruptedException {
        IPerson testperson = new Person("1", "1");
        singletonPerson.setPerson(testperson);
        BaseGame writingGame = new Writing();
        writingGame.setSecondsLeft(3);
        writingGame.startTimer();
        sleep(3000);
        writingGame.stopTimer();
        assertEquals(0, writingGame.getSecondsLeft());
    }
}
