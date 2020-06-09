package GUI;

import BACKEND.SpecializationType;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import BACKEND.Appointment;
import BACKEND.Doctor;
import BACKEND.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.text.Text;
import Education.*;

import java.io.IOException;
import java.time.LocalDateTime;
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
            allOpenAppointments = SingletonAppointments.getInstance().getAllOpenAppointments((Doctor) currentUser);
        } else {
            allOpenAppointments = SingletonAppointments.getInstance().getAllOpenAppointments(currentUser);
        }

        ObservableList<Appointment> appointments = FXCollections.observableArrayList(allOpenAppointments);
        /* appointments.addListener((ListChangeListener.Change<? extends Appointment> c) -> {
            while (c.next()) {
                System.out.println("Listening...");
                if (c.wasUpdated()) {
                    int start = c.getFrom() ;
                    int end = c.getTo() ;
                    for (int i = start ; i < end ; i++) {
                        System.out.println("Element at position "+i+" was updated to: " +c.getList().get(i).getCondition());
                    }
                }
            }
        }); */
        appointmentDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDateString"));
        appointmentCondition.setCellValueFactory(new PropertyValueFactory<>("condition"));
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
        SceneController.switchTo("education");
    }
}