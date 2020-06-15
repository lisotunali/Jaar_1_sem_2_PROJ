package GUI.Controllers.Livestock;

import BACKEND.Livestock.Animal;
import GUI.Controllers.Utility.AlertClass;
import GUI.Controllers.Utility.ConvertClass;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public void addButtonclicked() throws IOException {
        Integer amountAdded = ConvertClass.convertToInt(textFamount.getText());
        if(amountAdded == null){
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please insert a amount", "OK");
            return; }
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
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please select an animaltype", "OK");
        }
    }
    public void removeButtonclicked() throws IOException {
        Integer amountremoved = ConvertClass.convertToInt(textFamount.getText());
        if(amountremoved == null){
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please insert a amount", "OK");
            return; }

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
           AlertClass.showAlert(Alert.AlertType.ERROR,"Please select an animal type", "OK");
        }
    }
    public void backButtonclicked() throws IOException {
        SceneController.switchTo("mainUI");
    }
    public void newButtonclicked() throws IOException {
        Integer newAmount = ConvertClass.convertToInt(textFamount.getText());
        if(textFamount.getText().isEmpty()|| textFtype.getText().isBlank()){
            AlertClass.showAlert(Alert.AlertType.ERROR,"Please insert an animal type and amount", "OK");
        }
        else{
            Alert alert =  new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to add the animal type "+textFtype.getText(), ButtonType.YES,ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                singletonPerson.getInstance().addAnimal(textFtype.getText(),newAmount);
                SceneController.switchTo("livestock");
            }
            else{
                SceneController.switchTo("livestock");
            }
        }
    }
    public void deleteButtonclicked() throws IOException {
        Animal animalDelete = tableView.getSelectionModel().getSelectedItem();
        if(animalDelete == null){
            AlertClass.showAlert(Alert.AlertType.ERROR,"Please select an animal type","OK");
        }
        else{
            Alert alert =  new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete the selected animal type ", ButtonType.YES,ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                singletonPerson.getInstance().removeAnimal(animalDelete.getType());
                SceneController.switchTo("livestock");
            }
            else{
                SceneController.switchTo("livestock");
            }
        }
    }
}