package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HandymanEdule extends AppCompatActivity {

    TextView tv_j_he_handyman;
    ImageView img_j_he_scheduleAppointment;
    ImageView img_j_he_backButton;

    Intent int_j_handymanSelection;
    Intent int_j_scheduleConfirm;
    String service;
    String handymanName;
    User handyman;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_edule);

        //Set up variables
        tv_j_he_handyman = findViewById(R.id.tv_v_he_handyman);
        img_j_he_scheduleAppointment = findViewById(R.id.image_v_he_scheduleAppointment);
        img_j_he_backButton = findViewById(R.id.image_v_he_backButton);

        int_j_handymanSelection = new Intent(HandymanEdule.this, HandymanSelection.class);
        int_j_scheduleConfirm = new Intent(HandymanEdule.this, ScheduleConfirm.class);

        dbHelper = new DatabaseHelper(this);

        //Get info from last page
        Intent cameFrom = getIntent();
        Bundle bundle = cameFrom.getExtras();
        service = bundle.getString("service");

        //Convert the given email into a user
        handyman = dbHelper.getUser(bundle.getString("handymanName"));

        handymanName = handyman.getName();

        //Set the text to the name of the handyman
        tv_j_he_handyman.setText(handymanName);

        buttonScheduleAppointmentEventHandler();
        buttonBackEventHandler();
    }

    public void buttonScheduleAppointmentEventHandler()
    {
        img_j_he_scheduleAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add the call to the database
                dbHelper.addCall(handyman.getEmail(), LoginInfo.getUser().getEmail(), service);

                //Go to the confirmation page
                startActivity(int_j_scheduleConfirm);
            }
        });
    }

    public void buttonBackEventHandler()
    {
        img_j_he_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int_j_handymanSelection.putExtra("service",service);

                startActivity(int_j_handymanSelection);
            }
        });
    }
}