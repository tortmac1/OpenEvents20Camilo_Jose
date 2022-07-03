package com.example.openevents20.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

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
    SearchView buscador;
    HolderAdapterUsers usersAdapter;
    RecyclerView usersContainer;

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
                    usersAdapter = new HolderAdapterUsers(usuarios, getContext());
                    usersContainer = view.findViewById(R.id.container_users);
                    usersContainer.setLayoutManager(linearLayoutManager);
                    usersContainer.setAdapter(usersAdapter);
                } else {
                    Toast.makeText(getContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
            }
        });

        buscador = view.findViewById(R.id.buscador_email);
        buscador.clearFocus();
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                OpenApi.getInstance().searchEmail(getContext(), s, new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                        if (response.body() != null){
                            usersAdapter = new HolderAdapterUsers(response.body(), getContext());
                            usersContainer.setAdapter(usersAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                    }
                });
                return false;
            }
        });

        return view;
    }
}