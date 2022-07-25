package com.example.uaskmmizena;
import android.os.AsyncTask;
import android.util.Log;

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

public class DbHelper extends AsyncTask<String, Void, String> {
    private int byGetOrPost = 0;

    public DbHelper(int flag) {
        //this.context = context;
        byGetOrPost = flag;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        String hasil = "";

        if (byGetOrPost == 1) {
            try {
                String surat = (String) arg0[0];
                String ayat = (String) arg0[1];
                String nisn = (String) arg0[2];
                String tanggal = (String) arg0[3];

                String link = "http://192.168.43.195/uaskmmizena/simpanhafalan.php";
                String data = URLEncoder.encode("surat", "UTF-8")+ "=" + URLEncoder.encode(surat, "UTF-8");
                data += "&" + URLEncoder.encode("ayat", "UTF-8")+ "=" + URLEncoder.encode(ayat, "UTF-8");
                data += "&" + URLEncoder.encode("nisn", "UTF-8")+ "=" + URLEncoder.encode(nisn, "UTF-8");
                data += "&" + URLEncoder.encode("tanggal", "UTF-8")+ "=" + URLEncoder.encode(tanggal, "UTF-8");

                URL url = new URL(link);
                URLConnection con = url.openConnection();
                con.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                Log.e("TAG", "doInBackground: " + url.toString());
                Log.e("TAG", "doInBackground: " + con.toString());

                wr.write(data);
                wr.flush();
                Log.e("TAG", "doInBackground: " + wr.toString());
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Log.e("TAG", "doInBackground: " + br.toString());

                StringBuilder builder = new StringBuilder();

                String line = "";
                while((line = br.readLine()) != null) {
                    builder.append(line);
                    break;
                }
                //br.close();
                hasil = builder.toString();
                return hasil;
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        } else if (byGetOrPost == 2) {
            try {
                String id_hafalan = (String) arg0[0];
                String surat = (String) arg0[1];
                String ayat = (String) arg0[2];
                String tanggal = (String) arg0[3];

                String link = "http://192.168.43.195/uaskmmizena/edithafalan.php";
                String data = URLEncoder.encode("id_hafalan", "UTF-8")+ "=" + URLEncoder.encode(id_hafalan, "UTF-8");
                data += "&" + URLEncoder.encode("surat", "UTF-8")+ "=" + URLEncoder.encode(surat, "UTF-8");
                data += "&" + URLEncoder.encode("ayat", "UTF-8")+ "=" + URLEncoder.encode(ayat, "UTF-8");
                data += "&" + URLEncoder.encode("tanggal", "UTF-8")+ "=" + URLEncoder.encode(tanggal, "UTF-8");

                URL url = new URL(link);
                URLConnection con = url.openConnection();
                con.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                Log.e("TAG", "doInBackground: " + url.toString());
                Log.e("TAG", "doInBackground: " + con.toString());

                wr.write(data);
                wr.flush();
                Log.e("TAG", "doInBackground: " + wr.toString());
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Log.e("TAG", "doInBackground: " + br.toString());

                StringBuilder builder = new StringBuilder();

                String line = "";
                while((line = br.readLine()) != null) {
                    builder.append(line);
                    break;
                }
                //br.close();
                hasil = builder.toString();
                return hasil;
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }
        else {
            try {
                String id_hafalan = (String)  arg0[0];

                String link = "http://192.168.43.195/uaskmmizena/hapushafalan.php";
                String data = URLEncoder.encode("id_hafalan", "UTF-8")+ "=" + URLEncoder.encode(id_hafalan, "UTF-8");

                URL url = new URL(link);
                URLConnection con = url.openConnection();
                con.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                Log.e("TAG", "doInBackground: " + url.toString());
                Log.e("TAG", "doInBackground: " + con.toString());

                wr.write(data);
                wr.flush();
                Log.e("TAG", "doInBackground: " + wr.toString());
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Log.e("TAG", "doInBackground: " + br.toString());

                StringBuilder builder = new StringBuilder();

                String line = "";
                while((line = br.readLine()) != null) {
                    builder.append(line);
                    break;
                }
                //br.close();
                hasil = builder.toString();
                return hasil;
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

    }
}

