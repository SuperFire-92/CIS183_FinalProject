package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Services extends AppCompatActivity {

    Button btn_j_s_plumbing;
    Button btn_j_s_electrical;
    Button btn_j_s_hvac;
    Button btn_j_s_flooring;
    Button btn_j_s_paint;
    Button btn_j_s_carpentry;
    Button btn_j_s_other;
    ImageView btn_j_s_logout;
    ImageView btn_j_s_editInfo;

    Intent int_j_mainActivity;
    Intent int_j_handymanSelection;
    Intent int_j_editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        btn_j_s_plumbing = findViewById(R.id.btn_v_s_plumbing);
        btn_j_s_electrical = findViewById(R.id.btn_v_s_electrical);
        btn_j_s_hvac = findViewById(R.id.btn_v_s_hvac);
        btn_j_s_flooring = findViewById(R.id.btn_v_s_flooring);
        btn_j_s_paint = findViewById(R.id.btn_v_s_paint);
        btn_j_s_carpentry = findViewById(R.id.btn_v_s_carpentry);
        btn_j_s_other = findViewById(R.id.btn_v_s_other);
        btn_j_s_logout = findViewById(R.id.image_v_s_logout);
        btn_j_s_editInfo = findViewById(R.id.image_v_s_editInfo);

        int_j_mainActivity = new Intent(Services.this, MainActivity.class);
        int_j_handymanSelection = new Intent(Services.this, HandymanSelection.class);
        int_j_editProfile = new Intent(Services.this, EditProfile.class);

        buttonPlumbingEventHandler();
        buttonElectricalEventHandler();
        buttonHvacEventHandler();
        buttonFlooringEventHandler();
        buttonPaintEventHandler();
        buttonCarpentryEventHandler();
        buttonOtherEventHandler();
        buttonLogoutEventHandler();
        buttonEditInfoEventHandler();
    }

    public void moveIntents(String j)
    {
        Log.d("Button Clicked:",j);

        int_j_handymanSelection.putExtra("service",j);
        startActivity(int_j_handymanSelection);
    }

    public void buttonPlumbingEventHandler()
    {
        btn_j_s_plumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveIntents(DatabaseInfo.getJobPlumbing());
            }
        });
    }

    public void buttonElectricalEventHandler()
    {
        btn_j_s_electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveIntents(DatabaseInfo.getJobElectrical());
            }
        });
    }

    public void buttonHvacEventHandler()
    {
        btn_j_s_hvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveIntents(DatabaseInfo.getJobHvac());
            }
        });
    }

    public void buttonFlooringEventHandler()
    {
        btn_j_s_flooring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveIntents(DatabaseInfo.getJobFlooring());
            }
        });
    }

    public void buttonPaintEventHandler()
    {
        btn_j_s_paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveIntents(DatabaseInfo.getJobPaint());
            }
        });
    }

    public void buttonCarpentryEventHandler()
    {
        btn_j_s_carpentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveIntents(DatabaseInfo.getJobCarpentry());
            }
        });
    }

    public void buttonOtherEventHandler()
    {
        btn_j_s_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveIntents(DatabaseInfo.getJobOther());
            }
        });
    }

    public void buttonLogoutEventHandler()
    {
        btn_j_s_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginInfo.setUser(null);
                startActivity(int_j_mainActivity);
            }
        });
    }

    public void buttonEditInfoEventHandler()
    {
        btn_j_s_editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(int_j_editProfile);
            }
        });
    }
}