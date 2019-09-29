package com.skripsi.nigel.esvira;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Adpater.OngkirAdapter;
import com.skripsi.nigel.esvira.Controller.AppController;
import com.skripsi.nigel.esvira.Model.Ongkir;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    ProgressDialog pDialog;
    Button btn_register, btn_login;
    EditText txt_username, txt_email, txt_notelp, txt_password, txt_confirm_password,txt_alamat;
    Intent intent;
    ConnectivityManager conMgr;

    private String API_URL = Server.URL + "register";
    private static final String TAG = Register.class.getSimpleName();

//    private static final String TAG_SUCCESS = "success";
//    private static final String TAG_MESSAGE = "message";
//    int success;

    String tag_json_obj = "json_obj_req";
    private String API_ONGKIR = Server.URL + "buku/ongkoskirim";
    AutoCompleteTextView auto_kota;
    OngkirAdapter adapterongkir;
    ArrayList<Ongkir> listkota = new ArrayList<Ongkir>();
    int id_kota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        auto_kota = (AutoCompleteTextView) findViewById(R.id.txt_kota);
        btn_register = (Button) findViewById(R.id.btn_register);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_notelp = (EditText) findViewById(R.id.txt_notelp);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_alamat = (EditText) findViewById(R.id.txt_alamat);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_confirm_password = (EditText) findViewById(R.id.txt_confirm_password);
        pDialog = new ProgressDialog(Register.this);


        auto_kota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                id_kota = listkota.get(position).getId();
//                Toast.makeText(getApplicationContext(), "tes"+id_kota,
//                        Toast.LENGTH_SHORT).show();

            }
        });


        calldata();

        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username = txt_username.getText().toString();
                String notelp = txt_notelp.getText().toString();
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                String confirm_password = txt_confirm_password.getText().toString();
                String alamat = txt_alamat.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegister(username, notelp, email,alamat ,password, confirm_password);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void calldata() {
        listkota.clear();

        JsonArrayRequest jArr = new JsonArrayRequest(API_ONGKIR,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Ongkir item = new Ongkir();
                                item.setId(obj.getInt("id"));
                                item.setKota(obj.getString("kota"));
                                item.setHarga_ongkir(obj.getInt("harga_ongkir"));
                                listkota.add(item);
                                adapterongkir = new OngkirAdapter(Register.this,R.layout.list_ongkir ,listkota);
                                auto_kota.setAdapter(adapterongkir);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterongkir.notifyDataSetChanged();


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        Singleton.getInstance(this).addToRequestQueue(jArr);
    }



    private void checkRegister(final String username, final String notelp, final String email, final String alamat , final String password, final String confirm_password) {
        pDialog.setMessage("Register");
        pDialog.setCancelable(false);
        pDialog.show();
        StringRequest strReq = new StringRequest(Request.Method.POST, API_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try{
                    JSONObject jsonObject = new JSONObject(json);
                    pDialog.cancel();
                    if(jsonObject.getString("status").equals("failed")){
                        if(jsonObject.getString("field").equals("email")){
                            Toast.makeText(getApplicationContext(), "Email yang dimasukkan tidak sesuai format atau telah digunakan oleh user yang lain (contoh: abc@abc.com)",
                                    Toast.LENGTH_SHORT).show();
                        }else if(jsonObject.getString("field").equals("password")){
                            Toast.makeText(getApplicationContext(), "Konfirmasi password tidak sesuai dengan password yang telah dimasukkan sebelumnya",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Email yang dimasukkan tidak sesuai format atau telah digunakan oleh user yang lain (contoh: abc@abc.com)",
                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Konfirmasi password tidak sesuai dengan password yang telah dimasukkan sebelumnya",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Register Berhasil Silahkan Login",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.e(TAG, "Register Response: " + response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
                Log.e(TAG, "Register Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();



            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", username);
                params.put("email", email);
                params.put("nomor_telepon", notelp);
                params.put("alamat_user", alamat);
                params.put("id_kota", String.valueOf(id_kota));
                params.put("password", password);
                params.put("password_confirmation", confirm_password);
                params.put("tipe", "user");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(Register.this, Login.class);
        finish();
        startActivity(intent);
    }
}