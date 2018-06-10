package com.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "pharmacies_medications")
public class PharmaciesMedications {

    @EmbeddedId
    private PharmaciesMedicationsId id;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id", insertable=false, updatable=false)
    @JsonIgnoreProperties(value = {"medications"})
    private Pharmacy pharmacy;

    @ManyToOne
    @JoinColumn(name = "medication_id", insertable=false, updatable=false)
    @JsonIgnoreProperties(value = {"pharmacies"})
    private Medication medication;

    @Column(name="price")
    private double price;

    public PharmaciesMedications() {
    }

    public PharmaciesMedications(PharmaciesMedicationsId id, Pharmacy pharmacy, Medication medication, float price) {
        this.id = id;
        this.pharmacy = pharmacy;
        this.medication = medication;
        this.price = price;
    }

    public PharmaciesMedicationsId getId() {
        return id;
    }

    public void setId(PharmaciesMedicationsId id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
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
