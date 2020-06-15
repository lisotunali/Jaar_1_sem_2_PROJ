package GUI;

import BACKEND.Appointment;
import BACKEND.Appointments;
import BACKEND.SpecializationType;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

public class RequestController {

    public ComboBox<SpecializationType> specializationTypeComboBox = new ComboBox<>();
    public DatePicker datePicker;
    public Button requestButton;
    public TextArea conditionField;

    public void initialize() {
        specializationTypeComboBox.getItems().addAll(SpecializationType.values());
    }

    public void createAppointment() throws IOException {
        System.out.println("Attempting request...");
        if (!validateInput()) {
            return;
        }
        Appointment appointment = Appointments.createAppointment(datePicker.getValue(), conditionField.getText(), specializationTypeComboBox.getSelectionModel().getSelectedItem(), singletonPerson.getInstance());
        if (appointment == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Unable to create appointment.\n\nNo doctors are available.");
        } else {
            AlertClass.showAlert(Alert.AlertType.INFORMATION, "Appointment created.");
            mainScreen();
        }
        System.out.println(Appointments.getAllAppointments());
    }

    private Boolean validateInput() {
        if (datePicker.getValue() == null || datePicker.getValue().isBefore(LocalDate.now())) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Unable to create appointment.\n\nInvalid date.\n\nYou haven't selected a date or the date you selected is set in the past.");
            return false;
        }
        if (specializationTypeComboBox.getSelectionModel().getSelectedItem() == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Unable to create appointment.\n\nPlease select a specialty.");
            return false;
        }
        return true;
    }

    public void mainScreen() throws IOException {
        SceneController.switchTo("medicalPerson");
    }
}
