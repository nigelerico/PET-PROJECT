package com.skripsi.nigel.esvira.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AdminubahBuku extends AppCompatActivity {

    ImageView gambar_buku;
    TextView txt_nama;
    TextView txt_harga;
    TextView txt_berat;
    Spinner jenis_kuantitas;
    Button btn_edit;
    Bitmap bitmap, decoded;
    private String API_URL = Server.URL + "buku/delete";
    private String API_URL_detailbuku = Server.URL + "buku/detail";
    private String API_URL_ubahbuku = Server.URL + "buku/edit";
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60;
    Button btn_delete;
    ProgressDialog pd;
    Button btn_ubah;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminubahbuku);

        btn_ubah = (Button) findViewById(R.id.button_ubah);
        txt_nama = findViewById(R.id.txt_bukunama);
        txt_harga = findViewById(R.id.txt_hargabuku);
        btn_edit = (Button) findViewById(R.id.btn_ubahgambarbukuedit);
        gambar_buku = (ImageView) findViewById(R.id.gambar_buku_edit);
        btn_delete = (Button)findViewById(R.id.button_delete);
        txt_berat = (TextView)findViewById(R.id.txt_beratbuku);
        jenis_kuantitas = (Spinner)findViewById(R.id.spinnerkuantitas);
        final Context context = this.getApplicationContext();
        final Bundle mBundle = getIntent().getExtras();
        pd = new ProgressDialog(AdminubahBuku.this);
        SharedPreferences sharedpreferencesAdmnin = getSharedPreferences(Login.my_shared_preferences2, Context.MODE_PRIVATE);
        token = "Bearer "+sharedpreferencesAdmnin.getString("token", null);

        if (mBundle != null) {
            txt_nama.setText(mBundle.getString("nama"));
            gambar_buku.setImageResource(mBundle.getInt("gambar"));
            txt_harga.setText(mBundle.getString("harga"));
        }
        detailbuku(String.valueOf(mBundle.getInt("id")) ,context);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletebuku(String.valueOf(mBundle.getInt("id")), context);
            }
        });

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahbuku(String.valueOf(mBundle.getInt("id")), context);
            }
        });
    }

    public void ubahbuku (final String ID,final Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin untuk Mengubah data buku?" );

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk mengubah data buku!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        StringRequest updateReq = new StringRequest(Request.Method.POST, API_URL_ubahbuku,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Toast.makeText(getApplicationContext(), "Edit Buku Berhasil", Toast.LENGTH_SHORT).show();
                                        startActivity( new Intent(AdminubahBuku.this,AdminHome.class));
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(AdminubahBuku.this, "pesan : Gagal Edit buku", Toast.LENGTH_SHORT).show();
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
                                map.put("id",ID);
                                map.put("nama",txt_nama.getText().toString());
                                map.put("harga",txt_harga.getText().toString());
                                if(decoded != null) {
                                    map.put("foto", getStringImage(decoded));
                                }else{
                                    map.put("foto", "");
                                }
                                map.put("penulis", txt_berat.getText().toString());
                                map.put("kategori", jenis_kuantitas.getSelectedItem().toString());
                                return map;
                            }
                        };

                        Singleton.getInstance(context).addToRequestQueue(updateReq);

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


    private void detailbuku(final String ID, final Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL_detailbuku , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    txt_nama.setText(jsonObject.getString("nama"));
                    txt_harga.setText(jsonObject.getString("harga"));
                    showImage(Server.URLIMAGE+jsonObject.getString("foto"));
                    txt_berat.setText(jsonObject.getString("penulis"));
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
                gambar_buku.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

    private void deletebuku(final String ID, final Context context){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Hapus Buku yang dipilih?" );

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk hapus!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL , new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(getApplicationContext(), "Berhasil Hapus",
                                        Toast.LENGTH_SHORT).show();
                                Intent mIntent = new Intent(AdminubahBuku.this, AdminHome.class);
                                startActivity(mIntent);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

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
        gambar_buku.setImageBitmap(decoded);
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
