package com.skripsi.nigel.esvira;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Detailbuku extends AppCompatActivity {

    int ID,harga,id_user;
    private String API_URL_detailbukuser = Server.URL + "buku/detailbuku";
    private String API_KERANJANG = Server.URL + "cart/carthold";
    TextView Tvjudul,Tvpengarang,Tvharga,Tvkategori,Tvukuranbuku,Tvkulit,Tvthnterbit,Tvjmlhal,Tvbhnisi,Tvbhnkulit,Tvdeskripsi,Tvstok,Tvhalisi;
    ImageView imgbuku;
    Button btn_tambah;
    public static final String my_shared_preferences = "my_shared_preferences";
    public final static String TAG_ID = "id";
    SharedPreferences sharedpreferences;
    public static final String session_status = "session_status";
    Boolean session = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailbuku);


        Tvjudul = (TextView)findViewById(R.id.judulBuku);
        Tvpengarang = (TextView)findViewById(R.id.pengarang_buku);
        Tvharga = (TextView)findViewById(R.id.harga_buku);
        Tvkategori = (TextView)findViewById(R.id.kategori);
        Tvukuranbuku = (TextView)findViewById(R.id.uk_buku);
        Tvkulit = (TextView)findViewById(R.id.kulit);
        Tvthnterbit = (TextView)findViewById(R.id.thn_terbit);
        Tvjmlhal = (TextView)findViewById(R.id.jml_hal);
        Tvbhnisi = (TextView)findViewById(R.id.bahan_isi);
        Tvbhnkulit = (TextView)findViewById(R.id.bahan_kulit);
        Tvstok = (TextView)findViewById(R.id.stok);
        Tvdeskripsi = (TextView)findViewById(R.id.deskripsi_buku);
        Tvhalisi = (TextView)findViewById(R.id.halisi);
        imgbuku = (ImageView) findViewById(R.id.imageview);
        btn_tambah = (Button) findViewById(R.id.button_tambah);
        ID = getIntent().getIntExtra("id",0);
        harga = getIntent().getIntExtra("harga",0);
        sharedpreferences = this.getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id_user = sharedpreferences.getInt(TAG_ID, 0);
        session = sharedpreferences.getBoolean(session_status, false);

        detailbuku();

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!session) {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    Toast.makeText(getApplicationContext(), "Anda Harus Login Terlebih Dahulu", Toast.LENGTH_LONG).show();

                } else {
                    tambah_keranjang();

                }
            }
        });


    }


    private void detailbuku() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL_detailbukuser , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getString("jumlah").equals("0")) {
                            btn_tambah.setVisibility(View.GONE);
                        }
//                        if (jsonObject.getString("jumlah").equals(jsonObject.getString("jumlah_buku"))) {
//                            btn_tambah.setVisibility(View.GONE);
//                        }
                        Tvjudul.setText(jsonObject.getString("nama"));
                        Tvpengarang.setText(jsonObject.getString("penulis"));
                        showImage(Server.URLIMAGE + jsonObject.getString("foto"));
                        Tvharga.setText("Rp. " + jsonObject.getString("harga") + ",-");
                        Tvkategori.setText("Kategori : " + jsonObject.getString("kategori"));
                        Tvukuranbuku.setText("Ukuran Buku : " + jsonObject.getString("ukuranbuku"));
                        Tvkulit.setText("Kulit : " + jsonObject.getString("kulit"));
                        Tvthnterbit.setText("Tahun Terbit : " + jsonObject.getString("tahunterbit"));
                        Tvjmlhal.setText("Jumlah Halaman : " + jsonObject.getString("jmlhalaman"));
                        Tvbhnkulit.setText("Bahan Kulit : " + jsonObject.getString("bahankulit"));
                        Tvbhnisi.setText("Bahan Isi : " + jsonObject.getString("bahanisi"));
                        Tvstok.setText("Stok : " + jsonObject.getString("jumlah"));
                        Tvhalisi.setText("Halaman Isi : " + jsonObject.getString("halisi"));
                        Tvdeskripsi.setText(jsonObject.getString("deskripsi"));

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error Bro",Toast.LENGTH_LONG ).show();
            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(ID));
                return params;
            }
        };

        Singleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


    public void showImage(String linkImage){
        ImageLoader imageLoader = Singleton.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                imgbuku.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }


    private void tambah_keranjang(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,API_KERANJANG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    startActivity(new Intent(getApplicationContext(), Main.class));
                    Toast.makeText(getApplicationContext(),"Buku Berhasil Ditambahkan ke Keranjang",Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if(error!=null && error.getMessage() !=null){
                    Toast.makeText(getApplicationContext(),"error VOLLEY "+error.getMessage(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error gan",Toast.LENGTH_LONG).show();

                }
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", String.valueOf(id_user));
                params.put("buku_id",String.valueOf(ID));
                params.put("jumlah_buku","1");
                params.put("total_harga",String.valueOf(harga));

                return params;
            }

        };

        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }





}
