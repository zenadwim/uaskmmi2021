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

public class MainActivity extends AppCompatActivity {

    EditText nip_guru, pass, conpass;
    Button login, register;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nip_guru = (EditText) findViewById(R.id.nipReg);
        pass = (EditText) findViewById(R.id.passwordReg);
        conpass = (EditText) findViewById(R.id.confpasswordReg);
        register = (Button) findViewById(R.id.regisReg);
        login = (Button) findViewById(R.id.loginReg);

        progressDialog = new ProgressDialog(MainActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, Login.class);
                startActivity(loginIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNip = nip_guru.getText().toString();
                String sPassword = pass.getText().toString();
                String sConPassword = conpass.getText().toString();

                if(sPassword.equals(sConPassword) && !sPassword.equals("")){
                    CreateDataToServer(sNip, sPassword);
                    Intent loginIntent = new Intent(MainActivity.this, Login.class);
                    startActivity(loginIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal! Password tidak cocok!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void CreateDataToServer (final String nip_guru, final String password){
        if(checkNetworkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if(resp.equals("[{\"status\":\"OK\"}]")){
                                    Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_LONG).show();
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
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("nip_guru", nip_guru);
                    params.put("password", password);
                    return params;
                }
            };

            VolleyConnection.getInstance(MainActivity.this).addToRequestQue(stringRequest);

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