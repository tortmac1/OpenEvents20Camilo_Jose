package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EventInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Event event = (Event) getIntent().getSerializableExtra("event");

        TextView name_event = findViewById(R.id.name_event);


        name_event.setText(event.getName());
    }
}