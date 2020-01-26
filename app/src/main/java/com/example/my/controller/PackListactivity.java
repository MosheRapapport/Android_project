package com.example.my.controller;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my.R;
import com.example.my.model.datasource.Firebase_DBManager;
import com.example.my.model.entities.Pack;

import java.util.List;

public class PackListactivity extends AppCompatActivity {

    private RecyclerView packsRecycleView;
    private List<Pack> packs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_list);

        packsRecycleView = findViewById(R.id.packRecyclerview);
        packsRecycleView.setHasFixedSize(true);
        packsRecycleView.setLayoutManager(new LinearLayoutManager(this));

        Firebase_DBManager.notifitoPackList(new Firebase_DBManager.NotifyDataChange<List<Pack>>(){
            @Override
            public void OnDataChanged(List<Pack> obj) {
                if (packsRecycleView.getAdapter() == null) {
                    packs = obj;
                    packsRecycleView.setAdapter(new packRecycleViewAdapter(PackListactivity.this, packs));
                } else
                    packsRecycleView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(getBaseContext(), "error to get packages list\n" + exception.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Firebase_DBManager.stopnotifitoPackList();
        super.onDestroy();
    }





}
