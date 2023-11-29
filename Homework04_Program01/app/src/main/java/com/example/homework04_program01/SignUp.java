package com.example.homework04_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Button btn_j_su_back;
    Button btn_j_su_addAccount;
    EditText et_j_su_name;
    EditText et_j_su_addressStreet;
    EditText et_j_su_addressCity;
    EditText et_j_su_addressState;
    EditText et_j_su_addressZip;
    EditText et_j_su_phone;
    EditText et_j_su_email;
    EditText et_j_su_password;
    CheckBox checkBox_j_su_isHandyman;

    Intent int_j_mainActivity;
    Intent int_j_services;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_j_su_back            = findViewById(R.id.btn_v_su_back);
        btn_j_su_addAccount      = findViewById(R.id.btn_v_su_signIn);
        et_j_su_name             = findViewById(R.id.et_v_su_name);
        et_j_su_addressStreet    = findViewById(R.id.et_v_su_address);
        et_j_su_addressCity      = findViewById(R.id.et_v_su_city);
        et_j_su_addressState     = findViewById(R.id.et_v_su_state);
        et_j_su_addressZip       = findViewById(R.id.et_v_su_zipcode);
        et_j_su_phone            = findViewById(R.id.et_v_su_phone);
        et_j_su_email            = findViewById(R.id.et_v_su_email);
        et_j_su_password         = findViewById(R.id.et_v_su_password);
        checkBox_j_su_isHandyman = findViewById(R.id.checkBox_v_su_isHandyman);

        int_j_mainActivity       = new Intent(SignUp.this, MainActivity.class);
        int_j_services           = new Intent(SignUp.this, Services.class);

        dbHelper = new DatabaseHelper(this);

        buttonBackEventHandler();
        signUpButtonEventHandler();
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

    public void signUpButtonEventHandler()
    {
        btn_j_su_addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Build a new user based on the given qualities
                User u = new User(
                        et_j_su_email.getText().toString(),
                        et_j_su_password.getText().toString(),
                        et_j_su_name.getText().toString(),
                        et_j_su_addressStreet.getText().toString() + " " + et_j_su_addressCity.getText().toString() + ", " + et_j_su_addressState.getText().toString() + ", " + et_j_su_addressZip.getEditableText().toString(),
                        et_j_su_phone.getText().toString(),
                        "",
                        checkBox_j_su_isHandyman.isChecked()
                );

                if (u.getEmail().equals("") || u.getPassword().equals("") || u.getName().equals("") || u.getAddress().equals("") || u.getPhoneNumber().equals(""))
                {
                    Toast.makeText(SignUp.this, "All Fields Must Be Filled", Toast.LENGTH_SHORT).show();
                }
                else if (!dbHelper.checkIfEmailFree(u))
                {
                    Toast.makeText(SignUp.this, "Email Is Taken, Choose A New One", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dbHelper.addUser(u);
                    LoginInfo.setUser(dbHelper.getUser(u.getEmail()));

                    if (dbHelper.getUser(u.getEmail()).isHandyman())
                    {
                        //Go to handyman profile
                    }
                    else
                    {
                        startActivity(int_j_services);
                    }
                }
            }
        });
    }
}