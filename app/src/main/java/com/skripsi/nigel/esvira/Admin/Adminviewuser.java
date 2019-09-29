package com.skripsi.nigel.esvira.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Login;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Adminviewuser extends AppCompatActivity {

    ImageView imguser;
    TextView txt_nama;
    TextView txt_email;
    TextView txt_no;
    Button btn_hapus;
    private String API_URL = Server.URL + "user/delete";
    private String API_URL_detailuser = Server.URL + "user/detail";
    ProgressDialog pd;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminviewuser);
        final Context context = this.getApplicationContext();
        txt_nama = findViewById(R.id.txt_username);
        txt_email = findViewById(R.id.txt_notelp);
        txt_no = findViewById(R.id.txt_email);
        imguser = findViewById(R.id.userphoto);
        btn_hapus = findViewById(R.id.btn_hapus);
        pd = new ProgressDialog(Adminviewuser.this);
        SharedPreferences sharedpreferencesAdmnin = getSharedPreferences(Login.my_shared_preferences2, Context.MODE_PRIVATE);
        token = "Bearer "+sharedpreferencesAdmnin.getString("token", null);

        final Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            txt_nama.setText(mBundle.getString("nama"));
            imguser.setImageResource(mBundle.getInt("gambar"));
            txt_email.setText(mBundle.getString("email"));
            txt_no.setText(mBundle.getString("notelp"));
        }
        detailuser(String.valueOf(mBundle.getInt("id")) ,context);

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(String.valueOf(mBundle.getInt("id")), context);
            }
        });
    }

    private void detailuser(final String ID, final Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL_detailuser , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    txt_nama.setText(jsonObject.getString("name"));
                    txt_email.setText(jsonObject.getString("email"));
                    txt_no.setText(jsonObject.getString("nomor_telepon"));
                    showImage(Server.URLIMAGE+jsonObject.getString("foto"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context,"Error Bro",Toast.LENGTH_LONG ).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", token);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", ID);
                return params;
            }
        };

        Singleton.getInstance(context).addToRequestQueue(stringRequest);
    }



    public void showImage(String linkImage){
        ImageLoader imageLoader = Singleton.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                imguser.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }



    private void showDialog(final String ID, final Context context){
        pd.setMessage("Menghapus Data");
        pd.setCancelable(false);
        pd.show();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Hapus User yang dipilih?" );

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk hapus!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL , new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                pd.cancel();
                                Toast.makeText(getApplicationContext(), "Berhasil Hapus",
                                        Toast.LENGTH_SHORT).show();
                                Intent mIntent = new Intent(Adminviewuser.this, AdminHome.class);
                                startActivity(mIntent);


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                pd.cancel();
                                error.printStackTrace();
                            }
                        }){
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> headers = new HashMap<>();
                                headers.put("Accept", "application/json");
                                headers.put("Authorization", token);
                                return headers;
                            }

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> params = new HashMap<>();
                                params.put("id", ID);
                                return params;
                            }
                        };

                        Singleton.getInstance(context).addToRequestQueue(stringRequest);
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }




}
