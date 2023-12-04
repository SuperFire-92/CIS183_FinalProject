package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HandymanSelection extends AppCompatActivity {

    ImageView image_j_hs_searchImage;
    TextView tv_j_hs_searchText;

    String searchSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_selection);

        image_j_hs_searchImage = findViewById(R.id.image_v_hs_searchImage);
        tv_j_hs_searchText = findViewById(R.id.tv_v_hs_searchText);

        Intent cameFrom = getIntent();
        Bundle bundle = cameFrom.getExtras();
        searchSelection = bundle.getString("service");

        image_j_hs_searchImage.setImageResource(R.drawable.logo_carpentry);
        tv_j_hs_searchText.setText(searchSelection);
    }
}