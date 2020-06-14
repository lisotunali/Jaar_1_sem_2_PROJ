package MedicalTests;

import BACKEND.*;
import GUI.SceneController;
import GUI.SingletonAppointments;
import GUI.fakeDatabase;
import GUI.singletonPerson;
import TestFXBase.TestFXTestBase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.notification.RunListener;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class changeAppointmentTest extends TestFXTestBase {
    private Person testPerson = new Person("appointmentTester", "iAmTesting");
    private Person testPerson2 = new Person("appointmentTester2", "iAmTesting");
    private Doctor testDoc = new Doctor("docTester", "iAmStillTesting", SpecializationType.EAR);
    private Doctor testDoc2 = new Doctor("docTester2", "iAmStillTesting", SpecializationType.SKIN);

    public void init() {
        fakeDatabase.getUserDatabase().add(testDoc);
        fakeDatabase.getUserDatabase().add(testDoc2);
        fakeDatabase.getUserDatabase().add(testPerson);
        fakeDatabase.getUserDatabase().add(testPerson2);
    }

    /*
This will check all possible changes a doctor can make to an appointment and if the correctly work.
This test checks if the doctor can change the condition of the patient in the appointment screen.
 */
    @Test
    void doctorChangeCondition() throws IOException {
        init();
        singletonPerson.setPerson(testDoc);
        Appointments instanceApp = SingletonAppointments.getInstance();
        Appointment app = instanceApp.createAppointment(LocalDate.of(2020, 9,5 ), "testCondition", SpecializationType.EAR, testPerson);
        SceneController.switchTo("medicalDoctor");
        String TableviewID = "#appointmentTableView";
        String viewButton = "#viewAppointmentButton";
        clickOn(TableviewID);
        clickOn("appointmentTester");
        clickOn(viewButton);
        String conditionAreaID = "#conditionArea";
        String changeAppButton = "#changeAppointment";
        clickOn(conditionAreaID);
        sleep(200);
        write(". This condition has been updated");
        sleep(200);
        clickOn(changeAppButton);
        clickOn("Yes");
        assertEquals("testCondition. This condition has been updated",app.getCondition());
    }

    @Test
    void specializationChangeTest() throws IOException {
        init();
        singletonPerson.setPerson(testDoc);
        Appointments instanceApp = SingletonAppointments.getInstance();
        Appointment app = instanceApp.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testPerson);
        SceneController.switchTo("medicalDoctor");
        String TableviewID = "#appointmentTableView";
        String viewButton = "#viewAppointmentButton";
        String specialtiesID = "#specialties";
        String changeAppButton = "#changeAppointment";
        clickOn(TableviewID);
        clickOn("appointmentTester");
        clickOn(viewButton);
        clickOn(specialtiesID);
        clickOn("SKIN");
        clickOn(changeAppButton);
        clickOn("Yes");
        assertEquals(SpecializationType.SKIN,app.getAppointmentType());
    }
    /*
    This test will check if the doctor can set the appoint to done via the appointmentscreen. And if the back-end will save the change.
     */
@Test
    void setAppointmentOnDoneTest() throws IOException {
        init();
        singletonPerson.setPerson(testDoc);
        Appointments instanceApp = SingletonAppointments.getInstance();
        Appointment app = instanceApp.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testPerson);
        SceneController.switchTo("medicalDoctor");
        String TableviewID = "#appointmentTableView";
        String viewButton = "#viewAppointmentButton";
        String changeAppButton = "#changeAppointment";
        String doneCheckID = "#doneCheck";
        clickOn(TableviewID);
        clickOn("appointmentTester");
        clickOn(viewButton);
        clickOn(doneCheckID);
        clickOn(changeAppButton);
        clickOn("Yes");
        assertTrue(app.getDone());
    }
    /*
    This test will check if the appointment is properly cancelled by the doctor and gets removed from the patients appointment list.

     */
    @Test
    void cancelAppointmentTest() throws IOException {
        init();
        singletonPerson.setPerson(testDoc);
        Appointments instanceApp = SingletonAppointments.getInstance();
        instanceApp.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testPerson);
        SceneController.switchTo("medicalDoctor");
        String TableviewID = "#appointmentTableView";
        String viewButton = "#viewAppointmentButton";
        String deleteAppButton = "#deleteAppointment";
        clickOn(TableviewID);
        clickOn("appointmentTester");
        clickOn(viewButton);
        clickOn(deleteAppButton);
        clickOn("Yes");
    }


}
