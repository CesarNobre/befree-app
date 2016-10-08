package com.vitor.befree2;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText editUsuario;
    EditText editSenha;
    TextView txtFacebook;
    TextView txtCadastrar;
    TextView txtEsqueceu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializa();
    }

    public void Inicializa(){
        Typeface customFont = Typeface.createFromAsset(this.getAssets(),"fonts/BebasNeue Book.ttf");
        Float spacing = 0.13f;

        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editUsuario.setTypeface(customFont,Typeface.BOLD);
        editUsuario.setLetterSpacing(spacing);

        editSenha = (EditText) findViewById(R.id.editSenha);
        editSenha.setTypeface(customFont,Typeface.BOLD);
        editSenha.setLetterSpacing(spacing);

        txtFacebook = (TextView) findViewById(R.id.txtFacebook);
        txtFacebook.setTypeface(customFont,Typeface.BOLD);
        txtFacebook.setLetterSpacing(spacing);

        txtCadastrar = (TextView) findViewById(R.id.txtCadastrar);
        txtCadastrar.setTypeface(customFont,Typeface.BOLD);
        txtCadastrar.setLetterSpacing(spacing);

        txtEsqueceu = (TextView) findViewById(R.id.txtEsqueceu);
        txtEsqueceu.setTypeface(customFont,Typeface.BOLD);
        txtEsqueceu.setLetterSpacing(spacing);
    }

    public void Login(View v){
        Intent intLista = new Intent(this,ListaActivity.class);
        startActivity(intLista);
    }
}
