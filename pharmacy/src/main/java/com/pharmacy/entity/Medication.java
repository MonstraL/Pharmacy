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
@Table(name="medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="emblem")
    private String emblem;

    @Column(name="active_sub")
    private String activeSub;

    @Column(name="instruction")
    private String instruction;

    @Column(name="release_form")
    private String releaseForm;

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    @ManyToMany
    @JoinTable(
            name="pharmacies_medications",
            joinColumns = @JoinColumn(name="medication_id"),
            inverseJoinColumns = @JoinColumn(name="pharmacy_id")
    )
    @JsonIgnoreProperties(value = {"medications"})
    private List<Pharmacy> pharmacies;

    public Medication() {
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

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }

    public String getActiveSub() {
        return activeSub;
    }

    public void setActiveSub(String activeSub) {
        this.activeSub = activeSub;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(String releaseForm) {
        this.releaseForm = releaseForm;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activeSub='" + activeSub + '\'' +
                ", releaseForm='" + releaseForm + '\'' +
                '}';
    }
}
