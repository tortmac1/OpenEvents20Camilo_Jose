package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.openevents20.Clases.User;
import com.example.openevents20.R;

public class EditMyUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_user);

        Bundle extras = getIntent().getExtras();
        User usuario = (User) extras.getSerializable("user");
        EditText nombre = findViewById(R.id.name);
        nombre.setText(usuario.getName());
    }
}