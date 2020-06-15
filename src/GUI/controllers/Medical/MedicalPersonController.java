package GUI.controllers.Medical;

import BACKEND.Appointments;
import GUI.SceneController;
import javafx.collections.FXCollections;

import java.io.IOException;

public class MedicalPersonController extends MedicalBaseController {

    public void initialize() {
        appointments = FXCollections.observableArrayList(Appointments.getAllAppointments(getPerson()));
        super.refreshUserAppointments("doctor");
    }

    public void requestButtonPressed() throws IOException {
        SceneController.switchTo("request");
    }
}
