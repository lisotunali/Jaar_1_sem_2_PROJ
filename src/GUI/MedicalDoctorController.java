package GUI;

import BACKEND.Appointment;
import BACKEND.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

import static GUI.SceneController.getPrimaryStage;

public class MedicalDoctorController {

    public TableView<Appointment> appointmentTableView = new TableView<>();
    public TableColumn<Appointment, String> patientNameColumn;
    public TableColumn<Appointment, String> conditionColumn;
    public TableColumn<Appointment, Date> dateColumn;
    public TableColumn<Appointment, Boolean> doneColumn;

    public void initialize() {
        refreshUserAppointments();
    }

    public void refreshUserAppointments() {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList(SingletonAppointments.getInstance().getAllAppointments((Doctor) singletonPerson.getInstance()));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDateString"));
        doneColumn.setCellValueFactory(new PropertyValueFactory<>("doneToString"));
        appointmentTableView.setItems(appointments);
        appointmentTableView.refresh();
    }

    public void mainScreen() throws IOException {
        SceneController.switchTo("mainUi");
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