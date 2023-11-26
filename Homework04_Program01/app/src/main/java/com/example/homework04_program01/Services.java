package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Services extends AppCompatActivity {

    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        test = findViewById(R.id.btn_v_s_plumbing);

        buttonTestHandler();
    }

    public void buttonTestHandler()
    {
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Success","Plumbing Clicked");
            }
        });
    }
}