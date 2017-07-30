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
        final Button Rlogin = (Button) findViewById(R.id.RIL);



        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            final String Username = etUsername.getText().toString();
            final String  Password= etPassword.getText().toString();
                final int Age = Integer.parseInt(etAge.getText().toString());
            final String Firstname = First.getText().toString();
            final String Lastname = Last.getText().toString();
            final String Gender = gender.getText().toString();

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        etUsername.setText("try");
                        if (success) {
                            etUsername.setText("send");
                            Intent intent = new Intent(Register.this, Login.class);
                            Register.this.startActivity(intent);
                        } else {
                            etUsername.setText("notsend");
                            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                            builder.setMessage("Register Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
            };

            RegisterRequest registerRequest = new RegisterRequest(Username,Password,Firstname,Lastname,Age,Gender, responseListener);
            RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
        }
    });
        Rlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Register.this, Login.class);
                Register.this.startActivity(registerIntent);
            }
        });
}
}


