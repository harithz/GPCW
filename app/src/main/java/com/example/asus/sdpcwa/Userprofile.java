package com.example.asus.sdpcwa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Userprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);



        Intent intent = getIntent();
        final  String name = intent.getStringExtra("name");
        final String username = intent.getStringExtra("username");
        final String password = intent.getStringExtra("password");
        final  String Firstname = intent.getStringExtra("Firstname");
        final String Lastname = intent.getStringExtra("Lastname");
        final String age = intent.getStringExtra("age");
        final  String Gender = intent.getStringExtra("Gender");

        final EditText etAge = (EditText) findViewById(R.id.DA);
        final EditText First = (EditText) findViewById(R.id.DFN);
        final EditText Last = (EditText) findViewById(R.id.DLN);
        final EditText tGender = (EditText) findViewById(R.id.DG);
        final Button bChat = (Button) findViewById(R.id.chat);
        final Button bRegister = (Button) findViewById(R.id.register);


        etAge.setText(age);
        First.setText(Firstname);
        Last.setText(Lastname);
        tGender.setText(Gender);
        bChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatIntent = new Intent(Userprofile.this, Chat.class);
                Userprofile.this.startActivity(ChatIntent);
                ChatIntent.putExtra("username", name);
                ChatIntent.putExtra("age", username);
                ChatIntent.putExtra("password", password);
                ChatIntent.putExtra("Firstname", Firstname);
                ChatIntent.putExtra("Lastname", Lastname);
                ChatIntent.putExtra("Gender", Gender);

            }
        });
    }
}
