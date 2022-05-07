package com.example.openevents20.Api;

import com.example.openevents20.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OpenInterface {
    @POST("users")
    Call<User>register(@Body User usuario);
}
