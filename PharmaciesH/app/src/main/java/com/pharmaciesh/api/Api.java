package com.pharmaciesh.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static  Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static MedicationAPI getMedication() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MedicationAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Creating object for our interface
        return retrofit.create(MedicationAPI.class);
    }

    public static PharmacyAPI getPharmacy() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PharmacyAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Creating object for our interface
        return retrofit.create(PharmacyAPI.class);
    }

    public static ChainAPI getChain() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChainAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Creating object for our interface
        return retrofit.create(ChainAPI.class);
    }

}
