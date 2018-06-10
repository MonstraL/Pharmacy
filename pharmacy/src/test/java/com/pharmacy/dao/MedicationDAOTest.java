package com.pharmacy.dao;

import com.pharmacy.entity.Medication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "/spring/root-context.xml")
public class MedicationDAOTest extends AbstractJUnit4SpringContextTests {


    @Autowired
    private MedicationDAO medicationDAO;

    @Test
    public void testCreate(){
        int size = medicationDAO.findAll().size();
        Medication medication = new Medication();
        medication.setName("TestMedication");
        medicationDAO.create(medication);
        assertTrue(size < medicationDAO.findAll().size());
    }

    @Test
    public void testUpdate() {
        Medication medication = new Medication();
        medication.setName("TestMedication");
        medicationDAO.create(medication);
        medication.setName("updated");
        medicationDAO.update(medication);
        Medication found = medicationDAO.findById(medication.getId());
        assertEquals("updated", found.getName());
    }

    @Test
    public void testFindById() {
        Medication medication = new Medication();
        medicationDAO.create(medication);
        Medication found = medicationDAO.findById(medication.getId());
        assertEquals(found, medication);
    }

    @Test
    public void testFindAll() {
        int size = medicationDAO.findAll().size();
        Medication medication = new Medication();
        medicationDAO.create(medication);
        List<Medication> medications = medicationDAO.findAll();
        assertEquals(size + 1, medications.size());
        assertTrue(medications.contains(medication));
    }

    @Test
    public void testDelete() {
        Medication medication = new Medication();
        medicationDAO.create(medication);
        // successfully added
        assertEquals(medication, medicationDAO.findById(medication.getId()));

        // try to delete
        medicationDAO.delete(medication);
        assertNull(medicationDAO.findById(medication.getId()));
    }
}
