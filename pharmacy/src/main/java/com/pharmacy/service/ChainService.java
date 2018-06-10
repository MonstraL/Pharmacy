package com.pharmacy.service;

import com.pharmacy.entity.Chain;

public interface ChainService {
    void create(Chain chain);

    void update(Chain chain);

    void delete(Chain chain);

    Chain findById(int id);
    Chain findByIdInitialized(int id);
}
