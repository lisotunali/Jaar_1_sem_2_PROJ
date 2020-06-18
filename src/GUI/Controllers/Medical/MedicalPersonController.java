package GUI.Controllers.Medical;

import BACKEND.Medical.Appointments;
import GUI.SceneController;
import javafx.collections.FXCollections;

import java.io.IOException;

public class MedicalPersonController extends MedicalBaseController {

    public void initialize() {
        appointments = FXCollections.observableArrayList(Appointments.getAllAppointments(getPerson()));
        super.refreshUserAppointments("doctor");
    }

    public void requestButtonPressed() throws IOException {
        SceneController.switchTo("Medical/request");
    }
}
