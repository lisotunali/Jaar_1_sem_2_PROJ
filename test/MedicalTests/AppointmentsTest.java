package MedicalTests;

import BACKEND.*;
import GUI.fakeDatabase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentsTest {
    private Person testPerson = new Person("appointmentTester", "iAmTesting");
    private Person testPerson2 = new Person("appointmentTester2", "iAmTesting");
    private Doctor testDoc = new Doctor("testdoc", "test1", SpecializationType.EAR);
    private Doctor testDoc2 = new Doctor("docTester2", "iAmStillTesting", SpecializationType.EYES);

    void init() {
        fakeDatabase.getUserDatabase().clear();
        fakeDatabase.getUserDatabase().add(testDoc);
        fakeDatabase.getUserDatabase().add(testDoc2);
        fakeDatabase.getUserDatabase().add(testPerson);
        fakeDatabase.getUserDatabase().add(testPerson2);
        Appointments.getAllAppointments().clear();
    }
/*
This test will check if when the date is before the current date null will be returned.
 */
    @Test
    void dateBeforeCurrentDate() {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2015, 6, 10), "testCondition", SpecializationType.EAR, testPerson);
        assertNull(app,"Null is returned because the date of the appointement is before the current date");
    }
    /*
    findAvailableTimeAndDoctor method testing
     */
    @Test
    void patientAlreadyHasAppointment(){
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9,5 ), "testCondition", SpecializationType.EAR, testPerson);
        Appointment app2 = Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition 2", SpecializationType.EAR, testPerson);
        assertNull(app2);
    }

    @Test
    void patientHasNoAppointment(){
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9,5 ), "testCondition", SpecializationType.EAR, testPerson);
        Appointment app2 = Appointments.createAppointment(LocalDate.of(2020, 9, 6), "testCondition 2", SpecializationType.EAR, testPerson);
        assertNotNull(app2);
    }

/*
This test will check if the method findAvailableTimeAndDoctor returns null when no doctor with correct type is available.
 */
    @Test
    void noDoctorAvailableWithType(){
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9,5 ), "testCondition", SpecializationType.GENERAL, testPerson);
        assertNull(app);
    }

    /*
    This test wil check if the createAppointment method works properly and actually creates the appointment with the right doctors.
     */
    @Test
    void createAppointmentTest() {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2021, 6, 10), "testCondition", SpecializationType.EAR, testPerson);
        Appointment app2 = new Appointment(testPerson, testDoc, "testCondition", SpecializationType.EAR, app.getAppointmentDate());
        assertEquals(app.toString(), app2.toString(), "This checks if the createAppointment method finds the right doctor and time");
    }

    /*
    This test checks if the requested specialization is properly picked by the findAvailableTimeAndDoctor method.
    The specialization of the request is compared to the specialization of the doctor chosen.
     */
@Test
    void checkCorrectSpecialization(){
    init();
    Appointment app = Appointments.createAppointment(LocalDate.of(2021, 6, 10), "testCondition", SpecializationType.EAR, testPerson);
    String spectype = "["+app.getAppointmentType()+"]";
    assertEquals(spectype,app.getDoctor().getSpecializations().toString());
}
    /*
    This test will check if the updateAppointment method works properly.
    App is the appointment that is updated and app2 is the old appointment. So app and app2 should not be the same.
     */
    @Test
    void updateAppointmentTest() {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2021, 6, 10), "testCondition", SpecializationType.EAR, testPerson);
        Appointment app2 = new Appointment(testPerson, testDoc, "testCondition", SpecializationType.EAR, app.getAppointmentDate());
        Appointments.updateAppointment(app, LocalDate.of(2021, 6, 11));
        assertNotEquals(app.getAppointmentDateString(), app2.getAppointmentDateString());
    }




}