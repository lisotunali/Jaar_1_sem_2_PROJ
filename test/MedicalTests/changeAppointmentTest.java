package MedicalTests;

import BACKEND.Medical.Appointment;
import BACKEND.Medical.Appointments;
import BACKEND.Person.Doctor;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.Person.SpecializationType;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class changeAppointmentTest extends TestFXTestBase {
    private IPerson testIPerson = new Person("appointmentTester", "iAmTesting");
    private IPerson testIPerson2 = new Person("appointmentTester2", "iAmTesting");
    private Doctor testDoc = new Doctor("docTester", "iAmStillTesting", SpecializationType.EAR);
    private Doctor testDoc2 = new Doctor("docTester2", "iAmStillTesting", SpecializationType.SKIN);

    public void init() {
        fakeDatabase.getUserDatabase().clear();
        fakeDatabase.getUserDatabase().add(testDoc);
        fakeDatabase.getUserDatabase().add(testDoc2);
        fakeDatabase.getUserDatabase().add(testIPerson);
        fakeDatabase.getUserDatabase().add(testIPerson2);
        singletonPerson.setPerson(testDoc);
        Appointments.getAllAppointments().clear();
    }

    /*
This will check all possible changes a doctor can make to an appointment and if the correctly work.
This test checks if the doctor can change the condition of the patient in the appointment screen.
 */
    @Test
    void doctorChangeCondition() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testIPerson);
        SceneController.switchTo("Medical/medicalDoctor");
        String TableviewID = "#appointmentTableView";
        String viewButton = "#viewAppointmentButton";
        clickOn(TableviewID);
        sleep(500);
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
    void specializationChangeTest() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testIPerson);
        SceneController.switchTo("Medical/medicalDoctor");
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

        assertEquals(SpecializationType.EAR, app.getAppointmentType());
    }

    /*
    This test will check if the doctor can set the appoint to done via the appointmentscreen. And if the back-end will save the change.
     */
    @Test
    void setAppointmentOnDoneTest() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testIPerson);
        SceneController.switchTo("Medical/medicalDoctor");
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
    void cancelAppointmentTest() throws Exception {
        init();
        Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testIPerson);
        SceneController.switchTo("Medical/medicalDoctor");
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
