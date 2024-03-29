package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.R;
import com.example.openevents20.Clases.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button button_register = findViewById(R.id.btn_create_event);
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
                    Toast.makeText(getApplicationContext(),"Registrado con exito", Toast.LENGTH_SHORT).show();
                    finish();

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
                    //todo hacer toast de que ha habido un problema con la api
                }
            });
        });
    }


}