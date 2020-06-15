package GUI;

import BACKEND.Appointment;
import BACKEND.Appointments;
import BACKEND.Doctor;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static GUI.SceneController.getPrimaryStage;

public class MedicalDoctorController extends MedicalBaseController {

    public void initialize() {
        appointments = FXCollections.observableArrayList(Appointments.getAllAppointments(getPerson()));
        super.refreshUserAppointments("patient");
    }

    @Override
    public Doctor getPerson() {
        return (Doctor) super.getPerson();
    }

    @Override
    public ArrayList<Appointment> getOpen() {
        return Appointments.getAllOpenAppointments(getPerson());
    }

    @Override
    public ArrayList<Appointment> getClosed() {
        return Appointments.getDoneAppointments(getPerson());
    }

    public void pressViewAppointment() throws IOException {
        if (appointmentTableView.getSelectionModel().getSelectedItem() == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please select an appointment.");
        }

        if (appointmentTableView.getSelectionModel().getSelectedItem() != null) {
            Appointment selectedItem = appointmentTableView.getSelectionModel().getSelectedItem();

            Stage primaryStage = getPrimaryStage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("viewselectedappointment.fxml"));
            Parent parent = loader.load();
            ViewSelectedAppointmentController controller = loader.getController();
            controller.initData(selectedItem);
            primaryStage.getScene().setRoot(parent);
        }
    }
}
