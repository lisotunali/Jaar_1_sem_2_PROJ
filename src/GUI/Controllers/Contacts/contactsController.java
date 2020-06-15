package GUI.Controllers.Contacts;

import BACKEND.Contact.Contact;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
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
        SceneController.switchTo("mainUI");
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
        Contact newContact = new Contact(nameTextField.getText(), phoneTextField.getText(), emailTextField.getText(), addressTextField.getText());
        singletonPerson.getInstance().addContact(newContact);
        contacts.add(newContact);
    }

    public void contactsRemoveButtonClicked() throws IOException {
        Contact contactToBeDeleted = tableView.getSelectionModel().getSelectedItem();
        if (contactToBeDeleted != null) {
            for (Contact contact : singletonPerson.getInstance().getAddressBook()) {
                if (contact.getName().equals(contactToBeDeleted.getName())) {
                    singletonPerson.getInstance().removeContact(contact);
                    contacts.remove(contact);
                    return;
                }
            }
        }

    }

    public void contactsEditButtonClicked() throws IOException {
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        if (contact != null) {
            contact.setName(nameTextField.getText());
            contact.setPhoneNumber(phoneTextField.getText());
            contact.setEmail(emailTextField.getText());
            contact.setAddress(addressTextField.getText());
            tableView.refresh();
        }
    }
}
