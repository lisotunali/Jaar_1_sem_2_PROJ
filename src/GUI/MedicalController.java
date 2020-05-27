package GUI;

import BACKEND.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MedicalController {
    public Button backButton;
    public Button requestButton;

    public TableView<Appointment> appointmentTableView;
    public TableColumn<Appointment, String> doctorNameColumn;
    public TableColumn<Appointment, String> conditionColumn;
    public TableColumn<Appointment, Date> dateColumn;
    public TableColumn<Appointment, Boolean> doneColumn;

    public void initialize() {
        refreshUserAppointments();
    }

    public void refreshUserAppointments() {
        ArrayList<Appointment> tempAppointments = new ArrayList<>();

        for (Appointment appointment : SingletonAppointments.getInstance().getAllAppointments()) {
            if (appointment.getPatient() == singletonPerson.getInstance()) {
                tempAppointments.add(appointment);
                System.out.println(appointment.getPatient() + " " + singletonPerson.getInstance());
            }
        }

        System.out.println("ArrayList empty: " + tempAppointments.isEmpty());

        ObservableList<Appointment> appointments = FXCollections.observableArrayList(tempAppointments);
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
        doneColumn.setCellValueFactory(new PropertyValueFactory<>("done"));
        appointmentTableView.setItems(appointments);
        appointmentTableView.refresh();
    }

    public void requestButtonPressed() throws IOException {
        SceneController.switchTo("request");
    }

    public void mainScreen() throws IOException {
        SceneController.switchTo("mainUi");
    }
}
