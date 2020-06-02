package GUI;

import BACKEND.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;

public class MedicalController {
    public Button backButton;
    public Button requestButton;

    public TableView<Appointment> appointmentTableView;
    public TableColumn<Appointment, String> doctorNameColumn;
    public TableColumn<Appointment, String> conditionColumn;
    public TableColumn<Appointment, Date> dateColumn;
    public TableColumn<Appointment, String> doneColumn;
    public CheckBox showDoneCheck;
    public CheckBox showNotDoneCheck;

    ObservableList<Appointment> appointments;

    public void initialize() {
        refreshUserAppointments();
    }

    //Adds/refreshes the TableView, which contains all appointments that belong to the current logged in user.
    public void refreshUserAppointments() {
        appointments = FXCollections.observableArrayList(SingletonAppointments.getInstance().getAllAppointments(singletonPerson.getInstance()));
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDateString"));
        doneColumn.setCellValueFactory(new PropertyValueFactory<>("doneToString"));
        appointmentTableView.setItems(appointments);
        appointmentTableView.refresh();
    }

    //Filters the TableView based on the appointments that are done or not.
    public void filterAppointments() {
        appointmentTableView.getItems().clear();
        if (showNotDoneCheck.isSelected()) {
            appointments.addAll(SingletonAppointments.getInstance().getAllOpenAppointments(singletonPerson.getInstance()));
        }

        if (showDoneCheck.isSelected()) {
            appointments.addAll(SingletonAppointments.getInstance().getDoneAppointments(singletonPerson.getInstance()));
        }
    }

    public void requestButtonPressed() throws IOException {
        SceneController.switchTo("request");
    }

    //  Returns to the previous scene.
    public void mainScreen() throws IOException {
        SceneController.switchTo("mainUi");
    }
}
