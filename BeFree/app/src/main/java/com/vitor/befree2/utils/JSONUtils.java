package com.vitor.befree2.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Vitor Ribeiro on 05/10/2016.
 */
public class JSONUtils {

    public static ArrayList getListJSON(String json,int opcao) throws JSONException {

        ArrayList<Object> list = new ArrayList<Object>();

        Empresa empresa = new Empresa();

        JSONObject jsonO = new JSONObject();
        JSONArray jsonA = new JSONArray(json);

        for (int i = 0; i < jsonA.length(); i++){
            if (opcao==1){
                list.add(getEmpresaJSON(jsonA.getJSONObject(i)));
            }
        }

        return list;
    }

    public static Empresa getEmpresaJSON(JSONObject json) throws JSONException {
        Empresa empresa = new Empresa();

        empresa.setId(json.getInt("ID"));
        empresa.setNome(json.getString("NOME"));
        empresa.setEndereco(json.getString("ENDERECO"));
        empresa.setDescDistancia(json.getString("DISTANCIA"));
        empresa.setLogo(json.getString("LOGO"));
        empresa.setFavorito(json.getInt("FAVORITO"));

        return empresa;
    }


}
