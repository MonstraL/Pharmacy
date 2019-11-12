package com.pharmacy.dao.impl;

import com.pharmacy.dao.SymptomDAO;
import com.pharmacy.entity.Symptom;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;


@Repository("pharmacyDAO")
public class SymptomDAOImpl extends HibernateDAO<Symptom, Integer> implements SymptomDAO {
    @Override
    public Symptom findByIdInitialized(int id) {
        Symptom symptom = currentSession().get(Symptom.class, id);
        Hibernate.initialize(symptom.getMedications());
        return symptom;
    }

}