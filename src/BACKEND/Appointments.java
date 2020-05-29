package BACKEND;

import GUI.SingletonAppointments;
import GUI.fakeDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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

    public ArrayList<Appointment> getAllOpenAppointments(Person patient) {
        return getAllAppointments(patient).stream().filter(appointment -> !appointment.getDone()).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Appointment> getAllOpenAppointments(Doctor doctor) {
        return getAllAppointments(doctor).stream().filter(appointment -> !appointment.getDone()).collect(Collectors.toCollection(ArrayList::new));
    }

    // Automatically plan an appointment. Date should just be a date without time.
    public Appointment planAppointment(LocalDate date, String condition, SpecializationType specialtyType, Person patient) {
        // Assuming normal day is between 09:00 and 17:00
        LocalTime open = LocalTime.parse("09:00:00");
        LocalTime closed = LocalTime.parse("17:00:00");
        int appointmentMinutes = 15;
        LocalDate currentDate = LocalDate.now();

        ArrayList<Appointment> allAppointments = SingletonAppointments.getInstance().getAllAppointments();

        // Check if date is before current time
        if (date.isBefore(currentDate)) return null;

        // Grab all doctors available at date and has specified type
        ArrayList<Doctor> doctors = fakeDatabase.getUserDatabase()
                .stream()
                .filter(Doctor.class::isInstance)
                .map(Doctor.class::cast)
                .filter(doctor -> doctor.isAvailableOn(date.getDayOfWeek()) && doctor.getSpecializations().contains(specialtyType))
                .collect(Collectors.toCollection(ArrayList::new));

        if (doctors.size() == 0) return null;

        // Grab all appointments on given date
        ArrayList<Appointment> appointments = allAppointments.stream()
                .filter(appointment -> appointment.getAppointmentDate().toLocalDate().equals(date))
                .collect(Collectors.toCollection(ArrayList::new));

        // Check for each available doctor if they have time on day
        Doctor availableDoc = null;
        LocalDateTime appointmentTime = LocalDateTime.of(date, open);

        for (Doctor doc : doctors) {
            ArrayList<Appointment> appointmentsDoc = appointments.stream()
                    .filter(appointment -> appointment.getDoctor().equals(doc))
                    .sorted(Comparator.comparing(Appointment::getAppointmentDate))
                    .collect(Collectors.toCollection(ArrayList::new));

            if (appointmentsDoc.size() == 0) {
                availableDoc = doc;
                break;
            }

            LocalDateTime currentAppointmentTime = appointmentsDoc.get(appointmentsDoc.size() - 1).getAppointmentDate().plusMinutes(appointmentMinutes + 1);

            if (!currentAppointmentTime.toLocalTime().isAfter(closed)) {
                availableDoc = doc;
                appointmentTime = currentAppointmentTime;
                break;
            }
        }

        if (availableDoc == null) return null;

        System.out.format("Created appointment with doctor: %s at: %s%n", availableDoc.getName(), appointmentTime);

        Appointment appointment = new Appointment(patient, availableDoc, condition, specialtyType, appointmentTime);
        addAppointment(appointment);

        return appointment;
    }
}
