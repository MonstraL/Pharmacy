package com.pharmaciesh.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Stepan
 * on 22-Apr-18
 */

public class Pharmacy implements Parcelable {

    private int id;

    private String openingTime;

    private String closingTime;

    private String workDays;

    private String street;

    private int houseNum;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price; //temp column

    /*
    @Column(columnDefinition = "Geometry(Point,4326)",name="point")
    private Point point;*/

    private Chain chain;

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

   /* public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }*/

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

    protected Pharmacy(Parcel in) {
        id = in.readInt();
        openingTime = in.readString();
        closingTime = in.readString();
        workDays = in.readString();
        street = in.readString();
        houseNum = in.readInt();
        price = in.readDouble();
        chain = (Chain) in.readValue(Chain.class.getClassLoader());
        if (in.readByte() == 0x01) {
            medications = new ArrayList<Medication>();
            in.readList(medications, Medication.class.getClassLoader());
        } else {
            medications = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(openingTime);
        dest.writeString(closingTime);
        dest.writeString(workDays);
        dest.writeString(street);
        dest.writeInt(houseNum);
        dest.writeDouble(price);
        dest.writeValue(chain);
        if (medications == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(medications);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Pharmacy> CREATOR = new Parcelable.Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel in) {
            return new Pharmacy(in);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };
}