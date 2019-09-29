package com.skripsi.nigel.esvira.Adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skripsi.nigel.esvira.Model.UserCompleted;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.Userdetailtransaksi;

import java.util.List;

public class UserCompletedAdapter extends RecyclerView.Adapter<UserCompletedAdapter.UserCompletedViewHolder> {
    private Context mContext;
    private List<UserCompleted> mUserCompleted;

    public UserCompletedAdapter(Context mContext, List<UserCompleted> mUserCompletedList) {
        this.mContext = mContext;
        this.mUserCompleted= mUserCompletedList;
    }

    @Override
    public UserCompletedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_fragment_completed, parent, false);
        return new UserCompletedViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final UserCompletedViewHolder holder, final int position) {
        holder.mTanggal.setText(mUserCompleted.get(position).getCreated_at());
        holder.mJam.setText(mUserCompleted.get(position).getStatus());
        holder.mBarang1.setText(mUserCompleted.get(position).getKode_transaksi());
        holder.mBarang3.setText(mUserCompleted.get(position).getMetode_pembayaran());

        holder.btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = "[ESVIRA MANDIRI] \n " +
                            "Nomor Transaksi : " + mUserCompleted.get(position).getKode_transaksi() +
                            "\n Hai, saya mau tanya order saya dengan nomor transaksi diatas ?";// Replace with your message.

                    String toNumber = "+6281334771117"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
                    Toast.makeText(mContext, toNumber,
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    mContext.startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

//                openWhatsApp(mContext, String.valueOf(applyList.get(position).getApplyID()));
            }
        });


        holder.cView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, Userdetailtransaksi.class);
                mIntent.putExtra("idtransaksi",mUserCompleted.get(position).getIdtransaksi());
                mContext.startActivity(mIntent);
            }
        });

    }
    @Override
    public int getItemCount() {

        return mUserCompleted.size();
    }

    class UserCompletedViewHolder extends RecyclerView.ViewHolder {


        TextView mBarang1;
        TextView mBarang2;
        TextView mBarang3;
        TextView mTanggal;
        TextView mJam;
        ImageView mGambar;
        CardView cView;
        Button btn_chat;

        UserCompletedViewHolder(View itemView) {
            super(itemView);

            btn_chat= itemView.findViewById(R.id.btn_chat);
            mBarang1= itemView.findViewById(R.id.textView_barang1);
            mBarang2 = itemView.findViewById(R.id.textView_barang2);
            mBarang3 = itemView.findViewById(R.id.textView_barang3);
            mJam = itemView.findViewById(R.id.textView_jam);
            mTanggal = itemView.findViewById(R.id.textView_tanggal);
            mGambar = itemView.findViewById(R.id.imageView);
            cView = itemView.findViewById(R.id.carddviewuser);

        }

    }}
