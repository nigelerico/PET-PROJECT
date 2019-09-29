package com.skripsi.nigel.esvira;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.skripsi.nigel.esvira.Admin.AdminHome;
import com.skripsi.nigel.esvira.Admin.AdminubahBuku;
import com.skripsi.nigel.esvira.Adpater.Adapterusertransaksi;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Userdetailtransaksi extends AppCompatActivity {
    String iduser,idtransaksi, tanggalkirim, alamat, namauser, telpuser,status, note_alamat,metode ,no_ts,noresi;
    private TextView TxMetode, Txtanggalkirim, Txalamat,txTotalharga,txOngkir,txTotal,TxUser,TxTelp, user_note_alamat ,textViewno,Txnoresi,Txkodeunik;
    CardView cardm , cardd , cardnoresi;
    TextView textbiayapengiriman;
    ImageView bukti;
    private Adapterusertransaksi adapter;
    RelativeLayout myRelativeLayout;
    private String API_TRANSAKSI = Server.URL + "transaksi/list/detail";
    private String API_URL_UPLOAD = Server.URL + "transaksi/editTransaksi";
    private String API_URL_KEDATANGAN = Server.URL + "transaksi/confirm";
    private RecyclerView mList;
    int totalharga ,ongkir,kode_unik;
    private Fragment fragment;
    ArrayList<Buku> listKeranjang = new ArrayList<Buku>();
    Button upload,ubah;
    int bitmap_size = 60;
    int PICK_IMAGE_REQUEST = 1;
    Button btn_konfirmasi;
    Bitmap bitmap, decoded;
    FrameLayout frame,framenoresi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetail_transaksi);

        textViewno =(TextView) findViewById(R.id.Textviewno);
        upload = (Button)findViewById(R.id.buttonpick);
        bukti = (ImageView) findViewById(R.id.imageView);
        ubah = (Button)findViewById(R.id.button);
        btn_konfirmasi = (Button)findViewById(R.id.buttonDKonfirm);
        TxMetode =(TextView) findViewById(R.id.textViewmp);
        Txalamat =(TextView) findViewById(R.id.textView_Lokasi);
        Txkodeunik =(TextView) findViewById(R.id.textViewbiayapengiriman3);
        Txtanggalkirim = (TextView) findViewById(R.id.textViewTanggal);
        txTotalharga = (TextView) findViewById(R.id.textViewhargabarang2);
        txOngkir = (TextView) findViewById(R.id.textViewbiayapengiriman2);
        txTotal = (TextView) findViewById(R.id.textViewTotal2);
        TxUser= (TextView) findViewById(R.id.TextviewUser);
        TxTelp = (TextView)findViewById(R.id.TextviewTelp);
        textbiayapengiriman = (TextView) findViewById(R.id.textViewkodeunik) ;
        myRelativeLayout = (RelativeLayout)findViewById(R.id.RelativeLayoutmetode);
        frame = (FrameLayout) findViewById(R.id.frameframe);
        framenoresi = (FrameLayout) findViewById(R.id.framenoresi);

        user_note_alamat = (TextView)findViewById(R.id.user_note_alamat);
        idtransaksi = getIntent().getExtras().getString("idtransaksi");
        cardm = (CardView)findViewById(R.id.cv_upload);
        cardd = (CardView)findViewById(R.id.layoutUbahkedatangan);
        cardnoresi = (CardView)findViewById(R.id.card_noresi);
        adapter = new Adapterusertransaksi(this,listKeranjang);
        ambilkeranjang(API_TRANSAKSI,adapter);
        Txnoresi = (TextView)findViewById(R.id.textVienoresi);



        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahbuku();
            }
        });


        btn_konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                konfirmasi_kedatangan();
            }
        });



    }
    private void loadFragment(android.support.v4.app.Fragment fragment) {
        // load fragment
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayoutDrawer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }




    private void ambilkeranjang (String url, final Adapterusertransaksi keranjang){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                Log.e("Response:", response.toString());
                listKeranjang.clear();
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        if (jsonobject.getString("status_transaksi").equals("2")) {
                            upload.setVisibility(View.GONE);
                            ubah.setVisibility(View.GONE);
                        }
                        if (jsonobject.getString("metode_transaksi").equals("CASH ON DELIVERY")) {
                            myRelativeLayout.setVisibility(View.GONE);
                            cardm.setVisibility(View.GONE);
                            framenoresi.setVisibility(View.GONE);
                            cardnoresi.setVisibility(View.GONE);
                            textbiayapengiriman.setVisibility(View.GONE);
                            Txkodeunik.setVisibility(View.GONE);
                        }
                        if (jsonobject.getString("status_kedatangan").equals("confirmed")) {
                            frame.setVisibility(View.GONE);
                            cardd.setVisibility(View.GONE);
                        }
                        if (jsonobject.getString("status_transaksi").equals("0")) {
                            frame.setVisibility(View.GONE);
                            cardd.setVisibility(View.GONE);
                        }
                        if (jsonobject.getString("status_transaksi").equals("3")) {
                            myRelativeLayout.setVisibility(View.GONE);
                            cardm.setVisibility(View.GONE);
                            framenoresi.setVisibility(View.GONE);
                            cardnoresi.setVisibility(View.GONE);
                            frame.setVisibility(View.GONE);
                            cardd.setVisibility(View.GONE);
                        }

                        listKeranjang.add(new Buku(Server.URLIMAGE + jsonobject.getString("foto"), jsonobject.getInt("buku_id"),
                                    jsonobject.getString("nama"), jsonobject.getInt("harga"), jsonobject.getInt("jumlah_buku"),jsonobject.getInt("jumlah")));
                            mList = (RecyclerView) findViewById(R.id.detailtransaksi);
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            mList.setLayoutManager(mLayoutManager);
                            mList.setAdapter(keranjang);
                            tanggalkirim = jsonobject.getString("waktu_pengiriman");
                            alamat = jsonobject.getString("alamat");
                            showImage(Server.URLIMAGE+jsonobject.getString("foto_transaksi"));
                            namauser = jsonobject.getString("name");
                            telpuser = jsonobject.getString("nomor_telepon");
                            metode = jsonobject.getString("metode_transaksi");
                            totalharga =  totalharga+ jsonobject.getInt("total_harga");
                            status = jsonobject.getString("status_transaksi");
                            note_alamat = jsonobject.getString("note_alamat");
                            no_ts =  jsonobject.getString("kode_transaksi");
                            ongkir = jsonobject.getInt("ongkir");
                            kode_unik = jsonobject.getInt("kode_unik");
                            noresi = jsonobject.getString("no_resi");

                        if (jsonobject.getString("metode_transaksi").equals("TRANSFER MANDIRI")) {
                            txTotal.setText("Rp. "+String.valueOf(totalharga+ongkir+kode_unik));
                        }
                        if (jsonobject.getString("metode_transaksi").equals("CASH ON DELIVERY")) {
                            txTotal.setText("Rp. "+String.valueOf(totalharga+ongkir));
                        }

                        }

                    TxMetode.setText(metode);
                    textViewno .setText("No. Pesanan : "+no_ts);
                    txTotalharga.setText("Rp. "+String.valueOf(totalharga));
                    txOngkir.setText("Rp. "+String.valueOf(ongkir));
                   // txTotal.setText("Rp. "+String.valueOf(totalharga+ongkir+kode_unik));
                    Txkodeunik.setText(String.valueOf(kode_unik));
                    Txnoresi.setText(noresi);
                    Txalamat.setText(alamat);
                    user_note_alamat.setText("Note : " + note_alamat);
                    Txtanggalkirim.setText(tanggalkirim);
                    TxUser.setText("Nama  : " +namauser);
                    TxTelp.setText("Nomor : " +telpuser);


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
                params.put("id",idtransaksi);
                return params;
            }
        };

        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void ubahbuku (){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setTitle("Yakin untuk mengupload bukti transfer?" );


        alertDialogBuilder
                .setMessage("Klik Ya untuk mengupload bukti transfer!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        StringRequest updateReq = new StringRequest(Request.Method.POST, API_URL_UPLOAD,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Toast.makeText(getApplicationContext(), "Upload Berhasil", Toast.LENGTH_SHORT).show();
                                        startActivity( new Intent(Userdetailtransaksi.this,Main.class));
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Userdetailtransaksi.this, "pesan : Gagal upload", Toast.LENGTH_SHORT).show();
                                    }
                                }){

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> map = new HashMap<>();
                                map.put("id",idtransaksi);

                                if(decoded != null) {
                                    map.put("foto_transaksi", getStringImage(decoded));
                                }else{
                                    map.put("foto_transaksi", "");
                                }
                                return map;
                            }
                        };

                        Singleton.getInstance(getApplicationContext()).addToRequestQueue(updateReq);

                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }


    public void konfirmasi_kedatangan (){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setTitle("Yakin untuk mengkonfirmasi kedatangan barang?" );


        alertDialogBuilder
                .setMessage("Klik Ya untuk mengkonfirmasi!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        StringRequest updateReq = new StringRequest(Request.Method.POST, API_URL_KEDATANGAN,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Toast.makeText(getApplicationContext(), "Konfirmasi Berhasil", Toast.LENGTH_SHORT).show();
                                        startActivity( new Intent(Userdetailtransaksi.this,Main.class));
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Userdetailtransaksi.this, "pesan : Gagal upload", Toast.LENGTH_SHORT).show();
                                    }
                                }){

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> map = new HashMap<>();
                                map.put("transaksi_id",idtransaksi);
                                return map;
                            }
                        };

                        Singleton.getInstance(getApplicationContext()).addToRequestQueue(updateReq);

                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
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
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        bukti.setImageBitmap(decoded);
    }
    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


}
