package BACKEND;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Appointments {
    private ArrayList<Appointment> hasAppointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        hasAppointments.add(appointment);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return hasAppointments;
    }

    public ArrayList<Appointment> getAllAppointments(Person patient) {
        return hasAppointments.stream().filter(p -> p.getPatient() == patient).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Appointment> getAllAppointments(Doctor doctor) {
        return hasAppointments.stream().filter(p -> p.getDoctor() == doctor).collect(Collectors.toCollection(ArrayList::new));
    }
}
