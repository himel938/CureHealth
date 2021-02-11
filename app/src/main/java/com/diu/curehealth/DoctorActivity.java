package com.diu.curehealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.diu.curehealth.doctor.DoctorForm;
import com.diu.curehealth.doctor.NeedDoctor;

public class DoctorActivity extends AppCompatActivity {

    public static FirebaseDatabase database;
    public FirebaseUser firebaseUser;

    Button buttonDoctor;
    Button needDoctor;

    public static String doctorId="no";
    SharedPreferences sharedPreferences;

    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=1;
    public String userMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        needDoctor = (Button) findViewById(R.id.btn_need_doctor);
        buttonDoctor = (Button) findViewById(R.id.btn_doctor_profile);
        // Connecting to the database
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("doctors");

        /**
         * Wiring up every thing
         */
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userMail = firebaseUser.getEmail();
        if(userMail.equals("himelsk.work@gmail.com")){
            buttonDoctor.setVisibility(View.VISIBLE);
        }
        else{
            buttonDoctor.setVisibility(View.GONE);
        }


        needDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorActivity.this, NeedDoctor.class));
            }
        });



        if(doctorId.toString().equals("no")){
            buttonDoctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DoctorActivity.this, DoctorForm.class));
                }
            });
        } else {

        }

        /**
         * Initializing variable
         */
        try{
            doctorId = sharedPreferences.getString("id","no");

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
