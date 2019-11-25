package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PackArrivedActivity extends AppCompatActivity {

    Spinner packTypeSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_arrived);

        packTypeSpinner = (Spinner)findViewById(R.id.spinnerPackType);
        ArrayAdapter<String> myAdepter = new ArrayAdapter<String>(PackArrivedActivity.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.packTypes));
        myAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        packTypeSpinner.setAdapter(myAdepter);

    }
}
