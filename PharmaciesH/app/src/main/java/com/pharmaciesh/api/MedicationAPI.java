package com.pharmaciesh.api;

import com.pharmaciesh.entity.Medication;
import com.pharmaciesh.entity.Pharmacy;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MedicationAPI {

    String BASE_URL = "http://192.168.1.3:8080/";

    @GET("medications/{id}")
    Call<Medication> getMedication(@Path("id") int medicationId);

    @GET("medications/by_location/{name}+{longitude}+{latitude}")
    Call<List<Pharmacy>> getMedicationsPriceByLocation(@Path("name") String name, @Path("longitude") float longitude,
                                                            @Path("latitude") float latitude);

    @GET("medications/by_price/{name}")
    Call<List<Pharmacy>> getMedicationsByPrice(@Path("name") String name);

    @GET("medications/by_substance/{id}+{sub_name}")
    Call<List<Medication>> getMedicationsBySubstance(@Path("id") int id, @Path("sub_name") String activeSubName);

    @GET("medications//by_name/{name}")
    Call<List<Medication>> getMedicationsByName(@Path("name") String name);
}
