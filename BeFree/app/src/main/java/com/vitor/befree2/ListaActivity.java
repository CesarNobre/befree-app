package com.vitor.befree2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vitor.befree2.utils.Empresa;
import com.vitor.befree2.utils.EmpresaAdapter;
import com.vitor.befree2.utils.JSONUtils;
import com.vitor.befree2.utils.RequestTask;
import com.vitor.befree2.utils.Util;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lista;
    EmpresaAdapter empresaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Inicializa();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Inicializa(){
        ArrayList<Empresa> empresas = new ArrayList<Empresa>();
        String json = "";
        try {
            RequestTask request = new RequestTask(this, 1);
            request.execute("https://testecesao.mybluemix.net/api/restaurante");
        } catch (Exception e) {
            e.printStackTrace();
        }

        AtualizaLista(empresas);
    }



    public void AtualizaLista(ArrayList list){

        //list.add(new Empresa("SHOPPING D","AVENIDA CRUZEIRO DO SUL, 1100 - CANINDÉ","VOCÊ ESTÁ NELE!"));
        //list.add(new Empresa("SHOPPING CENTER NORTE","TRAVESSA CASALBUONO, 120 - VILA GUILHERME","VOCÊ ESTÁ A 3,9 KM"));
        //list.add(new Empresa("SHOPPING BOULEVARD TATUAPE","RUA GONÇALVES CRESPO, 78 - TATUAPÉ","VOCÊ ESTÁ A 7,9 KM"));
        //list.add(new Empresa("SHOPPING BOURBON","RUA PALESTRA ITÁLIA, 500 - BARRA FUNDA","VOCÊ ESTÁ A 8,2 KM"));
        //list.add(new Empresa("SHOPPING ELDORADO","AVENIDA REBOUÇAS, 3970 - PINHEIROS","VOCÊ ESTÁ A 15,3 KM"));

        empresaAdapter = new EmpresaAdapter(this,list);

        lista = (ListView) findViewById(R.id.listEstabelecimento);
        lista.setAdapter(empresaAdapter);
        lista.setOnItemClickListener(callEmpresa(this));
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
                startActivity(intent);
            }
        });
    }

}
