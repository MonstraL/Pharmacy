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
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class PharmaciesActivity extends AppCompatActivity {

    private GPSTracker gps = new GPSTracker(PharmaciesActivity.this);

    private Dialog chainDialog;

    public void failureDialog(String message, String question){
        AlertDialog.Builder builder = new AlertDialog.Builder(PharmaciesActivity.this);
        builder.setMessage(message);
        builder.setPositiveButton(question, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.show();
    }

    public void setResults(List<Pharmacy> pharmaciesList){
        Iterator<Pharmacy> pharmacyIterator = pharmaciesList.iterator();

        TextView nameText;
        TextView addressText;
        TextView stageText;
        EditText priceText;
        ImageButton chainButton;

        //Inflater service
        LayoutInflater layoutInfralte=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //parent layout xml refrence
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.pharmaciesLayout);

        linearLayout.removeAllViews();

        while(pharmacyIterator.hasNext())
        {
            //Child layout xml refrence
            View pharmacyItemView=layoutInfralte.inflate(R.layout.pharmacy_item, null);

            nameText = pharmacyItemView.findViewById(R.id.pharmacy_nameText);
            addressText = pharmacyItemView.findViewById(R.id.pharmacy_addressText);
            stageText = pharmacyItemView.findViewById(R.id.pharmacy_stageText);
            priceText = pharmacyItemView.findViewById(R.id.pharmacy_priceText);

            chainButton = pharmacyItemView.findViewById(R.id.pharmacy_infoButton);
            chainDialog = new Dialog(this);

            final Pharmacy pharmacy = pharmacyIterator.next();

            chainButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showChainDialog(pharmacy);
                }
            });


            nameText.setText(pharmacy.getChain().getName());
            addressText.setText(pharmacy.getStreet().replace("+", " ") + " " + pharmacy.getHouseNum());
            priceText.setText(new DecimalFormat("##0.00").format(pharmacy.getPrice()) + " ₴");
            chainButton.setImageResource(R.drawable.pharmacy_icon1);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String getCurrentTime = sdf.format(c.getTime());

            int day = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
            if(pharmacy.getOpeningTime().matches("00:00") || ( getCurrentTime.compareTo(pharmacy.getClosingTime()) < 0 && pharmacy.getOpeningTime().compareTo(getCurrentTime)<0 ))
                switch (day){
                    case 2: if(!pharmacy.getWorkDays().contains(getString(R.string.pharmacy_day_monday))) {
                        stageText.setText(getString(R.string.pharmacy_closed)); stageText.setTextColor(Color.RED);} break;
                    case 3: if(!pharmacy.getWorkDays().contains(getString(R.string.pharmacy_day_tuesday))) {
                        stageText.setText(getString(R.string.pharmacy_closed)); stageText.setTextColor(Color.RED);} break;
                    case 4: if(!pharmacy.getWorkDays().contains(getString(R.string.pharmacy_day_wednesday))) {
                        stageText.setText(getString(R.string.pharmacy_closed)); stageText.setTextColor(Color.RED);} break;
                    case 5: if(!pharmacy.getWorkDays().contains(getString(R.string.pharmacy_day_thursday))) {
                        stageText.setText(getString(R.string.pharmacy_closed)); stageText.setTextColor(Color.RED);} break;
                    case 6: if(!pharmacy.getWorkDays().contains(getString(R.string.pharmacy_day_friday))) {
                        stageText.setText(getString(R.string.pharmacy_closed)); stageText.setTextColor(Color.RED);} break;
                    case 7: if(!pharmacy.getWorkDays().contains(getString(R.string.pharmacy_day_saturday))) {
                        stageText.setText(getString(R.string.pharmacy_closed)); stageText.setTextColor(Color.RED);} break;
                    case 1: if(!pharmacy.getWorkDays().contains(getString(R.string.pharmacy_day_sunday))) {
                        stageText.setText(getString(R.string.pharmacy_closed)); stageText.setTextColor(Color.RED);} break;
              }
              else {
                stageText.setText(getString(R.string.pharmacy_closed));
                stageText.setTextColor(Color.RED);
            }

            ImageView imageView = pharmacyItemView.findViewById(R.id.pharmacy_infoButton);
            setImageFromURL(imageView, pharmacy.getChain().getEmblem());
            linearLayout.addView(pharmacyItemView);
        }
    }

    public void setImageFromURL(ImageView imageView, String URl)
    {
        Picasso.get()
                .load(URl)
                .resize(400, 400)
                .centerCrop()
                .into(imageView);
    }

    public void showChainDialog(final Pharmacy pharmacy){
        chainDialog.setContentView(R.layout.activity_chain);

        TextView pharmacyWorkTime = chainDialog.findViewById(R.id.workTimeTextView);
        Button closeChain = chainDialog.findViewById(R.id.closeCardButton);

        ((TextView) chainDialog.findViewById(R.id.foundationTextView))
                .setText(String.valueOf( pharmacy.getChain().getYear()));
        ((TextView) chainDialog.findViewById(R.id.chainNameTextView))
                .setText(pharmacy.getChain().getName());
        ((TextView) chainDialog.findViewById(R.id.workersTextView))
                .setText(String.valueOf( pharmacy.getChain().getEmployees()));

        ((TextView)chainDialog.findViewById(R.id.chainAddressTextView))
                .setText(pharmacy.getStreet().replace("+", " ") + " " + pharmacy.getHouseNum());

        if(pharmacy.getOpeningTime().matches("00:00")&&pharmacy.getClosingTime().matches("00:00"))
            pharmacyWorkTime.setText(getString(R.string.pharmacy_all_day_work));
        else
            pharmacyWorkTime.setText(pharmacy.getOpeningTime() + " - " + pharmacy.getClosingTime());

        setImageFromURL((ImageView) chainDialog.findViewById(R.id.emblemImageView),
                pharmacy.getChain().getEmblem());

        closeChain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chainDialog.dismiss();
            }
        });

        chainDialog.findViewById(R.id.closeCardButton).findViewById(R.id.locationButton)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com.ua/maps/place/"+pharmacy.getStreet()+",+"+pharmacy.getHouseNum()+"+Львів"));
                startActivity(intent);
            }
        });

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        chainDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        chainDialog.getWindow().setLayout((7 * width)/7, (3 * height)/3);
        chainDialog.show();
    }

    public void searchPharmaciesByPrice(int id){

        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(PharmaciesActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage(getString(R.string.progress_dialog_text)); // set message
        progressDialog.show(); // show progress dialog

        Api.getPharmacy().getPharmaciesByPrice(id).enqueue(new Callback<List<Pharmacy>>() {
            @Override
            public void onResponse(Call<List<Pharmacy>> call, Response<List<Pharmacy>> response) {
                List<Pharmacy> pharmaciesList = response.body();
                progressDialog.dismiss(); //dismiss progress dialog
                if(pharmaciesList.isEmpty())
                    failureDialog(getString(R.string.pharmacy_failure_to_find),
                            getString(R.string.medication_failure_question_prev_page));
                setResults(pharmaciesList);
                // call this method to set the data in adapter
            }

            @Override
            public void onFailure(Call<List<Pharmacy>> call, Throwable t) {
                progressDialog.dismiss(); //dismiss progress dialog
                failureDialog(getString(R.string.medication_failure_message),
                        getString(R.string.medication_failure_question_prev_page));
                t.printStackTrace();
            }
        });

    }

    public void searchPharmaciesByLocation(int id){

        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(PharmaciesActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage(getString(R.string.progress_dialog_text)); // set message
        progressDialog.show(); // show progress dialog


        Api.getPharmacy().getPharmaciesByLocation(id, gps.getLongitude(), gps.getLatitude()).enqueue(new Callback<List<Pharmacy>>() {
            @Override
            public void onResponse(Call<List<Pharmacy>> call, Response<List<Pharmacy>> response) {
                List<Pharmacy> pharmaciesList = response.body();
                progressDialog.dismiss(); //dismiss progress dialog
                if(pharmaciesList == null || pharmaciesList.isEmpty())
                    failureDialog(getString(R.string.pharmacy_failure_to_find),
                            getString(R.string.medication_failure_question_prev_page));
                setResults(pharmaciesList);
                // call this method to set the data in adapter
            }

            @Override
            public void onFailure(Call<List<Pharmacy>> call, Throwable t) {
                progressDialog.dismiss(); //dismiss progress dialog
                failureDialog(getString(R.string.medication_failure_message), getString(R.string.medication_failure_question_prev_page));
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacies);

        initializeAds();

        final Medication searchMedication = getIntent().getParcelableExtra(getString(R.string.medication_extra));

        initMainButtons(searchMedication);

        searchPharmaciesByPrice(searchMedication.getId());
    }

    private void initMainButtons(final Medication searchMedication){
        final DatabaseHandler db = new DatabaseHandler(this);

        final FloatingActionButton addMedicationButton = findViewById(R.id.addFavoriteButton);
        if(db.checkExists(searchMedication.getId()) || db.getMedicationsCount()>4) {
            addMedicationButton.hide();
        }

        final CompoundButton compoundButton = findViewById(R.id.search_switch);
        compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!compoundButton.isChecked()) { //Could be removed
                    searchPharmaciesByPrice(searchMedication.getId());
                } else {
                    searchPharmaciesByLocation(searchMedication.getId());
                }
            }
        });

        findViewById(R.id.goBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addMedication(searchMedication);
                recreate();
            }
        });

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
