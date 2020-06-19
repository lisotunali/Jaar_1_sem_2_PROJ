package BACKEND.Medical;

import BACKEND.Person.Doctor;

import java.time.LocalDateTime;

public class AppointmentResult {
    private Doctor doctor;
    private LocalDateTime appointmentTime;

    public AppointmentResult(Doctor doctor, LocalDateTime appointmentTime) {
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
}
