package com.pharmacy.service.impl;

import com.pharmacy.dao.PharmaciesMedicationsDAO;
import com.pharmacy.entity.Medication;
import com.pharmacy.entity.PharmaciesMedications;
import com.pharmacy.entity.PharmaciesMedicationsId;
import com.pharmacy.entity.Pharmacy;
import com.pharmacy.service.PharmaciesMedicationsService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("PharmaciesMedicationsServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class PharmaciesMedicationsServiceImpl implements PharmaciesMedicationsService{

    private SessionFactory sessionFactory;
    private PharmaciesMedicationsDAO pharmaciesMedicationsDAO;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setPharmaciesMedicationsDAO(PharmaciesMedicationsDAO pharmaciesMedicationsDAO) {
        this.pharmaciesMedicationsDAO = pharmaciesMedicationsDAO;
    }


    @Override
    public void updatePrice(Pharmacy pharmacy, Medication medication, float price) {
        PharmaciesMedications pharmaciesMedications = new PharmaciesMedications
                (new PharmaciesMedicationsId(pharmacy.getId(), medication.getId()), pharmacy, medication, price);
        pharmaciesMedicationsDAO.create(pharmaciesMedications);
    }


}
