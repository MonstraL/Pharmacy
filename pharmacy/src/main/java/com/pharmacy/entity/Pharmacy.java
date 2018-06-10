package com.pharmacy.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

/**
 * Created by
 * Stepan
 * on 22-Apr-18
 */

@Entity
@Table(name="pharmacy")
public class Pharmacy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="opening_time")
    private String openingTime;

    @Column(name="closing_time")
    private String closingTime;

    @Column(name="work_days")
    private String workDays;

    @Column(name="street")
    private String street;

    @Column(name="house_number")
    private int houseNum;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Transient
    private double price;

    @ManyToOne
    @JoinColumn(name = "chain_id")
    @JsonIgnoreProperties(value = {"pharmacies"})
    private Chain chain;

    @ManyToMany
    @JoinTable(
            name="pharmacies_medications",
            joinColumns = @JoinColumn(name="pharmacy_id"),
            inverseJoinColumns = @JoinColumn(name="medication_id")
    )
    @JsonIgnoreProperties(value = {"pharmacies"})
    private List<Medication> medications;

    public Pharmacy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public Chain getChain() {
        return chain;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", openingTime='" + openingTime + '\'' +
                ", closingTime='" + closingTime + '\'' +
                ", workDays='" + workDays + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +
                '}';
    }
}
