package com.example.openevents20.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.openevents20.Actividades.Login;
import com.example.openevents20.Actividades.Register;
import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.Event;
import com.example.openevents20.R;
import com.example.openevents20.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyUser extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_user, container, false);
        TextView name, last_name, email, password, image;

            name = view.findViewById(R.id.name);
            last_name = view.findViewById(R.id.last_name);
            email = view.findViewById(R.id.email);
            password = view.findViewById(R.id.password);
            image = view.findViewById(R.id.image);
        int id = searchid(getContext());


        OpenApi api = OpenApi.getInstance();
        api.getUser(getContext(),id , new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
            User usuario = response.body();
            name.setText(usuario.getName());
            last_name.setText(usuario.getLast_name());
            email.setText(usuario.getEmail());
            password.setText(usuario.getPassword());
            image.setText(usuario.getImage());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        Button button_register = view.findViewById(R.id.log_out);
        button_register.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getContext().getSharedPreferences
                    ("validador", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove("token").clear().apply();
            //
            // tosharedPreferences.edit().putString("token", null).apply();

            startActivity(new Intent(getActivity(), Login.class));
        });
        return  view;
    }
    public Integer searchid(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("validador", Context.MODE_PRIVATE);
        Integer id = sharedPreferences.getInt("id", -1);

        return id;
    }
}