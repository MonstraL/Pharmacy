package com.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "medications_symptoms")
public class MedicationsSymptoms {

    @EmbeddedId
    private MedicationsSymptomsId id;

    @ManyToOne
    @JoinColumn(name = "medication_id", insertable=false, updatable=false)
    @JsonIgnoreProperties(value = {"symptoms"})
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "symptoms_id", insertable=false, updatable=false)
    @JsonIgnoreProperties(value = {"medications"})
    private Symptom symptom;

    @Column(name="weight")
    private double weight;

    public MedicationsSymptoms() {
    }

    public MedicationsSymptoms(MedicationsSymptomsId id, Symptom symptom, Medication medication, double weight) {
        this.id = id;
        this.symptom = symptom;
        this.medication = medication;
        this.weight = weight;
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

    public void setSymptom(Pharmacy pharmacy) {
        this.symptom = symptom;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
