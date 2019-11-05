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
import com.pharmaciesh.entity.Medication;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicationsSearchActivity extends AppCompatActivity {

    private TextView title;

    public void setResults(List<Medication> medicationList){
        Iterator<Medication> medicationIterator = medicationList.iterator();

        while(medicationIterator.hasNext()) {
            //Inflater service
            LayoutInflater layoutInfralte = (LayoutInflater)getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //parent layout xml refrence
            LinearLayout linearLayout= (LinearLayout) findViewById(R.id.infolayout);
            //Child layout xml refrence
            View view=layoutInfralte.inflate(R.layout.infoitem, null);

            final Medication medication = medicationIterator.next();

            view.findViewById(R.id.instructionButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MedicationsSearchActivity.this,
                            InstructionActivity.class);
                    intent.putExtra(getString(R.string.parcelable_extra_medication), medication);
                    startActivity(intent);
                }
            });

            view.findViewById(R.id.searchSubstanceButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MedicationsSearchActivity.this,
                            MedicationsSearchActivity.class);
                    intent.putExtra(getString(R.string.string_extra_search_text), title.getText().toString());
                    intent.putExtra(getString(R.string.string_extra_type), "Substance");
                    intent.putExtra(getString(R.string.parcelable_extra_medication), medication);
                    startActivity(intent);
                }
            });

            view.findViewById(R.id.searchPharmaciesButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MedicationsSearchActivity.this,
                            PharmaciesActivity.class);
                    intent.putExtra(getString(R.string.parcelable_extra_medication), medication);
                    startActivity(intent);
                }
            });

            ((TextView) view.findViewById(R.id.nameText)).setText(medication.getName());
            ((TextView) view.findViewById(R.id.formText)).setText(medication.getReleaseForm());

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
        progressDialog.setMessage(getString(R.string.progress_dialog_text)); // set message
        progressDialog.show(); // show progress dialog

        Api.getMedication().getMedicationsByName(searchingText).enqueue(new Callback<List<Medication>>() {

            @Override
            public void onResponse(Call<List<Medication>> call, Response<List<Medication>> response) {
                List<Medication> medicationList = response.body();
                progressDialog.dismiss(); //dismiss progress dialog
                if(medicationList.isEmpty()) {
                    failureDialog(getString(R.string.medication_empty_list_failure_message),
                            getString(R.string.medication_failure_question));
                }
                setResults(medicationList);
                // call this method to set the data in adapter
            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable t) {
                progressDialog.dismiss(); //dismiss progress dialog
                failureDialog(getString(R.string.medication_failure_message),
                        getString(R.string.medication_failure_question));
                t.printStackTrace();
            }

        });

    }

    public void searchBySubstance(int id, String searchingText){
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MedicationsSearchActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage(getString(R.string.progress_dialog_text)); // set message
        progressDialog.show(); // show progress dialog

        Api.getMedication().getMedicationsBySubstance(id, searchingText).enqueue(new Callback<List<Medication>>() {
            @Override
            public void onResponse(Call<List<Medication>> call, Response<List<Medication>> response) {
                List<Medication> medicationList = response.body();
                progressDialog.dismiss(); //dismiss progress dialog
                if(medicationList.isEmpty())
                    failureDialog( getString(R.string.analog_empty_list_failure_message),
                            getString(R.string.medication_failure_question_prev_page));
                setResults(medicationList);
                // call this method to set the data in adapter
            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable t) {
                progressDialog.dismiss(); //dismiss progress dialog
                failureDialog(getString(R.string.medication_failure_message),
                        getString(R.string.medication_failure_question_prev_page));
                t.printStackTrace();
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medications_search);

        initializeAds();

        String searchingText = getIntent().getExtras().getString(getString(R.string.string_extra_search_text));
        String type = getIntent().getExtras().getString(getString(R.string.string_extra_type));
        final Medication searchMedication = getIntent().getParcelableExtra(getString(R.string.medication_extra));

        title = findViewById(R.id.textToSearch2);
        title.setText(searchingText);

        if(type.matches("Name")){
            searchByName(searchingText);
        } else {
            searchBySubstance(searchMedication.getId(), searchMedication.getActiveSub());
        }

        final Button button = findViewById(R.id.searchButton2);
        button.setOnClickListener(new View.OnClickListener() {
            private TextView searchingText = findViewById(R.id.textToSearch2);

            public void onClick(View v) {
                if(searchingText.getText().toString().length()<3)
                    return;
                Intent intent = getIntent();
                intent.putExtra(getString(R.string.string_extra_search_text), searchingText.getText().toString());
                intent.putExtra(getString(R.string.string_extra_type), "Name");
                finish();
                startActivity(intent);
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