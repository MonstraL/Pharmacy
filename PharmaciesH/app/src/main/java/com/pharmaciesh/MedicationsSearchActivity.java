package com.pharmaciesh;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.pharmaciesh.api.Api;
import com.pharmaciesh.controller.Controller;
import com.pharmaciesh.entity.Medication;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicationsSearchActivity extends AppCompatActivity {

    private AdView mAdView;
    TextView title;

    public void setResults(List<Medication> medicationList){
        Iterator<Medication> medicationIterator = medicationList.iterator();

        TextView nameText;
        TextView formText;
        Button searchPharmaciesButton;
        Button searchBySubstanceButton;
        Button instructionButton;

        while(medicationIterator.hasNext())
        {
            //Inflater service
            LayoutInflater layoutInfralte=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //parent layout xml refrence
            LinearLayout linearLayout=(LinearLayout)findViewById(R.id.infolayout);
            //Child layout xml refrence
            View view=layoutInfralte.inflate(R.layout.infoitem, null);

            nameText = view.findViewById(R.id.nameText);
            formText = view.findViewById(R.id.formText);

            searchPharmaciesButton = view.findViewById(R.id.searchPharmaciesButton);
            searchBySubstanceButton = view.findViewById(R.id.searchSubstanceButton);
            instructionButton = view.findViewById(R.id.instructionButton);

            final Medication medication = medicationIterator.next();

            instructionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MedicationsSearchActivity.this, InstructionActivity.class);
                    intent.putExtra("Medication", medication);
                    startActivity(intent);
                }
            });

            searchBySubstanceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MedicationsSearchActivity.this, MedicationsSearchActivity.class);
                    intent.putExtra("searchText", title.getText().toString());
                    intent.putExtra("type", "Substance");
                    intent.putExtra("Medication", medication);
                    startActivity(intent);
                }
            });

            searchPharmaciesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MedicationsSearchActivity.this, PharmaciesActivity.class);
                    intent.putExtra("Medication", medication);
                    startActivity(intent);
                }
            });

            nameText.setText(medication.getName());
            formText.setText(medication.getReleaseForm());

            ImageView imageView = view.findViewById(R.id.imageView);
            Picasso.get()
                    .load(medication.getEmblem())
                    .resize(400, 400)
                    .centerCrop()
                    .into(imageView);

            linearLayout.addView(view);
        }
    }

    public void failureDialog(String message, String question){
        AlertDialog.Builder builder = new AlertDialog.Builder(MedicationsSearchActivity.this);
        builder.setMessage(message);
        builder.setPositiveButton(question, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.show();
    }

    public void searchByName(String searchingText){

        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MedicationsSearchActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Зачекайте, будь ласка"); // set message
        progressDialog.show(); // show progress dialog

        Api.getMedication().getMedicationsByName(searchingText).enqueue(new Callback<List<Medication>>() {
            @Override
            public void onResponse(Call<List<Medication>> call, Response<List<Medication>> response) {
                List<Medication> medicationList = response.body();
                progressDialog.dismiss(); //dismiss progress dialog
                if(medicationList.isEmpty())
                    failureDialog("Лікарський засіб не знайдено", "Перейти на головну");
                setResults(medicationList);
                // call this method to set the data in adapter
            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable t) {
                progressDialog.dismiss(); //dismiss progress dialog
                failureDialog("Немає з'єднання з сервером", "Перейти на головну");
                t.printStackTrace();
            }

        });

    }

    public void searchBySubstance(int id, String searchingText){

        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MedicationsSearchActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Зачекайте, будь ласка"); // set message
        progressDialog.show(); // show progress dialog

        Api.getMedication().getMedicationsBySubstance(id, searchingText).enqueue(new Callback<List<Medication>>() {
            @Override
            public void onResponse(Call<List<Medication>> call, Response<List<Medication>> response) {
                List<Medication> medicationList = response.body();
                progressDialog.dismiss(); //dismiss progress dialog
                if(medicationList.isEmpty())
                    failureDialog("Аналоги не знайдено", "Попередня сторінка");
                setResults(medicationList);
                // call this method to set the data in adapter
            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable t) {
                progressDialog.dismiss(); //dismiss progress dialog
                failureDialog("Немає з'єднання з сервером", "Попередня сторінка");
                t.printStackTrace();
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medications_search);

        MobileAds.initialize(this, "ca-app-pub-3198313229258011~5462698642");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String searchingText = getIntent().getExtras().getString("searchText");
        String type = getIntent().getExtras().getString("type");
        final Medication searchMedication = getIntent().getParcelableExtra("Medication");

        title = findViewById(R.id.textToSearch2);
        title.setText(searchingText);
        if(type.matches("Name"))
            searchByName(searchingText);
        else    
            searchBySubstance(searchMedication.getId(), searchMedication.getActiveSub());

        final Button button = findViewById(R.id.searchButton2);
        button.setOnClickListener(new View.OnClickListener() {
            private TextView searchingText = findViewById(R.id.textToSearch2);

            public void onClick(View v) {
                if(searchingText.getText().toString().length()<3)
                    return;
                Intent intent = getIntent();
                intent.putExtra("searchText", searchingText.getText().toString());
                intent.putExtra("type", "Name");
                finish();
                startActivity(intent);
            }
        });
    }
}