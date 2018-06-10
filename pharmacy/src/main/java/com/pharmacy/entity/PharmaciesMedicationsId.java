package com.pharmacy.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PharmaciesMedicationsId implements Serializable{

    @Column(name="pharmacy_id")
    private int pharmacyId;

    @Column(name="medication_id")
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
