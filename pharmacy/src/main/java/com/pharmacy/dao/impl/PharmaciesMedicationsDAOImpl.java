package com.pharmacy.dao.impl;

import com.pharmacy.dao.PharmaciesMedicationsDAO;
import com.pharmacy.entity.PharmaciesMedications;
import org.springframework.stereotype.Repository;

@Repository("pharmacies_medicationsDAO")
public class PharmaciesMedicationsDAOImpl extends HibernateDAO<PharmaciesMedications, Integer> implements PharmaciesMedicationsDAO {
}
