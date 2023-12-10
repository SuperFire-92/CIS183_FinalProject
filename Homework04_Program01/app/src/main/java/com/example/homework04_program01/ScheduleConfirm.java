package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ScheduleConfirm extends AppCompatActivity {

    ImageView img_j_sc_confirm;
    Intent int_j_services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_confirm);

        img_j_sc_confirm = findViewById(R.id.img_v_sc_confirm);

        int_j_services = new Intent(ScheduleConfirm.this, Services.class);

        buttonConfirmEventHandler();
    }

    public void buttonConfirmEventHandler()
    {
        img_j_sc_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(int_j_services);
            }
        });
    }
}