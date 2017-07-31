package com.example.asus.sdpcwa;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus on 7/29/2017.
 */

public class SendChat extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://cwweb.000webhostapp.com/SendChat.php";
    private Map<String, String> params;

    public SendChat(String Username, String SUsername, String Message, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Username", Username);
        params.put("SUsername", SUsername);
        params.put("Message", Message);

    }
    public Map<String, String> getParams() {
        return params;

}}
