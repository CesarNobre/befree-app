package com.vitor.befree2.utils;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cesar on 09/10/2016.
 */

public class RequestFavoritoTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        String json = "";

        try {

            URL url = new URL(params[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("POST");
            if (c.getResponseCode() == 200) { //OK!!!!
                json = Util.toString(c.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;

    }
}
