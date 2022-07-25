package com.example.uaskmmizena;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText nip_guru, pass;
    Button login, register;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nip_guru = (EditText) findViewById(R.id.nipLogin);
        pass = (EditText) findViewById(R.id.passwordLogin);
        login = (Button) findViewById(R.id.loginLog);
        register = (Button) findViewById(R.id.regisLog);

        progressDialog = new ProgressDialog(Login.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this, MainActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    public void loginlog(View view) {
        String sNip = nip_guru.getText().toString();
        String sPassword = pass.getText().toString();
        CheckLogin(sNip, sPassword);
    }

    public void CheckLogin(final String nip_guru, final String password){
        if(checkNetworkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if(resp.equals("[{\"status\":\"OK\"}]")){
                                    Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_LONG).show();
                                    Intent homeIntent = new Intent(Login.this, DaftarKelas.class);
                                    homeIntent.putExtra("nip_guru",nip_guru);
//                                    nip_guru = getIntent().getStringExtra("nip_guru");
                                    startActivity(homeIntent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nip_guru", nip_guru);
                    params.put("password", password);
                    return params;
                }
            };
            VolleyConnection.getInstance(Login.this).addToRequestQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        }else{
            Toast.makeText(getApplicationContext(), "Tidak Terkoneksi Internet", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}