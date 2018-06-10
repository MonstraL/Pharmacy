package com.pharmacy.service.impl;

import com.pharmacy.dao.MedicationDAO;
import com.pharmacy.entity.Medication;
import com.pharmacy.entity.Pharmacy;
import com.pharmacy.service.MedicationService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("MedicationServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class MedicationServiceImpl implements MedicationService{

    private SessionFactory sessionFactory;
    private MedicationDAO medicationDAO;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setMedicationDAO(MedicationDAO medicationDAO) {
        this.medicationDAO = medicationDAO;
    }

    @Override
    public void create(Medication medication) {
        medicationDAO.create(medication);
    }

    @Override
    public void update(Medication medication) {
        medicationDAO.update(medication);
    }

    @Override
    public void delete(Medication medication) {
        medicationDAO.delete(medication);
    }

    @Override
    public Medication findById(int id) {
       return medicationDAO.findById(id);
    }

    @Override
    public Medication findByIdInitialized(int id) {
        return medicationDAO.findByIdInitialized(id);
    }

    @Override
    public List<Medication> findByActiveSubs(int id, String nameSubstance) {
        return medicationDAO.findByActiveSubs(id, nameSubstance);
    }

    @Override
    public List<Medication> findMedicationsByName(String name) {
        return medicationDAO.findByName(name);
    }

}
