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

public class DaftarKelas extends AppCompatActivity {
    RecyclerView mRecyclerViewS;
    RecyclerView.Adapter mAdapterS;
    RecyclerView.LayoutManager mManagerS;
    RequestQueue mRequestS;
    List<MKelas> mKelasList;

    String nip_guru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kelas);
        nip_guru = getIntent().getStringExtra("nip_guru");

        mRecyclerViewS = (RecyclerView) findViewById(R.id.listKelas);
        mRequestS = Volley.newRequestQueue(getApplicationContext());
        mKelasList = new ArrayList<>();
        Log.d("ID", String.valueOf(nip_guru));

        showlistKelas();

        mManagerS = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerViewS.setLayoutManager(mManagerS);
        mAdapterS = new AdapterKelasNipGuru(mKelasList,DaftarKelas.this);

        mRecyclerViewS.setAdapter(mAdapterS);
    }

    private void showlistKelas() {
        String link = "http://192.168.43.195/uaskmmizena/tampilkelas.php"+"?nip_guru="+nip_guru;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, link, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("JSONResponse", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                MKelas mKelas = new MKelas();

                                String nip_guru = data.getString("nip_guru");
                                mKelas.setid_kelas(data.getString("id_kelas"));
                                mKelas.setnama_kelas(data.getString("nama_kelas"));
                                mKelasList.add(mKelas);


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