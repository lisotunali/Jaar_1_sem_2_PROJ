package BACKEND;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

public class Doctor extends Person {
    private HashSet<SpecializationType> specializations = new HashSet<>();
    private HashMap<DayOfWeek, Boolean> availableDays = new HashMap<>();

    public Doctor(String name, String password, SpecializationType speciality) {
        super(name, password);
        addSpecialization(speciality);
        setDefaultAvailability();
    }

    public Doctor(String name, String password, Collection<SpecializationType> specialities) {
        super(name, password);
        addSpecializations(specialities);
        setDefaultAvailability();
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

    public void setSpecializations(HashSet<SpecializationType> specializations) {
        this.specializations = specializations;
    }

    public void removeSpecialization(SpecializationType specializationType) {
        specializations.remove(specializationType);
    }

    private void setDefaultAvailability() {
        EnumSet.allOf(DayOfWeek.class).forEach(d -> availableDays.put(d, true));
    }

    public HashMap<DayOfWeek, Boolean> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(HashMap<DayOfWeek, Boolean> availableDays) {
        this.availableDays = availableDays;
    }

    public boolean isAvailableOn(DayOfWeek day) {
        return availableDays.get(day);
    }

    public void setAvailableDay(DayOfWeek day, Boolean bool) {
        availableDays.put(day, bool);
    }
}
