package com.pharmacy.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by
 * Stepan
 * on 06-Nov-19
 */

@Entity
@Table(name="symptom")
public class Symptom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="symptom")
    private String symptom;

    @ManyToMany
    @JoinTable(
            name="medications_symptoms",
            joinColumns = @JoinColumn(name="symptom_id"),
            inverseJoinColumns = @JoinColumn(name="medication_id")
    )
    @JsonIgnoreProperties(value = {"symptoms"})
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
