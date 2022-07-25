package com.example.uaskmmizena;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class InputHafalan extends AppCompatActivity {
    TextView nama;
    EditText ayat, tanggal, Spinnersurat;
    Button input, edit;
    String namasiswa, surat, ayatget;

    ArrayList<String> suratlist = new ArrayList<>();
    ArrayAdapter<String> suratAdapter;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_hafalan);

        nama = (TextView) findViewById(R.id.namasiswa);
        Spinnersurat = (EditText) findViewById(R.id.spinnersurat);
        ayat = (EditText) findViewById(R.id.namaayat);
        tanggal = (EditText) findViewById(R.id.tanggal);
        input = (Button) findViewById(R.id.Input);
        edit = (Button) findViewById(R.id.Edit);

        requestQueue = Volley.newRequestQueue(this);

        Spinnersurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(InputHafalan.this);

                dialog.setContentView(R.layout.dialog_search_spinner);

                dialog.getWindow().setLayout(1000,900);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                EditText editText = dialog.findViewById(R.id.carisurat);
                ListView listView = dialog.findViewById(R.id.listsurat);

                String url = "http://192.168.43.195/uaskmmizena/spinnerhafalan.php";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("surat");
                            suratlist.clear();
                            for (int i=0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String nama_surat = jsonObject.optString("nama_surat");
                                suratlist.add(nama_surat);
                                suratAdapter = new ArrayAdapter<>(InputHafalan.this,
                                        android.R.layout.simple_spinner_item, suratlist);
                                listView.setAdapter(suratAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObjectRequest);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        suratAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Spinnersurat.setText(suratAdapter.getItem(position));
                        dialog.dismiss();
                        surat = Spinnersurat.getText().toString();
//                        new DbInput(ayat, 0).execute(surat);
                    }
                });
            }
        });
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String tgl = sdf1.format(c1.getTime());

        namasiswa = getIntent().getStringExtra("namasiswahistory");
        if (namasiswa!=null){
            nama.setText(namasiswa);
            tanggal.setText(tgl);
            edit.setVisibility(View.GONE);
        } else {
            nama.setText(getIntent().getStringExtra("nama_siswa"));
            tanggal.setText(getIntent().getStringExtra("tanggal"));
            Spinnersurat.setText(getIntent().getStringExtra("surat"));
            ayat.setText(getIntent().getStringExtra("ayat"));
            input.setVisibility(View.GONE);
        }
    }

    public void Input(View view) {
        String tgl = tanggal.getText().toString();
        String surat = Spinnersurat.getText().toString();
        String nama = getIntent().getStringExtra("namasiswahistory");
        String nisn = getIntent().getStringExtra("nisnhistory");
        String ayatinp = ayat.getText().toString();


        if (!Spinnersurat.equals("")&&!ayat.equals("")){
            new DbHelper(1).execute(surat,ayatinp,nisn,tgl);
            finish();
        }else {
            Toast.makeText(this, "Data masih kosong !!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Edit(View view) {
        String id = String.valueOf(getIntent().getIntExtra("id_hafalan",0));
        Log.d("ID_HAFALAN", String.valueOf(getIntent().getIntExtra("id_hafalan",0)));
        String tgl = tanggal.getText().toString();
        String surat = Spinnersurat.getText().toString();
        String ayatinp = ayat.getText().toString();

        new DbHelper(2).execute(id,surat,ayatinp,tgl);
        finish();
    }
}