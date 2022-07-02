package com.example.openevents20.Api;

import com.example.openevents20.Clases.Event;
import com.example.openevents20.Clases.Soporte;
import com.example.openevents20.Clases.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OpenInterface {
    @POST("users")
    Call<User>register(@Body User usuario);

    @POST("users/login")
    Call<User>login(@Body User usuario);

    @GET("users")
    Call<ArrayList<User>> listUsers(@Header("Authorization") String token);

    @GET("users/{id}")
    Call<User> getUser(@Header("Authorization") String token, @Path("id") Integer id);

    @GET("users/{id}/events")
    Call<ArrayList<Event>> listMyEvents(@Header("Authorization") String token, @Path("id") Integer id);

    @GET("events")
    Call<ArrayList<Event>> listEvents(@Header("Authorization") String token);

    @POST("events")
    Call<Event>createEvent(@Header("Authorization") String token, @Body Event evento);

    @POST("events/{id}/assistances")
    Call<Soporte> joinEvent(@Header("Authorization") String token, @Path("id") Integer id);

    @DELETE("events/{id}/assistances")
    Call<Soporte> leaveEvent(@Header("Authorization") String token, @Path("id") Integer id);






}
