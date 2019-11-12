package com.pharmacy.service.impl;

import com.pharmacy.dao.MedicationsSymptomsDAO;
import com.pharmacy.entity.*;
import com.pharmacy.service.MedicationsSymptomsService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("PharmaciesMedicationsServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class MedicationsSymptomsServiceImpl implements MedicationsSymptomsService {

    private SessionFactory sessionFactory;
    private MedicationsSymptomsDAO medicationsSymptomsDAO;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setPharmaciesMedicationsDAO(MedicationsSymptomsDAO medicationsSymptomsDAO) {
        this.medicationsSymptomsDAO = medicationsSymptomsDAO;
    }

    @Override
    public void updateWeight(Medication medication, Symptom symptom, double weight) {

    }
}
