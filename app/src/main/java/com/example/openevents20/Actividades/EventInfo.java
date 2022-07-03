package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.Clases.Event;
import com.example.openevents20.R;
import com.example.openevents20.Clases.Soporte;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

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

        cargarImagen = findViewById(R.id.miImagen);
        try {
            String path = event.getImage();
            Picasso.get()
                    .load(path)
                    .error(R.mipmap.ic_launcher_round)
                    .into(cargarImagen);
        }catch (IllegalArgumentException  e){

        }


        location.setText(event.getLocation());
        description.setText(event.getDescription());
        eventStart_date.setText(event.getEventStart_date());
        eventEnd_date.setText(event.getEventEnd_date());
        n_participators.setText(String.valueOf(event.getN_participators()));
        type.setText(event.getType());

        Button unirse = findViewById(R.id.unirse);
        Button edit = findViewById(R.id.edit_event);
        Button eliminate = findViewById(R.id.eliminate_event);

        int myId = searchid(this);
        if (event.getOwner_id() == myId){

            unirse.setVisibility(View.INVISIBLE);
        }else {
            edit.setVisibility(View.INVISIBLE);
            eliminate.setVisibility(View.INVISIBLE);
        }

        final boolean[] joined = {false};

        unirse.setOnClickListener(view -> {
            if (!joined[0]) {
                OpenApi.getInstance().joinEvent(this, event.getId(), new Callback<Soporte>() {
                    @Override
                    public void onResponse(Call<Soporte> call, Response<Soporte> response) {
                        Toast.makeText(getApplicationContext(),"Te has subido al evento", Toast.LENGTH_SHORT).show();
                        unirse.setText("Salir del evento");
                        joined[0] = true;
                    }

                    @Override
                    public void onFailure(Call<Soporte> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                OpenApi.getInstance().leaveEvent(this, event.getId(), new Callback<Soporte>() {
                    @Override
                    public void onResponse(Call<Soporte> call, Response<Soporte> response) {
                        Toast.makeText(getApplicationContext(),"Has salido del evento", Toast.LENGTH_SHORT).show();
                        unirse.setText("UNIRSE");
                        joined[0] = false;
                    }

                    @Override
                    public void onFailure(Call<Soporte> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        edit.setOnClickListener(view -> {
            Intent i = new Intent(EventInfo.this, EditMyEvent.class);
            i.putExtra("event", (Serializable)event );
            startActivity(i);
        });

        eliminate.setOnClickListener(view -> {
            OpenApi.getInstance().deleteEvent(this, event.getId(), new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    if (response.body() != null){
                        Toast.makeText(getApplicationContext(),"El evento ha sido eliminado con exito", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
    public Integer searchid(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("validador", Context.MODE_PRIVATE);
        Integer id = sharedPreferences.getInt("id", -1);

        return id;
    }
}