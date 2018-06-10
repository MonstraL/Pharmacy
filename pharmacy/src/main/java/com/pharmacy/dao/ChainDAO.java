package com.pharmacy.dao;

import com.pharmacy.entity.Chain;

public interface ChainDAO extends GenericDAO<Chain, Integer> {
    Chain findByIdInitialized(int id);
}
