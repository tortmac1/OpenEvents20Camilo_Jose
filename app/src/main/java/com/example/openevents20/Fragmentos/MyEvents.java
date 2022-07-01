package com.example.openevents20.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.openevents20.Actividades.Login;
import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.CreateEvent;
import com.example.openevents20.Event;
import com.example.openevents20.HolderAdapterEvents;
import com.example.openevents20.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyEvents extends Fragment {

    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_events, container, false);
        Button create_event = view.findViewById(R.id.create_event);
        create_event.setOnClickListener(v -> {
                    startActivity(new Intent(getActivity(), CreateEvent.class));
                });

        OpenApi api = OpenApi.getInstance();
        api.listMyEvents(getContext(),searchid(getContext()), new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                if (response.body() != null) {
                    ArrayList<Event> eventos = response.body();
                    HolderAdapterEvents eventsAdapter = new HolderAdapterEvents(eventos, getContext());
                    RecyclerView eventsContainer = view.findViewById(R.id.container_events);
                    eventsContainer.setLayoutManager(linearLayoutManager);
                    eventsContainer.setAdapter(eventsAdapter);
                }else {
                    Log.d("error", ""+ response.body());
                }


            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {

            }
        });
        return view;
    }

    public Integer searchid(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("validador", Context.MODE_PRIVATE);
        Integer id = sharedPreferences.getInt("id", -1);

        return id;
    }
}