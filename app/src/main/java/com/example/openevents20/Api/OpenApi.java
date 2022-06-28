package com.example.openevents20.Api;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.openevents20.Event;
import com.example.openevents20.User;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenApi {
    private static OpenApi openApi;
    private Retrofit retrofit;
    private OpenInterface openInterface;

    public OpenApi() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://puigmal.salle.url.edu/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
        this.openInterface = retrofit.create(OpenInterface.class);

    }

    public static OpenApi getInstance() {
        if (openApi== null) {
            openApi= new OpenApi();
        }
        return openApi;

    }

    public void register(User usuario, Callback<User> callback){
        openInterface.register(usuario).enqueue(callback);
    }

    public void login(User usuario, Callback<User> callback){
        openInterface.login(usuario).enqueue(callback);
    }

    public void listEvents(Context c, Callback<ArrayList<Event>> callback) {
        openInterface.listEvents(searchtoken(c)).enqueue(callback);
    }


    public String searchtoken(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("validador", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }

}
