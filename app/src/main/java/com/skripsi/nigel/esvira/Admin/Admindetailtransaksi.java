package com.skripsi.nigel.esvira.Admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Adpater.Adapterdetailtransaksi;
import com.skripsi.nigel.esvira.Login;
import com.skripsi.nigel.esvira.MapsDirection;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Admindetailtransaksi extends AppCompatActivity {
    String iduser,idtransaksi, tanggalkirim, alamat, namauser, telpuser,status, json_note_alamat,metode,no_ts,noresi,kedatangan;
    private TextView Txtanggalkirim, Txalamat,txTotalharga,txOngkir,txTotal,TxUser,TxTelp,TxUbah, note_alamat,TxMetode,textViewno,txKedatangan;
    EditText Txnoresi;
    private Adapterdetailtransaksi adapter;
    private String API_TRANSAKSI = Server.URL + "transaksi/list/detail";
    private String API_UbahComplete = Server.URL + "transaksi/complete";
    private String API_UbahDelivery = Server.URL + "transaksi/onDelivery";
    private String API_UbahNoresi = Server.URL + "transaksi/editnoresi";
    Button btn_lihat_map, btnDelivery, btnComplete;
    private RecyclerView mList;
    private CardView cView;
    int totalharga,ongkir;
    private Fragment fragment;
    Button button_edit_no_resi;
    ImageView bukti;
    RelativeLayout myRelativeLayout;
    CardView cardm;
    String token;


    ArrayList<Buku> listKeranjang = new ArrayList<Buku>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admindetailtransaksi);
        Txalamat =(TextView) findViewById(R.id.textView_Lokasi);
        Txtanggalkirim = (TextView) findViewById(R.id.textViewTanggal);
        txTotalharga = (TextView) findViewById(R.id.textViewhargabarang2);
        txOngkir = (TextView) findViewById(R.id.textViewbiayapengiriman2);
        txTotal = (TextView) findViewById(R.id.textViewTotal2);
        TxUser= (TextView) findViewById(R.id.TextviewUser);
        txKedatangan = (TextView) findViewById(R.id.tv_kedatangan);
        TxTelp = (TextView)findViewById(R.id.TextviewTelp);
        btnDelivery =(Button) findViewById(R.id.buttonDelivery);
        btnComplete =(Button) findViewById(R.id.buttonSelesai);
        TxUbah =(TextView)findViewById(R.id.textUbahStatus) ;
        cView =(CardView)findViewById(R.id.layoutUbah);
        note_alamat = (TextView)findViewById(R.id.admin_note_alamat);
        cardm = (CardView)findViewById(R.id.cv_upload);
        myRelativeLayout = (RelativeLayout)findViewById(R.id.RelativeLayoutmetode);
        TxMetode =(TextView) findViewById(R.id.textViewmp);
        textViewno =(TextView) findViewById(R.id.Textviewno);
        bukti = (ImageView) findViewById(R.id.imageView);
        Txnoresi = (EditText)findViewById(R.id.textVienoresi);
        button_edit_no_resi = (Button) findViewById(R.id.buttonedit);
        SharedPreferences sharedpreferencesAdmnin = getSharedPreferences(Login.my_shared_preferences2, Context.MODE_PRIVATE);
        token = "Bearer "+sharedpreferencesAdmnin.getString("token", null);

        cView.setVisibility(View.GONE);
        idtransaksi = getIntent().getExtras().getString("idtransaksi");
        btn_lihat_map = (Button) findViewById(R.id.buttonmap);
        btn_lihat_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admindetailtransaksi.this, MapsDirection.class);
                intent.putExtra("data1", Txalamat.getText().toString());
                startActivity(intent);
            }
        });

        adapter = new Adapterdetailtransaksi(this,listKeranjang);
        ambilkeranjang(API_TRANSAKSI,adapter);




        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UbahstatusCom(API_UbahComplete);
            }
        });
        btnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UbahstatusDeliv(API_UbahDelivery);
            }
        });

        button_edit_no_resi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahnoresi();
            }
        });

    }


    public void ubahnoresi (){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin untuk Mengubah Nomor Resi?" );

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk mengubah Nomor Resi!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        StringRequest updateReq = new StringRequest(Request.Method.POST, API_UbahNoresi,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Toast.makeText(getApplicationContext(), "Edit nomor resi Berhasil", Toast.LENGTH_SHORT).show();
                                        startActivity( new Intent(Admindetailtransaksi.this,AdminHome.class));
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Admindetailtransaksi.this, "pesan : Gagal Edit nomor resi", Toast.LENGTH_SHORT).show();
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
                                Map<String,String> map = new HashMap<>();
                                map.put("id",idtransaksi);
                                map.put("no_resi",Txnoresi.getText().toString());
                                return map;
                            }
                        };

                        Singleton.getInstance(getApplicationContext()).addToRequestQueue(updateReq);

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

    private void UbahstatusCom(String url) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    Toast.makeText(getApplicationContext(),"Berhasil diubah",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AdminHome.class));
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
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("transaksi_id", String.valueOf(idtransaksi));
                return params;
            }

        };

        Singleton.getInstance(this).addToRequestQueue(stringRequest);

    }
    private void UbahstatusDeliv(String url) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    Toast.makeText(getApplicationContext(),"Berhasil diubah",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AdminHome.class));
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
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("transaksi_id", String.valueOf(idtransaksi));
                return params;
            }
        };

        Singleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void ambilkeranjang (String url, final Adapterdetailtransaksi keranjang){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                Log.e("Response:", response.toString());
                listKeranjang.clear();
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonobject = jsonArray.getJSONObject(i);

                        if (jsonobject.getString("metode_transaksi").equals("CASH ON DELIVERY")) {
                            myRelativeLayout.setVisibility(View.GONE);
                            cardm.setVisibility(View.GONE);
                        }

                        if (jsonobject.getString("status_transaksi").equals("2")) {
                            button_edit_no_resi.setVisibility(View.GONE);
                        }

                            listKeranjang.add(new Buku(Server.URLIMAGE + jsonobject.getString("foto"), jsonobject.getInt("buku_id"),
                                    jsonobject.getString("nama"), jsonobject.getInt("harga"), jsonobject.getInt("jumlah_buku"),jsonobject.getInt("jumlah")));
                            mList = (RecyclerView) findViewById(R.id.detailtransaksi);
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            mList.setLayoutManager(mLayoutManager);
                            mList.setAdapter(keranjang);
                            tanggalkirim = jsonobject.getString("waktu_pengiriman");
                            showImage(Server.URLIMAGE+jsonobject.getString("foto_transaksi"));
                            alamat = jsonobject.getString("alamat");
                            namauser = jsonobject.getString("name");
                            telpuser = jsonobject.getString("nomor_telepon");
                            totalharga = totalharga + jsonobject.getInt("total_harga");
                            status = jsonobject.getString("status_transaksi");
                            json_note_alamat = jsonobject.getString("note_alamat");
                            ongkir = jsonobject.getInt("ongkir");
                            metode = jsonobject.getString("metode_transaksi");
                            no_ts =  jsonobject.getString("kode_transaksi");
                            noresi = jsonobject.getString("no_resi");
                            kedatangan = jsonobject.getString("status_kedatangan");
                    }
                    if (status.equals("0")){
                        cView.setVisibility(View.VISIBLE);
                    }if (status.equals("1")){
                        cView.setVisibility(View.VISIBLE);
                        btnDelivery.setVisibility(View.GONE);
                    }if (status.equals("2")){
                        TxUbah.setText("Transaksi Selesai");
                    }

                    Txnoresi.setText(noresi);
                    TxMetode.setText(metode);
                    textViewno .setText("No. Pesanan : "+no_ts);
                    txTotalharga.setText("Rp. "+String.valueOf(totalharga));
                    txOngkir.setText("Rp. "+String.valueOf(ongkir));
                    txTotal.setText("Rp. "+String.valueOf(totalharga+ongkir));
                    Txalamat.setText(alamat);
                    Txtanggalkirim.setText(tanggalkirim);
                    TxUser.setText("Nama  : " +namauser);
                    TxTelp.setText("Nomor : " +telpuser);
                    note_alamat.setText("Note : " + json_note_alamat);
                    txKedatangan.setText(kedatangan);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", idtransaksi);
                return params;
            }
        };

        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void showImage(String linkImage){
        ImageLoader imageLoader = Singleton.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                bukti.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }


}
