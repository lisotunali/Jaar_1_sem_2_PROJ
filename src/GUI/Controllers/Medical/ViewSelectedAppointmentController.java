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
//            boolean changed = false;
//            try {
//                if (!(conditionArea.getText().equals(currentAppointment.getCondition()))) {
//                    currentAppointment.setCondition(conditionArea.getText());
//                    changed = true;
//                }
//                if (!(specialties.getSelectionModel().getSelectedItem().equals(currentAppointment.getAppointmentType())) && !(dateText.getValue().equals(currentAppointment.getAppointmentDate().toLocalDate()))) {
//                    changed = true;
//                    Appointments.updateAppointment(currentAppointment, currentAppointment.getAppointmentDate().toLocalDate(), specialties.getSelectionModel().getSelectedItem());
////                    if (!(dateText.getValue().equals(currentAppointment.getAppointmentDate().toLocalDate()))) {
////                        Appointments.updateAppointment(currentAppointment, dateText.getValue());
//                    //}
//                }
//                else {
//                    if (!(dateText.getValue().equals(currentAppointment.getAppointmentDate().toLocalDate()) || dateText.getValue().isBefore(LocalDate.now()))) {
//                        changed = true;
//                        Appointments.updateAppointment(currentAppointment, dateText.getValue(), currentAppointment.getAppointmentType());
//                    }
//                }
//                if (!(doneCheck.isSelected() == currentAppointment.getDone())) {
//                    currentAppointment.setDone(doneCheck.isSelected());
//                    changed = true;
//                }
//            }
//            catch(Exception e){
//                System.out.println(changed);
//                System.out.println();
//                AlertClass.showAlert(Alert.AlertType.ERROR, e.getMessage());
//            }
////            if (changed == true) {
////                AlertClass.showAlert(Alert.AlertType.INFORMATION, "Appointment has been changed.");
////                mainScreen();
////            } else {
////                AlertClass.showAlert(Alert.AlertType.ERROR, "Appointment has not been changed.\n\nYou haven't changed any information,\nor you entered invalid data.\n\nIf you tried changing the specialization,\nyou must specify a new date as well.");
////            }
//        }
//        System.out.println(currentAppointment);
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
