package GUI.Views.Main;

import BACKEND.Medical.Appointment;
import BACKEND.Medical.Appointments;
import BACKEND.Person.Doctor;
import BACKEND.Person.Person;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
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
    public TableColumn<Appointment, String> appointmentCondition;

    public void initialize() {
        Person currentUser = singletonPerson.getInstance();
        currentUserLabel.setText("Current user: " + currentUser.getName());
        setAppointments(currentUser);
    }

    private void setAppointments(Person currentUser) {
        ArrayList<Appointment> allOpenAppointments = new ArrayList<>();
        if (currentUser instanceof Doctor) {
            allOpenAppointments = Appointments.getAllOpenAppointments((Doctor) currentUser);
        } else {
            allOpenAppointments = Appointments.getAllOpenAppointments(currentUser);
        }

        ObservableList<Appointment> appointments = FXCollections.observableArrayList(allOpenAppointments);
        appointmentDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDateString"));
        appointmentCondition.setCellValueFactory(new PropertyValueFactory<>("condition"));
        userAppointments.setItems(appointments);

        userAppointments.getSortOrder().add(appointmentDate);
    }

    public void contactsButtonClicked() throws IOException {
        SceneController.switchTo("Contacts/contacts");
    }

    public void profileButtonClicked() throws IOException {
        if (singletonPerson.getInstance() instanceof Doctor) {
            SceneController.switchTo("Profile/profileDoctor");
        } else {
            SceneController.switchTo("Profile/profile");
        }
    }

    public void logoutButtonClicked() throws IOException {
        SceneController.switchTo("Account/login");
    }

    public void pressMarketplace() throws IOException {
        SceneController.switchTo("Marketplace/marketplace");

    }

    public void LivestockButtonclicked() throws IOException {
        SceneController.switchTo("Livestock/livestock");
    }

    public void medicalButtonClicked() throws IOException {
        if (singletonPerson.getInstance() instanceof Doctor) {
            SceneController.switchTo("Medical/medicalDoctor");
        } else {
            SceneController.switchTo("Medical/medicalPerson");
        }
    }

    public void educationalButtonClicked() throws IOException {
        SceneController.switchTo("Educational/education");
    }
}