package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                openOrderRideActivity();
            }
        });
    }

    public void openOrderRideActivity(){
        Intent intent=new Intent(this,PackArrivedActivity.class);
        startActivity(intent);
    }
}
