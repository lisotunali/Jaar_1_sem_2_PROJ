package MedicalTests;

import BACKEND.*;
import GUI.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashMap;


public class dayAvailabilityTest {



    @Test
    public void availableDayBeforeToday() {
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate.minusDays(1), "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void unavailableDayBeforeToday() {
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        EnumSet.allOf(DayOfWeek.class).forEach(d -> testdoc.setAvailableDay(d, false));
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate.minusDays(1), "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void availableToday() {
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void unavailableToday() {
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        EnumSet.allOf(DayOfWeek.class).forEach(d -> testdoc.setAvailableDay(d, false));
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void availableDayAfterToday() {
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate.plusDays(1), "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void unavailableDayAfterToday() {
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        EnumSet.allOf(DayOfWeek.class).forEach(d -> testdoc.setAvailableDay(d, false));
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate.plusDays(1), "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments(testdoc).isEmpty());
    }
}

