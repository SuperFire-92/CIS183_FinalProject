package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    EditText et_j_m_email;
    EditText et_j_m_password;

    Intent int_j_signUp;
    Intent int_j_services;
    Intent int_j_handymanProfile;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_j_m_signUp  = findViewById(R.id.btn_v_m_signUp);
        btn_j_m_signIn  = findViewById(R.id.btn_v_m_signIn);
        et_j_m_email = findViewById(R.id.et_v_m_email);
        et_j_m_password = findViewById(R.id.et_v_m_password);

        int_j_signUp    = new Intent(MainActivity.this, SignUp.class);
        int_j_services  = new Intent(MainActivity.this, Services.class);
        int_j_handymanProfile = new Intent(MainActivity.this, HandymanProfile.class);

        dbHelper        = new DatabaseHelper(this);

        buttonSignInEventHandler();
        buttonSignUpEventHandler();
    }

    public void buttonSignInEventHandler()
    {
        btn_j_m_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typedEmail, typedPassword;
                typedEmail = et_j_m_email.getText().toString();
                typedPassword = et_j_m_password.getText().toString();

                User user = dbHelper.getUser(typedEmail);

                if (user.getEmail() != null)
                {
                    //Make sure the user entered a valid username and password
                    if (user.getEmail().toString().equals(typedEmail) && user.getPassword().toString().equals(typedPassword))
                    {
                        //Set the username to be stored in the LoginInfo
                        LoginInfo.setUser(user);

                        if (user.isHandyman())
                        {
                            //Go to the handyman's profile
                            startActivity(int_j_handymanProfile);
                        }
                        else
                        {
                            startActivity(int_j_services);
                        }
                    }
                }
                else
                {
                    Log.d("Test","Test");
                    Toast.makeText(MainActivity.this, "Username or Password is Incorrect", Toast.LENGTH_SHORT).show();
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