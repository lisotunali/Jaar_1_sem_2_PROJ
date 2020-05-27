package BACKEND;

public class Appointment {
    private Doctor doctor;
    private Person patient;
    private Boolean done;
    private String doneAsString;
    private String condition;
    private SpecializationType appointmentType;

    public Appointment(Person patient, Doctor doctor, SpecializationType appointmentType) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentType = appointmentType;
        this.done = false;
        this.doneAsString = doneToString();
        this.condition = "";
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

    public Person getPatient() {
        return patient;
    }

    public SpecializationType getAppointmentType() {
        return appointmentType;
    }

    public String doneToString() {
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
                ", condition='" + condition + '\'' +
                '}';
    }
}
