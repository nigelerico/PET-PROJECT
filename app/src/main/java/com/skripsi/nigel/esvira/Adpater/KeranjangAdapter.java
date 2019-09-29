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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Admin.AdminHome;
import com.skripsi.nigel.esvira.Callbacks;
import com.skripsi.nigel.esvira.Detailbuku;
import com.skripsi.nigel.esvira.Main;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter .MyViewHolder> {
    private int total = 0, harga;
    private int jumlah = 0;
    Callbacks callbacks;
    Context context;
    private String API_URL = Server.URL + "cart/deleteCart";
    SharedPreferences sharedpreferences;
    public final static String TAG_ID = "id";
    public static final String my_shared_preferences = "my_shared_preferences";
    int id_user;

    ArrayList<Buku> keranjang;

    int ongkir = 22000;
    int multiplier = 1;
    int counter = 0;

    int jAja, jTotal = 0;

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }


    public KeranjangAdapter(Context context, ArrayList<Buku> keranjang, Callbacks callbacks) {
        this.context = context;
        this.callbacks = callbacks;
        this.keranjang = keranjang;
    }

    public void addCartItems(int id, int jumlah) {

        for (int i = 0; i < this.keranjang.size(); i++) {
            if (jumlah < 1) {
                if (this.keranjang.get(i).getId() == id) {
                    this.keranjang.remove(i);
                    notifyItemRemoved(i);
                }
            } else {

                if (this.keranjang.get(i).getId() == id) {
                    this.keranjang.get(i).setJumlah(jumlah);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void total(int harga) {
        total = total + harga;
    }

    public void kurangtotal(int harga) {
        total = total - harga;
    }


    public void jTotal(int jAja) {
        jTotal = jTotal + jAja;
    }

    public void kTotal(int jAja) {
        jTotal = jTotal - jAja;
    }

    public void harga(int harga) {
        this.harga = harga;
    }

    public int getHarga() {
        return this.harga;
    }

    public void jumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getJumlah() {
        return this.jumlah;
    }


    @Override
    public KeranjangAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.keranjang_single, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        jumlah = Integer.parseInt(holder.itemKuan.getText().toString());
        ImageLoader imageLoader = Singleton.getInstance(context).getImageLoader();
        holder.itemTitle.setText(keranjang.get(position).getNama());
        holder.itemKuan.setText(String.valueOf(keranjang.get(position).getJumlah()));
        holder.itemPrice.setText("Rp. " + String.valueOf(keranjang.get(position).getHarga()));



        imageLoader.get(keranjang.get(position).getFoto(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.imageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), Detailbuku.class);
                mIntent.putExtra("id",keranjang.get(position).getId());
                mIntent.putExtra("harga",keranjang.get(position).getHarga());
                view.getContext().startActivity(mIntent);
            }
        });

        holder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah = Integer.parseInt(holder.itemKuan.getText().toString());
                if (jumlah <= 1) {
                    jumlah = 0;
                    kurangtotal(keranjang.get(position).getHarga());
                    kTotal(keranjang.get(position).getJumlah());
                    callbacks.updateJumlah(keranjang.get(position).getJumlah(), 1);
                    callbacks.updateharga(keranjang.get(position).getHarga(), 1);
                    callbacks.updateCart(keranjang.get(position), 2, jumlah);
                } else {
                    kurangtotal(keranjang.get(position).getHarga());
                    kTotal(keranjang.get(position).getJumlah());
                    callbacks.updateJumlah(keranjang.get(position).getJumlah(), 1);
                    callbacks.updateharga(keranjang.get(position).getHarga(), 1);
                    holder.itemKuan.setText(String.valueOf(--jumlah));
                    //ikiloooo
//                    counter = 0;
//                    for (int i = 1; i<=jumlah; i++){
//                        if (counter == 1){
//                            multiplier++;
//                            counter = 0;
//                        }
//                        if(i>0 && i%5==0){
//                            counter++;
//                        }
//                    }
//                    ongkir=22000*multiplier;
//                    Toast.makeText(mContext,"j :" + jumlah +"m :" + multiplier +"ongkir :" + ongkir,Toast.LENGTH_LONG).show();
//                    multiplier = 1;
                    //sampek keneloooo
                    callbacks.updateCart(keranjang.get(position), 2, jumlah);
                }

            }
        });
        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah = Integer.parseInt(holder.itemKuan.getText().toString());


                if (jumlah >= keranjang.get(position).getStok()) {
                    jumlah = keranjang.get(position).getStok() - 1;
                    Toast.makeText(view.getContext(), " Stok telah mencapai batas ", Toast.LENGTH_LONG).show();
                    //holder.mTambahQ.getBackground().setAlpha(64);
                } else {
                    callbacks.updateJumlah(keranjang.get(position).getJumlah(), 0);
                    callbacks.updateharga(keranjang.get(position).getHarga(), 0);
                    // callbacks.updateharga(total,1);
                }
                holder.itemKuan.setText(String.valueOf(jumlah++));
                callbacks.updateCart(keranjang.get(position), 2, jumlah);
            }
        });
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////                notifyItemRemoved(position);
////                int harga = keranjang.get(position).getHarga() * jumlah;
////                keranjang.remove(position);
////                kurangtotal(harga);
////                callbacks.updateharga(harga,1);
//                notifyItemRemoved(position);
//                kurangtotal(keranjang.get(position).getHarga());
//                kTotal(keranjang.get(position).getJumlah());
//                callbacks.updateJumlah(keranjang.get(position).getJumlah(), 1);
//                callbacks.updateharga(keranjang.get(position).getHarga(), 1);
//                callbacks.updateCart(keranjang.get(position), 2, jumlah);
//                keranjang.remove(position);
                sharedpreferences =  view.getContext().getSharedPreferences(my_shared_preferences,Context.MODE_PRIVATE);
                id_user = sharedpreferences.getInt(TAG_ID, 0);
                deletecart(view.getContext(), String.valueOf((keranjang.get(position).getId())));
            }
        });


    }

    private void deletecart(final Context context, final String idBuku){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title dialog
        alertDialogBuilder.setTitle("Hapus item ini dari keranjang");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk hapus!")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL , new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(context,"Berhasil menghapus buku dari keranjang",Toast.LENGTH_LONG).show();
                                Intent mIntent = new Intent(context, Main.class);
                                context.startActivity(mIntent);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> params = new HashMap<>();
                                params.put("id", idBuku);
                                params.put("user_id", String.valueOf(id_user));
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
        return keranjang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTitle, itemPrice, itemKuan;
        ImageView imageView;
        Button btn_minus;
        Button btn_plus, btn_del;
        CardView card;
        TextView tvjumlah;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_minus = (Button) itemView.findViewById(R.id.buttonMinus);
            btn_plus = (Button) itemView.findViewById(R.id.buttonPlus);
            btn_del = (Button) itemView.findViewById(R.id.button_delete);
            itemTitle = (TextView) itemView.findViewById(R.id.cart_namaBuku);
            itemKuan = (TextView) itemView.findViewById(R.id.textView_jumlahBuku);
            itemPrice = (TextView) itemView.findViewById(R.id.cart_hargaBuku);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            card = (CardView) itemView.findViewById(R.id.cardView);

        }
    }
}
