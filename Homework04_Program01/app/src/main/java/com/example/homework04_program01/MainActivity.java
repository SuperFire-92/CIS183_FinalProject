package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    Intent int_j_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_j_m_signUp = findViewById(R.id.btn_v_m_signUp);
        btn_j_m_signIn = findViewById(R.id.btn_v_m_signIn);

        int_j_signUp   = new Intent(MainActivity.this, SignUp.class);


        buttonSignInEventHandler();
        buttonSignUpEventHandler();
    }

    public void buttonSignInEventHandler()
    {
        btn_j_m_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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