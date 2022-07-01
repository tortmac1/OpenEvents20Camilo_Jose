package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.openevents20.Api.OpenApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Button button_create = findViewById(R.id.btn_create_event);
        button_create.setOnClickListener(view -> {
            EditText nombre = findViewById(R.id.name);
            EditText imagen = findViewById(R.id.image);
            EditText locacion = findViewById(R.id.location);
            EditText descripcion = findViewById(R.id.description);
            EditText fechaInicio = findViewById(R.id.eventStart_date);
            EditText fechaFinal = findViewById(R.id.eventEnd_date);
            EditText numeroParticipantes = findViewById(R.id.n_participators);
            EditText tipo = findViewById(R.id.type);
            Event evento;
            /*
            evento = new Event(nombre.getText().toString(), imagen.getText().toString(), locacion.getText().toString(), descripcion.getText().toString(),
                    fechaInicio.getText().toString(), fechaFinal.getText().toString(), Integer.parseInt(numeroParticipantes.getText().toString()), tipo.getText().toString());
            */
            evento = new Event("Enchiladas", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Quesadilla_2.jpg/1024px-Quesadilla_2.jpg",
                    "Barcelona", "Las mejores de Europa", "2022-9-29:17:30", "2023-06-29:17:30",
                    30, "Fiesta");

            OpenApi.getInstance().createEvent(this, evento, new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    Log.d("response", ""+ response.body().getDescription());
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    Log.d("error", "Error");
                }
            });
        });
    }
}