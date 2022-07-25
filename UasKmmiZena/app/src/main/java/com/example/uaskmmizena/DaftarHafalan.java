package com.example.uaskmmizena;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.util.ArrayList;
import java.util.List;

public class DaftarHafalan extends AppCompatActivity {
    ImageView logout;
    RecyclerView mRecyclerViewS;
    RecyclerView.Adapter mAdapterS;
    RecyclerView.LayoutManager mManagerS;
    RequestQueue mRequestS;
    List<MHistory> mHistoryList;
    private SwipeRefreshLayout srlhistory;
    private ProgressBar pbhistory;

    TextView namasiswa;

    String nama_siswa, nisn, id_kelas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_hafalan);

        namasiswa = (TextView) findViewById(R.id.namasiswa);
        srlhistory = (SwipeRefreshLayout) findViewById(R.id.refreshhistory);
        pbhistory = (ProgressBar) findViewById(R.id.progressH);
        logout = (ImageView) findViewById(R.id.logout);

        nama_siswa = getIntent().getStringExtra("nama_siswa");
        nisn = getIntent().getStringExtra("nisn");
        id_kelas = getIntent().getStringExtra("id_kelas");

        namasiswa.setText(nama_siswa);

        mRecyclerViewS = (RecyclerView) findViewById(R.id.listHafalanTemp);
        mRequestS = Volley.newRequestQueue(getApplicationContext());
        mHistoryList = new ArrayList<>();

        showlistHistory();

        mManagerS = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerViewS.setLayoutManager(mManagerS);
        mAdapterS = new AdapterHistory(mHistoryList,DaftarHafalan.this);

        mRecyclerViewS.setAdapter(mAdapterS);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(DaftarHafalan.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        srlhistory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlhistory.setRefreshing(true);
                showlistHistory();
                srlhistory.setRefreshing(false);
            }
        });
    }

    private void showlistHistory() {
        pbhistory.setVisibility(View.VISIBLE);
        String link = "http://192.168.43.195/uaskmmizena/tampilhistory.php"+"?nisn="+nisn;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, link, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("JSONResponse", response.toString());
                        mHistoryList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                MHistory mHistory = new MHistory();
                                mHistory.setNamasiswa(nama_siswa);
                                mHistory.setNisn(data.getString("nisn"));
                                mHistory.setId_hafalan(data.getInt("id_hafalan"));
                                mHistory.setSurat(data.getString("surat"));
                                mHistory.setAyat(data.getString("ayat"));
                                mHistory.setTanggal(data.getString("tanggal"));
                                mHistoryList.add(mHistory);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mAdapterS.notifyDataSetChanged();
                        }
                        pbhistory.setVisibility(View.INVISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERRORRequest", "Error : " + error.getMessage());
                        pbhistory.setVisibility(View.INVISIBLE);
                    }
                });
        mRequestS.add(request);
        Log.d("URL", request.toString());
    }

    public void InputHafalan(View view){
        Intent intent = new Intent(DaftarHafalan.this, InputHafalan.class);
        intent.putExtra("nisnhistory", nisn);
        intent.putExtra("namasiswahistory", nama_siswa);
        startActivity(intent);
    }
}