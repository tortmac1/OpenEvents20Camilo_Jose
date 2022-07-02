package com.example.openevents20.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.openevents20.Actividades.EditMyUser;
import com.example.openevents20.Actividades.Login;
import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.R;
import com.example.openevents20.Clases.User;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyUser extends Fragment {

User user = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_user, container, false);

        int id = searchid(getContext());

        searchUser(id, view);

        Button edit = view.findViewById(R.id.edit_profile);
        edit.setOnClickListener(view1 -> {
            Intent i = new Intent(getActivity(), EditMyUser.class);
            i.putExtra("user", (Serializable) user);

            startActivity(i);
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


    private void searchUser(int id, View view) {

        TextView name, last_name, email, password, image;

        name = view.findViewById(R.id.name);
        last_name = view.findViewById(R.id.last_name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        image = view.findViewById(R.id.image);

        OpenApi.getInstance().listUsers2(getContext(), new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                Log.d("Camilo", "Entra for");
                for (User u : response.body()) {
                    if (u.getId() == id) {
                        user = u;
                        name.setText(u.getName());
                        last_name.setText(u.getLast_name());
                        email.setText(u.getEmail());
                        password.setText(u.getPassword());
                        image.setText(u.getImage());
                        Log.d("Camilo", "Correct");
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                //TODO crear un toast avisando error api
                Log.d("Camilo", "Error");
            }
        });
    }

}