package com.example.openevents20.Clases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openevents20.Actividades.EventInfo;
import com.example.openevents20.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;


public class HolderAdapterEvents extends RecyclerView.Adapter<HolderAdapterEvents.ViewHolder> {
    ArrayList<Event> eventos;
    Context c;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, location;
        LinearLayout item_event;
        ImageView cargarImagen;
        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_event);
            date = view.findViewById(R.id.date_event);
            location = view.findViewById(R.id.location_event);
            item_event = view.findViewById(R.id.item_event);
            cargarImagen = view.findViewById(R.id.miImagen);
        }

        public void bind(int pos) {
            name.setText(eventos.get(pos).getName());
            date.setText(eventos.get(pos).getDate());
            location.setText(eventos.get(pos).getLocation());

            try {
                String path = eventos.get(pos).getImage();
                Picasso.get()
                        .load(path)
                        .error(R.mipmap.ic_launcher_round)
                        .into(cargarImagen);
            }catch (IllegalArgumentException  e){

            }

            item_event.setOnClickListener(v -> {
                Intent intent = new Intent((Activity)c, EventInfo.class);
                intent.putExtra("event", (Serializable) eventos.get(pos));

                c.startActivity(intent);
            });

        }


    }


    public HolderAdapterEvents(ArrayList<Event> eventos, Context c) {
        this.eventos = eventos;
        this.c = c;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events, parent, false);
        return new HolderAdapterEvents.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }
}
