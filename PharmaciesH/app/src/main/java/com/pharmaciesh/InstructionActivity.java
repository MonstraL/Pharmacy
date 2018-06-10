package com.pharmaciesh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.pharmaciesh.entity.Medication;

public class InstructionActivity extends AppCompatActivity {

    private AdView mAdView;
    private TextView nameViewText;
    private EditText instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        MobileAds.initialize(this, "ca-app-pub-3198313229258011~5462698642");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final Medication searchMedication = getIntent().getParcelableExtra("Medication");

        nameViewText = findViewById(R.id.nameText);
        nameViewText.setText(searchMedication.getName());

        instruction = findViewById(R.id.instructionText);
        instruction.setText(searchMedication.getInstruction());

        findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
