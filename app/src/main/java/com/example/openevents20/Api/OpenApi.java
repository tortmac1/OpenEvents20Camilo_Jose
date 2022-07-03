package com.example.openevents20.Api;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.openevents20.Clases.Event;
import com.example.openevents20.Clases.Soporte;
import com.example.openevents20.Clases.User;

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

    public void getUser(Context c, Integer id, Callback<User> callback){
        openInterface.getUser(searchtoken(c), id).enqueue(callback);
    }


    public void listUsers(String token, Callback<ArrayList<User>> callback) {
        openInterface.listUsers("Bearer " + token).enqueue(callback);
    }

    public void listUsers2(Context c, Callback<ArrayList<User>> callback) {
        openInterface.listUsers(searchtoken(c)).enqueue(callback);
    }

    public void editUser(Context c, User user, Callback<User> callback){
        openInterface.editUser(searchtoken(c), user).enqueue(callback);
    }

    public void searchEmail(Context c, String email, Callback<ArrayList<User>> callback){
        openInterface.searchEmail(searchtoken(c), email).enqueue(callback);
    }

    public void listEvents(Context c, Callback<ArrayList<Event>> callback) {
        openInterface.listEvents(searchtoken(c)).enqueue(callback);
    }

    public void listMyEvents(Context c, Integer id, Callback<ArrayList<Event>> callback) {
        openInterface.listMyEvents(searchtoken(c), id).enqueue(callback);
    }

    public void createEvent(Context c,Event evento, Callback<Event> callback) {
        openInterface.createEvent(searchtoken(c), evento).enqueue(callback);
    }

    public void joinEvent(Context c, int idEvent, Callback<Soporte> callback){
        openInterface.joinEvent(searchtoken(c), idEvent).enqueue(callback);
    }

    public void leaveEvent(Context c, int idEvent, Callback<Soporte> callback){
        openInterface.leaveEvent(searchtoken(c), idEvent).enqueue(callback);
    }

    public void getEventsBest(Context c, Callback<ArrayList<Event>> callback) {
        openInterface.getBestEvents(searchtoken(c)).enqueue(callback);
    }

    public void editEvent(Context c, int idEvent, Event event, Callback<Event> callback) {
        openInterface.editEvent(searchtoken(c), idEvent, event).enqueue(callback);
    }

    public void deleteEvent(Context c, int idEvent, Callback<Event> callback) {
        openInterface.deleteEvent(searchtoken(c), idEvent).enqueue(callback);
    }

    public void searchEvents(Context c, String location, String keyword, String date,  Callback<ArrayList<Event>> callback){
        openInterface.searchEvents(searchtoken(c), location, keyword, date).enqueue(callback);
    }


    public String searchtoken(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("validador", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return "Bearer " + token;
    }

}
