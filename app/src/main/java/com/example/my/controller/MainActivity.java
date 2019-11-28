package com.example.my.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.my.R;

public class MainActivity extends AppCompatActivity {

    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button)findViewById(R.id.button_Start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPackArrivedActivity();
            }
        });
    }

    public void openPackArrivedActivity(){
        Intent intent=new Intent(this, PackArrivedActivity.class);
        startActivity(intent);
    }
}
