package MedicalTests;

import BACKEND.Doctor;
import BACKEND.Person;
import BACKEND.SpecializationType;
import GUI.SceneController;
import GUI.SingletonAppointments;
import GUI.fakeDatabase;
import GUI.singletonPerson;
import TestFXBase.TestFXTestBase;
import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.service.query.EmptyNodeQueryException;

import java.io.IOException;
import java.time.LocalDate;

public class ObservableMainUITest extends TestFXTestBase {

    @Test
    public void updateObservableScheduleTest() throws IOException {
        singletonPerson.setPerson(new Person("test instance", "123"));
        SceneController.switchTo("mainUI");
        /* Platform.runLater(() -> {
            try {
                SceneController.switchTo("mainUI");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }); */
        fakeDatabase.getUserDatabase().add(new Doctor("Teendokter", "123", SpecializationType.GENERAL));
        SingletonAppointments.getInstance().createAppointment(LocalDate.of(2020, 6, 23), "Teenvocht onsteking", SpecializationType.GENERAL, singletonPerson.getInstance());
        System.out.println(SingletonAppointments.getInstance().getAllAppointments(singletonPerson.getInstance()));
        clickOn("#userAppointments");
        try {
            lookup("nieuwe afspraak").query().getParent();
        }
        catch (Exception e) {
            Assertions.fail("Nieuwe afspraak niet gevonden in de list " + e);
        }

    }
}
