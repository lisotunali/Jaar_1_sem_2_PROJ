package GUI;

import BACKEND.Appointment;
import BACKEND.Doctor;
import BACKEND.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class mainUiController {
    public Text currentUserLabel;
    public TableView<Appointment> userAppointments;
    public TableColumn<Appointment, String> appointmentDate;
    public TableColumn<Appointment, String> appointmentDoctor;

    public void initialize() {
        Person currentUser = singletonPerson.getInstance();
        currentUserLabel.setText("Current user: " + currentUser.getName());
        setAppointments(currentUser);
    }

    private void setAppointments(Person currentUser) {
        ArrayList<Appointment> allOpenAppointments = SingletonAppointments.getInstance().getAllOpenAppointments(currentUser);

        ObservableList<Appointment> appointments = FXCollections.observableArrayList(allOpenAppointments);
        appointmentDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDateString"));
        appointmentDoctor.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        userAppointments.setItems(appointments);

        userAppointments.getSortOrder().add(appointmentDate);
    }

    public void contactsButtonClicked() throws IOException {
        SceneController.switchTo("contacts");
    }

    public void profileButtonClicked() throws IOException {
        if (singletonPerson.getInstance() instanceof Doctor) {
            SceneController.switchTo("profileDoctor");
        } else {
            SceneController.switchTo("profile");
        }
    }

    public void logoutButtonClicked() throws IOException {
        SceneController.switchTo("login");
    }

    public void pressMarketplace() throws IOException {
        SceneController.switchTo("marketplace");

    }

    public void LivestockButtonclicked() throws IOException {
        SceneController.switchTo("livestock");
    }

    public void medicalButtonClicked() throws IOException {
        if (singletonPerson.getInstance() instanceof Doctor) {
            SceneController.switchTo("medicalDoctor");
        } else {
            SceneController.switchTo("medicalPerson");
        }
    }

    public void educationalButtonClicked() throws IOException {
        SceneController.switchTo("educational");
    }
}
