package GUI;

import javafx.collections.FXCollections;

import java.io.IOException;

public class MedicalController extends MedicalBaseController {

    public void initialize() {
        appointments = FXCollections.observableArrayList(SingletonAppointments.getInstance().getAllAppointments(getPerson()));
        super.refreshUserAppointments("doctor");
    }

    public void requestButtonPressed() throws IOException {
        SceneController.switchTo("request");
    }
}
