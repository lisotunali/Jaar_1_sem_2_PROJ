package GUI;
import BACKEND.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class LivestockController{
    @FXML
    public TableView<Animal> tableView;
    @FXML
    public TableColumn<Animal, String> typeCol;
    @FXML
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
        Integer getal = Integer.parseInt(textFamount.getText());
        Animal animalEdit = tableView.getSelectionModel().getSelectedItem();
        if(animalEdit != null) {
            for (Animal animal : singletonPerson.getInstance().getAnimals()) {
                if (animal.getType().equalsIgnoreCase(animalEdit.getType())) {
                    singletonPerson.getInstance().getAnimal(animalEdit.getType()).addAmount(getal);
                    Main.switchSceneTo("livestock");
                }
            }
        }
        else{
           // TODO: Change to method used in dev Branch
            Alert alert =  new Alert(Alert.AlertType.ERROR,"Please select an animaltype", ButtonType.OK);
            alert.showAndWait();
        }
    }
    public void removeButtonclicked(ActionEvent actionEvent) throws IOException {
        int getal = Integer.parseInt(textFamount.getText());
        Animal animalEdit = tableView.getSelectionModel().getSelectedItem();
//       if(animalEdit == null){ singletonPerson.getInstance().removeAnimal(textFtype.getText());
        //    initialize();}

        if(animalEdit != null){
            for (Animal animal: singletonPerson.getInstance().getAnimals()) {
                if(animal.getType().equalsIgnoreCase(animalEdit.getType())){
                    singletonPerson.getInstance().getAnimal(animalEdit.getType()).removeAmount(getal);
                   Main.switchSceneTo("livestock");
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
        Main.switchSceneTo("mainUI");
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
                Main.switchSceneTo("livestock");
            }
            else{
                Main.switchSceneTo("livestock");
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
            Main.switchSceneTo("livestock");
        }
    }
}



