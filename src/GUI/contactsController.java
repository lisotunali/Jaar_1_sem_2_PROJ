package GUI;

import BACKEND.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class contactsController {

    public Button contactsBackButton;
    public Button contactsSearchButton;
    public Button contactsAddButton;
    public Button contactsRemoveButton;
    public Button contactsEditButton;
    public TextField phoneTextField;
    public TextField emailTextField;
    public TextField addressTextField;
    public TextField nameTextField;
    public TableView<Contact> tableView;
    public TableColumn<Contact, String> contactsTable;
    public ObservableList<Contact> contacts;


    public void initialize() {
        contacts = FXCollections.observableArrayList(singletonPerson.getInstance().getAddressBook());
        contactsTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setItems(contacts);
    }

    public void contactsBackButtonClicked() throws IOException {
        Main.switchSceneTo("mainUI");
    }

    public void nameClicked() {
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        if (contact != null) {
            nameTextField.setText(contact.getName());
            phoneTextField.setText(contact.getPhoneNumber());
            emailTextField.setText(contact.getEmail());
            addressTextField.setText(contact.getAddress());
        }
    }

    public void contactsSearchButtonClicked() {
        for (Contact contact : singletonPerson.getInstance().getAddressBook()) {
            if (contact.getName().equalsIgnoreCase((nameTextField.getText()))) {
                phoneTextField.setText(contact.getPhoneNumber());
                emailTextField.setText(contact.getEmail());
                addressTextField.setText(contact.getAddress());
            }
        }
    }

    public void contactsAddButtonclicked() throws IOException {
        for (Contact contact : singletonPerson.getInstance().getAddressBook()) {
            if (nameTextField.getText().equals(contact.getName())) {
                return;
            }
        }
        singletonPerson.getInstance().addContact(new Contact(nameTextField.getText(), phoneTextField.getText(), emailTextField.getText(), addressTextField.getText()));
        Main.switchSceneTo("contacts");
    }

    public void contactsRemoveButtonClicked() throws IOException {
        Contact contactToBeDeleted = tableView.getSelectionModel().getSelectedItem();
        for (Contact contact : singletonPerson.getInstance().getAddressBook()) {
            if (contact.getName().equals(contactToBeDeleted.getName())) {
                singletonPerson.getInstance().removeContact(contact);
                Main.switchSceneTo("contacts");
                return;
            }
        }

    }

    public void contactsEditButtonClicked() throws IOException {
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        contact.setName(nameTextField.getText());
        contact.setPhoneNumber(phoneTextField.getText());
        contact.setEmail(emailTextField.getText());
        contact.setAddress(addressTextField.getText());
        Main.switchSceneTo("contacts");
    }
}
