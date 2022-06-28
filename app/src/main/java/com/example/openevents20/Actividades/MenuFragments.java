package com.example.openevents20.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.openevents20.Fragmentos.Events;
import com.example.openevents20.Fragmentos.MyEvents;
import com.example.openevents20.Fragmentos.MyUser;
import com.example.openevents20.Fragmentos.SearchUsers;
import com.example.openevents20.R;
import com.example.openevents20.databinding.ActivityMenuBinding;

public class MenuFragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMenuBinding binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        menuFragment(new Events());
        binding.navigation.setSelectedItemId(R.id.Events);
        binding.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.SearchUsers:
                    menuFragment(new SearchUsers());
                    break;

                case R.id.Events:
                    menuFragment(new Events());
                    break;

                case R.id.MyUser:
                    menuFragment(new MyUser());
                    break;

                case R.id.MyEvents:
                    menuFragment(new MyEvents());
                    break;
            }
            return true;
        });
    }

    public void menuFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.PlantillaFragment, fragment);
        fragmentTransaction.commit();
    }
}