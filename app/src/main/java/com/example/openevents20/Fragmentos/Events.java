package com.example.openevents20.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.Event;
import com.example.openevents20.HolderAdapterEvents;
import com.example.openevents20.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Events extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        OpenApi api = OpenApi.getInstance();
        api.listEvents(getContext(), new Callback<ArrayList<Event>>() {
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


}