package com.pharmacy.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Stepan
 * on 22-Apr-18
 * GenericDAO interface supporting CRUD operations.
 */

public interface GenericDAO<Item, Id extends Serializable> {

    /**
     * Saves an com.pharmacy.entity
     * @param entity any type of com.pharmacy.entity
     */
    void create(Item entity);

    /**
     * Updates com.pharmacy.entity's contents.
     * @param entity any type of com.pharmacy.entity
     */
    void update(Item entity);

    /**
     * Deletes an com.pharmacy.entity.
     * @param entity any type of com.pharmacy.entity
     */
    void delete(Item entity);

    /**
     * Searches for an com.pharmacy.entity of a specified type with a specified id
     * and returns it if present.
     * @param id: id of an com.pharmacy.entity
     * @return Entity object
     */
    Item findById(Id id);

    /**
     * @return list of all existing com.pharmacy.entity objects of a specified type
     */
    List<Item> findAll();

}
