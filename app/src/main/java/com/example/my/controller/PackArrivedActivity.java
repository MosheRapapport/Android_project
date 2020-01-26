package com.example.my.controller;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.my.R;
import com.example.my.model.datasource.Firebase_DBManager;
import com.example.my.model.entities.AddressAndLocation;
import com.example.my.model.entities.Exceptions;
import com.example.my.model.entities.Pack;
import com.example.my.model.entities.PackStatus;
import com.example.my.model.entities.PackType;
import com.example.my.model.entities.PackWeight;
import com.example.my.model.entities.Person;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

public class PackArrivedActivity extends AppCompatActivity implements View.OnClickListener {

    final int PLACE_PICKER_REQUEST_DESTINATION = 1;
    final int PLACE_PICKER_REQUEST_PICKUP = 2;
    final int REQUEST_CHECK_SETTINGS = 3;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;


    private EditText storageLocationEditText;
    private Spinner spinnerPackType;
    private Spinner spinnerPackWeight;
    private Switch fragileSwitch;
    private EditText PackageDeliveryDateEditText;
    private Button PackageDeliveryDateButton;
    private EditText PackageReceiptDateEditText;
    private Button PackageReceiptDateButton;
    private TextInputEditText receiptIDeditText;
    private EditText recipientDestinationEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private Button addPacageButton;
    private Pack pack;
    private LocalDate localDate;
    private int mYear, mMonth, mDay;
    private static Firebase_DBManager firebase_dbManager;
    private ProgressDialog progressDialog;
    private Geocoder mGeocoder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_arrived);

        pack = new Pack();
        findViews();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Finding your location...");
        progressDialog.show();
        getLocation();


    }


    private void findViews() {
        firstNameEditText = (EditText)findViewById( R.id.firstNameEditText );
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkOnlyLetters(s.toString()))
                {
                    firstNameEditText.setError("Only letters");
                }
                else
                {
                    firstNameEditText.setError(null);

                }
            }
        });

        lastNameEditText = (EditText)findViewById( R.id.lastNameEditText );
        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkOnlyLetters(s.toString())){
                    lastNameEditText.setError("Only letters");
                }
                else{
                    lastNameEditText.setError(null);
                }
            }
        });

        receiptIDeditText = (TextInputEditText)findViewById( R.id.receiptIDeditText );
        receiptIDeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkOnlyNumbers(s.toString())){
                    receiptIDeditText.setError("Only numbers");
                }
                else{
                    receiptIDeditText.setError(null);
                }
            }
        });

        phoneNumberEditText = (EditText)findViewById( R.id.phoneNumberEditText );
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkOnlyNumbers(s.toString())){
                    phoneNumberEditText.setError("Only numbers");
                }
                else{
                    phoneNumberEditText.setError(null);
                }
            }
        });

        emailEditText = (EditText)findViewById( R.id.emailEditText );
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkEmail(s.toString())){
                    emailEditText.setError("Email not valid");
                }
                else{
                    emailEditText.setError(null);
                }

            }
        });

        recipientDestinationEditText = (EditText)findViewById( R.id.recipientDestinationEditText );
        recipientDestinationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(PackArrivedActivity.this), PLACE_PICKER_REQUEST_PICKUP);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        storageLocationEditText = (EditText)findViewById( R.id.storageLocationEditText );
        storageLocationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(PackArrivedActivity.this), PLACE_PICKER_REQUEST_PICKUP);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        spinnerPackType = (Spinner)findViewById(R.id.spinnerPackType);
        ArrayAdapter<String> myAdepter1 = new ArrayAdapter<String>(PackArrivedActivity.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.packTypes))
        {
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        myAdepter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPackType.setAdapter(myAdepter1);

        spinnerPackWeight = (Spinner)findViewById(R.id.spinnerPackWeight);
        ArrayAdapter<String> myAdepter2 = new ArrayAdapter<String>(PackArrivedActivity.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.packWeight))
        {
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        myAdepter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPackWeight.setAdapter(myAdepter2);

        fragileSwitch = (Switch)findViewById( R.id.fragileSwitch );

        PackageDeliveryDateEditText = (EditText)findViewById( R.id.PackageDeliveryDateEditText );
        PackageDeliveryDateButton = (Button)findViewById(R.id.deliveryDatebutton);
        PackageDeliveryDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(PackArrivedActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {

                                PackageDeliveryDateEditText.setText(day + "/" + (month + 1) + "/" + year);
                                pack.setDeliveryDate(new Date(year,month,day));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        PackageReceiptDateEditText = (EditText)findViewById( R.id.PackageReceiptDateEditText );
        PackageReceiptDateButton=(Button)findViewById(R.id.receiptDatebutton);
        PackageReceiptDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(PackArrivedActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {

                                PackageReceiptDateEditText.setText(day + "/" + (month + 1) + "/" + year);
                                pack.setReceivedDate(new Date(year,month,day));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        addPacageButton = (Button)findViewById( R.id.addPacageButton );
        addPacageButton.setOnClickListener( this );
    }

    private void setPack()
    {

        pack.setPackType(PackType.values()[(spinnerPackType.getSelectedItemPosition()-1)]);
        pack.setPackWeight(PackWeight.values()[spinnerPackWeight.getSelectedItemPosition()-1]);

        pack.setPackFragile(fragileSwitch.isChecked());
        pack.setRecipient(new Person(receiptIDeditText.getText().toString(),
                                        firstNameEditText.getText().toString(),
                                        lastNameEditText.getText().toString(),
                                        phoneNumberEditText.getText().toString(),
                                        emailEditText.getText().toString(),
                                        new AddressAndLocation()));
        pack.setPackStatus(PackStatus.SHIPPED);
        pack.setDeliveryName("NO");
    }

    private boolean isEmptyInput() {
        return TextUtils.isEmpty(firstNameEditText.getText())||
                TextUtils.isEmpty(lastNameEditText.getText())||
                TextUtils.isEmpty(emailEditText.getText())||
                TextUtils.isEmpty(phoneNumberEditText.getText())||
                TextUtils.isEmpty(PackageDeliveryDateEditText.getText())||
                TextUtils.isEmpty(PackageReceiptDateEditText.getText())||
                TextUtils.isEmpty(receiptIDeditText.getText())||
                spinnerPackWeight.getSelectedItemPosition()==0||
                spinnerPackType.getSelectedItemPosition()==0||
               // TextUtils.isEmpty(recipientDestinationEditText.getText())||
                TextUtils.isEmpty(storageLocationEditText.getText());
    }

    @Override
    public void onClick(View v) {
        if ( v == addPacageButton ) {
            if(isEmptyInput()){
                Toast.makeText(getBaseContext(), R.string.all_fields_required,LENGTH_LONG).show();
                return;
            }
            setPack();
            addPacageButton.setEnabled(false);
            Firebase_DBManager.addPackToFirebase(pack,new Firebase_DBManager.Action<Long>(){
                @Override
                public void onSuccess(Long obj) {
                    Toast.makeText(getBaseContext(), "The Package added!", Toast.LENGTH_LONG).show();
                    addPacageButton.setEnabled(true);
                    finish();

                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(getBaseContext(), "We are sorry!  didn't success\n Try Again!", Toast.LENGTH_LONG).show();
                    addPacageButton.setEnabled(true);
                }

                @Override
                public void onProgress(String status, double percent) {
                }
            });
       //     this.finish();
            // Handle clicks for addPacageButton
        }
    }

    private void getLocation() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CHECK_SETTINGS);
        } else {
            //if permission is granted
            enableGps();
            buildLocationRequest();
            buildLocationCallBack();

            //create FusedProviderClient
            mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CHECK_SETTINGS);
                return;
            }
            mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }
    }

    private void enableGps() {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick( final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        Toast.makeText(PackArrivedActivity.this,"Sorry, you must allow gps location to use this app",LENGTH_LONG).show();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void buildLocationCallBack() {
        mLocationCallback=new LocationCallback()
        {
            Location fLocation;
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for(Location location: locationResult.getLocations())
                    fLocation=location;
                String address=locationToAddress(fLocation);
                pack.setStorageLocation(new AddressAndLocation(fLocation,address));
                progressDialog.dismiss();
                storageLocationEditText.setText(address);
            }
        };
    }

    private String locationToAddress(Location location) {
        mGeocoder = new Geocoder(PackArrivedActivity.this, Locale.getDefault());
        Address address;
        List<Address> addresses;
        try {
            addresses = mGeocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
            if(addresses!=null && addresses.size()>0) {
                address = addresses.get(0);
                return address.getAddressLine(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void buildLocationRequest() {
        mLocationRequest=new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setSmallestDisplacement(10);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST_DESTINATION) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                recipientDestinationEditText.setText(place.getAddress());
                Location location = new Location("destination");
                location.setLatitude(place.getLatLng().latitude);
                location.setLongitude(place.getLatLng().longitude);
                pack.setStorageLocation(new AddressAndLocation(location, place.getAddress().toString()));
            }
        }
        if (requestCode == PLACE_PICKER_REQUEST_PICKUP) {
            if (resultCode == RESULT_OK) {
                mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
                Place place = PlacePicker.getPlace(data, this);
                storageLocationEditText.setText(place.getAddress());
                Location location = new Location("pickup");
                location.setLatitude(place.getLatLng().latitude);
                location.setLongitude(place.getLatLng().longitude);
                pack.getRecipient().setAddress(new AddressAndLocation(location, place.getAddress().toString()));
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case REQUEST_CHECK_SETTINGS:
            {
                if(grantResults.length>0)
                {
                    if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    {

                    }
                    else if(grantResults[0]==PackageManager.PERMISSION_DENIED)
                    {

                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

}
