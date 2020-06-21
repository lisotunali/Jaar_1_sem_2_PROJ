package GUI.Controllers.Medical;

import BACKEND.Medical.Appointment;
import BACKEND.Medical.Appointments;
import BACKEND.Person.SpecializationType;
import GUI.Controllers.Utility.AlertClass;
import GUI.SceneController;
import javafx.scene.control.*;

import java.io.IOException;

public class ViewSelectedAppointmentController {

    public TextField patientName;
    public TextArea conditionArea;
    public DatePicker dateText;
    public TextField timeField;
    public CheckBox doneCheck;
    public ComboBox<SpecializationType> specialties = new ComboBox<>();
    private Appointment currentAppointment;

    public void initialize() {
    }

    public void initData(Appointment selectedAppointment) {
        currentAppointment = selectedAppointment;

        patientName.setText(selectedAppointment.getPatient().getName());
        conditionArea.setText(selectedAppointment.getCondition());
        dateText.setValue(selectedAppointment.getAppointmentDate().toLocalDate());
        timeField.setText(selectedAppointment.getAppointmentDate().toLocalTime().toString());
        specialties.getItems().addAll(SpecializationType.values());
        specialties.getSelectionModel().select(selectedAppointment.getAppointmentType());
        doneCheck.setSelected(selectedAppointment.getDone());
    }

    public void changeAppointment() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to change this appointment?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            if (!conditionArea.getText().equals(currentAppointment.getCondition())) {
                currentAppointment.setCondition(conditionArea.getText());
            }
            if (!(doneCheck.isSelected() == currentAppointment.getDone())) {
                currentAppointment.setDone(doneCheck.isSelected());
            }
            try {
                Appointments.updateAppointment(currentAppointment, dateText.getValue(), specialties.getSelectionModel().getSelectedItem());
                AlertClass.showAlert(Alert.AlertType.INFORMATION, "Appointment has been changed.");
                mainScreen();
            } catch (Exception e) {
                AlertClass.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }

    public void deleteAppointment() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this appointment?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Appointments.removeAppointment(currentAppointment);
            AlertClass.showAlert(Alert.AlertType.INFORMATION, "Appointment has been cancelled.");
            mainScreen();
        }
    }

    public void mainScreen() throws IOException {
        SceneController.switchTo("Medical/medicalDoctor");
    }
}
