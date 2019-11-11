package com.pharmaciesh.entity;

public class MedicationsSymptoms {
    private MedicationsSymptomsId id;

    private Medication medication;
    private Symptom symptom;

    private double price;

    public MedicationsSymptoms() {
    }

    public MedicationsSymptoms(MedicationsSymptomsId id, Symptom symptom, Medication medication, float price) {
        this.id = id;
        this.symptom = symptom;
        this.medication = medication;
        this.price = price;
    }

    public MedicationsSymptomsId getId() {
        return id;
    }

    public void setId(MedicationsSymptomsId id) {
        this.id = id;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
