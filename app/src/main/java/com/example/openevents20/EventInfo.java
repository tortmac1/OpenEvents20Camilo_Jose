package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        cargarImagen=findViewById(R.id.miImagen);
        Picasso.get()
                        .load("https://i.pinimg.com/originals/62/a8/ec/62a8ec0a94d90ea47a5b535e619b5366.jpg")
                        .error(R.mipmap.ic_launcher_round)
                                .into(cargarImagen);


        image.setText(event.getImage());
        location.setText(event.getLocation());
        description.setText(event.getDescription());
        eventStart_date.setText(event.getEventStart_date());
        eventEnd_date.setText(event.getEventEnd_date());
        n_participators.setText(event.getN_participators());
        type.setText(event.getType());
    }
}