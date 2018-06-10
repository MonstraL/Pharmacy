package com.pharmacy.service.impl;

import com.pharmacy.dao.MedicationDAO;
import com.pharmacy.dao.PharmacyDAO;
import com.pharmacy.entity.Pharmacy;
import com.pharmacy.service.PharmacyService;
import com.pharmacy.util.HttpDataHandler;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("PharmacyServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class PharmacyServiceImpl implements PharmacyService {

    private SessionFactory sessionFactory;
    private PharmacyDAO pharmacyDAO;
    private MedicationDAO medicationDAO;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setPharmacyDAO(PharmacyDAO pharmacyDAO) {
        this.pharmacyDAO = pharmacyDAO;
    }

    @Autowired
    public void setMedicationDAO(MedicationDAO medicationDAO) {
        this.medicationDAO = medicationDAO;
    }

    @Override
    public void create(Pharmacy pharmacy) {
        float[] coords = HttpDataHandler.findCoordinates(pharmacy.getStreet()+"+"+pharmacy.getHouseNum());
        pharmacyDAO.create(pharmacy, coords[0], coords[1]);
    }

    @Override
    public void update(Pharmacy pharmacy) {
        pharmacyDAO.update(pharmacy);
    }

    @Override
    public void delete(Pharmacy pharmacy) {
        pharmacyDAO.delete(pharmacy);
    }

    @Override
    public Pharmacy findById(int id) {
        return pharmacyDAO.findById(id);
    }

    @Override
    public Pharmacy findByIdInitialized(int id) {
        return pharmacyDAO.findByIdInitialized(id);
    }

    @Override
    public List findByLocation(int id, float longitude, float latitude) {
        return medicationDAO.findByLocationAndPrice(id, longitude, latitude);
    }


    @Override
    public List<Pharmacy> findPharmacyAndPrice(int id)
    {
        return  medicationDAO.findMedicationsAndPrice(id);
    }


}
