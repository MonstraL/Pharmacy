package com.pharmaciesh.entity;


public class PharmaciesMedications {

    private PharmaciesMedicationsId id;

    private Pharmacy pharmacy;

    private Medication medication;

    private float price;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
