package com.vitor.befree2.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vitor.befree2.R;

import java.util.ArrayList;

/**
 * Created by Vitor Ribeiro on 25/09/2016.
 */
public class LojaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Loja> lojas;
    private int posicao;

    public LojaAdapter(Context context, ArrayList<Loja> lojas) {
        this.context = context;
        this.lojas = lojas;
    }

    @Override
    public int getCount() {
        return lojas.size();
    }

    @Override
    public Object getItem(int i) {
        return lojas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Loja loja = lojas.get(i);

        Float spacing = 0.13f;

        posicao = i;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.rowlayout, null);
        Typeface customFont = Typeface.createFromAsset(context.getAssets(),"fonts/BebasNeue Book.ttf");

        TextView nome = (TextView) layout.findViewById(R.id.titulo);
        nome.setText(loja.getNome());
        nome.setTypeface(customFont,Typeface.BOLD);
        nome.setLetterSpacing(spacing);

        TextView avaliacao = (TextView) layout.findViewById(R.id.subTitulo1);
        avaliacao.setText(loja.getAvaliacao());
        avaliacao.setTypeface(customFont,Typeface.BOLD);
        avaliacao.setLetterSpacing(spacing);

        TextView descricao = (TextView) layout.findViewById(R.id.subTitulo2);
        descricao.setText(loja.getDescricao());
        descricao.setTypeface(customFont,Typeface.BOLD);
        descricao.setLetterSpacing(spacing);

        ImageView logo = (ImageView) layout.findViewById(R.id.logo);
        //logo.setImageResource(loja.getLogo(i));

        TextView favorito = (TextView) layout.findViewById(R.id.txtFavorito);
        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView favorito = (TextView) v.findViewById(R.id.txtFavorito);

                if (lojas.get(posicao).getFavorito() == 0){
                    lojas.get(posicao).setFavorito(1);
                    favorito.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getResources().getDrawable(R.mipmap.ic_favorite_yes),null);
                }else{
                    lojas.get(posicao).setFavorito(0);
                    favorito.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getResources().getDrawable(R.mipmap.ic_favorite_no),null);
                }
            }
        });

        return layout;
    }
}
