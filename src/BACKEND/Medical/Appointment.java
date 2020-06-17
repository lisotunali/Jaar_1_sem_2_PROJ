package BACKEND.Medical;

import BACKEND.Person.Doctor;
import BACKEND.Person.IPerson;
import BACKEND.Person.SpecializationType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private Doctor doctor;
    private IPerson patient;
    private Boolean done;
    private String condition;
    private SpecializationType appointmentType;
    private LocalDateTime appointmentDate;

    public Appointment(IPerson patient, Doctor doctor, String condition, SpecializationType appointmentType, LocalDateTime appointmentDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentType = appointmentType;
        this.done = false;
        this.condition = condition;
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentType(SpecializationType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public IPerson getPatient() {
        return patient;
    }

    public SpecializationType getAppointmentType() {
        return appointmentType;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDoctorName() {
        return getDoctor().getName();
    }

    public String getAppointmentDateString() {
        return appointmentDate.format(DateTimeFormatter.ofPattern("EEEE, d MMM yyyy, HH:mm"));
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDoneToString() {
        if (!done) {
            return "No";
        } else {
            return "Yes";
        }
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", done=" + done +
                ", doneAsString='" + getDoneToString() + '\'' +
                ", condition='" + condition + '\'' +
                ", appointmentType=" + appointmentType +
                ", appointmentDate=" + appointmentDate +
                '}';
    }
}
