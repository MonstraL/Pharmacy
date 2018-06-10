package com.pharmacy.dao;

import com.pharmacy.entity.Medication;
import com.pharmacy.entity.Pharmacy;

import java.util.List;
import java.util.Map;

public interface MedicationDAO extends GenericDAO<Medication, Integer> {
    List findByLocationAndPrice(int id, float lng, float lat);
    Medication findByIdInitialized(int id);
    List<Medication> findByName(String name);
    List<Pharmacy> findMedicationsAndPrice(int id);
    List<Medication> findByActiveSubs(int id, String nameSubstance);
}
