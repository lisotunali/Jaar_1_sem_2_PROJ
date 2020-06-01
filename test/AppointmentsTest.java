import BACKEND.*;
import GUI.SingletonAppointments;
import GUI.fakeDatabase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentsTest {
    private Person testPerson = new Person("appointmentTester", "iAmTesting");
    private Person testPerson2 = new Person("appointmentTester2", "iAmTesting");
    private Doctor testDoc = new Doctor("docTester", "iAmStillTesting", SpecializationType.EAR);
    private Doctor testDoc2 = new Doctor("docTester2", "iAmStillTesting", SpecializationType.EYES);
    private Doctor testDoc3 = new Doctor("docTester3", "iAmStillTesting", SpecializationType.EAR);

    void init() {
        fakeDatabase.getUserDatabase().add(testDoc);
        fakeDatabase.getUserDatabase().add(testDoc2);
        fakeDatabase.getUserDatabase().add(testDoc3);
        fakeDatabase.getUserDatabase().add(testPerson);
        fakeDatabase.getUserDatabase().add(testPerson2);
    }

    @Test
    void planAppointmentBeforeCurrentDate() {
        init();

        Appointments instanceAppointments = SingletonAppointments.getInstance();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Appointment appointment = instanceAppointments.createAppointment(yesterday, "test condition1", SpecializationType.GENERAL, testPerson);

        assertNull(appointment);
    }

    @Test
    void planAppointment() {
        init();

        Appointments instanceAppointments = SingletonAppointments.getInstance();
        Appointment appointment = instanceAppointments.createAppointment(LocalDate.of(2021, 2, 13), "test", SpecializationType.EAR, testPerson);
        Appointment appointment2 = instanceAppointments.createAppointment(LocalDate.of(2021, 2, 13), "test", SpecializationType.EAR, testPerson2);

        assertNotEquals(null, appointment);
        assertNotEquals(null, appointment2);
    }

//    @Test
//    void planAppointmentNoTimeLeft() {
//        init();
//
//        Appointments instanceAppointments = SingletonAppointments.getInstance();
//        Appointment testAppointmentInDb = new Appointment(testPerson, testDoc, "test", SpecializationType.EAR, LocalDateTime.of(2021, 2, 13, 9, 0));
//        instanceAppointments.addAppointment(testAppointmentInDb);
//
//        for (int i = 0; i < 100000; i++) {
//            instanceAppointments.createAppointment(LocalDate.of(2021, 2, 13), "test", SpecializationType.EAR, testPerson);
//        }
//
//        Appointment appointment = instanceAppointments.createAppointment(LocalDate.of(2021, 2, 13), "test", SpecializationType.EAR, testPerson);
//
//        assertNull(appointment);
//    }

    @Test
    void planAppointmentIfAnotherDoctorIsAvailableShouldBeOnSameTime() {
        init();

        Appointments instanceAppointments = SingletonAppointments.getInstance();
        Appointment appointment = instanceAppointments.createAppointment(LocalDate.of(2121, 2, 13), "test", SpecializationType.EAR, testPerson);
        Appointment appointment2 = instanceAppointments.createAppointment(LocalDate.of(2121, 2, 13), "test2", SpecializationType.EYES, testPerson2);

        assertEquals(appointment.getAppointmentDate(), appointment2.getAppointmentDate(), "Since there are two available doctors this should match");
    }

    @Test
    void planAppointmentCanNotBeOnSameDay() {
        init();

        Appointments instanceAppointments = SingletonAppointments.getInstance();
        instanceAppointments.createAppointment(LocalDate.of(2121, 2, 13), "test", SpecializationType.EAR, testPerson);
        Appointment appointment2 = instanceAppointments.createAppointment(LocalDate.of(2121, 2, 13), "test2", SpecializationType.EAR, testPerson);

        assertNull(appointment2, "It's not allowed to create two appointments from the same person on the same day");
    }
}