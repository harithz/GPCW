package com.example.asus.sdpcwa;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Userprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);



        Intent intent = getIntent();

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
        final Button bedit = (Button) findViewById(R.id.ES);
        final Button bDelete = (Button) findViewById(R.id.Del);
        final Button bOUt = (Button) findViewById(R.id.Log);
        final EditText bUN = (EditText) findViewById(R.id.DUN);

        etAge.setText(age);
        First.setText(Firstname);
        Last.setText(Lastname);
        tGender.setText(Gender);
        bUN.setText(username);
        bChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatIntent = new Intent(Userprofile.this, Chat.class);

                ChatIntent.putExtra("username", username);
                ChatIntent.putExtra("age", age);
                ChatIntent.putExtra("password", password);
                ChatIntent.putExtra("Firstname", Firstname);
                ChatIntent.putExtra("Lastname", Lastname);
                ChatIntent.putExtra("Gender", Gender);
                Userprofile.this.startActivity(ChatIntent);
            }
        });
        bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String NFirstname = First.getText().toString();
                final String NLastname =  Last.getText().toString();
                final int Nage =  Integer.parseInt(etAge.getText().toString());
                final  String NGender =  tGender.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {




                                Intent Refreshintent = new Intent(Userprofile.this, Userprofile.class);
                                Refreshintent.putExtra("username", username);
                                Refreshintent.putExtra("age", Nage+" ");
                                Refreshintent.putExtra("password", password);
                                Refreshintent.putExtra("Firstname", NFirstname);
                                Refreshintent.putExtra("Lastname", NLastname);
                                Refreshintent.putExtra("Gender", NGender);
                                finish();
                                Userprofile.this.startActivity(Refreshintent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));


                            } else {
                                bedit.setText("no");
                                AlertDialog.Builder builder = new AlertDialog.Builder(Userprofile.this);
                                builder.setMessage("update Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                };

                Updaterequst updaterequst = new Updaterequst(username,password,NFirstname,NLastname,Nage,NGender, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Userprofile.this);
                queue.add(updaterequst);
            }
        });
        bOUt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LogoutIntent = new Intent(Userprofile.this, Login.class);


                Userprofile.this.startActivity(LogoutIntent);
            }
        });
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                Intent intent = new Intent(Userprofile.this, Login.class);
                                Userprofile.this.startActivity(intent);
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(Userprofile.this);
                                builder.setMessage("delet Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                };

                Deleterequst deleterequst = new Deleterequst(username, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Userprofile.this);
                queue.add(deleterequst);
            }
        });


    }
}
