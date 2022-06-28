package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.openevents20.Actividades.Login;
import com.example.openevents20.Actividades.MenuFragments;
import com.example.openevents20.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Log.d("Entrados en el Splash", "Etramos en el splash");
        String token = searchtoken();
        if (!token.equals("")){
            startActivity(new Intent(this, MenuFragments.class));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, Login.class));
    }

    public String searchtoken(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("validador", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }


}