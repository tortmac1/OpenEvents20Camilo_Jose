package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.Clases.User;
import com.example.openevents20.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMyUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_user);

        Bundle extras = getIntent().getExtras();
        User usuario = (User) extras.getSerializable("user");
        EditText nombre, apellido, correo, imagen;


        nombre = findViewById(R.id.name);
        apellido = findViewById(R.id.last_name);
        correo = findViewById(R.id.email);
        imagen = findViewById(R.id.image);




        nombre.setText(usuario.getName());
        apellido.setText(usuario.getLast_name());
        correo.setText(usuario.getEmail());
        imagen.setText(usuario.getImage());
        Button btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(view -> {
            User modificado = new User(nombre.getText().toString(), apellido.getText().toString(), correo.getText().toString(), imagen.getText().toString());
            OpenApi.getInstance().editUser(this, modificado, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null){
                        Toast.makeText(getApplicationContext(),"El usuario ha sido modificado con exito", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"ERROR, no se ha podido modificar el usuario", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}