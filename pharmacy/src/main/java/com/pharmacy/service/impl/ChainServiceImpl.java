package com.pharmacy.service.impl;

import com.pharmacy.dao.ChainDAO;
import com.pharmacy.entity.Chain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.pharmacy.service.ChainService;

@Service("ChainServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class ChainServiceImpl implements ChainService {

    private SessionFactory sessionFactory;
    private ChainDAO chainDAO;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setChainDAO(ChainDAO chainDAO) {
        this.chainDAO = chainDAO;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Chain chain) {
        chainDAO.create(chain);
    }

    @Override
    public void update(Chain chain) {
        chainDAO.update(chain);
    }

    @Override
    public void delete(Chain chain) {
        chainDAO.delete(chain);
    }

    @Override
    public Chain findById(int id) {
        return chainDAO.findById(id);
    }

    @Override
    public Chain findByIdInitialized(int id) {
        return chainDAO.findByIdInitialized(id);
    }
}
