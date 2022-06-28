package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.R;
import com.example.openevents20.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Context context = this;
    /*
        String token = searchtoken();
        if (!token.equals("")){
            startActivity(new Intent(this, MenuFragments.class));
        }
    */
        Button button_login = findViewById(R.id.btn_login);
        button_login.setOnClickListener(view -> {
            EditText email = findViewById(R.id.email);
            EditText password = findViewById(R.id.password);
            User usuario;
            usuario = new User(email.getText().toString(), password.getText().toString());

            OpenApi.getInstance().login(usuario, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User usuario = response.body();

                    Log.d("RESPUESTA", "CORRECTO" + usuario.getAccessToken());

                    SharedPreferences sharedPreferences = getSharedPreferences
                            ("validador", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.apply();
                    edit.putString("token", usuario.getAccessToken());

                    edit.commit();

                    startActivity(new Intent(context, MenuFragments.class));
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("RESPUESTA", "INCORRECTO" + t);
                }

            });
        });

        Button button_register = findViewById(R.id.btn_register);
        button_register.setOnClickListener(view -> {
            startActivity(new Intent(this, Register.class));
        });
    }
    /*
    public String searchtoken(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("validador", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }

     */

}

