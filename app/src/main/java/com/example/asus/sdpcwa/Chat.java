package com.example.asus.sdpcwa;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        String[] fruits = new String[] {
                "Cape Gooseberry",
                "Capuli cherry"
        };

        // Create a List from String Array elements
        final List<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));

        final EditText Resiver = (EditText) findViewById(R.id.EUN);
        final EditText Message = (EditText) findViewById(R.id.Mess);
        final ListView List = (ListView) findViewById(R.id.ChatL);
        final Button bSelect = (Button) findViewById(R.id.SLR);
        final Button bSend = (Button) findViewById(R.id.Send);

        ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fruits_list);
        List.setAdapter(myarrayAdapter);
        List.setTextFilterEnabled(true);

    }

}
