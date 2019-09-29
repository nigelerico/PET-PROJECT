package com.skripsi.nigel.esvira;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Adpater.OngkirAdapter;
import com.skripsi.nigel.esvira.Model.Ongkir;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    TextView txt_email, txt_name, txt_nomor_telepon,txt_simpan,txt_alamat;
    FloatingActionButton fab;
    int id;
    String name, email, nomor_telepon, token, foto,alamat;
    SharedPreferences sharedpreferences;
    Button btn_edit_user ,simpan;
    ImageView gambar_user;
    private String API_URL_detailuser = Server.URL + "user/detail";
    private String API_URL = Server.URL + "user/edit";
    public static final String my_shared_preferences = "my_shared_preferences";
    Intent intent;

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_NOMOR_TELEPON = "nomor_telepon";
    public static final String TAG_ALAMAT = "alamat_user";
    public final static String TAG_TOKEN = "token";
    public final static String TAG_FOTO = "foto";

    Bitmap bitmap, decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60;

    private static final String TAG = Chart.class.getSimpleName();
    int ongkir;
    int id_kota;
    String kota_pengiriman;
    AutoCompleteTextView auto_kota;
    private String API_GET_ONGKIR = Server.URL + "transaksi/getOngkir";
    private String API_ONGKIR = Server.URL + "buku/ongkoskirim";
    OngkirAdapter adapterongkir;
    ArrayList<Ongkir> listkota = new ArrayList<Ongkir>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.edit_profil);
            getSupportActionBar().hide();

            txt_name = (TextView) findViewById(R.id.editTextnama);
            txt_email = (TextView) findViewById(R.id.editTextemail);
            txt_nomor_telepon = (TextView) findViewById(R.id.editTextNomorTelepon);
            txt_alamat = (TextView) findViewById(R.id.editTextAlamat);
            fab =(FloatingActionButton) findViewById(R.id.button_logout);
            auto_kota = (AutoCompleteTextView) findViewById(R.id.editTextKota);
            //sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
            btn_edit_user =(Button) findViewById(R.id.button);
            gambar_user = (ImageView) findViewById(R.id.imageView);
            simpan = (Button) findViewById (R.id.btn_simpan);
            final Context context = this.getApplicationContext();
            //final Bundle mBundle = getIntent().getExtras();

            sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
            id = sharedpreferences.getInt(TAG_ID, 0);
            name = sharedpreferences.getString(TAG_NAME, null);
            email = sharedpreferences.getString(TAG_EMAIL, null);
            nomor_telepon = sharedpreferences.getString(TAG_NOMOR_TELEPON, null);
            alamat = sharedpreferences.getString(TAG_ALAMAT, null);
            token = "Bearer "+ sharedpreferences.getString(TAG_TOKEN, null);
            foto = Server.URLIMAGE + sharedpreferences.getString(TAG_FOTO, null);
            //gambar(String.valueOf(id) ,context);
            showImage(foto);
            //if (mBundle != null) {
                txt_name.setText(name);
                txt_email.setText(email);
                txt_nomor_telepon.setText(nomor_telepon);
                txt_alamat.setText(alamat);
            //}
            fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    // update login session ke FALSE dan mengosongkan nilai id dan username
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(Login.session_status, false);
                    editor.putInt(TAG_ID,0);
                    editor.putString(TAG_NAME, null);
                    editor.putString(TAG_EMAIL, null);
                    editor.putString(TAG_NOMOR_TELEPON, null);
                    editor.putString(TAG_ALAMAT, null);
                    editor.commit();
                    Intent intent = new Intent(Profile.this, Main.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            btn_edit_user.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v)
                {
                    showFileChooser();
                }
            });

            simpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ubahprofile(context, String.valueOf(id));
                }
            });

            auto_kota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    id_kota = listkota.get(position).getId();
//                    Toast.makeText(getApplicationContext(), "tes"+id_kota, Toast.LENGTH_SHORT).show();

                }
            });

            getOngkir(String.valueOf(id));
            calldata();

        }

        public String getToken()
        {
            return this.token;
        }

    private void gambar(final String ID, final Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL_detailuser , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    showImage(Server.URLIMAGE+jsonObject.getString("foto"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Toast.makeText(context,"Error Bro",Toast.LENGTH_LONG ).show();
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
                                adapterongkir = new OngkirAdapter(Profile.this,R.layout.list_ongkir ,listkota);
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
                Toast.makeText(Profile.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        Singleton.getInstance(this).addToRequestQueue(jArr);
    }

    public void showImage(String linkImage){
        ImageLoader imageLoader = Singleton.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(linkImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                gambar_user.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }


    private void getOngkir (final String ID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_GET_ONGKIR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                Log.e("Response:", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        id_kota = jsonobject.getInt("id_kota");
                        ongkir = jsonobject.getInt("harga_ongkir");
                        kota_pengiriman = jsonobject.getString("kota");
                    }
                    auto_kota.setText(kota_pengiriman);

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
                params.put("id",String.valueOf(ID));
                return params;
            }
        };

        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }


       public void ubahprofile (final Context context, final String ID){
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

       // set title dialog
       alertDialogBuilder.setTitle("Yakin untuk Mengubah Profil?");

       // set pesan dari dialog
       alertDialogBuilder
               .setMessage("Klik Ya untuk mengubah profile!")
               .setCancelable(false)
               .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, final int id) {
                       StringRequest updateReq = new StringRequest(Request.Method.POST, API_URL,
                               new Response.Listener<String>() {
                                   @Override
                                   public void onResponse(String response) {
                                       String json = response.toString();

                                       try {
                                           JSONObject jsonObject = new JSONObject(json);
                                           SharedPreferences.Editor editor = sharedpreferences.edit();
                                           editor.putString(TAG_NAME, jsonObject.getString("name") );
                                           editor.putString(TAG_EMAIL, jsonObject.getString("email") );
                                           editor.putString(TAG_NOMOR_TELEPON, jsonObject.getString("nomor_telepon") );
                                           editor.putString(TAG_ALAMAT, jsonObject.getString("alamat_user") );
                                           editor.putString("id_kota", jsonObject.getString("id_kota"));
                                           editor.putString("foto", jsonObject.getString("foto"));
                                           editor.commit();
                                           Toast.makeText(getApplicationContext(), "Edit Profile Berhasil", Toast.LENGTH_SHORT).show();
                                           startActivity( new Intent(Profile.this,Main.class));
                                       }catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               },
                               new Response.ErrorListener() {
                                   @Override
                                   public void onErrorResponse(VolleyError error) {
                                       Toast.makeText(Profile.this, "pesan : Gagal Edit Profil", Toast.LENGTH_SHORT).show();
                                   }
                               }){
                           @Override
                           protected Map<String, String> getParams() throws AuthFailureError {
                               Map<String,String> map = new HashMap<>();
                               map.put("id",ID);
                               map.put("name",txt_name.getText().toString());
                               map.put("email",txt_email.getText().toString());
                               map.put("nomor_telepon",txt_nomor_telepon.getText().toString());
                               map.put("alamat_user",txt_alamat.getText().toString());
                               map.put("id_kota",String.valueOf(id_kota));
                               if(decoded != null){
                                   map.put("foto", getStringImage(decoded));
                               }else{
                                   map.put("foto", "");
                               }

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
        gambar_user.setImageBitmap(decoded);
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

    @Override
    public void onBackPressed() {
        intent = new Intent(Profile.this, Main.class);
        finish();
        startActivity(intent);
    }

}
