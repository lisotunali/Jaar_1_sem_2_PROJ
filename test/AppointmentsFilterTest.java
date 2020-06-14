import BACKEND.Appointment;
import BACKEND.Doctor;
import BACKEND.Person;
import BACKEND.SpecializationType;
import GUI.SingletonAppointments;
import GUI.fakeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AppointmentsFilterTest {

    Doctor doctor;
    Person person;

    public void init(){
        SingletonAppointments.getInstance().resetArrayList();
        fakeDatabase.getUserDatabase().clear();
        doctor = new Doctor("testerino", "123", SpecializationType.GENERAL);
        person = new Person("test", "123");
        fakeDatabase.getUserDatabase().add(doctor);
        fakeDatabase.getUserDatabase().add(person);
        SingletonAppointments.getInstance().createAppointment(LocalDate.now(), "helemaal niks", SpecializationType.GENERAL, person);
    }

    @Test
    public void getAllAppointmentsPerson1(){
        init();
        SingletonAppointments.getInstance().getAllAppointments().get(0).setDone(true);
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(person).isEmpty());
    }

    @Test
    public void getAllAppointmentsPerson2(){
        init();
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(person).isEmpty());
    }

    @Test
    public void getAllAppointmentsDoctor1(){
        init();
        SingletonAppointments.getInstance().getAllAppointments().get(0).setDone(true);
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllAppointmentsDoctor2(){
        init();
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllDoneAppointmentsPerson1(){
        init();
        SingletonAppointments.getInstance().getAllAppointments().get(0).setDone(true);
        Assertions.assertFalse(SingletonAppointments.getInstance().getDoneAppointments(person).isEmpty());
    }

    @Test
    public void getAllDoneAppointmentsPerson2(){
        init();
        Assertions.assertTrue(SingletonAppointments.getInstance().getDoneAppointments(person).isEmpty());
    }

    @Test
    public void getAllDoneAppointmentsDoctor1(){
        init();
        SingletonAppointments.getInstance().getAllAppointments().get(0).setDone(true);
        Assertions.assertFalse(SingletonAppointments.getInstance().getDoneAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllDoneAppointmentsDoctor2(){
        init();
        Assertions.assertTrue(SingletonAppointments.getInstance().getDoneAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllNotDoneAppointmentsPerson1(){
        init();
        SingletonAppointments.getInstance().getAllAppointments().get(0).setDone(true);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllOpenAppointments(person).isEmpty());
    }

    @Test
    public void getAllNotDoneAppointmentsPerson2(){
        init();
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllOpenAppointments(person).isEmpty());
    }

    @Test
    public void getAllNotDoneAppointmentsDoctor1(){
        init();
        SingletonAppointments.getInstance().getAllAppointments().get(0).setDone(true);
        Assertions.assertTrue(SingletonAppointments.getInstance().getAllOpenAppointments(doctor).isEmpty());
    }

    @Test
    public void getAllNotDoneAppointmentsDoctor2(){
        init();
        Assertions.assertFalse(SingletonAppointments.getInstance().getAllOpenAppointments(doctor).isEmpty());
    }
}
