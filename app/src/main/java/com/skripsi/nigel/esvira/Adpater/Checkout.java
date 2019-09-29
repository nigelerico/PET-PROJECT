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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.skripsi.nigel.esvira.Callbacks;
import com.skripsi.nigel.esvira.Detailbuku;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import java.util.ArrayList;

public class Checkout extends RecyclerView.Adapter<Checkout .MyViewHolder> {
    private int total=0, harga;
    private int jumlah=0;
    Callbacks callbacks;
    Context mCtx;
    ArrayList<Buku>keranjang;

    int ongkir=22000;
    int multiplier = 1;
    int counter = 0;

    int jAja, jTotal=0;

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }


    public Checkout (Context context, ArrayList<Buku> keranjang, Callbacks callbacks){
        this.mCtx = context;
        this.callbacks = callbacks;
        this.keranjang = keranjang;
    }
    public void addCartItems(int id, int jumlah){

        for(int i = 0; i < this.keranjang.size(); i++) {
            if(jumlah < 1){
                if (this.keranjang.get(i).getId() == id) {
                    this.keranjang.remove(i);
                    notifyItemRemoved(i);
                }
            }else{

                if (this.keranjang.get(i).getId() == id) {
                    this.keranjang.get(i).setJumlah(jumlah);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void total(int harga){
        total = total+harga;
    }
    public void kurangtotal(int harga){
        total = total-harga;
    }


    public void jTotal(int jAja){
        jTotal = jTotal+jAja;
    }
    public void kTotal(int jAja){  jTotal = jTotal-jAja; }

    public void harga(int harga){
        this.harga=harga;
    }

    public  int getHarga(){
        return this.harga;
    }

    public void jumlah(int jumlah){
        this.jumlah=jumlah;
    }

    public  int getJumlah(){
        return this.jumlah;
    }


    @Override
    public Checkout .MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_row ,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        jumlah = Integer.parseInt(holder.itemKuan.getText().toString());
        ImageLoader imageLoader = Singleton.getInstance(mCtx).getImageLoader();
        holder.itemTitle.setText(keranjang.get(position).getNama());
        holder.itemKuan.setText(String.valueOf(keranjang.get(position).getJumlah()));
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

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mCtx, Detailbuku.class);
                mIntent.putExtra("id",keranjang.get(position).getId());
                mCtx.startActivity(mIntent);
            }
        });

//        holder.btn_minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                jumlah = Integer.parseInt(holder.itemKuan.getText().toString());
//                if(jumlah<=1){
//                    jumlah=0;
//                    kurangtotal(keranjang.get(position).getHarga());
//                    kTotal(keranjang.get(position).getJumlah());
//                    callbacks.updateJumlah(keranjang.get(position).getJumlah(),1);
//                    callbacks.updateharga(keranjang.get(position).getHarga(),1);
//                    callbacks.updateCart(keranjang.get(position),2,jumlah);
//                }else {
//                    kurangtotal(keranjang.get(position).getHarga());
//                    kTotal(keranjang.get(position).getJumlah());
//                    callbacks.updateJumlah(keranjang.get(position).getJumlah(),1);
//                    callbacks.updateharga(keranjang.get(position).getHarga(),1);
//                    holder.itemKuan.setText(String.valueOf(--jumlah));
//                    //ikiloooo
////                    counter = 0;
////                    for (int i = 1; i<=jumlah; i++){
////                        if (counter == 1){
////                            multiplier++;
////                            counter = 0;
////                        }
////                        if(i>0 && i%5==0){
////                            counter++;
////                        }
////                    }
////                    ongkir=22000*multiplier;
////                    Toast.makeText(mContext,"j :" + jumlah +"m :" + multiplier +"ongkir :" + ongkir,Toast.LENGTH_LONG).show();
////                    multiplier = 1;
//                    //sampek keneloooo
//                    callbacks.updateCart(keranjang.get(position),2,jumlah);
//                }
//
//            }
//        });
//        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                jumlah = Integer.parseInt(holder.itemKuan.getText().toString());
//                //Toast.makeText(mContext,"ASU" + jumlah + "KoNT"+keranjang.get(position).getStok() +"L",Toast.LENGTH_LONG).show();
//
//                if(jumlah>=keranjang.get(position).getStok()){
//                    jumlah = keranjang.get(position).getStok() - 1;
//                    Toast.makeText(mCtx," Stok telah mencapai batas " ,Toast.LENGTH_LONG).show();
//                    //holder.mTambahQ.getBackground().setAlpha(64);
//                }else{
//                    callbacks.updateJumlah(keranjang.get(position).getJumlah(),0);
//                    callbacks.updateharga(keranjang.get(position).getHarga(),0);
//                   // callbacks.updateharga(total,1);
//                }
//                holder.itemKuan.setText(String.valueOf(jumlah++));
//                callbacks.updateCart(keranjang.get(position),2,jumlah);
//            }
//        });
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                notifyItemRemoved(position);
//                int harga = keranjang.get(position).getHarga() * jumlah;
//                keranjang.remove(position);
//                kurangtotal(harga);
//                callbacks.updateharga(harga,1);
                notifyItemRemoved(position);
                kurangtotal(keranjang.get(position).getHarga());
                kTotal(keranjang.get(position).getJumlah());
                callbacks.updateJumlah(keranjang.get(position).getJumlah(),1);
                callbacks.updateharga(keranjang.get(position).getHarga(), 1);
                callbacks.updateCart(keranjang.get(position),2,jumlah);
                keranjang.remove(position);
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
        CardView card;
        TextView tvjumlah;
        public MyViewHolder(View itemView) {
            super(itemView);
         //   btn_minus = (Button) itemView.findViewById(R.id.buttonMinus);
          //  btn_plus = (Button) itemView.findViewById(R.id.buttonPlus);
            btn_del = (Button) itemView.findViewById(R.id.button_delete);
            itemTitle = (TextView)itemView.findViewById(R.id.cart_namaBuku);
            itemKuan = (TextView) itemView.findViewById(R.id.textView_jumlahBuku);
            itemPrice = (TextView) itemView.findViewById(R.id.cart_hargaBuku);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            card = (CardView) itemView.findViewById(R.id.cardView);

        }
    }

}