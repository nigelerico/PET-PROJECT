package com.skripsi.nigel.esvira.Adpater;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import java.util.ArrayList;

public class Adapterusertransaksi extends RecyclerView.Adapter<Adapterusertransaksi .MyViewHolder> {
    private int counter = 1;
    private int jumlah=1;
    Context mContext;
    ArrayList<Buku>keranjang;

    public Adapterusertransaksi (Context context, ArrayList<Buku> keranjang){
        this.mContext = context;
        this.keranjang = keranjang;
    }

    @Override
    public Adapterusertransaksi .MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdetail_list ,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ImageLoader imageLoader = Singleton.getInstance(mContext).getImageLoader();
        holder.itemTitle.setText(keranjang.get(position).getNama());
        holder.itemKuan.setText("qty : "+String.valueOf(keranjang.get(position).getJumlah()));
        holder.itemPrice.setText("Rp. "+String.valueOf(keranjang.get(position).getHarga()));
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
    }

    @Override
    public int getItemCount() {
        return keranjang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTitle,itemPrice, itemKuan;
        ImageView imageView;
        Button btn_minus;
        Button btn_plus , btn_del;
        TextView tvjumlah;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView)itemView.findViewById(R.id.detail_nama);
            itemKuan = (TextView) itemView.findViewById(R.id.detail_jumlahBuku);
            itemPrice = (TextView) itemView.findViewById(R.id.detail_hargaBuku);
            imageView = (ImageView) itemView.findViewById(R.id.deatail_imageView);

        }
    }

}
