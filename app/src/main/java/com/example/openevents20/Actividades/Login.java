package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.R;
import com.example.openevents20.User;

import java.sql.Time;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Context context = this;

        Button button_login = findViewById(R.id.btn_login);
        button_login.setOnClickListener(view -> {
            EditText email = findViewById(R.id.email);
            EditText password = findViewById(R.id.password);
            usuario = new User(email.getText().toString(), password.getText().toString());

            OpenApi.getInstance().login(usuario, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User u = response.body();


                    Log.d("RESPUESTA", "CORRECTO" + u.getAccessToken());


                    searchId(usuario.getEmail(), u.getAccessToken());
                    new CountDownTimer(5000, 1000) {
                        public void onFinish() {
                            SharedPreferences sharedPreferences = getSharedPreferences
                                    ("validador", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.apply();
                            edit.putString("token", u.getAccessToken());
                            edit.putInt("id", usuario.getId());
                            edit.commit();

                            startActivity(new Intent(context, MenuFragments.class));
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();








                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("RESPUESTA", "INCORRECTO" + t);
                }


            });
        });

        Button button_register = findViewById(R.id.btn_create_event);
        button_register.setOnClickListener(view -> {
            startActivity(new Intent(this, Register.class));
        });
    }

    private void searchId(String email, String token) {
        OpenApi.getInstance().listUsers(token, new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                Log.d("Camilo", "Entra for");
                for (User u : response.body()) {
                    if (u.getEmail().equals(email)) {
                        usuario.setId(u.getId());
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
    /*


     */

}

