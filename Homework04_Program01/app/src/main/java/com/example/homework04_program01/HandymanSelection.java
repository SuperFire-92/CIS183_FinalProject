package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HandymanSelection extends AppCompatActivity {

    ImageView image_j_hs_searchImage;
    ImageView image_j_hs_backButton;
    TextView tv_j_hs_searchText;
    ListView lv_j_hs_handymanList;
    DatabaseHelper dbHelper;
    ArrayList<User> handymanList;
    ListAdapterHandymanDisplay adapter;
    Intent int_j_services;
    Intent int_j_handymanSchedule;

    String searchSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_selection);

        image_j_hs_searchImage = findViewById(R.id.image_v_hs_searchImage);
        tv_j_hs_searchText = findViewById(R.id.tv_v_hs_searchText);
        lv_j_hs_handymanList = findViewById(R.id.lv_v_hs_handymanList);
        image_j_hs_backButton = findViewById(R.id.image_v_hs_backButton);

        Intent cameFrom = getIntent();
        Bundle bundle = cameFrom.getExtras();
        searchSelection = bundle.getString("service");

        dbHelper = new DatabaseHelper(this);

        int_j_services = new Intent(HandymanSelection.this, Services.class);
        int_j_handymanSchedule = new Intent(HandymanSelection.this, HandymanEdule.class);

        handymanList = dbHelper.getAllHandymen(searchSelection);

        fillListView();

        setImageDisplay();
        tv_j_hs_searchText.setText(searchSelection);

        setBackButtonEventHandler();
        listViewEventHandler();
    }

    public void listViewEventHandler()
    {
        lv_j_hs_handymanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String handymanName = handymanList.get(position).getEmail();
                int_j_handymanSchedule.putExtra("handymanName",handymanName);
                int_j_handymanSchedule.putExtra("service",searchSelection);

                startActivity(int_j_handymanSchedule);
            }
        });
    }

    public void setBackButtonEventHandler()
    {
        image_j_hs_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(int_j_services);
            }
        });
    }

    public void fillListView()
    {
        adapter = new ListAdapterHandymanDisplay(this, handymanList);

        lv_j_hs_handymanList.setAdapter(adapter);
    }

    public void setImageDisplay()
    {
        if (searchSelection.equals(DatabaseInfo.getJobPlumbing()))
        {
            image_j_hs_searchImage.setImageResource(R.drawable.logo_plumbing);
        }
        else if (searchSelection.equals(DatabaseInfo.getJobElectrical()))
        {
            image_j_hs_searchImage.setImageResource(R.drawable.logo_electrical);
        }
        else if (searchSelection.equals(DatabaseInfo.getJobHvac()))
        {
            image_j_hs_searchImage.setImageResource(R.drawable.logo_hvac);
        }
        else if (searchSelection.equals(DatabaseInfo.getJobFlooring()))
        {
            image_j_hs_searchImage.setImageResource(R.drawable.logo_flooring);
        }
        else if (searchSelection.equals(DatabaseInfo.getJobPaint()))
        {
            image_j_hs_searchImage.setImageResource(R.drawable.logo_painting);
        }
        else if (searchSelection.equals(DatabaseInfo.getJobCarpentry()))
        {
            image_j_hs_searchImage.setImageResource(R.drawable.logo_carpentry);
        }
        else if (searchSelection.equals(DatabaseInfo.getJobOther()))
        {
            image_j_hs_searchImage.setImageResource(R.drawable.logo_other);
        }
    }
}