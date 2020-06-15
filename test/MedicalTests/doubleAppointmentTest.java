package MedicalTests;//Testen of je een afspraak kan maken bij een doctor als deze (g)een afspraak heeft staan op deze tijd. (Equivalence + Randvoorwaarden) Liso (0 doctors, 1 doctor, multiple doctors

import BACKEND.Doctor;
import BACKEND.Person;
import BACKEND.SpecializationType;
import GUI.SingletonAppointments;
import GUI.fakeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.time.LocalDate;

public class doubleAppointmentTest {

    @Test
    public void noDoctor() {
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments().isEmpty());
    }

    @Test
    public void availableDoctor(){
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
    public void DoctorHasAppointment(){
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        Person testperson2 = new Person("testpers2", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        fakeDatabase.getUserDatabase().add(testperson2);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson2);
        Assertions.assertNotEquals(SingletonAppointments.getInstance().getAllAppointments(testperson).get(0).getAppointmentDate(), SingletonAppointments.getInstance().getAllAppointments(testperson2).get(0).getAppointmentDate());
    }

    @Test
    public void TwoDoctorsAvailable(){
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Doctor testdoc2 = new Doctor("testdoc2", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testdoc2);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(testdoc).isEmpty());
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments(testdoc2).isEmpty());
    }

    @Test
    public void TwoDoctorsOneHasAppointment(){
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Doctor testdoc2 = new Doctor("testdoc2", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        Person testperson2 = new Person("testpers2", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testdoc2);
        fakeDatabase.getUserDatabase().add(testperson);
        fakeDatabase.getUserDatabase().add(testperson2);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson2);
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(testdoc).isEmpty());
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(testdoc2).isEmpty());
    }

    @Test
    public void patientMakesTwoAppointmentsOneDoctor(){
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments(testdoc).size() == 1);
    }

    @Test
    public void patientMakesTwoAppointmentsTwoDoctors(){
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Doctor testdoc2 = new Doctor("testdoc2", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testdoc2);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        SingletonAppointments.getInstance().createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments(testdoc).size() == 1);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllAppointments(testdoc2).isEmpty());
    }
}
