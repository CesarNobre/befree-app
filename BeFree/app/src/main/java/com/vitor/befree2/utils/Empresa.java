package com.vitor.befree2.utils;

import com.vitor.befree2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Empresa implements Serializable{

    private int id;
    private String nome;
    private String endereco;
    private String descDistancia;
    private String logo;
    private int favorito;

    public Empresa(){

    }

    public Empresa(String nome, String endereco,String descDistancia){
        this.nome = nome;
        this.endereco = endereco;
        this.descDistancia = descDistancia;
        this.favorito = 0;
    }

    public Empresa(JSONObject json){
        try {
            this.nome = json.getString("nomeShopping");
            JSONObject enderecoJson = json.getJSONObject("endereco");
            this.endereco = enderecoJson.getString("rua");
            this.favorito = json.getBoolean("favorito") == true ? 1 : 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescDistancia() {
        return descDistancia;
    }

    public void setDescDistancia(String descDistancia) {
        this.descDistancia = descDistancia;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public int getLogo(int position){
        switch (position){
            case 0:
                return(R.drawable.lg_shoppingd);
            case 1:
                return(R.drawable.lg_shoppingcenternorte);
            case 2:
                return(R.drawable.lg_shoppingboulevardtatuape);
            case 3:
                return(R.drawable.lg_shoppingbourbon);
            case 4:
                return(R.drawable.lg_shoppingeldorado);
            default:
                return 0;
        }
    }
}
