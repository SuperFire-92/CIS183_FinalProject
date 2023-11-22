package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    Button btn_j_su_back;
    Button btn_j_su_addAccount;

    Intent int_j_mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_j_su_back = findViewById(R.id.btn_v_su_back);
        btn_j_su_addAccount = findViewById(R.id.btn_v_su_signIn);

        int_j_mainActivity = new Intent(SignUp.this, MainActivity.class);

        buttonBackEventHandler();
    }

    public void buttonBackEventHandler()
    {
        btn_j_su_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(int_j_mainActivity);
            }
        });
    }
}