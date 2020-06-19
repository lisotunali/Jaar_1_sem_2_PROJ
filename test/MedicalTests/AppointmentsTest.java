package MedicalTests;

import BACKEND.Medical.Appointment;
import BACKEND.Medical.Appointments;
import BACKEND.Person.Doctor;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.Person.SpecializationType;
import BACKEND.fakeDatabase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentsTest {
    private IPerson testIPerson = new Person("appointmentTester", "iAmTesting");
    private IPerson testIPerson2 = new Person("appointmentTester2", "iAmTesting");
    private Doctor testDoc = new Doctor("testdoc", "test1", SpecializationType.EAR);
    private Doctor testDoc2 = new Doctor("docTester2", "iAmStillTesting", SpecializationType.EYES);

    void init() {
        fakeDatabase.getUserDatabase().clear();
        fakeDatabase.getUserDatabase().add(testDoc);
        fakeDatabase.getUserDatabase().add(testDoc2);
        fakeDatabase.getUserDatabase().add(testIPerson);
        fakeDatabase.getUserDatabase().add(testIPerson2);
        Appointments.getAllAppointments().clear();
    }

    /*
    This test will check if when the date is before the current date null will be returned.
     */
    @Test
    void dateBeforeCurrentDate() throws Exception {
        init();
        assertThrows(Exception.class, () -> Appointments.createAppointment(LocalDate.now().minusDays(1), "testCondition", SpecializationType.EAR, testIPerson));
    }

    /*
    findAvailableTimeAndDoctor method testing
     */
    @Test
    void patientAlreadyHasAppointment() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testIPerson);
        Appointment app2 = Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition 2", SpecializationType.EAR, testIPerson);
        assertNull(app2);
    }

    @Test
    void patientHasNoAppointment() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.EAR, testIPerson);
        Appointment app2 = Appointments.createAppointment(LocalDate.of(2020, 9, 6), "testCondition 2", SpecializationType.EAR, testIPerson);
        assertNotNull(app2);
    }

    /*
    This test will check if the method findAvailableTimeAndDoctor returns null when no doctor with correct type is available.
     */
    @Test
    void noDoctorAvailableWithType() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2020, 9, 5), "testCondition", SpecializationType.GENERAL, testIPerson);
        assertNull(app);
    }

    /*
    This test wil check if the createAppointment method works properly and actually creates the appointment with the right doctors.
     */
    @Test
    void createAppointmentTest() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2021, 6, 10), "testCondition", SpecializationType.EAR, testIPerson);
        Appointment app2 = new Appointment(testIPerson, testDoc, "testCondition", SpecializationType.EAR, app.getAppointmentDate());
        assertEquals(app.toString(), app2.toString(), "This checks if the createAppointment method finds the right doctor and time");
    }

    /*
    This test checks if the requested specialization is properly picked by the findAvailableTimeAndDoctor method.
    The specialization of the request is compared to the specialization of the doctor chosen.
     */
    @Test
    void checkCorrectSpecialization() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2021, 6, 10), "testCondition", SpecializationType.EAR, testIPerson);
        String spectype = "[" + app.getAppointmentType() + "]";
        assertEquals(spectype, app.getDoctor().getSpecializations().toString());
    }

    /*
    This test will check if the updateAppointment method works properly.
    App is the appointment that is updated and app2 is the old appointment. So app and app2 should not be the same.
     */
    @Test
    void updateAppointmentTest() throws Exception {
        init();
        Appointment app = Appointments.createAppointment(LocalDate.of(2021, 6, 10), "testCondition", SpecializationType.EAR, testIPerson);
        Appointment app2 = new Appointment(testIPerson, testDoc, "testCondition", SpecializationType.EAR, app.getAppointmentDate());
        Appointments.updateAppointment(app, LocalDate.of(2021, 6, 11));
        assertNotEquals(app.getAppointmentDateString(), app2.getAppointmentDateString());
    }




}