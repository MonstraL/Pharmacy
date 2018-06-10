package com.pharmaciesh.api;

import com.pharmaciesh.entity.Pharmacy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PharmacyAPI {

    String BASE_URL = "http://192.168.1.3:8080/";

    @GET("pharmacies/{id}")
    Call<Pharmacy> getPharmacy(@Path("id") int pharmacyId);

    @GET("pharmacies/by_location/{id}+{longitude}+{latitude}")
    Call<List<Pharmacy>> getPharmaciesByLocation(@Path("id") int medicationId, @Path("longitude") double longitude, @Path("latitude") double latitude);

    @GET("pharmacies/by_price/{id}")
    Call<List<Pharmacy>> getPharmaciesByPrice(@Path("id") int medicationId);
}
