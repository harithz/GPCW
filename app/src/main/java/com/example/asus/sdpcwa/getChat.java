package com.example.asus.sdpcwa;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus on 7/29/2017.
 */

public class getChat extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://cwweb.000webhostapp.com/Getchat.php";
    private Map<String, String> params;

    public getChat(String Username, String SUsername, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Username", Username);
        params.put("SUsername", SUsername);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


