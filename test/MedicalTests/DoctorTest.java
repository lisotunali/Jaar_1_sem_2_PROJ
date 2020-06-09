package MedicalTests;

import BACKEND.Doctor;
import BACKEND.SpecializationType;
import TestFXBase.TestFXTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DoctorTest extends TestFXTestBase {

    @Test //Testing decision point of creating a doctor with 1. one specialization or 2. more than one specialization(in this case three)
    public void testDoctorWithOneOrMoreSpecializations(){

        //Maak een dokter aan met één specialisatie in de constructor
        Doctor generalDoctor = new Doctor("Dokter 1", "123", SpecializationType.GENERAL);
        //Maak een dokter aan met een collectie aan specialisaties in de constructor
        List<SpecializationType> specializations = new ArrayList<>(Arrays.asList(SpecializationType.EAR, SpecializationType.EYES, SpecializationType.SKIN));
        Doctor smartDoctor = new Doctor("Dokter 2", "123", specializations);

        //Dokter 1 heeft maar 1 specialisatie, GENERAL
        Assertions.assertTrue(generalDoctor.getSpecializations().contains(SpecializationType.GENERAL), "generalDoctor should have the GENERAL specializationtype");
        Assertions.assertFalse(generalDoctor.getSpecializations().containsAll(specializations), "generalDoctor should have none of the SKIN, EAR, or EYES specializationtypes");

        //Dokter 2 heeft de SKIN, EYES en EAR specialisaties
        Assertions.assertTrue(smartDoctor.getSpecializations().containsAll(specializations), "smartDoctor should have the EAR, EYES and SKIN specializationtype");
        Assertions.assertFalse(smartDoctor.getSpecializations().contains(SpecializationType.GENERAL), "smartDoctor should not have the GENERAL specializationtype");

    }
}
