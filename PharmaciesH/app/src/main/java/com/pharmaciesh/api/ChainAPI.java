package com.pharmaciesh.api;

import com.pharmaciesh.entity.Chain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChainAPI {

    String BASE_URL = "http://192.168.1.3:8080/";

    @GET("chains/{id}")
    Call<Chain> getMedication(@Path("id") int chainId);


}
