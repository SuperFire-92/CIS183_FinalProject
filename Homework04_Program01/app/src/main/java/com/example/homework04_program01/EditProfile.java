package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    EditText et_j_ep_name;
    EditText et_j_ep_address;
    EditText et_j_ep_phoneNumber;
    TextView tv_j_ep_email;
    EditText et_j_ep_password;
    ImageView img_j_ep_back;
    ImageView img_j_ep_update;
    ImageView img_j_ep_delete;

    Intent int_j_services;
    Intent int_j_handymanProfile;
    Intent int_j_mainActivity;

    DatabaseHelper dbHelper;
    User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        et_j_ep_name = findViewById(R.id.et_v_ep_name);
        et_j_ep_address = findViewById(R.id.et_v_ep_street);
        et_j_ep_phoneNumber = findViewById(R.id.et_v_ep_phoneNumber);
        tv_j_ep_email = findViewById(R.id.tv_v_ep_email);
        et_j_ep_password = findViewById(R.id.et_v_ep_password);
        img_j_ep_back = findViewById(R.id.img_v_ep_back);
        img_j_ep_update = findViewById(R.id.img_j_ep_update);
        img_j_ep_delete = findViewById(R.id.img_j_ep_delete);

        int_j_services = new Intent(EditProfile.this, Services.class);
        int_j_handymanProfile = new Intent(EditProfile.this, HandymanProfile.class);
        int_j_mainActivity = new Intent(EditProfile.this, MainActivity.class);

        dbHelper = new DatabaseHelper(this);

        curUser = dbHelper.getUser(LoginInfo.getUser().getEmail());

        fillInfo();

        buttonBackEventHandler();
        buttonUpdateEventHandler();
        buttonDeleteEventHandler();
    }

    public void buttonBackEventHandler()
    {
        img_j_ep_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPrevious();
            }
        });
    }

    public void buttonUpdateEventHandler()
    {
        img_j_ep_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User updatedUser = new User(
                        curUser.getEmail(),
                        et_j_ep_password.getText().toString(),
                        et_j_ep_name.getText().toString(),
                        et_j_ep_address.getText().toString(),
                        et_j_ep_phoneNumber.getText().toString(),
                        "",
                        curUser.isHandyman()
                );

                if (updatedUser.getEmail().equals("") || updatedUser.getPassword().equals("") || updatedUser.getName().equals("") || updatedUser.getAddress().equals("") || updatedUser.getPhoneNumber().equals(""))
                {
                    Toast.makeText(EditProfile.this, "All Fields Must Be Filled", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dbHelper.updateUser(updatedUser);
                    curUser = dbHelper.getUser(updatedUser.getEmail());

                    backToPrevious();
                }
            }
        });
    }

    public void buttonDeleteEventHandler()
    {
        img_j_ep_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteUser(curUser);

                LoginInfo.setUser(null);

                startActivity(int_j_mainActivity);
            }
        });

    }

    public void fillInfo()
    {
        et_j_ep_name.setText(curUser.getName());
        et_j_ep_address.setText(curUser.getAddress());
        et_j_ep_phoneNumber.setText(curUser.getPhoneNumber());
        tv_j_ep_email.setText(curUser.getEmail());
        et_j_ep_password.setText(curUser.getPassword());
        Log.d("Password",curUser.getPassword());
    }

    public void backToPrevious()
    {
        LoginInfo.setUser(curUser);
        if (curUser.isHandyman())
        {
            startActivity(int_j_handymanProfile);
        }
        else
        {
            startActivity(int_j_services);
        }
    }
}