package com.diu.curehealth.doctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.diu.curehealth.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import static com.diu.curehealth.DoctorActivity.database;

public class DoctorProfile extends AppCompatActivity {

    ArrayList<Doctor> dataModels;
    ListView listView;
    private static DoctorAdapter adapter;
    String city;
    String group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        Bundle extras = getIntent().getExtras();
        city = extras.getString("city");
        group = extras.getString("group");
        listView = (ListView) findViewById(R.id.list_doctor);
        dataModels = new ArrayList<>();

        adapter = new DoctorAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        DatabaseReference myRef = database.getReference("doctors");
        myRef.child(city).child(group).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Doctor doctor = dataSnapshot.getValue(Doctor.class);
                dataModels.add(doctor);
                adapter.notifyDataSetChanged();
                /*donorInfo.add(donor);
                String donorInfo = donor.name + "\n     " + donor.contuctNumber;
                donorList.add(donorInfo);
                arrayAdapter.notifyDataSetChanged();*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       //

    }
}
