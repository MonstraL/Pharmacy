package com.pharmacy.service;

import com.pharmacy.entity.Symptom;

import java.util.List;

public interface SymptomService {
    Symptom findById(int id);
    Symptom findByIdInitialized(int id);
}
