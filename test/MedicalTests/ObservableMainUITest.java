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

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ObservableMainUITest extends TestFXTestBase {

    @Test //De gebruiker kijkt naar zijn afspraken op het hoofdscherm. De lijst met afspraken wordt gewijzigd (er wordt een nieuwe afspraak gemaakt hier). Het hoofdscherm van de gebruiker moet door de verandering zichzelf updaten.
    public void updateObservableScheduleTest() throws IOException {
        singletonPerson.setPerson(new Person("test instance", "123"));
        SceneController.switchTo("mainUI");
        fakeDatabase.getUserDatabase().add(new Doctor("Teendokter", "123", SpecializationType.GENERAL));
        assertNotNull(SingletonAppointments.getInstance().createAppointment(LocalDate.of(2020, 6, 23), "Teenvocht onsteking", SpecializationType.GENERAL, singletonPerson.getInstance()), "Afspraak niet aangemaakt. Test zal falen.");
        System.out.println(SingletonAppointments.getInstance().getAllAppointments(singletonPerson.getInstance()));
        clickOn("#userAppointments");
        try {
            lookup("Teenvocht onsteking").query().getParent();
        }
        catch (Exception e) {
            Assertions.fail("Nieuwe afspraak niet gevonden in de list " + e);
        }

    }
}
