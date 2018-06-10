package com.pharmacy.dao;

import com.pharmacy.entity.Pharmacy;

import java.util.Map;

public interface PharmacyDAO extends GenericDAO<Pharmacy, Integer> {
    Pharmacy findByIdInitialized(int id);
    void create(Pharmacy pharmacy, float longitude, float latitude);
}
