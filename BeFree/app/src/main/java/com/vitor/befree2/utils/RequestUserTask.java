package com.vitor.befree2.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.vitor.befree2.ListaActivity;
import com.vitor.befree2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by cesar on 09/10/2016.
 */

public class RequestUserTask  extends AsyncTask<String, Void, String>{

    private final Activity activity;

    public RequestUserTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        String json = "";
        try {
            URL url = new URL(params[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            if (c.getResponseCode() == 200) { //OK!!!!
                json = Util.toString(c.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    protected void onPostExecute(String json) {
        if(json == ""){
            Toast.makeText(activity, "usuário ou senha errado", Toast.LENGTH_LONG).show();
        } else {
            Intent intLista = new Intent(activity, ListaActivity.class);
            activity.startActivity(intLista);
        }
    }
}
