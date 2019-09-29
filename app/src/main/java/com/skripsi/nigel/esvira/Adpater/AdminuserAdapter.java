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
import com.skripsi.nigel.esvira.Admin.Adminviewuser;
import com.skripsi.nigel.esvira.Model.Userdata;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import java.util.List;

public class AdminuserAdapter extends RecyclerView.Adapter<AdminuserAdapter.UserViewHolder> {
    private Context mContext;
    private List<Userdata> mUserList;

    public AdminuserAdapter(Context mContext, List<Userdata> mUserListList) {
        this.mContext = mContext;
        this.mUserList = mUserListList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem_row_adminuser, parent, false);
        return new UserViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        ImageLoader imageLoader = Singleton.getInstance(mContext).getImageLoader();
        imageLoader.get(mUserList.get(position).getFoto(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.mGambar.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        holder.mNama.setText(mUserList.get(position).getName());
        holder.mEmail.setText(mUserList.get(position).getEmail());
        holder.mNotelp.setText(mUserList.get(position).getNomor_telepon());

        holder.btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, Adminviewuser.class);
                mIntent.putExtra("id", mUserList.get(position).getId());
                mContext.startActivity(mIntent);
            }
        });

    }
    @Override
    public int getItemCount() {

        return mUserList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {


        TextView mNama;
        TextView mEmail;
        TextView mNotelp;
        ImageView mGambar;
        CardView mCardView;
        Button btn_lihat;

        UserViewHolder(View itemView) {
            super(itemView);

            mGambar = itemView.findViewById(R.id.gambaruser);
            mNama= itemView.findViewById(R.id.txt_username);
            mEmail = itemView.findViewById(R.id.txt_email);
            mNotelp = itemView.findViewById(R.id.txt_notelp);
            mCardView = itemView.findViewById(R.id.carddviewuser);
            btn_lihat = itemView.findViewById(R.id.btn_lihat_buku);
        }

    }}
