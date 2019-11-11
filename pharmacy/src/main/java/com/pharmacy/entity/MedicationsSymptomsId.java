package com.pharmacy.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MedicationsSymptomsId implements Serializable{

    @Column(name="medication_id")
    private int medicationId;

    @Column(name="symptom_id")
    private int symptomId;

    public MedicationsSymptomsId(int medicationId, int symptomId) {
        this.medicationId = medicationId;
        this.symptomId = symptomId;
    }

    public MedicationsSymptomsId() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(symptomId, medicationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MedicationsSymptomsId that = (MedicationsSymptomsId) o;
        return Objects.equals(symptomId, that.symptomId) &&
                Objects.equals(medicationId, that.medicationId);
    }
}
