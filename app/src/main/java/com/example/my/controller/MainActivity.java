package com.example.my.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my.R;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button GetPackagesButton;


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

        GetPackagesButton = (Button)findViewById(R.id.GetPackagesbutton);
        GetPackagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPackListactivity();
            }
        });
    }

    public void openPackListactivity(){
        Intent intent=new Intent(this, PackListactivity.class);
        startActivity(intent);
    }

    public void openPackArrivedActivity(){
        Intent intent=new Intent(this, PackArrivedActivity.class);
        startActivity(intent);
    }


}
