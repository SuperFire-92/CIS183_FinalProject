package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class HandymanProfile extends AppCompatActivity {

    CheckBox checkBox_j_hp_plumbing;
    CheckBox checkBox_j_hp_electrical;
    CheckBox checkBox_j_hp_hvac;
    CheckBox checkBox_j_hp_flooring;
    CheckBox checkBox_j_hp_paint;
    CheckBox checkBox_j_hp_carpentry;
    CheckBox checkBox_j_hp_other;
    ImageView img_j_hp_logout;
    ImageView img_j_hp_apply;
    ImageView img_j_hp_editInfo;
    ImageView img_j_hp_jobs;
    EditText et_j_hp_description;

    Intent int_j_mainActivity;
    Intent int_j_editProfile;
    DatabaseHelper dbHelper;
    User handyman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_profile);

        checkBox_j_hp_plumbing = findViewById(R.id.checkBox_v_hp_plumbing);
        checkBox_j_hp_electrical = findViewById(R.id.checkBox_v_hp_electrical);
        checkBox_j_hp_hvac = findViewById(R.id.checkBox_v_hp_hvac);
        checkBox_j_hp_flooring = findViewById(R.id.checkBox_v_hp_flooring);
        checkBox_j_hp_paint = findViewById(R.id.checkBox_v_hp_paint);
        checkBox_j_hp_carpentry = findViewById(R.id.checkBox_v_hp_carpentry);
        checkBox_j_hp_other = findViewById(R.id.checkBox_v_hp_other);
        img_j_hp_logout = findViewById(R.id.img_v_hp_logout);
        img_j_hp_apply = findViewById(R.id.img_v_hp_apply);
        img_j_hp_editInfo = findViewById(R.id.img_v_hp_editInfo);
        img_j_hp_jobs = findViewById(R.id.img_v_hp_jobs);
        et_j_hp_description = findViewById(R.id.et_v_hp_description);

        int_j_mainActivity = new Intent(HandymanProfile.this, MainActivity.class);
        int_j_editProfile = new Intent(HandymanProfile.this, EditProfile.class);

        dbHelper = new DatabaseHelper(this);

        handyman = dbHelper.getUser(LoginInfo.getUser().getEmail());

        //Get a list of the users jobs
        ArrayList<String> jobList = dbHelper.getHandymanJobs(LoginInfo.getUser().getEmail());

        //Fill each checkbox with true or false depending on whether the handyman has that job
        for (int i = 0; i < jobList.size(); i++)
        {
            fillCheckboxes(jobList.get(i));
        }

        //Fill the description
        et_j_hp_description.setText(handyman.getDescription());

        buttonApplyEventHandler();
        buttonLogoutEventHandler();
        buttonEditInfoEventHandler();
    }

    public void buttonApplyEventHandler()
    {
        img_j_hp_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listOfJobs = getCheckboxes();

                dbHelper.setHandymanJobs(handyman.getEmail(), listOfJobs);
                dbHelper.setHandymanDescription(handyman.getEmail(), et_j_hp_description.getText().toString());
            }
        });
    }

    public void buttonLogoutEventHandler()
    {
        img_j_hp_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginInfo.setUser(null);

                startActivity(int_j_mainActivity);
            }
        });
    }

    public void buttonEditInfoEventHandler()
    {
        img_j_hp_editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(int_j_editProfile);
            }
        });
    }

    public void fillCheckboxes(String jobName)
    {
        if (jobName.equals(DatabaseInfo.getJobPlumbing()))
        {
            checkBox_j_hp_plumbing.setChecked(true);
        }
        if (jobName.equals(DatabaseInfo.getJobElectrical()))
        {
            checkBox_j_hp_electrical.setChecked(true);
        }
        if (jobName.equals(DatabaseInfo.getJobHvac()))
        {
            checkBox_j_hp_hvac.setChecked(true);
        }
        if (jobName.equals(DatabaseInfo.getJobFlooring()))
        {
            checkBox_j_hp_flooring.setChecked(true);
        }
        if (jobName.equals(DatabaseInfo.getJobPaint()))
        {
            checkBox_j_hp_paint.setChecked(true);
        }
        if (jobName.equals(DatabaseInfo.getJobCarpentry()))
        {
            checkBox_j_hp_carpentry.setChecked(true);
        }
        if (jobName.equals(DatabaseInfo.getJobOther()))
        {
            checkBox_j_hp_other.setChecked(true);
        }
    }

    public ArrayList<String> getCheckboxes()
    {
        ArrayList<String> listOfJobs = new ArrayList<String>();
        if (checkBox_j_hp_plumbing.isChecked())
        {
            listOfJobs.add(DatabaseInfo.getJobPlumbing());
        }
        if (checkBox_j_hp_electrical.isChecked())
        {
            listOfJobs.add(DatabaseInfo.getJobElectrical());
        }
        if (checkBox_j_hp_hvac.isChecked())
        {
            listOfJobs.add(DatabaseInfo.getJobHvac());
        }
        if (checkBox_j_hp_flooring.isChecked())
        {
            listOfJobs.add(DatabaseInfo.getJobFlooring());
        }
        if (checkBox_j_hp_paint.isChecked())
        {
            listOfJobs.add(DatabaseInfo.getJobPaint());
        }
        if (checkBox_j_hp_carpentry.isChecked())
        {
            listOfJobs.add(DatabaseInfo.getJobCarpentry());
        }
        if (checkBox_j_hp_other.isChecked())
        {
            listOfJobs.add(DatabaseInfo.getJobOther());
        }
        return listOfJobs;
    }
}