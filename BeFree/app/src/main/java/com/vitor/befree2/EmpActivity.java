package com.vitor.befree2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vitor.befree2.utils.Empresa;
import com.vitor.befree2.utils.MesaAdapter;

import java.io.Serializable;

public class EmpActivity extends AppCompatActivity {

    Intent intent;

    Empresa empresa;
    int position;

    ImageView imgLogo;
    TextView txtNome;
    TextView txtPedido;
    TextView txtMesas;
    TextView txtPromocoes;
    TextView txtEstacionamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);

        Inicializa();
    }

    public void Inicializa(){
        Typeface customFont = Typeface.createFromAsset(this.getAssets(),"fonts/BebasNeue Book.ttf");
        Float spacing = 0.13f;

        imgLogo = (ImageView) findViewById(R.id.imgLogo);

        txtNome = (TextView) findViewById(R.id.txtNome);
        txtNome.setTypeface(customFont,Typeface.BOLD);
        txtNome.setLetterSpacing(spacing);

        txtPedido = (TextView) findViewById(R.id.txtPedido);
        txtPedido.setTypeface(customFont,Typeface.BOLD);
        txtPedido.setLetterSpacing(spacing);

        txtMesas = (TextView) findViewById(R.id.txtMesas);
        txtMesas.setTypeface(customFont,Typeface.BOLD);
        txtMesas.setLetterSpacing(spacing);

        txtPromocoes = (TextView) findViewById(R.id.txtPromocoes);
        txtPromocoes.setTypeface(customFont,Typeface.BOLD);
        txtPromocoes.setLetterSpacing(spacing);

        txtEstacionamento = (TextView) findViewById(R.id.txtEstacionamento);
        txtEstacionamento.setTypeface(customFont,Typeface.BOLD);
        txtEstacionamento.setLetterSpacing(spacing);

        intent = getIntent();

        if(intent != null){
            Bundle params = intent.getExtras();

            if(params != null){
                empresa = (Empresa) params.getSerializable("empresa");
                position = params.getInt("position");

                imgLogo.setImageResource(empresa.getLogo(position));
                txtNome.setText(empresa.getNome());
            }
        }
    }

    public void fPedido(View v){

        Bundle params = new Bundle();
        params.putSerializable("empresa", (Serializable) empresa);
        params.putInt("position",position);

        Intent intent;

        if(v.getId() == R.id.txtMesas || v.getId() == R.id.imgMesas){
            intent = new Intent(this, MesaActivity.class);
        } else{
            intent = new Intent(this, ListaRestActivity.class);
        }
        intent.putExtras(params);
        startActivity(intent);

    }

    public void fBuscar(View v){
        Intent intent = new Intent(this, MesaActivity.class);
//        intent.putExtras(params);
        startActivity(intent);
    }

    public void fPromocoes(View v){

    }

    public void fEstacionamento(View v){

    }
}
