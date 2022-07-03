package com.example.openevents20.Fragmentos;

import android.graphics.Path;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.Clases.Event;
import com.example.openevents20.Clases.HolderAdapterEvents;
import com.example.openevents20.Clases.HolderAdapterUsers;
import com.example.openevents20.Clases.User;
import com.example.openevents20.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Events extends Fragment {
    LinearLayoutManager linearLayoutManager;
    SearchView buscador;
    HolderAdapterEvents eventsAdapter;
    RecyclerView eventsContainer;

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
        Button best;
        best = view.findViewById(R.id.btn_best);
        best.setOnClickListener(view1 -> {
            OpenApi.getInstance().getEventsBest(getContext(), new Callback<ArrayList<Event>>() {

                @Override
                public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                    if (response.body() != null) {
                        ArrayList<Event> eventos = response.body();
                        HolderAdapterEvents eventsAdapter = new HolderAdapterEvents(eventos, getContext());
                        RecyclerView eventsContainer = view.findViewById(R.id.container_events);
                        eventsContainer.setLayoutManager(linearLayoutManager);
                        eventsContainer.setAdapter(eventsAdapter);
                    } else {
                        Log.d("error", "" + response.body());
                    }


                }

                @Override
                public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                    Toast.makeText(getContext(), "ERROR en la Api", Toast.LENGTH_SHORT).show();
                }
            });
        });
        listEvents(view);

        buscador = view.findViewById(R.id.buscador_event);
        buscador.clearFocus();
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty()) {
                    listEvents(view);
                }
                OpenApi.getInstance().searchEvents(getContext(), null, s, null, new Callback<ArrayList<Event>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                        if (response.body() != null){
                            eventsAdapter = new HolderAdapterEvents(response.body(), getContext());
                            eventsContainer.setLayoutManager(linearLayoutManager);
                            eventsContainer.setAdapter(eventsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                        Toast.makeText(getContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }
        });

        return view;
    }

    private void listEvents(View view) {
        OpenApi.getInstance().listEvents(getContext(), new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                if (response.body() != null) {
                    ArrayList<Event> eventos = response.body();
                    eventsAdapter = new HolderAdapterEvents(eventos, getContext());
                    eventsContainer = view.findViewById(R.id.container_events);
                    eventsContainer.setLayoutManager(linearLayoutManager);
                    eventsContainer.setAdapter(eventsAdapter);
                } else {
                    Toast.makeText(getContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                Toast.makeText(getContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
            }

        });

    }


}