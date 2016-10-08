package com.vitor.befree2.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;

import com.vitor.befree2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by cesar on 08/10/2016.
 */

public class RequestLojasTask extends AsyncTask<String,Void,String> {
    private Activity activity;
    LojaAdapter lojaAdapter;
    ListView lista;

    public RequestLojasTask(Activity activity) {
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
        try {
            ArrayList list = new ArrayList();
            JSONObject shopping = new JSONObject(json);
            JSONArray jsonArray = shopping.getJSONArray("restaurantes");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonData = jsonArray.getJSONObject(i);
                Loja empresa = new Loja(jsonData);
                list.add(empresa);
            }
            AtualizaLojas(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void AtualizaLojas(ArrayList list){
        this.lojaAdapter = new LojaAdapter(activity, list);

        lista = (ListView) activity.findViewById(R.id.listLojas);
        lista.setAdapter(lojaAdapter);
//        lista.setOnItemClickListener(callEmpresa(activity));

    }

}
