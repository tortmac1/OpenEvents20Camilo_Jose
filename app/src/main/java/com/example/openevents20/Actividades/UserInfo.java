package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.openevents20.Clases.Event;
import com.example.openevents20.Clases.User;
import com.example.openevents20.R;
import com.squareup.picasso.Picasso;

public class UserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        User usuario = (User) getIntent().getSerializableExtra("user");
        ImageView cargarImagen;
        TextView name, lastname, email;

        name = findViewById(R.id.name);
        lastname = findViewById(R.id.last_name);
        email = findViewById(R.id.email);

        name.setText(usuario.getName());
        lastname.setText(usuario.getLast_name());
        email.setText(usuario.getEmail());
        //TODO hacer que Picasso lea todas las imagenes con su load
        cargarImagen = findViewById(R.id.image);
        Picasso.get()
                .load(usuario.getImage())
                .error(R.mipmap.ic_launcher_round)
                .into(cargarImagen);



    }
}