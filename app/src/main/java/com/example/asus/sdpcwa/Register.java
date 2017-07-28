package com.example.asus.sdpcwa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etUsername = (EditText) findViewById(R.id.EUN);
        final EditText etPassword = (EditText) findViewById(R.id.EPW);
        final EditText etAge = (EditText) findViewById(R.id.EA);
        final EditText First = (EditText) findViewById(R.id.EFN);
        final EditText Last = (EditText) findViewById(R.id.ELN);
        final EditText gender = (EditText) findViewById(R.id.EG);
        final Button bRegister = (Button) findViewById(R.id.register);
    }
}
