package com.pharmacy.dao;

import com.pharmacy.entity.Symptom;

import java.util.List;

public interface SymptomDAO extends GenericDAO<Symptom, Integer> {
    Symptom findByIdInitialized(int id);
}
