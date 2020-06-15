package MedicalTests;//Testen of je een afspraak kan maken bij een doctor als deze (g)een afspraak heeft staan op deze tijd. (Equivalence + Randvoorwaarden) Liso (0 doctors, 1 doctor, multiple doctors

import BACKEND.Medical.Appointments;
import BACKEND.Person.Doctor;
import BACKEND.Person.Person;
import BACKEND.Person.SpecializationType;
import BACKEND.fakeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class doubleAppointmentTest {

    @Test
    public void noDoctor() {
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(Appointments.getAllAppointments().isEmpty());
    }

    @Test
    public void availableDoctor(){
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertFalse(Appointments.getAllAppointments(testdoc).isEmpty());
    }

    @Test
    public void DoctorHasAppointment(){
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        Person testperson2 = new Person("testpers2", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        fakeDatabase.getUserDatabase().add(testperson2);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson2);
        Assertions.assertNotEquals(Appointments.getAllAppointments(testperson).get(0).getAppointmentDate(), Appointments.getAllAppointments(testperson2).get(0).getAppointmentDate());
    }

    @Test
    public void TwoDoctorsAvailable(){
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Doctor testdoc2 = new Doctor("testdoc2", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testdoc2);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertFalse(Appointments.getAllAppointments(testdoc).isEmpty());
        Assertions.assertTrue(Appointments.getAllAppointments(testdoc2).isEmpty());
    }

    @Test
    public void TwoDoctorsOneHasAppointment(){
        Appointments.resetArrayList();
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
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson2);
        Assertions.assertFalse(Appointments.getAllAppointments(testdoc).isEmpty());
        Assertions.assertFalse(Appointments.getAllAppointments(testdoc2).isEmpty());
    }

    @Test
    public void patientMakesTwoAppointmentsOneDoctor(){
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(Appointments.getAllAppointments(testdoc).size() == 1);
    }

    @Test
    public void patientMakesTwoAppointmentsTwoDoctors(){
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        Doctor testdoc = new Doctor("testdoc", "123", SpecializationType.GENERAL);
        Doctor testdoc2 = new Doctor("testdoc2", "123", SpecializationType.GENERAL);
        Person testperson = new Person("testpers", "123");
        fakeDatabase.getUserDatabase().add(testdoc);
        fakeDatabase.getUserDatabase().add(testdoc2);
        fakeDatabase.getUserDatabase().add(testperson);
        LocalDate localDate = LocalDate.now();
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Appointments.createAppointment(localDate, "test condition for a test appointment", SpecializationType.GENERAL, testperson);
        Assertions.assertTrue(Appointments.getAllAppointments(testdoc).size() == 1);
        Assertions.assertTrue(Appointments.getAllAppointments(testdoc2).isEmpty());
    }
}
