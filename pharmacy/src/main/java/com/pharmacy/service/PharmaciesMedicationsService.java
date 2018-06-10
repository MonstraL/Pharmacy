package com.pharmacy.service;

import com.pharmacy.entity.Medication;
import com.pharmacy.entity.Pharmacy;

public interface PharmaciesMedicationsService {
    void updatePrice(Pharmacy pharmacy, Medication medication, float price);
}
