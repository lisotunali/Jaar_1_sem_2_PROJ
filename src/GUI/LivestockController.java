package GUI;

import BACKEND.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class LivestockController{
    public TableView<Animal> tableView;
    public TableColumn<Animal, String> typeCol;
    public TableColumn<Animal, Integer> amountCol;
    public TextField textFtype;
    public TextField textFamount;

    public void initialize() {
        textFtype.getText().equals(null);
        textFamount.getText().equals(null);
        ObservableList <Animal> livestock = FXCollections.observableArrayList(singletonPerson.getInstance().getAnimals());
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableView.setItems(livestock);

    }
    public void addButtonclicked(ActionEvent actionEvent) throws IOException {
        Integer amountAdded = Integer.parseInt(textFamount.getText());
        Animal animalEdit = tableView.getSelectionModel().getSelectedItem();
        if(animalEdit != null) {
            for (Animal animal : singletonPerson.getInstance().getAnimals()) {
                if (animal.getType().equalsIgnoreCase(animalEdit.getType())) {
                    singletonPerson.getInstance().getAnimal(animalEdit.getType()).addAmount(amountAdded);
                    SceneController.switchTo("livestock");
                }
            }
        }
        else{
           // TODO: Change to method used in dev Branch
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please select an animaltype", "OK");
        }
    }
    public void removeButtonclicked(ActionEvent actionEvent) throws IOException {
        int amountremoved = Integer.parseInt(textFamount.getText());
        Animal animalEdit = tableView.getSelectionModel().getSelectedItem();

        if(animalEdit != null){
            for (Animal animal: singletonPerson.getInstance().getAnimals()) {
                if(animal.getType().equalsIgnoreCase(animalEdit.getType())){
                    singletonPerson.getInstance().getAnimal(animalEdit.getType()).removeAmount(amountremoved);
                    SceneController.switchTo("livestock");
                    return;
                }
            }
        }
        else{
            Alert alert =  new Alert(Alert.AlertType.ERROR,"Please select an animal type", ButtonType.OK);
            alert.showAndWait();
        }
    }
    public void backButtonclicked(ActionEvent actionEvent) throws IOException {
        SceneController.switchTo("mainUI");
    }
    public void newButtonclicked(ActionEvent actionEvent) throws IOException {

        if(textFamount.getText().isEmpty()|| textFtype.getText().isBlank()){
            // TODO: Change to method used in dev Branch
            Alert alert =  new Alert(Alert.AlertType.ERROR,"Please insert an animal type and amount", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            // TODO: Change to method used in dev Branch
            Alert alert =  new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to add the animal type "+textFtype.getText(), ButtonType.YES,ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                singletonPerson.getInstance().addAnimal(textFtype.getText(), Integer.parseInt(textFamount.getText()));
                SceneController.switchTo("livestock");
            }
            else{
                SceneController.switchTo("livestock");
            }
        }
    }
    public void deleteButtonclicked(ActionEvent actionEvent) throws IOException {
        Animal animalDelete = tableView.getSelectionModel().getSelectedItem();
        if(animalDelete == null){
            // TODO: Change to method used in dev Branch
            Alert alert =  new Alert(Alert.AlertType.ERROR,"Please select an animal type", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            singletonPerson.getInstance().removeAnimal(animalDelete.getType());
            SceneController.switchTo("livestock");
        }
    }
}