package com.vitor.befree2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.vitor.befree2.utils.LojaAdapter;

public class ListaPedidoActivity extends AppCompatActivity {

    ListView lista;
    LojaAdapter lojaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);
    }
}
