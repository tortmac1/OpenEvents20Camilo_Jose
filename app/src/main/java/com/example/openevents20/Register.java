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

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button button_register = findViewById(R.id.btn_register);
        button_register.setOnClickListener(view -> {
            EditText nombre = findViewById(R.id.nombre);
            EditText apellido = findViewById(R.id.apellido);
            EditText email = findViewById(R.id.email);
            EditText password = findViewById(R.id.password);
            EditText image = findViewById(R.id.image);
            User usuario;
            usuario = new User(nombre.getText().toString(), apellido.getText().toString(), email.getText().toString(), password.getText().toString(), image.getText().toString());
            OpenApi.getInstance().register(usuario, new Callback<User>() {
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