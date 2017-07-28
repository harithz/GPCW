package com.example.asus.sdpcwa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Userprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        final EditText etAge = (EditText) findViewById(R.id.DA);
        final EditText First = (EditText) findViewById(R.id.DFN);
        final EditText Last = (EditText) findViewById(R.id.DLN);
        final EditText Gender = (EditText) findViewById(R.id.DG);

        final Button bRegister = (Button) findViewById(R.id.register);
    }
}
