package com.pharmacy.dao;

import com.pharmacy.entity.Chain;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "/spring/root-context.xml")
public class ChainDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ChainDAO chainDAO;

    @Test
    public void testCreate(){
        int size = chainDAO.findAll().size();
        Chain chain = new Chain();
        chain.setName("TestChain");
        chainDAO.create(chain);
        assertTrue(size < chainDAO.findAll().size());
    }

    @Test
    public void testUpdate() {
        Chain chain = new Chain();
        chain.setName("TestChain");
        chainDAO.create(chain);
        chain.setName("updated");
        chainDAO.update(chain);
        Chain found = chainDAO.findById(chain.getId());
        assertEquals("updated", found.getName());
    }

    @Test
    public void testFindById() {
        Chain chain = new Chain();
        chainDAO.create(chain);
        Chain found = chainDAO.findById(chain.getId());
        assertEquals(found, chain);
    }

    @Test
    public void testFindAll() {
        int size = chainDAO.findAll().size();
        Chain chain = new Chain();
        chainDAO.create(chain);
        List<Chain> chains = chainDAO.findAll();
        assertEquals(size + 1, chains.size());
        assertTrue(chains.contains(chain));
    }

    @Test
    public void testDelete() {
        Chain chain = new Chain();
        chainDAO.create(chain);
        // successfully added
        assertEquals(chain, chainDAO.findById(chain.getId()));
        // try to delete
        chainDAO.delete(chain);
        assertNull(chainDAO.findById(chain.getId()));
    }

}
