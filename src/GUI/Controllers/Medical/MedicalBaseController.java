package GUI.Controllers.Medical;

import BACKEND.Medical.Appointment;
import BACKEND.Medical.Appointments;
import BACKEND.Person.IPerson;
import BACKEND.Person.SpecializationType;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public abstract class MedicalBaseController {

    public TableView<Appointment> appointmentTableView = new TableView<>();
    public TableColumn<Appointment, String> NameColumn;
    public TableColumn<Appointment, String> conditionColumn;
    public TableColumn<Appointment, Date> dateColumn;
    public TableColumn<Appointment, Boolean> doneColumn;
    public TableColumn<Appointment, SpecializationType> specialtyColumn;
    public CheckBox showDoneCheck;
    public CheckBox showNotDoneCheck;

    ObservableList<Appointment> appointments;

    public abstract void initialize();

    //Adds/refreshes the TableView, which contains all appointments that belong to the current logged in user.
    public void refreshUserAppointments(String personType) {
        NameColumn.setCellValueFactory(new PropertyValueFactory<>(personType));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDateString"));
        doneColumn.setCellValueFactory(new PropertyValueFactory<>("doneToString"));
        specialtyColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentType"));
        appointmentTableView.setItems(appointments);
        appointmentTableView.refresh();
    }

    //Filters the TableView based on the appointments that are done or not.
    public void filterAppointments() {
        appointmentTableView.getItems().clear();
        if (showNotDoneCheck.isSelected()) {
            appointments.addAll(getOpen());
        }

        if (showDoneCheck.isSelected()) {
            appointments.addAll(getClosed());
        }
    }

    public IPerson getPerson() {
        return singletonPerson.getInstance();
    }

    public ArrayList<Appointment> getOpen() {
        return Appointments.getAllOpenAppointments(getPerson());
    }

    public ArrayList<Appointment> getClosed() {
        return Appointments.getDoneAppointments(getPerson());
    }

    //  Returns to the previous scene.
    public void mainScreen() throws IOException {
        SceneController.switchTo("Main/mainUi");
    }
}
