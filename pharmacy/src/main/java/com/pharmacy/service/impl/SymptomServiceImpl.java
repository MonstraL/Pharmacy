package com.pharmacy.service.impl;

import com.pharmacy.dao.MedicationDAO;
import com.pharmacy.dao.SymptomDAO;
import com.pharmacy.entity.Symptom;
import com.pharmacy.service.SymptomService;
import com.pharmacy.util.HttpDataHandler;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("SymptomServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class SymptomServiceImpl implements SymptomService {

    private SessionFactory sessionFactory;
    private SymptomDAO symptomDAO;
    private MedicationDAO medicationDAO;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setSymptomDAO(SymptomDAO symptomDAO) {
        this.symptomDAO = symptomDAO;
    }

    @Autowired
    public void setMedicationDAO(MedicationDAO medicationDAO) {
        this.medicationDAO = medicationDAO;
    }

    @Override
    public Symptom findById(int id) {
        return symptomDAO.findById(id);
    }

    @Override
    public Symptom findByIdInitialized(int id) {
        return symptomDAO.findByIdInitialized(id);
    }

}
