package com.pharmaciesh.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Stepan
 * on 22-Apr-18
 */

public class Chain implements Parcelable {

    private int id;

    private String name;

    private int year;

    private int employees;

    private String emblem;

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

    protected Chain(Parcel in) {
        id = in.readInt();
        name = in.readString();
        year = in.readInt();
        employees = in.readInt();
        emblem = in.readString();
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
        dest.writeInt(year);
        dest.writeInt(employees);
        dest.writeString(emblem);
        if (pharmacies == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(pharmacies);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Chain> CREATOR = new Parcelable.Creator<Chain>() {
        @Override
        public Chain createFromParcel(Parcel in) {
            return new Chain(in);
        }

        @Override
        public Chain[] newArray(int size) {
            return new Chain[size];
        }
    };
}