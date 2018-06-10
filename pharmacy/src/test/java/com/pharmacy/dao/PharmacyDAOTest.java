package com.pharmacy.dao;

import com.pharmacy.entity.Pharmacy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "/spring/root-context.xml")
public class PharmacyDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private PharmacyDAO pharmacyDAO;

    @Autowired
    private ChainDAO chainDAO;

    @Test
    public void testCreate(){
        int size = pharmacyDAO.findAll().size();
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setChain(chainDAO.findAll().get(0));
        pharmacyDAO.create(pharmacy);
        assertTrue(size < pharmacyDAO.findAll().size());
    }

    @Test
    public void testUpdate() {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setHouseNum(1);
        pharmacyDAO.create(pharmacy);
        pharmacy.setHouseNum(2);
        pharmacyDAO.update(pharmacy);
        Pharmacy found = pharmacyDAO.findById(pharmacy.getId());
        assertEquals(2, found.getHouseNum());
    }

    @Test
    public void testFindById() {
        Pharmacy pharmacy = new Pharmacy();
        pharmacyDAO.create(pharmacy);
        Pharmacy found = pharmacyDAO.findById(pharmacy.getId());
        assertEquals(found, pharmacy);
    }

    @Test
    public void testFindAll() {
        int size = pharmacyDAO.findAll().size();
        Pharmacy pharmacy = new Pharmacy();
        pharmacyDAO.create(pharmacy);
        List<Pharmacy> pharmacies = pharmacyDAO.findAll();
        assertEquals(size + 1, pharmacies.size());
        assertTrue(pharmacies.contains(pharmacy));
    }

    @Test
    public void testDelete() {
        Pharmacy pharmacy = new Pharmacy();
        pharmacyDAO.create(pharmacy);
        // successfully added
        assertEquals(pharmacy, pharmacyDAO.findById(pharmacy.getId()));

        // try to delete
        pharmacyDAO.delete(pharmacy);
        assertNull(pharmacyDAO.findById(pharmacy.getId()));
    }
}
