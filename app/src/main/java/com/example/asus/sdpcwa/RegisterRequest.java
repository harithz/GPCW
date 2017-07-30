package com.example.asus.sdpcwa;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus on 7/29/2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://cwweb.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String Username, String Password, String Firstname,String Lastname ,int Age,String Gender, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Username", Username);
        params.put("Password", Password);
        params.put("Firstname", Firstname);
        params.put("Lastname", Lastname);
        params.put("Age", Age+ "");
        params.put("Gender", Gender);
    }
    public Map<String, String> getParams() {
        return params;

}}
