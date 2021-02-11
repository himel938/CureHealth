package com.diu.curehealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.diu.curehealth.donor.DonorForm;
import com.diu.curehealth.donor.Information;
import com.diu.curehealth.donor.NeedBlood;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorActivity extends AppCompatActivity {
    public static FirebaseDatabase database;
    public FirebaseUser firebaseUser;

    Button buttonDonor;
    Button buttonInfo;
    Button needBlood;

    public static String donorId="no";
    SharedPreferences sharedPreferences;


    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=1;
    public String userMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        buttonInfo = (Button) findViewById(R.id.btn_info);
        needBlood = (Button) findViewById(R.id.btn_need_blood);
        buttonDonor = (Button) findViewById(R.id.btn_donor_profile);

        // Connecting to the database
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("donors");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userMail = firebaseUser.getEmail();
        if(userMail.equals("himelsk.work@gmail.com")){
            buttonDonor.setVisibility(View.VISIBLE);
        }
        else{
            buttonDonor.setVisibility(View.GONE);
        }
        /**
         * Wiring up every thing
         */



        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonorActivity.this, Information.class));
            }
        });


        needBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonorActivity.this, NeedBlood.class));
            }
        });



        if(donorId.toString().equals("no")){
            buttonDonor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DonorActivity.this, DonorForm.class));
                }
            });
        } else {

        }

        /**
         * Initializing variable
         */
        try{
            donorId = sharedPreferences.getString("id","no");

        } catch (Exception e){
            e.printStackTrace();
        }


    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }
}
