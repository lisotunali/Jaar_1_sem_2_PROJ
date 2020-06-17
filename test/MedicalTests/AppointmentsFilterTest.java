package MedicalTests;

import BACKEND.Medical.Appointments;
import BACKEND.Person.Doctor;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.Person.SpecializationType;
import BACKEND.fakeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AppointmentsFilterTest {

    Doctor doctor;
    IPerson IPerson;

    public void init(){
        Appointments.resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        doctor = new Doctor("testerino", "123", SpecializationType.GENERAL);
        IPerson = new Person("test", "123");
        fakeDatabase.getUserDatabase().add(doctor);
        fakeDatabase.getUserDatabase().add(IPerson);
        Appointments.createAppointment(LocalDate.now(), "helemaal niks", SpecializationType.GENERAL, IPerson);
    }

    @Test
    public void getAllAppointmentsPerson1(){
        init();
        Appointments.getAllAppointments().get(0).setDone(true);
        Assertions.assertFalse(Appointments.getAllAppointments(IPerson).isEmpty());
    }

    @Test
    public void getAllAppointmentsPerson2(){
        init();
        Assertions.assertFalse(Appointments.getAllAppointments(IPerson).isEmpty());
    }

    @Test
    public void getAllAppointmentsDoctor1(){
        init();
        Appointments.getAllAppointments().get(0).setDone(true);
        Assertions.assertFalse(Appointments.getAllAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllAppointmentsDoctor2(){
        init();
        Assertions.assertFalse(Appointments.getAllAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllDoneAppointmentsPerson1(){
        init();
        Appointments.getAllAppointments().get(0).setDone(true);
        Assertions.assertFalse(Appointments.getDoneAppointments(IPerson).isEmpty());
    }

    @Test
    public void getAllDoneAppointmentsPerson2(){
        init();
        Assertions.assertTrue(Appointments.getDoneAppointments(IPerson).isEmpty());
    }

    @Test
    public void getAllDoneAppointmentsDoctor1(){
        init();
        Appointments.getAllAppointments().get(0).setDone(true);
        Assertions.assertFalse(Appointments.getDoneAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllDoneAppointmentsDoctor2(){
        init();
        Assertions.assertTrue(Appointments.getDoneAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllNotDoneAppointmentsPerson1(){
        init();
        Appointments.getAllAppointments().get(0).setDone(true);
        Assertions.assertTrue(Appointments.getAllOpenAppointments(IPerson).isEmpty());
    }

    @Test
    public void getAllNotDoneAppointmentsPerson2(){
        init();
        Assertions.assertFalse(Appointments.getAllOpenAppointments(IPerson).isEmpty());
    }

    @Test
    public void getAllNotDoneAppointmentsDoctor1(){
        init();
        Appointments.getAllAppointments().get(0).setDone(true);
        Assertions.assertTrue(Appointments.getAllOpenAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllNotDoneAppointmentsDoctor2(){
        init();
        Assertions.assertFalse(Appointments.getAllOpenAppointments(doctor).isEmpty());
    }
}
