package com.pharmacy.service;

import com.pharmacy.entity.Medication;
import com.pharmacy.entity.Symptom;

public interface MedicationsSymptomsService {
    void updateWeight(Medication medication, Symptom symptom, double weight);
}
