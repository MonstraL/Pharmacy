package com.pharmaciesh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.pharmaciesh.api.Api;
import com.pharmaciesh.entity.Medication;
import com.pharmaciesh.entity.Pharmacy;
import com.pharmaciesh.entity.Symptom;
import com.pharmaciesh.utils.DatabaseHandler;
import com.pharmaciesh.utils.GPSTracker;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendationsActivity extends AppCompatActivity {

    public void failureDialog(String message, String question){
        AlertDialog.Builder builder = new AlertDialog.Builder(RecommendationsActivity.this);
        builder.setMessage(message);
        builder.setPositiveButton(question, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.show();
    }

    public void setResults(List<Symptom> symptomsList){

    }

    public void setImageFromURL(ImageView imageView, String URl)
    {
        Picasso.get()
                .load(URl)
                .resize(400, 400)
                .centerCrop()
                .into(imageView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacies);

        initializeAds();

        final Medication searchMedication = getIntent().getParcelableExtra(getString(R.string.medication_extra));

        initMainButtons();

    }

    private void initMainButtons(){

    }

    @Override
    public void onRestart() {
        super.onRestart();
        recreate();
    }

    private void initializeAds(){
        MobileAds.initialize(this, getString(R.string.ads_account));

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

}
