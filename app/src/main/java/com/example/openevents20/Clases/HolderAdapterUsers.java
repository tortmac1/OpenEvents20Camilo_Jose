package com.example.openevents20.Clases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openevents20.Actividades.EventInfo;
import com.example.openevents20.R;

import java.io.Serializable;
import java.util.ArrayList;

public class HolderAdapterUsers extends RecyclerView.Adapter<HolderAdapterUsers.ViewHolder> {
    ArrayList<User> usuarios;
    Context c;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout item_user;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name_user);
            item_user = view.findViewById(R.id.item_user);

        }

        public void bind(int pos) {

            Log.d("camilo","error"+usuarios.get(pos).getName());
            name.setText(usuarios.get(pos).getName());

            item_user.setOnClickListener(v -> {
                Intent intent = new Intent((Activity) c, EventInfo.class);
                intent.putExtra("user", (Serializable) usuarios.get(pos));

                c.startActivity(intent);
            });

        }


    }


    public HolderAdapterUsers(ArrayList<User> usuarios, Context c) {
        this.usuarios = usuarios;
        this.c = c;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}
