package MedicalTests;

import BACKEND.Doctor;
import BACKEND.SpecializationType;
import GUI.SceneController;
import GUI.singletonPerson;
import TestFXBase.TestFXTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

public class ProfileDoctorTest extends TestFXTestBase {

    @Test
    public void profileDoctorControllerTest() throws IOException { //Testen of de gebruiker (dokter) op het profileDoctor scherm komt
        Doctor user = new Doctor("testdoctor", "passwerd", SpecializationType.EAR);
        singletonPerson.setPerson(user);
        SceneController.switchTo("mainUi");
        sleep(500); //loading..
        clickOn("Profile");
        Assertions.assertFalse(lookup("#doctorTypes").tryQuery().isEmpty(), "Doctors specializationtypes not found.");
        Assertions.assertFalse(lookup("#doctorAvailability").tryQuery().isEmpty(), "Doctors availability not found.");
    }

    @Test
    public void removeAvailableDayTest() throws IOException {
        Doctor user = new Doctor("testdoctor", "passwerd", SpecializationType.EAR);
        singletonPerson.setPerson(user);
        SceneController.switchTo("profileDoctor");
        clickOn("MONDAY");
        clickOn("Update");
        Assertions.assertFalse(lookup("Profile has been updated").tryQuery().isEmpty(), "Confirmation message not shown.");
        clickOn("OK");
        Doctor userDoctor = (Doctor) singletonPerson.getInstance(); //cast user to doctor to get availability
        HashMap<DayOfWeek, Boolean> expectedAvailability = new HashMap<DayOfWeek, Boolean>();
        EnumSet.allOf(DayOfWeek.class).forEach(d -> expectedAvailability.put(d, true));
        expectedAvailability.put(DayOfWeek.MONDAY, false);
        Assertions.assertEquals(expectedAvailability, userDoctor.getAvailableDays(), "Availability was not updated.");
    }

    @Test
    public void addAvailableDayTest() throws IOException {
        Doctor user = new Doctor("testdoctor", "passwerd", SpecializationType.EAR);
        user.setAvailableDay(DayOfWeek.MONDAY, false);
        singletonPerson.setPerson(user);
        SceneController.switchTo("profileDoctor");
        clickOn("MONDAY");
        clickOn("Update");
        Assertions.assertFalse(lookup("Profile has been updated").tryQuery().isEmpty(), "Confirmation message not shown.");
        clickOn("OK");
        Doctor userDoctor = (Doctor) singletonPerson.getInstance(); //cast user to doctor to get availability
        HashMap<DayOfWeek, Boolean> expectedAvailability = new HashMap<DayOfWeek, Boolean>();
        EnumSet.allOf(DayOfWeek.class).forEach(d -> expectedAvailability.put(d, true));
        Assertions.assertEquals(expectedAvailability, userDoctor.getAvailableDays(), "Availability was not updated.");
    }

    @Test
    public void addSpecializationsTest() throws IOException {
        Doctor user = new Doctor("testdoctor", "passwerd", SpecializationType.EAR);
        singletonPerson.setPerson(user);
        SceneController.switchTo("profileDoctor");
        sleep(500); //loading..
        clickOn("EYES");
        clickOn("Update");
        Assertions.assertFalse(lookup("Profile has been updated").tryQuery().isEmpty(), "Confirmation message not shown.");
        clickOn("OK");
        Doctor userDoctor = (Doctor) singletonPerson.getInstance(); //cast user to doctor to get availability
        HashSet<SpecializationType> expectedSpecializations = new HashSet<SpecializationType>();
        expectedSpecializations.add(SpecializationType.EAR);
        expectedSpecializations.add(SpecializationType.EYES);
        EnumSet.allOf(SpecializationType.class).forEach(t ->
                Assertions.assertEquals(userDoctor.getSpecializations().contains(t), expectedSpecializations.contains(t), "Specializationtype " + t + " was not updated."));
    }

    @Test
    public void removeSpecializationsTest() throws IOException {
        Doctor user = new Doctor("testdoctor", "passwerd", SpecializationType.EAR);
        singletonPerson.setPerson(user);
        SceneController.switchTo("profileDoctor");
        sleep(500); //loading..
        clickOn("EAR");
        clickOn("Update");
        Assertions.assertFalse(lookup("Profile has been updated").tryQuery().isEmpty(), "Confirmation message not shown.");
        clickOn("OK");
        Doctor userDoctor = (Doctor) singletonPerson.getInstance(); //cast user to doctor to get availability
        EnumSet.allOf(SpecializationType.class).forEach(t ->
                Assertions.assertFalse(userDoctor.getSpecializations().contains(t), "Specializationtype " + t + " was not updated."));
    }
}
