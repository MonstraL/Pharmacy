package com.pharmaciesh;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.pharmaciesh.api.Api;
import com.pharmaciesh.entity.Medication;
import com.pharmaciesh.utils.DatabaseHandler;
import com.pharmaciesh.utils.GPSTracker;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private LocationManager mLocationManager;
    private GPSTracker gps;

    DatabaseHandler db = new DatabaseHandler(this);

   public void setViews(){
        final List<Medication> medications = db.getAllMedications();

        Button favoriteButton0 = findViewById(R.id.favButton);
        Button favoriteButton1 = findViewById(R.id.favButton1);
        Button favoriteButton2 = findViewById(R.id.favButton2);
        Button favoriteButton3 = findViewById(R.id.favButton3);

        Button deleteButton0 = findViewById(R.id.deleteFavButton);
        Button deleteButton1 = findViewById(R.id.deleteFavButton1);
        Button deleteButton2 = findViewById(R.id.deleteFavButton2);
        Button deleteButton3 = findViewById(R.id.deleteFavButton3);

        Button findButton0 = findViewById(R.id.searchFavButton);
        Button findButton1 = findViewById(R.id.searchFavButton1);
        Button findButton2 = findViewById(R.id.searchFavButton2);
        Button findButton3 = findViewById(R.id.searchFavButton3);

       switch (medications.size()){
           case(4): favoriteButton3.setText(medications.get(3).getName());
               deleteButton3.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) { db.deleteMedication(medications.get(3).getId()); recreate();}
               });
               findButton3.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this, PharmaciesActivity.class);
                       intent.putExtra("Medication", medications.get(3));
                       startActivity(intent);
                   }
               });
               favoriteButton3.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                       intent.putExtra("Medication", medications.get(3));
                       startActivity(intent);
                   }
               });
           case(3): favoriteButton2.setText(medications.get(2).getName());
               deleteButton2.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) { db.deleteMedication(medications.get(2).getId()); recreate();}
               });
               findButton2.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this, PharmaciesActivity.class);
                       intent.putExtra("Medication", medications.get(2));
                       startActivity(intent);
                   }
               });
               favoriteButton2.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                       intent.putExtra("Medication", medications.get(2));
                       startActivity(intent);
                   }
               });
           case(2): favoriteButton1.setText(medications.get(1).getName());
               deleteButton1.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) { db.deleteMedication(medications.get(1).getId()); recreate();}
               });
               findButton1.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this, PharmaciesActivity.class);
                       intent.putExtra("Medication", medications.get(1));
                       startActivity(intent);
                   }
               });
               favoriteButton1.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                       intent.putExtra("Medication", medications.get(1));
                       startActivity(intent);
                   }
               });
           case(1): favoriteButton0.setText(medications.get(0).getName());
               deleteButton0.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) { db.deleteMedication(medications.get(0).getId()); recreate(); }
               });
               findButton0.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this, PharmaciesActivity.class);
                       intent.putExtra("Medication", medications.get(0));
                       startActivity(intent);
                   }
               });
               favoriteButton0.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                       intent.putExtra("Medication", medications.get(0));
                       startActivity(intent);
                   }
               }); break;
               default: db.deleteAll();
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-3198313229258011~5462698642");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        setViews();

        final Button button = findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            private TextView searchingText = findViewById(R.id.textToSearch);

            public void onClick(View v) {
                if(searchingText.getText().toString().length()<3)
                    return;
                Intent intent = new Intent(MainActivity.this, MedicationsSearchActivity.class);
                intent.putExtra("searchText", searchingText.getText().toString());
                intent.putExtra("type", "Name");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        recreate();
    }
}
