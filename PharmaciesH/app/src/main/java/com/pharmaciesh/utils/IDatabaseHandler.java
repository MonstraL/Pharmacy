package com.pharmaciesh.utils;

import com.pharmaciesh.entity.Medication;

import java.util.List;

public interface IDatabaseHandler {
        public void addMedication(Medication medication);
        public Medication getMedication(int id);
        public List<Medication> getAllMedications();
        public int getMedicationsCount();
        public boolean checkExists(int id);
        public int updateMedication(Medication medication);
        public void deleteMedication(int id);
        public void deleteAll();
}
