package BACKEND;

import java.util.Collection;
import java.util.HashSet;

public class Doctor extends Person {
    private HashSet<SpecializationType> specializations = new HashSet<>();

    public Doctor(String name, String password, SpecializationType speciality) {
        super(name, password);
        addSpecialization(speciality);
    }

    public Doctor(String name, String password, Collection<SpecializationType> specialities) {
        super(name, password);
        addSpecializations(specialities);
    }

    public HashSet<SpecializationType> getSpecializations() {
        return specializations;
    }

    public void addSpecialization(SpecializationType specializationType) {
        specializations.add(specializationType);
    }

    public void addSpecializations(Collection<SpecializationType> specialities) {
        specializations.addAll(specialities);
    }

    public void removeSpecialization(SpecializationType specializationType) {
        specializations.remove(specializationType);
    }
}
