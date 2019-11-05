package com.pharmaciesh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.pharmaciesh.entity.Medication;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        setViews();

        initializeAds();
    }

    private void setViews(){
        final Medication searchMedication = getIntent().getParcelableExtra(getString(R.string.medication_extra));

        ((TextView) findViewById(R.id.nameText)).setText(searchMedication.getName());

        ((EditText) findViewById(R.id.instructionText)).setText(searchMedication.getInstruction());

        findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initializeAds(){
        MobileAds.initialize(this, getString(R.string.ads_account));

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}
