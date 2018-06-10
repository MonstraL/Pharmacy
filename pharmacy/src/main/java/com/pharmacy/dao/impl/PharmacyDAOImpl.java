package com.pharmacy.dao.impl;

import com.pharmacy.dao.PharmacyDAO;
import com.pharmacy.entity.Pharmacy;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("pharmacyDAO")
public class PharmacyDAOImpl extends HibernateDAO<Pharmacy, Integer> implements PharmacyDAO {
    @Override
    public Pharmacy findByIdInitialized(int id) {
        Pharmacy pharmacy = currentSession().get(Pharmacy.class, id);
        Hibernate.initialize(pharmacy.getMedications());
        return pharmacy;
    }

    @Override
    public void create(Pharmacy pharmacy, float longitude, float latitude) {
        super.create(pharmacy);
        String hql = "update Pharmacy set point = ST_GeomFromEWKT('SRID=4326;POINT("+ (longitude) +" "+ (latitude) + ")') where id = " + pharmacy.getId();
        currentSession().createQuery(hql).executeUpdate();
    }

}