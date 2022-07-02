package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.openevents20.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d("error", "entra aqui");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String token = searchtoken();
                if (!token.equals("")){
                    startActivity(new Intent(SplashActivity.this, MenuFragments.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, Login.class));
                    finish();
                }

            }
        }, 2000);
    }

    public String searchtoken(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("validador", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }
}