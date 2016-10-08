package com.vitor.befree2.utils;

import com.vitor.befree2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Vitor Ribeiro on 25/09/2016.
 */
public class Loja implements Serializable{

    private int id;
    private String nome;
    private String avaliacao;
    private String descricao;
    private int favorito;

    public Loja(String nome, String avaliacao,String descricao){
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.descricao = descricao;
        this.favorito = 0;
    }

    public Loja(JSONObject json){
        try {
            this.nome = json.getString("nome");
            this.avaliacao = json.getString("avaliacao");
            this.descricao = json.getString("descricao");
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

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
                return(R.drawable.lg_mcdonalds);
            case 1:
                return(R.drawable.lg_subway);
            case 2:
                return(R.drawable.lg_bobs);
            case 3:
                return(R.drawable.lg_giraffas);
            case 4:
                return(R.drawable.lg_gendai);
            case 5:
                return(R.drawable.lg_spoleto);
            case 6:
                return(R.drawable.lg_divinofogao);
            default:
                return 0;
        }
    }
}
