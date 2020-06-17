package MedicalTests;

import BACKEND.Medical.Appointments;
import BACKEND.Person.Doctor;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.Person.SpecializationType;
import BACKEND.fakeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;


public class dayAvailabilityTest {



    @Test
    public void availableDayBeforeToday() {
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        IPerson testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate.minusDays(1), "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(Appointments.getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void unavailableDayBeforeToday() {
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        IPerson testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        EnumSet.allOf(DayOfWeek.class).forEach(d -> testdoc.setAvailableDay(d, false));
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate.minusDays(1), "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(Appointments.getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void availableToday() {
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        IPerson testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertFalse(Appointments.getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void unavailableToday() {
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        IPerson testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        EnumSet.allOf(DayOfWeek.class).forEach(d -> testdoc.setAvailableDay(d, false));
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(Appointments.getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void availableDayAfterToday() {
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        IPerson testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate.plusDays(1), "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertFalse(Appointments.getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void unavailableDayAfterToday() {
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        IPerson testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        EnumSet.allOf(DayOfWeek.class).forEach(d -> testdoc.setAvailableDay(d, false));
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate.plusDays(1), "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(Appointments.getAllAppointments(testdoc).isEmpty());
    }
}

