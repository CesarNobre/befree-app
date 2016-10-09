package com.vitor.befree2.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.vitor.befree2.EmpActivity;
import com.vitor.befree2.ListaActivity;
import com.vitor.befree2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implementa uma requisição asincrona
 * ao backend para obter um JSON
 * Parametros:
 * 1. Tipo dados de entrada (String URL servico)
 * 2. Tipo dado andamento (Void ignorar)
 * 3. Tipo dado saída (String JSON)
 */
public class RequestTask extends AsyncTask<String,Void,String>{

    Activity activity;
    int opcao;
    ListView lista;
    EmpresaAdapter empresaAdapter;
    ProgressDialog progress;

    public RequestTask(Activity activity, int opcao) {
        this.activity = activity;
        this.opcao = opcao;
        progress = new ProgressDialog(activity);
        progress.setTitle("Carregando");
        progress.setMessage("Aguarde...");

    }

    protected void onPreExecute() {
        this.progress.show();
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
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonData = jsonArray.getJSONObject(i);
                Empresa empresa = new Empresa(jsonData);
                list.add(empresa);
            }
            AtualizaEmpresa(list);
            if(this.progress.isShowing()){
                this.progress.dismiss();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void AtualizaEmpresa(ArrayList list){

        this.empresaAdapter = new EmpresaAdapter(activity, list);
        lista = (ListView) activity.findViewById(R.id.listEstabelecimento);
        lista.setAdapter(empresaAdapter);
        lista.setOnItemClickListener(callEmpresa(activity));

    }

    public AdapterView.OnItemClickListener callEmpresa(final Context context){
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Bundle params = new Bundle();
                params.putSerializable("empresa", (Serializable) empresaAdapter.getItem(position));
                params.putInt("position",position);

                Intent intent;
                intent = new Intent(context, EmpActivity.class);
                intent.putExtras(params);
                activity.startActivity(intent);
            }
        });
    }
}
