package com.pharmacy.service;

import com.pharmacy.entity.Pharmacy;

import java.util.List;

public interface PharmacyService {
    void create(Pharmacy pharmacy);
    void update(Pharmacy pharmacy);
    void delete(Pharmacy pharmacy);

    Pharmacy findById(int id);
    Pharmacy findByIdInitialized(int id);
    List findByLocation(int id, float longitude, float latitude);
    List findPharmacyAndPrice(int id);
}
