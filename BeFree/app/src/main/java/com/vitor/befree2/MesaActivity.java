package com.vitor.befree2;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vitor.befree2.utils.Empresa;
import com.vitor.befree2.utils.EmpresaAdapter;
import com.vitor.befree2.utils.MesaAdapter;
import com.vitor.befree2.utils.RequestMesaDisponivelTask;
import com.vitor.befree2.utils.RequestTask;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by cesar on 08/10/2016.
 */

public class MesaActivity extends AppCompatActivity {
    ListView lista;
    MesaAdapter mesaAdapter;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);

        timerHandler.postDelayed(timerRunnable, 5000);
        startTime = System.currentTimeMillis();

        Inicializa();
    }
    public void Inicializa(){
        try {
            RequestMesaDisponivelTask request = new RequestMesaDisponivelTask(this);
            request.execute("https://testecesao.mybluemix.net/api/mesa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            Inicializa();

            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerHandler.postDelayed(this, 5000);
        }
    };


    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }



}
