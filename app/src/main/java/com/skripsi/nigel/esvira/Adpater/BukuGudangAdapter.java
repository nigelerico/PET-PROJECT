package com.skripsi.nigel.esvira.Adpater;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Admin.AdminHome;
import com.skripsi.nigel.esvira.Login;
import com.skripsi.nigel.esvira.Model.BukuGudangModel;
import com.skripsi.nigel.esvira.Profile;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BukuGudangAdapter extends RecyclerView.Adapter<BukuGudangAdapter.BukuGudangViewHolder> {
    private Context context;
    private ArrayList<BukuGudangModel> bukuGudangList;
    private String API_URL = Server.URL + "buku";
    Profile profile;
    String token;

    public BukuGudangAdapter(Context context, ArrayList<BukuGudangModel> bukuGudangList){
        this.context = context;
        this.bukuGudangList = bukuGudangList;
        SharedPreferences sharedpreferencesAdmnin = context.getSharedPreferences(Login.my_shared_preferences2, Context.MODE_PRIVATE);
        token = "Bearer "+sharedpreferencesAdmnin.getString("token", null);
    }

    @Override
    public BukuGudangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buku_gudang_row, parent, false);
        BukuGudangViewHolder vh = new BukuGudangViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final BukuGudangViewHolder holder, final int position) {
        holder.mNama.setText(bukuGudangList.get(position).getNama());
        holder.mHarga.setText(String.valueOf(bukuGudangList.get(position).getHarga()));
        holder.mStokkuantitas.setText("Stok " + String.valueOf(bukuGudangList.get(position).getJumlah()) + " / " + String.valueOf(bukuGudangList.get(position).getPenulis()) + " " + bukuGudangList.get(position).getKategori());

        ImageLoader imageLoader = Singleton.getInstance(context).getImageLoader();
        imageLoader.get(bukuGudangList.get(position).getFoto(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.mFoto.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(context, String.valueOf((bukuGudangList.get(position).getId())));
            }
        });

    }
    private void showDialog(final Context context, final String idBuku){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title dialog
        alertDialogBuilder.setTitle("Tambah ");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk Tambah!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL + "/tambah-buku", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent mIntent = new Intent(context, AdminHome.class);
                                context.startActivity(mIntent);
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
                                params.put("id", idBuku);
                                return params;
                            }
                        };

                        Singleton.getInstance(context).addToRequestQueue(stringRequest);
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


    @Override
    public int getItemCount() {
        return bukuGudangList.size();
    }

    public class BukuGudangViewHolder extends RecyclerView.ViewHolder {
        TextView mHarga;
        TextView mNama;
        TextView mStokkuantitas;
        CardView mCardView;
        ImageView mFoto;
        Button btn_add;

        public BukuGudangViewHolder(View view){
            super(view);
            mFoto = view.findViewById(R.id.gambar_bukugudang);
            mNama = view.findViewById(R.id.nama_bukugudang);
            mHarga = view.findViewById(R.id.harga_bukugudang);
            mCardView = view.findViewById(R.id.cradview_bukugudang);
            btn_add = view.findViewById(R.id.btn_add_buku_admin);
            mStokkuantitas = view.findViewById(R.id.stokkuantitas);

        }
    }

    public void clear(){
        bukuGudangList.clear();
        notifyDataSetChanged();
    }

}
