package com.example.asus.sdpcwa;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray ;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;
public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
         Intent intent = getIntent();

        final String username = intent.getStringExtra("username");
        final String password = intent.getStringExtra("password");
        final  String Firstname = intent.getStringExtra("Firstname");
        final String Lastname = intent.getStringExtra("Lastname");
        final String age = intent.getStringExtra("age");
        final  String Gender = intent.getStringExtra("Gender");
        String[] textes = new String[] {

        };

        final List<String> Mesagelist = new ArrayList<String>(Arrays.asList(textes));

        final EditText Resiver = (EditText) findViewById(R.id.RN);
        final EditText Message = (EditText) findViewById(R.id.Mess);
        final ListView List = (ListView) findViewById(R.id.ChatL);
        final Button bSelect = (Button) findViewById(R.id.SLR);
        final Button bSend = (Button) findViewById(R.id.Send);
        final Button Back = (Button) findViewById(R.id.BackC);
       final ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Mesagelist);
        List.setAdapter(myarrayAdapter);
        List.setTextFilterEnabled(true);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(Chat.this, Userprofile.class);

                returnIntent.putExtra("username", username);
                returnIntent.putExtra("age", age);
                returnIntent.putExtra("password", password);
                returnIntent.putExtra("Firstname", Firstname);
                returnIntent.putExtra("Lastname", Lastname);
                returnIntent.putExtra("Gender", Gender);
                Chat.this.startActivity(returnIntent);
            }




        });

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String RUsername = Resiver.getText().toString();
                final String  SMessage= Message.getText().toString();




                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                Mesagelist.add(username+" "+SMessage);
                                myarrayAdapter.notifyDataSetChanged();
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(Chat.this);
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

                SendChat SendChatr = new SendChat(username,RUsername,SMessage,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Chat.this);
                queue.add(SendChatr);
            }
        });
        bSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String RUsername = Resiver.getText().toString();
                Mesagelist.clear();
                myarrayAdapter.notifyDataSetChanged();
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray  jsonResponse = new JSONArray (response);
                            boolean success = jsonResponse.getJSONObject(0).getBoolean("success");


                            if (success) {
                                for (int x=1;x<jsonResponse.length();x++) {
                                    Mesagelist.add(jsonResponse.getJSONObject(x).getString("Sender")+" "+jsonResponse.getJSONObject(x).getString("Message"));
                                }

                                        myarrayAdapter.notifyDataSetChanged();



                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(Chat.this);
                                builder.setMessage("no messages")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                };

                getChat GetChat = new getChat(username, RUsername, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Chat.this);
                queue.add(GetChat);
            }
        });
    }

}
