package com.vitor.befree2.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ListView;

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

public class RequestMesaDisponivelTask extends AsyncTask<String,Void,String> {
    private Activity activity;

    public RequestMesaDisponivelTask(Activity activity){ this.activity = activity;}

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
        try {
            ArrayList list = new ArrayList();
            JSONObject shopping = new JSONObject(json);
            String disponivel = shopping.getString("ocupado");
            AtualizaLojas(disponivel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void AtualizaLojas(String disponivel){
        if(disponivel == "true"){
            ImageView image;
            image = (ImageView)this.activity.findViewById(R.id.imgMesas);
            image.setImageResource(R.mipmap.ic_facebook);
        } else {
            ImageView image;
            image = (ImageView)this.activity.findViewById(R.id.imgMesas);
            image.setImageResource(R.mipmap.ic_favorite_yes);
        }
    }
}
