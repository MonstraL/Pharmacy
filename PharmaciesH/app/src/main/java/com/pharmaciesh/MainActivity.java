package com.pharmaciesh;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.pharmaciesh.entity.Medication;
import com.pharmaciesh.utils.DatabaseHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db = new DatabaseHandler(this);
    private List<Medication> medications;

    private Button favoriteButton0;
    private Button favoriteButton1;
    private Button favoriteButton2;
    private Button favoriteButton3;

   public void setFavoriteButtons(){
        favoriteButton0 = findViewById(R.id.favButton);
        favoriteButton1 = findViewById(R.id.favButton1);
        favoriteButton2 = findViewById(R.id.favButton2);
        favoriteButton3 = findViewById(R.id.favButton3);
    }

    private void setOnClickListeners(){
       if(medications != null)
        switch (medications.size()) {
            case (4):
                favoriteButton3.setText(medications.get(3).getName());
                findViewById(R.id.deleteFavButton3).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        db.deleteMedication(medications.get(3).getId());
                        recreate();
                    }
                });
                findViewById(R.id.searchFavButton3).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, PharmaciesActivity.class);
                        intent.putExtra(getString(R.string.medication_extra), medications.get(3));
                        startActivity(intent);
                    }
                });
                favoriteButton3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                        intent.putExtra(getString(R.string.medication_extra), medications.get(3));
                        startActivity(intent);
                    }
                });
            case (3):
                favoriteButton2.setText(medications.get(2).getName());
                findViewById(R.id.deleteFavButton2).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        db.deleteMedication(medications.get(2).getId());
                        recreate();
                    }
                });
                findViewById(R.id.searchFavButton2).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, PharmaciesActivity.class);
                        intent.putExtra(getString(R.string.medication_extra), medications.get(2));
                        startActivity(intent);
                    }
                });
                favoriteButton2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                        intent.putExtra(getString(R.string.medication_extra), medications.get(2));
                        startActivity(intent);
                    }
                });
            case (2):
                favoriteButton1.setText(medications.get(1).getName());
                findViewById(R.id.deleteFavButton1).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        db.deleteMedication(medications.get(1).getId());
                        recreate();
                    }
                });
                findViewById(R.id.searchFavButton1).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, PharmaciesActivity.class);
                        intent.putExtra(getString(R.string.medication_extra), medications.get(1));
                        startActivity(intent);
                    }
                });
                favoriteButton1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                        intent.putExtra(getString(R.string.medication_extra), medications.get(1));
                        startActivity(intent);
                    }
                });
            case (1):
                favoriteButton0.setText(medications.get(0).getName());
                findViewById(R.id.deleteFavButton).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        db.deleteMedication(medications.get(0).getId());
                        recreate();
                    }
                });
                findViewById(R.id.searchFavButton).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, PharmaciesActivity.class);
                        intent.putExtra(getString(R.string.medication_extra), medications.get(0));
                        startActivity(intent);
                    }
                });
                favoriteButton0.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, InstructionActivity.class);
                        intent.putExtra(getString(R.string.medication_extra), medications.get(0));
                        startActivity(intent);
                    }
                });
                break;
            default:
                db.deleteAll();
        }

        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {

            TextView searchingText = findViewById(R.id.textToSearch);

            public void onClick(View v) {
                if(searchingText.getText().toString().length() < 3) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, MedicationsSearchActivity.class);
                intent.putExtra(getString(R.string.string_extra_search_text), searchingText.getText().toString());
                intent.putExtra(getString(R.string.string_extra_type), "Name");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListeners();

        initializeAds();

        checkSelfPermissions();

        medications = db.getAllMedications();

        setFavoriteButtons();

    }

    private void checkSelfPermissions(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void initializeAds(){
        MobileAds.initialize(this, getString(R.string.ads_account));

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        recreate();
    }
}
