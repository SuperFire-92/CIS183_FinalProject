package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//          HANDYMAN APP
//================================
//This is an app used to schedule appointments with handymen.
//Using a database we store users and handymen accounts, along with
//which handymen offer which services. Handymen can select which jobs
//they offer. Users can search for handymen based on the jobs they're
//looking for.
//
//We start on this page with a login screen.
//From here we can navigate to the signup screen.
//================================

public class MainActivity extends AppCompatActivity {

    Button btn_j_m_signUp;
    Button btn_j_m_signIn;
    EditText et_j_m_username;
    EditText et_j_m_password;

    Intent int_j_signUp;
    Intent int_j_services;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_j_m_signUp  = findViewById(R.id.btn_v_m_signUp);
        btn_j_m_signIn  = findViewById(R.id.btn_v_m_signIn);
        et_j_m_username    = findViewById(R.id.et_v_m_email);
        et_j_m_password = findViewById(R.id.et_v_m_password);

        int_j_signUp    = new Intent(MainActivity.this, SignUp.class);
        int_j_services  = new Intent(MainActivity.this, Services.class);

        dbHelper        = new DatabaseHelper(this);

        buttonSignInEventHandler();
        buttonSignUpEventHandler();
    }

    public void buttonSignInEventHandler()
    {
        btn_j_m_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typedUsername, typedPassword;
                typedUsername = et_j_m_username.getText().toString();
                typedPassword = et_j_m_password.getText().toString();

                User user = dbHelper.getUser(typedUsername);

                //Make sure the user entered a valid username and password
                if (user.getUsername().toString().equals(typedUsername) && user.getPassword().toString().equals(typedPassword))
                {
                    //Set the username to be stored in the LoginInfo
                    LoginInfo.setUsername(user.getUsername());

                    if (user.isHandyman())
                    {
                        //Go to the handyman's profile
                    }
                    else
                    {
                        startActivity(int_j_services);
                    }
                }
            }
        });
    }

    public void buttonSignUpEventHandler()
    {
        btn_j_m_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(int_j_signUp);
            }
        });
    }
}