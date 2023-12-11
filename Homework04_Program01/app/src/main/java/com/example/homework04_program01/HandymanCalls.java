package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class HandymanCalls extends AppCompatActivity {

    ListView lv_j_hc_callView;
    ImageView img_j_hc_back;

    ListAdapterCalls adapter;
    ArrayList<Appointment> callList;
    Intent int_j_services;
    Intent int_j_handymanProfile;
    Intent int_j_jobView;

    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_calls);

        lv_j_hc_callView = findViewById(R.id.lv_v_hc_callView);
        img_j_hc_back = findViewById(R.id.img_v_hc_back);

        int_j_services = new Intent(HandymanCalls.this, Services.class);
        int_j_handymanProfile = new Intent(HandymanCalls.this, HandymanProfile.class);

        dbHelper = new DatabaseHelper(this);

        callList = dbHelper.getCalls(LoginInfo.getUser());

        fillListView();

        backButtonEventHandler();
        listViewEventHandler();
    }

    public void backButtonEventHandler()
    {
        img_j_hc_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginInfo.getUser().isHandyman())
                {
                    startActivity(int_j_handymanProfile);
                }
                else
                {
                    startActivity(int_j_services);
                }
            }
        });
    }

    public void listViewEventHandler()
    {
        lv_j_hc_callView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dbHelper.deleteCall(callList.get(position));
                callList.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void fillListView()
    {
        adapter = new ListAdapterCalls(this, callList);

        lv_j_hc_callView.setAdapter(adapter);
    }
}