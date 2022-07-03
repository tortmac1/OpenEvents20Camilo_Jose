package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.openevents20.Api.OpenApi;
import com.example.openevents20.Clases.Event;
import com.example.openevents20.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMyEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_event);
        EditText nombre, imagen, locacion, descripcion, fechaInicio, fechaFinal, participantes, tipo;
        Bundle extras = getIntent().getExtras();
        Event event = (Event) extras.getSerializable("event");





        nombre = findViewById(R.id.name);
        imagen = findViewById(R.id.image);
        locacion = findViewById(R.id.location);
        descripcion = findViewById(R.id.description);
        fechaInicio = findViewById(R.id.eventStart_date);
        fechaFinal = findViewById(R.id.eventEnd_date);
        participantes = findViewById(R.id.n_participators);
        tipo = findViewById(R.id.type);




        nombre.setText(event.getName());
        imagen.setText(event.getImage());
        locacion.setText(event.getLocation());
        descripcion.setText(event.getDescription());
        //fechaInicio.setText(event.getEventStart_date());
        //fechaFinal.setText(event.getEventEnd_date());
        participantes.setText(""+event.getN_participators());
        tipo.setText(event.getType());

        Button btn_edit = findViewById(R.id.btn_edit);

        btn_edit.setOnClickListener(view -> {
            Event modificado = new Event(nombre.getText().toString(),imagen.getText().toString(), locacion.getText().toString(), descripcion.getText().toString(), Integer.parseInt(participantes.getText().toString()),tipo.getText().toString());
            OpenApi.getInstance().editEvent(this, event.getId(), modificado, new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    if (response.body() != null){
                        Toast.makeText(getApplicationContext(),"Evento modificado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"ERROR en la Api", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}