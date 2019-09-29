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
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.skripsi.nigel.esvira.Admin.AdminubahBuku;
import com.skripsi.nigel.esvira.Model.BukuListModel;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.Singleton.Singleton;


import java.util.ArrayList;
import java.util.List;

public class BukuListAdapter extends RecyclerView.Adapter<BukuListAdapter.BukulistViewHolder> {
    private Context mContext;
    private List<BukuListModel> mBukulist;
    View mView;


    public BukuListAdapter(Context mContext, ArrayList<BukuListModel> mBukulist) {
        this.mContext = mContext;
        this.mBukulist = mBukulist;

    }
    @Override
    public BukulistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.buku_admin_list_row, parent, false);
        BukulistViewHolder vh = new BukulistViewHolder(mView); // pass the view to View Holder
        return vh;
    }

    @Override
    public int getItemCount() {
        return mBukulist.size();
    }

    @Override
    public void onBindViewHolder(final BukulistViewHolder holder, final int position) {
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
        holder.mHarga.setText(String.valueOf(mBukulist.get(position).getHarga()));
        holder.mKuantitas.setText(String.valueOf(mBukulist.get(position).getPenulis()) + " " + mBukulist.get(position).getKategori());

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, AdminubahBuku.class);
                mIntent.putExtra("id", mBukulist.get(position).getId());
                mContext.startActivity(mIntent);
            }
        });
    }



    class BukulistViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mHarga;
        TextView mTitle;
        TextView mKuantitas;
        CardView mCardView;
        Button Edit;

        public BukulistViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.gambar_buku_admin_list);
            mTitle = itemView.findViewById(R.id.nama_buku_admin_list);
            mHarga = itemView.findViewById(R.id.harga_buku_admin_list);
            mCardView = itemView.findViewById(R.id.cardviewadminlist);
            Edit = itemView.findViewById(R.id.btn_edit_buku_admin_list);
            mKuantitas = itemView.findViewById(R.id.kuantitas);
            //mToggle = itemView.findViewById(R.id.toggle);
        }
    }


}
