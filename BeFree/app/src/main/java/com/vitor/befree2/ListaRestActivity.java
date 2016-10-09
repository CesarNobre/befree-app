package com.vitor.befree2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.vitor.befree2.utils.Empresa;
import com.vitor.befree2.utils.Loja;
import com.vitor.befree2.utils.LojaAdapter;
import com.vitor.befree2.utils.RequestLojasTask;
import com.vitor.befree2.utils.RequestTask;

import java.util.ArrayList;

public class ListaRestActivity extends AppCompatActivity {

    ListView lista;
    LojaAdapter lojaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rest);

        Inicializa();
    }

    public void Inicializa(){
        Bundle extras = getIntent().getExtras();
        Empresa empresa = null;
        if (extras != null) {
            empresa = (Empresa)extras.getSerializable("empresa");
            //The key argument here must match that used in the other activity
        }

        ArrayList<Loja> lojas = new ArrayList<Loja>();

        String json = "";
        try {
            RequestLojasTask request = new RequestLojasTask(this);
            String nomeEmpresaFormatado = empresa.getNome().replaceAll(" ", "%20");
            String url = "http://testecesao.mybluemix.net/api/restaurante/"+ nomeEmpresaFormatado;
            request.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
