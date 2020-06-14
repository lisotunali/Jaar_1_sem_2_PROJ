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

    public ArrayList<Appointment> getDoneAppointments(Person patient) {
        return getAllAppointments(patient).stream().filter(Appointment::getDone).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Appointment> getDoneAppointments(Doctor doctor) {
        return getAllAppointments(doctor).stream().filter(Appointment::getDone).collect(Collectors.toCollection(ArrayList::new));
    }

    public Appointment createAppointment(LocalDate date, String condition, SpecializationType specialtyType, Person patient) {
        AppointmentResult availableTimeAndDoctor = findAvailableTimeAndDoctor(date, specialtyType, patient);

        if (availableTimeAndDoctor == null) return null;

        System.out.format("Created appointment with doctor: %s at: %s%n", availableTimeAndDoctor.getDoctor(), availableTimeAndDoctor.getAppointmentTime());
        Appointment appointment = new Appointment(patient, availableTimeAndDoctor.getDoctor(), condition, specialtyType, availableTimeAndDoctor.getAppointmentTime());
        addAppointment(appointment);

        return appointment;
    }

    public void resetArrayList(){
        hasAppointments.clear();
    }

    public Appointment updateAppointment(Appointment appointment, LocalDate date) {
        AppointmentResult availableTimeAndDoctor = findAvailableTimeAndDoctor(date, appointment.getAppointmentType(), appointment.getPatient());

        if (availableTimeAndDoctor == null) return null;

        System.out.format("Updated appointment with doctor: %s at: %s%n to doctor: %s at: %s%n", appointment.getDoctor(), appointment.getAppointmentDate(), availableTimeAndDoctor.getDoctor(), availableTimeAndDoctor.getAppointmentTime());
        appointment.setAppointmentDate(availableTimeAndDoctor.getAppointmentTime());
        appointment.setDoctor(availableTimeAndDoctor.getDoctor());

        return appointment;
    }

    // Automatically plan an appointment with the earliest available doctor.
    // If a patient already has an appointment on specified date we'll deny it.
    private AppointmentResult findAvailableTimeAndDoctor(LocalDate date, SpecializationType specialtyType, Person patient) {
        // Assuming normal day is between 09:00 and 17:00
        LocalTime open = LocalTime.parse("09:00:00");
        LocalTime closed = LocalTime.parse("17:00:00");
        int appointmentMinutes = 15;
        LocalDate currentDate = LocalDate.now();

        // Check if date is before current time or patient already has appointment
        if (date.isBefore(currentDate) || getAllOpenAppointments(patient).stream().anyMatch(appointment -> appointment.getAppointmentDate().toLocalDate().equals(date)))
            return null;

        // Grab all doctors available at date with specified type
        ArrayList<Doctor> doctors = getDoctorsAvailableAtDateWithType(date, specialtyType);

        if (doctors.size() == 0) return null;

        // Grab all appointments on given date
        ArrayList<Appointment> appointments = getAllAppointmentsOnDate(date);

        // Check for each available doctor if they have time on day
        ArrayList<AppointmentResult> availableDoctors = new ArrayList<>();

        // Need to check if a doctor is available at time, check if there's another doctor available before that.
        // If there is, just assign that doctor to the appointment.
        for (Doctor doc : doctors) {
            ArrayList<Appointment> appointmentsDoc = getAppointmentsDoctorInAppointment(appointments, doc);

            // Doctor has no appointments on day
            if (appointmentsDoc.size() == 0) {
                availableDoctors.add(new AppointmentResult(doc, LocalDateTime.of(date, open)));
                break;
            }

            // Actually just need to check the last appointment
            LocalDateTime currentAppointmentTime = appointmentsDoc.get(appointmentsDoc.size() - 1).getAppointmentDate().plusMinutes(appointmentMinutes + 1);

            // Found a doctor available
            if (currentAppointmentTime.toLocalTime().isBefore(closed.minusMinutes(appointmentMinutes))) {
                availableDoctors.add(new AppointmentResult(doc, currentAppointmentTime));
            }
        }

        availableDoctors.sort(Comparator.comparing(AppointmentResult::getAppointmentTime));

        if (availableDoctors.isEmpty()) return null;

        // Return the first in the array since that doctor is available at the earliest time
        return availableDoctors.get(0);
    }

    // Get all appointments that the doctor has in given appointments array list
    private ArrayList<Appointment> getAppointmentsDoctorInAppointment(ArrayList<Appointment> appointments, Doctor doc) {
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().equals(doc))
                .sorted(Comparator.comparing(Appointment::getAppointmentDate)).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Appointment> getAllAppointmentsOnDate(LocalDate date) {
        return SingletonAppointments.getInstance().getAllAppointments().stream()
                .filter(appointment -> appointment.getAppointmentDate().toLocalDate().equals(date))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Doctor> getDoctorsAvailableAtDateWithType(LocalDate date, SpecializationType specialtyType) {
        return fakeDatabase.getUserDatabase()
                .stream()
                .filter(Doctor.class::isInstance)
                .map(Doctor.class::cast)
                .filter(doctor -> doctor.isAvailableOn(date.getDayOfWeek()) && doctor.getSpecializations().contains(specialtyType))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeAppointment(Appointment toBeRemoved) {
        hasAppointments.remove(toBeRemoved);
    }
}
