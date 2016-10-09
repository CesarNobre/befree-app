package com.vitor.befree2.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vitor.befree2.R;

import java.util.ArrayList;

public class EmpresaAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Empresa> empresas;
    private int posicao;

    public EmpresaAdapter(Context context, ArrayList<Empresa> empresas) {
        this.context = context;
        this.empresas = empresas;
    }

    @Override
    public int getCount() {
        return empresas.size();
    }

    @Override
    public Object getItem(int i) {
        return empresas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Empresa empresa = empresas.get(i);

        Float spacing = 0.13f;

        posicao = i;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.rowlayout, null);
        Typeface customFont = Typeface.createFromAsset(context.getAssets(),"fonts/BebasNeue Book.ttf");

        TextView nome = (TextView) layout.findViewById(R.id.titulo);
        nome.setText(empresa.getNome());
        nome.setTypeface(customFont,Typeface.BOLD);
        nome.setLetterSpacing(spacing);

        TextView endereco = (TextView) layout.findViewById(R.id.subTitulo1);
        endereco.setText(empresa.getEndereco());
        endereco.setTypeface(customFont,Typeface.BOLD);
        endereco.setLetterSpacing(spacing);

        TextView descDistancia = (TextView) layout.findViewById(R.id.subTitulo2);
        descDistancia.setText(empresa.getDescDistancia());
        descDistancia.setTypeface(customFont,Typeface.BOLD);
        descDistancia.setLetterSpacing(spacing);

        if (descDistancia.getText().toString() == "VOCÊ ESTÁ NELE!"){
            descDistancia.setTextColor(Color.parseColor("#FF8C16"));
        }

        ImageView logo = (ImageView) layout.findViewById(R.id.logo);
        logo.setImageResource(empresa.getLogo(i));

        TextView favorito = (TextView) layout.findViewById(R.id.txtFavorito);

        if(empresa.getFavorito() == 1){
            favorito.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getResources().getDrawable(R.mipmap.ic_favorite_yes),null);
        }else{
            favorito.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getResources().getDrawable(R.mipmap.ic_favorite_no),null);
        }

        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView favorito = (TextView) v.findViewById(R.id.txtFavorito);

                if (empresas.get(posicao).getFavorito() == 0){
                    empresas.get(posicao).setFavorito(1);
                    favorito.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getResources().getDrawable(R.mipmap.ic_favorite_yes),null);
                }else{
                    empresas.get(posicao).setFavorito(0);
                    favorito.setCompoundDrawablesWithIntrinsicBounds(null,null,context.getResources().getDrawable(R.mipmap.ic_favorite_no),null);
                }
            }
        });

        return layout;
    }

}
