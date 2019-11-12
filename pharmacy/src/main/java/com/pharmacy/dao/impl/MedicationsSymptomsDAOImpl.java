package com.pharmacy.dao.impl;

import com.pharmacy.dao.MedicationsSymptomsDAO;
import com.pharmacy.entity.MedicationsSymptoms;
import org.springframework.stereotype.Repository;

@Repository("medications_symptomsDAO")
public class MedicationsSymptomsDAOImpl extends HibernateDAO<MedicationsSymptoms, Integer> implements MedicationsSymptomsDAO {
}
