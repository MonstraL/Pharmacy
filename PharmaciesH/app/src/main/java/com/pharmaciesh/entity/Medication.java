package com.pharmaciesh.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Stepan
 * on 22-Apr-18
 */

public class Medication implements Parcelable {

    private int id;

    private String name;

    private String emblem;

    private String activeSub;

    private String instruction;

    private String releaseForm;

    public Medication(int id, String name, String emblem, String activeSub, String instruction, String releaseForm) {
        this.id = id;
        this.name = name;
        this.emblem = emblem;
        this.activeSub = activeSub;
        this.instruction = instruction;
        this.releaseForm = releaseForm;
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    @JsonIgnoreProperties(value = {"pharmacies"})
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

    protected Medication(Parcel in) {
        id = in.readInt();
        name = in.readString();
        emblem = in.readString();
        activeSub = in.readString();
        instruction = in.readString();
        releaseForm = in.readString();
        if (in.readByte() == 0x01) {
            pharmacies = new ArrayList<Pharmacy>();
            in.readList(pharmacies, Pharmacy.class.getClassLoader());
        } else {
            pharmacies = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(emblem);
        dest.writeString(activeSub);
        dest.writeString(instruction);
        dest.writeString(releaseForm);
        if (pharmacies == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(pharmacies);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Medication> CREATOR = new Parcelable.Creator<Medication>() {
        @Override
        public Medication createFromParcel(Parcel in) {
            return new Medication(in);
        }

        @Override
        public Medication[] newArray(int size) {
            return new Medication[size];
        }
    };
}
