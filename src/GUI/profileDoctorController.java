package GUI;

import BACKEND.Doctor;
import BACKEND.SpecializationType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.stream.Collectors;

public class profileDoctorController extends profileController {
    public VBox doctorTypes;

    //FiXME: We know it's safe to cast to a doctor but is it possible to remove the cast?
    private Doctor currentUser = (Doctor) singletonPerson.getInstance();
    private ArrayList<CheckBox> specializationsCheckbox = new ArrayList<>();

    @Override
    public void initialize() {
        super.initialize();
        ArrayList<SpecializationType> allTypes = new ArrayList<>(EnumSet.allOf(SpecializationType.class));

        allTypes.forEach(type -> {
            CheckBox cb1 = new CheckBox();
            cb1.setText(String.valueOf(type));
            cb1.setSelected(currentUser.getSpecializations().contains(type));

            specializationsCheckbox.add(cb1);
        });

        doctorTypes.getChildren().addAll(specializationsCheckbox);
    }

    @Override
    public void onUpdateClicked() {
        HashSet<SpecializationType> specializations = specializationsCheckbox.stream()
                .filter(CheckBox::isSelected)
                .map(type -> SpecializationType.valueOf(type.getText()))
                .collect(Collectors.toCollection(HashSet::new));

        currentUser.setSpecializations(specializations);

        super.onUpdateClicked();
    }
}
