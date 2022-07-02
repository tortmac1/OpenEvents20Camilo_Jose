package com.example.openevents20.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.Clases.HolderAdapterUsers;
import com.example.openevents20.R;
import com.example.openevents20.Clases.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchUsers extends Fragment {
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

        View view = inflater.inflate(R.layout.fragment_search_users, container, false);

        OpenApi api = OpenApi.getInstance();
        api.listUsers2(getContext(), new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.body() != null) {
                    ArrayList<User> usuarios = response.body();
                    HolderAdapterUsers usersAdapter = new HolderAdapterUsers(usuarios, getContext());
                    RecyclerView usersContainer = view.findViewById(R.id.container_users);
                    usersContainer.setLayoutManager(linearLayoutManager);
                    usersContainer.setAdapter(usersAdapter);
                } else {
                    Log.d("error", "" + response.body());
                }


            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                //TODO agregar error
            }
        });
        return view;
    }
}