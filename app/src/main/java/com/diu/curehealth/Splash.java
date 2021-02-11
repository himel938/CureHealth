package com.diu.curehealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diu.curehealth.loginsignup.SignupActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread t = new Thread(){
            public void run()
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent in= new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(in);
                finish();
            }
        };
        t.start();
    }
}
