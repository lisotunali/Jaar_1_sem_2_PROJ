package GUI.Controllers.Medical;

import BACKEND.Medical.Appointments;
import BACKEND.Person.SpecializationType;
import GUI.Controllers.Utility.AlertClass;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import javafx.scene.control.*;

import java.io.IOException;

public class RequestController {

    public ComboBox<SpecializationType> specializationTypeComboBox = new ComboBox<>();
    public DatePicker datePicker;
    public Button requestButton;
    public TextArea conditionField;

    public void initialize() {
        specializationTypeComboBox.getItems().addAll(SpecializationType.values());
    }

    public void createAppointment() throws Exception {
        System.out.println("Attempting request...");
        if (!validateInput()) {
            return;
        }
        try {
            Appointments.createAppointment(datePicker.getValue(), conditionField.getText(), specializationTypeComboBox.getSelectionModel().getSelectedItem(), singletonPerson.getInstance());
            AlertClass.showAlert(Alert.AlertType.INFORMATION, "Appointment created.");
            mainScreen();
        } catch (Exception e) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Unable to create appointment.\n\n" + e.getMessage());
        }
        System.out.println(Appointments.getAllAppointments());
    }

    private Boolean validateInput() {
        if (datePicker.getValue() == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Unable to create appointment.\n\nPlease select a date.");
            return false;
        }
        if (specializationTypeComboBox.getSelectionModel().getSelectedItem() == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Unable to create appointment.\n\nPlease select a specialty.");
            return false;
        }
        return true;
    }

    public void mainScreen() throws IOException {
        SceneController.switchTo("Medical/medicalPerson");
    }
}
