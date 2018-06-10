package com.pharmacy.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by reale on 11/30/2016.
 */

public class HttpDataHandler {

    public HttpDataHandler() {

    }

    public String getHTTPData(String requestURL)
    {
        URL url;
        String response = "";
        try{
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpsURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = br.readLine()) != null)
                    response+=line;
            }
            else
                response = "";

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static float[] findCoordinates(String address) {
        address = "вулиця+"+address+"+Львів";
        String response = null;
        float[] coords = new float[]{0,0};
        try {
            HttpDataHandler http = new HttpDataHandler();
            String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s", address+"&key="+"AIzaSyCftyqnJnlbaJWqY34UHTge0AkTIbaBDIE");
            int i = 0;
            while (i<3){
                response = http.getHTTPData(url);
                if(response.contains("long_name"))
                    break;
                i++;
            }
        } catch (Exception ex) {

        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            String lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location").get("lat").toString();
            coords[0] = Float.valueOf(lat);
            String lng = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location").get("lng").toString();
            coords[1] = Float.valueOf(lng);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return coords;
    }
}
