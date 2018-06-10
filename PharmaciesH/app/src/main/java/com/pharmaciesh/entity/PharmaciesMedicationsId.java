package com.pharmaciesh.entity;


import java.io.Serializable;
import java.util.Objects;

public class PharmaciesMedicationsId implements Serializable{

    private int pharmacyId;

    private int medicationId;


    public PharmaciesMedicationsId(int pharmacyId, int medicationId) {
        this.pharmacyId = pharmacyId;
        this.medicationId = medicationId;
    }

    public PharmaciesMedicationsId() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacyId, medicationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PharmaciesMedicationsId that = (PharmaciesMedicationsId) o;
        return Objects.equals(pharmacyId, that.pharmacyId) &&
                Objects.equals(medicationId, that.medicationId);
    }
}
