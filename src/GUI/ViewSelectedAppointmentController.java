package GUI;

import BACKEND.Appointment;
import javafx.scene.control.*;

import java.io.IOException;

public class ViewSelectedAppointmentController {

    public TextField patientName;
    public TextArea conditionArea;
    public DatePicker dateText;
    public TextField timeField;
    public CheckBox doneCheck;
    private Appointment currentAppointment;

    public void initialize() {
    }

    public void initData(Appointment selectedAppointment) {
        currentAppointment = selectedAppointment;
        doneCheck.setSelected(selectedAppointment.getDone());
        patientName.setText(selectedAppointment.getPatient().getName());
        dateText.setValue(selectedAppointment.getAppointmentDate().toLocalDate());
    }

    public void changeAppointment() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to change this appointment?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            if (!(currentAppointment.getAppointmentDate().toLocalDate().equals(dateText.getValue()))) {
                SingletonAppointments.getInstance().updateAppointment(currentAppointment, dateText.getValue());
            }
            if (!(conditionArea.getText().equals(currentAppointment.getCondition()))) {
                currentAppointment.setCondition(conditionArea.getText());
            }

            SceneController.switchTo("medicalDoctor");
        }
    }

    public void mainScreen() throws IOException {
        SceneController.switchTo("medicalDoctor");
    }
}
