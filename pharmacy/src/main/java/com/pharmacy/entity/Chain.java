package com.pharmacy.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * Created by
 * Stepan
 * on 22-Apr-18
 */

@Entity
@Table(name="chain")
public class Chain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "employees")
    private int employees;

    @Column(name = "emblem")
    private String emblem;

    @OneToMany(mappedBy = "chain")
    @JsonIgnoreProperties(value = {"medications"})
    private List<Pharmacy> pharmacies;

    public Chain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    @Override
    public String toString() {
        return "Chain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", employees=" + employees +
                '}';
    }
}
