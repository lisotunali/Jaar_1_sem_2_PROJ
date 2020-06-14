package GUI;

import BACKEND.Appointments;

public class SingletonAppointments {
    private static Appointments appointments = new Appointments();

    private SingletonAppointments() {
    }

    public static Appointments getInstance() {
        return appointments;
    }

}
