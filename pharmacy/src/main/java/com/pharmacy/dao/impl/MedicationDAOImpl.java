package com.pharmacy.dao.impl;

import com.pharmacy.dao.MedicationDAO;
import com.pharmacy.entity.Medication;
import com.pharmacy.entity.Pharmacy;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.*;

@Repository("medicationDAO")
public class MedicationDAOImpl extends HibernateDAO<Medication, Integer> implements MedicationDAO{

    /*
    Demo version.
     */
    @Override
    public List<Pharmacy> findByLocationAndPrice(int id, float lng, float lat) {
        String hql = "select * "+
                "from pharmacy as p INNER join pharmacies_medications as pm on pm.pharmacy_id = p.id WHERE pm.medication_id = " + id + "  order by ST_DistanceSphere(p.point, ST_GeomFromEWKT('SRID=4326;POINT("+(lng)+" "+(lat)+")')) limit 20" ;
        List<Pharmacy> pharmacyList= currentSession().createNativeQuery(hql).addEntity(Pharmacy.class).getResultList();
        hql = "select pm.price "+
                "from pharmacy as p INNER join pharmacies_medications as pm on pm.pharmacy_id = p.id WHERE pm.medication_id = " + id + "  order by ST_DistanceSphere(p.point, ST_GeomFromEWKT('SRID=4326;POINT("+(lng)+" "+(lat)+")')) limit 20" ;
        List<Double> priceList = currentSession().createNativeQuery(hql).getResultList();
        Iterator<Pharmacy> i1 = pharmacyList.iterator();
        Iterator<Double> i2 = priceList.iterator();
        while (i1.hasNext() || i2.hasNext()) {
            Pharmacy pharmacy = i1.next();
            Hibernate.initialize(pharmacy.getMedications());
            pharmacy.setPrice(i2.next());
        }
        return pharmacyList;
    }

    @Override
    public Medication findByIdInitialized(int id) {
        Medication medication = currentSession().get(Medication.class, id);
        Hibernate.initialize(medication.getPharmacies());
        return medication;
    }

    @Override
    public List<Medication> findByName(String name) {

        List<Medication> list = currentSession()
                .createQuery("from " +  Medication.class.getName() + " where lower( name ) like lower('%" + name + "%')")
                .setMaxResults(20)
                .getResultList();
        Iterator<Medication> iterator = list.iterator();
        while(iterator.hasNext())
            Hibernate.initialize(iterator.next().getPharmacies());
        return list;
    }

    @Override
    public List<Medication> findByActiveSubs(int id, String nameSubstance) {
        String hql = "select distinct m from Medication m inner join m.pharmacies p where '" + nameSubstance + "' like m.activeSub and m.id != " + id;
        List list = currentSession()
                .createQuery(hql)
                .setMaxResults(20)
                .getResultList();
        Iterator<Medication> iterator = list.iterator();
        while(iterator.hasNext())
            Hibernate.initialize(iterator.next().getPharmacies());
        return list;
    }

    @Override
    public List<Pharmacy> findMedicationsAndPrice(int id) {
        List<Pharmacy> results = new LinkedList<>();
        String hql = "select pm.pharmacy, pm.price from Medication m inner join PharmaciesMedications pm on m.id = pm.medication.id where m.id = "+id+" order by pm.price";
        List<Object[]> list =  currentSession()
                .createQuery(hql)
                .setMaxResults(20)
                .getResultList();
        Pharmacy pharmacy;
        for (Object[] borderTypes: list) {
            pharmacy = (Pharmacy) borderTypes[0];
            pharmacy.setPrice((Double) borderTypes[1]);
            Hibernate.initialize(pharmacy.getMedications());
            results.add(pharmacy);
        }
        return results;
    }
}
