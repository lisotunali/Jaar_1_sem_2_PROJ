import BACKEND.*;
import GUI.SingletonAppointments;
import GUI.fakeDatabase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AppointmentsTest {
    private Person testPerson = new Person("appointmentTester", "iAmTesting");
    private Doctor testDoc = new Doctor("docTester", "iAmStillTesting", SpecializationType.EAR);
    private Doctor testDoc2 = new Doctor("docTester2", "iAmStillTesting", SpecializationType.EAR);

    void init() {
        fakeDatabase.getUserDatabase().add(testDoc);
        fakeDatabase.getUserDatabase().add(testDoc2);
        fakeDatabase.getUserDatabase().add(testPerson);
    }

    @Test
    void planAppointmentBeforeCurrentDate() {
        init();

        Appointments instanceAppointments = SingletonAppointments.getInstance();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Appointment appointment = instanceAppointments.planAppointment(yesterday, "test condition1", SpecializationType.GENERAL, testPerson);

        assertNull(appointment);
    }

    @Test
    void planAppointment() {
        init();

        Appointments instanceAppointments = SingletonAppointments.getInstance();
        Appointment testAppointmentInDb = new Appointment(testPerson, testDoc, "test", SpecializationType.EAR, LocalDateTime.of(2021, 2, 13, 9, 00));
        instanceAppointments.addAppointment(testAppointmentInDb);

        Appointment appointment = instanceAppointments.planAppointment(LocalDate.of(2021, 2, 13), "test", SpecializationType.EAR, testPerson);

        assertNotEquals(null, appointment);
    }

    @Test
    void planAppointmentNoTimeLeft() {
        init();

        Appointments instanceAppointments = SingletonAppointments.getInstance();
        Appointment testAppointmentInDb = new Appointment(testPerson, testDoc, "test", SpecializationType.EAR, LocalDateTime.of(2021, 2, 13, 9, 00));
        instanceAppointments.addAppointment(testAppointmentInDb);

        for (int i = 0; i < 100000; i++) {
            instanceAppointments.planAppointment(LocalDate.of(2021, 2, 13), "test", SpecializationType.EAR, testPerson);
        }

        Appointment appointment = instanceAppointments.planAppointment(LocalDate.of(2021, 2, 13), "test", SpecializationType.EAR, testPerson);

        assertNull(appointment);
    }
}