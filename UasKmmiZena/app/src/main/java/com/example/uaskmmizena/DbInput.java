package com.example.uaskmmizena;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DbInput extends AsyncTask<String, Void, String>{
    private EditText ayat;
    private int byGetOrPost = 0;

    public DbInput(EditText ayatField, int flag){
        this.ayat = ayatField;
        byGetOrPost = flag;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        String hasil = "";

        if (byGetOrPost == 0) {
            //Get method
            try {
                String surat = (String)arg0[0];
                String link = "http://192.168.43.195/uaskmmizena/tampilayat.php";
                String data = URLEncoder.encode("surat", "UTF-8")+ "=" + URLEncoder.encode(surat, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line=null;
                while((line = reader.readLine()) != null){
                    sb.append(line);
                    break;
                }
                hasil = sb.toString();
                return hasil;
            } catch (Exception e){
                return new String("Exception: " + e.getMessage());
            }
        } else {
            try {
                String totalpoin = (String)arg0[0];
                //"http://localhost/serverGet.php?username="+username+"&password="+password;
                String link = "http://192.168.43.195/uaskmmizena/tampiltotal.php";
                String data = URLEncoder.encode("totalpoin", "UTF-8")+ "=" + URLEncoder.encode(totalpoin, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line=null;
                while((line = reader.readLine()) != null){
                    sb.append(line);
                    break;
                }
                hasil = sb.toString();
                return hasil;
            } catch (Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }

    @Override
    protected void onPostExecute(String result){
        this.ayat.setText(result);
    }

}
