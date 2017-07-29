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

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etUsername = (EditText) findViewById(R.id.Iusername);
        final EditText etPassword = (EditText) findViewById(R.id.IPass);
        final Button bLogin = (Button) findViewById(R.id.login);
        final Button bmoveregister = (Button) findViewById(R.id.moveregister);

        bmoveregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                Login.this.startActivity(registerIntent);
            }




        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            bmoveregister.setText("try");
                            if (success) {
                                bmoveregister.setText("ok");
                                String username = jsonResponse.getString("username");
                                String password = jsonResponse.getString("password");
                                String Firstname = jsonResponse.getString("Firstname");
                                String Lastname = jsonResponse.getString("Lastname");
                                String age = jsonResponse.getString("age");
                                String Gender = jsonResponse.getString("Gender");


                                Intent intent = new Intent(Login.this, Userprofile.class);
                                intent.putExtra("username", username);
                                intent.putExtra("age", age);
                                intent.putExtra("password", password);
                                intent.putExtra("Firstname", Firstname);
                                intent.putExtra("Lastname", Lastname);
                                intent.putExtra("Gender", Gender);

                                Login.this.startActivity(intent);
                            } else {
                                bmoveregister.setText("no");
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            bmoveregister.setText("exp");
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });
    }
}




