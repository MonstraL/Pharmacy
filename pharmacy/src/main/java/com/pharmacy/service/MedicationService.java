package com.pharmacy.service;

import com.pharmacy.entity.Medication;
import com.pharmacy.entity.Pharmacy;
import org.geolatte.geom.M;

import java.util.List;
import java.util.Map;

public interface MedicationService {
    void create(Medication medication);
    void update(Medication medication);
    void delete(Medication medication);

    Medication findById(int id);
    Medication findByIdInitialized(int id);
    List<Medication> findByActiveSubs(int id, String nameSubstance);
    List<Medication> findMedicationsByName(String name);

}
