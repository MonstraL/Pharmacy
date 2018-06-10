package com.pharmacy.dao.impl;

import com.pharmacy.dao.ChainDAO;
import com.pharmacy.entity.Chain;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

@Repository("chainDAO")
public class ChainDAOImpl extends HibernateDAO<Chain, Integer> implements ChainDAO{

    @Override
    public Chain findByIdInitialized(int id) {
        Chain chain = currentSession().get(Chain.class, id);
        Hibernate.initialize(chain.getPharmacies());
        return chain;
    }
}
