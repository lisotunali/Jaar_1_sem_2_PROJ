package GUI;

import BACKEND.Doctor;
import BACKEND.SpecializationType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

public class profileDoctorController extends profileController {
    public VBox doctorTypes;
    public VBox doctorAvailability;

    //FiXME: We know it's safe to cast to a doctor but is it possible to remove the cast?
    private Doctor currentUser = (Doctor) singletonPerson.getInstance();
    private ArrayList<CheckBox> specializationsCheckbox = new ArrayList<>();
    private ArrayList<CheckBox> availabilityCheckbox = new ArrayList<>();

    @Override
    public void initialize() {
        super.initialize();
        createCheckboxesFromEnum(SpecializationType.class, specializationsCheckbox, doctorTypes, currentUser.getSpecializations().stream().map(Enum::toString).collect(Collectors.toList()));
        createCheckboxesFromEnum(DayOfWeek.class, availabilityCheckbox, doctorAvailability, currentUser.getAvailableDays().entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).map(Enum::toString).collect(Collectors.toList()));
    }

    // Enable all checkbox of values given in array if text match
    private void setSelectedCheckboxes(ArrayList<CheckBox> array, List<String> enableList) {
        array.stream().filter(checkBox -> enableList.contains(checkBox.getText())).forEach(checkBox -> checkBox.setSelected(true));
    }

    // Generic method to create checkboxes from enum with selected boxes
    private <E extends Enum<E>> void createCheckboxesFromEnum(Class<E> elementType, ArrayList<CheckBox> checkboxArray, Pane pane, List<String> setSelected) {
        ArrayList<E> allTypes = new ArrayList<>(EnumSet.allOf(elementType));

        allTypes.stream().map(String::valueOf).forEach(o -> {
            CheckBox cb = new CheckBox();
            cb.setText(o);
            cb.setSelected(setSelected.contains(o));
            checkboxArray.add(cb);
        });

        pane.getChildren().addAll(checkboxArray);
    }

    @Override
    public void onUpdateClicked() {
        HashSet<SpecializationType> specializations = specializationsCheckbox.stream()
                .filter(CheckBox::isSelected)
                .map(type -> SpecializationType.valueOf(type.getText()))
                .collect(Collectors.toCollection(HashSet::new));

        currentUser.setSpecializations(specializations);

        HashMap<DayOfWeek, Boolean> availabilities = availabilityCheckbox.stream()
                .collect(Collectors.toMap(checkBox -> DayOfWeek.valueOf(checkBox.getText()), CheckBox::isSelected, (a, b) -> b, HashMap::new));

        currentUser.setAvailableDays(availabilities);

        super.onUpdateClicked();
    }
}
