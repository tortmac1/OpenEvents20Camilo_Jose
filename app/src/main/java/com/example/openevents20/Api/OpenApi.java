package com.example.openevents20.Api;

import com.example.openevents20.User;

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

}
