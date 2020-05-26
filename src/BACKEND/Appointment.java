package BACKEND;

public class Appointment {
    private Doctor doctor;
    private Person patient;
    private Boolean done;
    private String condition;

    public Appointment(Person patient, Doctor doctor) {
        this.doctor = doctor;
        this.patient = patient;
        this.done = false;
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
