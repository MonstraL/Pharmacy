package com.pharmaciesh.entity;

import java.util.List;

public class Symptom{

    private int id;
    private String symptom;

    private List<Medication> medications;

    public Symptom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", symptom='" + symptom + '\'' +
                '}';
    }
}
