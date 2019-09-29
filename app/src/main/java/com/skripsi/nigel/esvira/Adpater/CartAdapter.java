package com.skripsi.nigel.esvira.Adpater;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.skripsi.nigel.esvira.Callbacks;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private int counter = 1;
    private int harga;
    private Context mContext;
    SharedPreferences sharedpreferences, sharedPreferences_cart;
    public static final String shared_preferences_cart = "shared_preferences_cart";

    public static final String TAG_CART_ID="id_buku";
    public static final String TAG_CART_JUMLAH="jumlah_buku";

    private CoordinatorLayout coordinatorLayout;
    private TextView cart;

    private ArrayList<Buku> cartList;
    private Callbacks callbacks;
    public CartAdapter(Context context, ArrayList<Buku> cartList, Callbacks callbacks){
        this.callbacks = callbacks;
        this.cartList = cartList;
    }

    public void addCartItems(ArrayList<Buku> cartList){
        this.cartList = cartList;
        notifyDataSetChanged();
        }
    public void removeAt(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
    }
    public void addCartItems(int id, int jumlah){

        for(int i = 0; i < this.cartList.size(); i++) {
            if(jumlah < 1){
                if (this.cartList.get(i).getId() == id) {
                    this.cartList.remove(i);
                    notifyItemRemoved(i);
                }
            }else{

                if (this.cartList.get(i).getId() == id) {
                    this.cartList.get(i).setJumlah(jumlah);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void harga(int harga){
        this.harga=harga;
    }
    public  int getHarga(){
        return this.harga;
    }


@Override
public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottomsheet_row ,parent,false);

        return new MyViewHolder(view);
        }

@Override
public void onBindViewHolder(final CartAdapter.MyViewHolder holder, final int position) {
    ImageLoader imageLoader = Singleton.getInstance(mContext).getImageLoader();
    imageLoader.get(cartList.get(position).getFoto(), new ImageLoader.ImageListener() {
        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            holder.gambar.setImageBitmap(response.getBitmap());
        }
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });
    holder.itemTitle.setText(cartList.get(position).getNama());
    holder.itemPrice.setText("Rp. "+cartList.get(position).getHarga());
    holder.tvjumlah.setText("qty : "+String.valueOf(cartList.get(position).getJumlah()));
        }


@Override
public int getItemCount() {
        return cartList.size();
        }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView itemTitle,itemPrice, itemKuan;
    Button btn_minus;
    Button btn_plus , btn_del;
    TextView tvjumlah;
    ImageView gambar;
    public MyViewHolder(View itemView) {
        super(itemView);
        gambar  = (ImageView) itemView.findViewById(R.id.imageView);
        itemTitle = (TextView)itemView.findViewById(R.id.itemTitle);
        itemPrice = (TextView)itemView.findViewById(R.id.itemPrice);
        itemKuan = (TextView)itemView.findViewById(R.id.textView_kuantitas);
        tvjumlah = (TextView) itemView.findViewById(R.id.textView_jumlahBuku);
        btn_del = (Button) itemView.findViewById(R.id.button_delete);
    }
}

}