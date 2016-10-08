package com.vitor.befree2.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConn {
    public static String getSetDataWeb(String endereco, String method, String data) throws IOException {
        String retorno = "";

        URL url = new URL(endereco);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);

        if (conn.getResponseCode()==200){
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            InputStreamReader r = new InputStreamReader(conn.getInputStream(),"UTF-8");
            StringWriter w = new StringWriter();
            int v = r.read();
            while (v != 1){
                w.write(v);
                v = r.read();
            }

            retorno = w.toString();
        }

        return(retorno);
    }
}
