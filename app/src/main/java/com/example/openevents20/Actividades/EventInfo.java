package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.Clases.Event;
import com.example.openevents20.R;
import com.example.openevents20.Clases.Soporte;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Event event = (Event) getIntent().getSerializableExtra("event");
        ImageView cargarImagen;
        TextView name, image, location, description, eventStart_date, eventEnd_date, n_participators, type;

        name = findViewById(R.id.name);
        image = findViewById(R.id.image);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);
        eventStart_date = findViewById(R.id.eventStart_date);
        eventEnd_date = findViewById(R.id.eventEnd_date);
        n_participators = findViewById(R.id.n_participators);
        type = findViewById(R.id.type);


        name.setText(event.getName());
        //TODO hacer que Picasso lea todas las imagenes con su load
        cargarImagen = findViewById(R.id.miImagen);
        Picasso.get()
                .load(event.getImage())
                .error(R.mipmap.ic_launcher_round)
                .into(cargarImagen);


        location.setText(event.getLocation());
        description.setText(event.getDescription());
        eventStart_date.setText(event.getEventStart_date());
        eventEnd_date.setText(event.getEventEnd_date());
        n_participators.setText(String.valueOf(event.getN_participators()));
        type.setText(event.getType());
        final boolean[] joined = {false};
        Button unirse = findViewById(R.id.unirse);
        unirse.setOnClickListener(view -> {
            if (!joined[0]) {
                OpenApi.getInstance().joinEvent(this, event.getId(), new Callback<Soporte>() {
                    @Override
                    public void onResponse(Call<Soporte> call, Response<Soporte> response) {
                        //TODO crear toast conforme funciona
                        unirse.setText("Salir del evento");
                        joined[0] = true;
                    }

                    @Override
                    public void onFailure(Call<Soporte> call, Throwable t) {
                        //TODO crear toast conforme  no funciona
                    }
                });
            } else {
                OpenApi.getInstance().leaveEvent(this, event.getId(), new Callback<Soporte>() {
                    @Override
                    public void onResponse(Call<Soporte> call, Response<Soporte> response) {
                        //TODO crear toast conforme funciona
                        unirse.setText("UNIRSE");
                        joined[0] = false;
                    }

                    @Override
                    public void onFailure(Call<Soporte> call, Throwable t) {
                        //TODO crear toast conforme  no funciona
                    }
                });
            }

        });

    }
}