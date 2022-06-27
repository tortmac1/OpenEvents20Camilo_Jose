package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.openevents20.Api.OpenApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            Button button_login = findViewById(R.id.btn_login);
            button_login.setOnClickListener(view -> {
                EditText email = findViewById(R.id.email);
                EditText password = findViewById(R.id.password);
                User usuario;
                usuario = new User(email.getText().toString(), password.getText().toString());

                OpenApi.getInstance().login(usuario, new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("RESPUESTA","CORRECTO"+response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("RESPUESTA","INCORRECTO"+t);
                    }
                });
            });
        }
    }
