package com.skripsi.nigel.esvira.Adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.skripsi.nigel.esvira.Callbacks;
import com.skripsi.nigel.esvira.Detailbuku;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.Singleton.Singleton;
import com.skripsi.nigel.esvira.Userdetailtransaksi;

import java.util.ArrayList;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.BukuViewHolder> {

    private Context mContext;
    private ArrayList<Buku> mBukulist;
    private Callbacks callbacks;
    int jumlah, updatebottom;

    int harga, total=0;
    int jAja, jTotal=0;

    public void total(int harga){
        total = total+harga;
    }
    public void kurangtotal(int harga){
        total = total-harga;
    }


    public void jTotal(int jAja){
        jTotal = jTotal+jAja;
    }
    public void kTotal(int jAja){
        jTotal = jTotal-jAja;
    }

    public BukuAdapter(Context mContext, ArrayList<Buku> mBukulist, Callbacks callbacks) {
        this.callbacks = callbacks;
        this.mContext = mContext;
        this.mBukulist = mBukulist;
    }
    @Override
    public BukuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_row, parent, false);
        BukuViewHolder vh = new BukuViewHolder(mView); // pass the view to View Holder
        return vh;
    }

    @Override
    public int getItemCount() {
        return mBukulist.size();
    }

    @Override
    public void onBindViewHolder(final BukuViewHolder holder, final int position) {

        ImageLoader imageLoader = Singleton.getInstance(mContext).getImageLoader();
        imageLoader.get(mBukulist.get(position).getFoto(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.mImage.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        holder.mTitle.setText(mBukulist.get(position).getNama());
        holder.mHarga.setText(String.valueOf("Rp. "+mBukulist.get(position).getHarga()));
        holder.mKuantitas.setText(String.valueOf(mBukulist.get(position).getPenulis()));
        holder.mLayoutStok.setVisibility(View.GONE);
        //holder.mButtonAdd.setVisibility(View.GONE);


        if(mBukulist.get(position).getStok() == 0){
          //  holder.mLayoutKuantitas.setVisibility(View.GONE);
          //  holder.mButtonAdd.setVisibility(View.GONE);
            holder.mLayoutStok.setVisibility(View.VISIBLE);
           // holder.mImage.setAlpha(64);


        }

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, Detailbuku.class);
                mIntent.putExtra("id",mBukulist.get(position).getId());
                mIntent.putExtra("harga",mBukulist.get(position).getHarga());
                mContext.startActivity(mIntent);
            }
        });


//        holder.mButtonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                total(mBukulist.get(position).getHarga());
//                jTotal(mBukulist.get(position).getJumlah());
//                callbacks.updateharga(total,1);
//                callbacks.updateCart(mBukulist.get(position),1, 1);
//                holder.mButtonAdd.setVisibility(View.INVISIBLE);
//                holder.mLayoutKuantitas.setVisibility(View.VISIBLE);
//
//            }
//        });
//        holder.mTambahQ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                jumlah = Integer.parseInt(holder.mTextQ.getText().toString());
//                if(jumlah>=mBukulist.get(position).getStok()){
//                    jumlah = mBukulist.get(position).getStok() - 1;
//                    Toast.makeText(mContext," Stok telah mencapai batas " ,Toast.LENGTH_LONG).show();
//                    //holder.mTambahQ.getBackground().setAlpha(64);
//                }else{
//                    total(mBukulist.get(position).getHarga());
//                    jTotal(mBukulist.get(position).getJumlah());
//                    callbacks.updateharga(total,1);
//                }
//                holder.mTextQ.setText(String.valueOf(jumlah+1));
//                callbacks.updateCart(mBukulist.get(position),2,jumlah+1);
//            }
//        });
//        holder.mKurangQ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                jumlah = Integer.parseInt(holder.mTextQ.getText().toString());
//                if(jumlah<=1){
//                    jumlah=0;
//                    holder.mLayoutKuantitas.setVisibility(View.INVISIBLE);
//                    holder.mButtonAdd.setVisibility(View.VISIBLE);
//                    kurangtotal(mBukulist.get(position).getHarga());
//                    kTotal(mBukulist.get(position).getJumlah());
//                    callbacks.updateharga(total,1);
//                    callbacks.updateCart(mBukulist.get(position),2,jumlah);
//                }else {
//                    //holder.mTambahQ.getBackground().setAlpha(255);
//                    kurangtotal(mBukulist.get(position).getHarga());
//                    kTotal(mBukulist.get(position).getJumlah());
//                    callbacks.updateharga(total,1);
//                    holder.mTextQ.setText(String.valueOf(--jumlah));
//                    callbacks.updateCart(mBukulist.get(position),2,jumlah);
//                }
//            }
//        });


    }

    class BukuViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mHarga, mTextQ;
        TextView mTitle;
        TextView mKuantitas;
        CardView mCardView;
        Button mButtonAdd, mTambahQ, mKurangQ;
        LinearLayout mLayoutKuantitas;
        LinearLayout mLayoutStok;

        public BukuViewHolder(View itemView) {
            super(itemView);
           // mButtonAdd = itemView.findViewById(R.id.button_tambah_buku);
            mImage = itemView.findViewById(R.id.gambar_buku);
            mTitle = itemView.findViewById(R.id.nama_buku);
            mHarga = itemView.findViewById(R.id.harga_buku);
            mCardView = itemView.findViewById(R.id.cradview);
           // mTextQ = itemView.findViewById(R.id.tV_jumlahBuku);
           // mTambahQ = itemView.findViewById(R.id.buttonPlus);
          //  mKurangQ = itemView.findViewById(R.id.buttonMinus);
//            mLayoutKuantitas = itemView.findViewById(R.id.LayoutQ);
            mLayoutStok = itemView.findViewById(R.id.LayoutP);
//            mLayoutKuantitas.setVisibility(View.INVISIBLE);
//            if(mTextQ.getText().equals("0")){
//                mLayoutKuantitas.setVisibility(View.INVISIBLE);
//            }
            mKuantitas = itemView.findViewById(R.id.kuantitasbarang);


        }
    }
}
