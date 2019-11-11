package com.pharmaciesh.entity;

import java.io.Serializable;
import java.util.Objects;

public class MedicationsSymptomsId implements Serializable{

    private int medicationId;

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
