package com.example.uaskmmizena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DaftarSiswa extends AppCompatActivity {
    RecyclerView mRecyclerViewS;
    RecyclerView.Adapter mAdapterS;
    RecyclerView.LayoutManager mManagerS;
    RequestQueue mRequestS;
    List<MSiswa> mSiswaList;

    String id_kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_siswa);
        id_kelas = getIntent().getStringExtra("id_kelas");

        mRecyclerViewS = (RecyclerView) findViewById(R.id.listKelas);
        mRequestS = Volley.newRequestQueue(getApplicationContext());
        mSiswaList = new ArrayList<>();
        Log.d("ID", String.valueOf(id_kelas));

        showlistSiswa();

        mManagerS = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerViewS.setLayoutManager(mManagerS);
        mAdapterS = new AdapterSiswaKelas(mSiswaList,DaftarSiswa.this);

        mRecyclerViewS.setAdapter(mAdapterS);
    }

    private void showlistSiswa() {
        String link = "http://192.168.43.195/uaskmmizena/tampilsiswa.php"+"?id_kelas="+id_kelas;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, link, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("JSONResponse", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                MSiswa mSiswa = new MSiswa();

                                String id_kelas= data.getString("id_kelas");
                                mSiswa.setnisn(data.getString("nisn"));
                                mSiswa.setnama_siswa(data.getString("nama_siswa"));
                                mSiswaList.add(mSiswa);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mAdapterS.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERRORRequest", "Error : " + error.getMessage());
                    }
                });
        mRequestS.add(request);
        Log.d("URL", request.toString());
    }
}