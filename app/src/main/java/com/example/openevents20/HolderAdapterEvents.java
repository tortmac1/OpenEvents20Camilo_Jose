package com.example.openevents20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;


public class HolderAdapterEvents extends RecyclerView.Adapter<HolderAdapterEvents.ViewHolder> {
    ArrayList<Event> eventos;
    Context c;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout item_event;
        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.name_event);
            item_event = view.findViewById(R.id.item_event);
            // TODO añadir image view y demas atributos --> item_events.xml
        }

        public void bind(int pos) {
            textView.setText(eventos.get(pos).getName());

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
