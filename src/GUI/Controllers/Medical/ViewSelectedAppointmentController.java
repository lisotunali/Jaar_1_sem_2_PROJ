package GUI.Controllers.Medical;

import BACKEND.Medical.Appointment;
import BACKEND.Medical.Appointments;
import BACKEND.Person.SpecializationType;
import GUI.Controllers.Utility.AlertClass;
import GUI.SceneController;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

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

    public void changeAppointment() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to change this appointment?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            boolean changed = false;
            if (!(conditionArea.getText().equals(currentAppointment.getCondition()))) {
                currentAppointment.setCondition(conditionArea.getText());
                changed = true;
            }
            if (!(dateText.getValue().equals(currentAppointment.getAppointmentDate().toLocalDate()) || dateText.getValue().isBefore(LocalDate.now()))) {
                Appointments.updateAppointment(currentAppointment, dateText.getValue());
                changed = true;
            }
            if (!(specialties.getSelectionModel().getSelectedItem().equals(currentAppointment.getAppointmentType()))) {
                currentAppointment.setAppointmentType(specialties.getValue());
                Appointments.updateAppointment(currentAppointment, currentAppointment.getAppointmentDate().toLocalDate());
                if (!(dateText.getValue().equals(currentAppointment.getAppointmentDate().toLocalDate()))) {
                    Appointments.updateAppointment(currentAppointment, dateText.getValue());
                    changed = true;
                }
            }
            if (!(doneCheck.isSelected() == currentAppointment.getDone())) {
                currentAppointment.setDone(doneCheck.isSelected());
                changed = true;
            }
            if (changed == true) {
                AlertClass.showAlert(Alert.AlertType.INFORMATION, "Appointment has been changed.");
                mainScreen();
            } else {
                AlertClass.showAlert(Alert.AlertType.ERROR, "Appointment has not been changed.\n\nYou haven't changed any information,\nor you entered invalid data.\n\nIf you tried changing the specialization,\nyou must specify a new date as well.");
            }
        }
        System.out.println(currentAppointment);
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
