package com.skripsi.nigel.esvira;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Admin.Admindetailtransaksi;
import com.skripsi.nigel.esvira.Adpater.Checkout;
import com.skripsi.nigel.esvira.Adpater.KeranjangAdapter;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeranjangFragment extends Fragment implements Callbacks {


    ArrayList<Buku> listKeranjang = new ArrayList<Buku>();
    private KeranjangAdapter adapter;
    private RecyclerView mList;
    Context mContext;
    private String API_KERANJANG = Server.URL + "cart/list";
    private String API_URL_cart = Server.URL + "cart";
    int jumlahbuku , counter , multiplier = 1;
    private TextView tvPlaceAPI, txTotalharga;
    int id_user,totalharga,ongkir, totalbelanja;
    SharedPreferences sharedpreferences;
    public final static String TAG_ID = "id";
    Button btn_checkout;
    public static final String my_shared_preferences = "my_shared_preferences";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keranjang, container, false);

        adapter = new KeranjangAdapter(mContext,listKeranjang,this);
        sharedpreferences = this.getActivity().getSharedPreferences(my_shared_preferences,Context.MODE_PRIVATE);
        id_user = sharedpreferences.getInt(TAG_ID, 0);
        mContext = view.getContext();
        btn_checkout = (Button) view.findViewById(R.id.button_checkout);
        txTotalharga = (TextView) view.findViewById(R.id.editText_harga_barang);
        ambilkeranjang(view,adapter);
        ongkir=22000;
    //    Toast.makeText(getContext(), "Silahkan lengkapi dulu data pengiriman" + 0.7*3, Toast.LENGTH_LONG).show();

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    postKeranjang();


            }
        });

        return  view;
    }




    private void ambilkeranjang (final View view, final KeranjangAdapter keranjang){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_KERANJANG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                Log.e("Response:", response.toString());
                listKeranjang.clear();
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        listKeranjang.add(new Buku(Server.URLIMAGE+ jsonobject.getString("foto"),jsonobject.getInt("buku_id"),
                                jsonobject.getString("nama"),jsonobject.getInt("harga"),jsonobject.getInt("jumlah_buku"),
                                jsonobject.getInt("jumlah")));
                        mList = (RecyclerView) view.findViewById(R.id.recyclerview_checkout);
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                        mList.setLayoutManager(mLayoutManager);
                        mList.setAdapter(keranjang);
                        totalharga = totalharga+jsonobject.getInt("jumlah_buku")*jsonobject.getInt("harga");
                        jumlahbuku = jumlahbuku+jsonobject.getInt("jumlah_buku");
                    }

                    txTotalharga.setText("Rp. "+String.valueOf(totalharga));

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
                params.put("id", String.valueOf(id_user));
                return params;
            }
        };

        Singleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }



//    int totJumlah = 0;
    public void postKeranjang() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL_cart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
//                    Intent intent = new Intent(getActivity(), Chart.class);
//                    intent.putExtra("totJumlah", totJumlah);
//                    Toast.makeText(mContext, ""+totJumlah, Toast.LENGTH_SHORT).show();
//                    totJumlah = 0;
//                    startActivity(intent);
                    startActivity(new Intent(getActivity(), Chart.class));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", String.valueOf(id_user));
                for (int i = 0; i < listKeranjang.size(); i++) {
                    params.put("buku_id[" + i + "]", String.valueOf(listKeranjang.get(i).getId()));
                    params.put("jumlah[" + i + "]", String.valueOf(listKeranjang.get(i).getJumlah()));
                 //   totJumlah = totJumlah + listKeranjang.get(i).getJumlah();
                }
                return params;
            }

        };

        Singleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }


    @Override
    public void updateCart(Buku listkeranjang, int status, int jumlah) {
        adapter.addCartItems(listkeranjang.getId(), jumlah);
    }

    @Override
    public void hapuscart(int Position) {

    }

    @Override
    public void updateJumlah(int jumlah, int status) {
        if (status==1) {
            jumlahbuku = jumlahbuku - 1;
            counter = 0;
            for (int i = 1; i<=jumlahbuku; i++){
                if (counter == 1){
                    multiplier++;
                    counter = 0;
                }
                if(i>0 && i%5==0){
                    counter++;
                }
            }
            ongkir=22000*multiplier;
            //Toast.makeText(mContext,"j :" + jumlahbuku +"m :" + multiplier +"ongkir :" + ongkir,Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "Silahkan lengkapi dulu data pengiriman" + ongkir, Toast.LENGTH_LONG).show();
            multiplier = 1;

        }else {
            jumlahbuku=jumlahbuku + 1;
            counter = 0;
            for (int i = 1; i<=jumlahbuku; i++){
                if (counter == 1){
                    multiplier++;
                    counter = 0;
                }
                if(i>0 && i%5==0){
                    counter++;
                }
            }
            ongkir=22000*multiplier;
            //Toast.makeText(mContext,"j :" + jumlahbuku +"m :" + multiplier +"ongkir :" + ongkir,Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "Silahkan lengkapi dulu data pengiriman" + ongkir, Toast.LENGTH_LONG).show();

            multiplier = 1;
        }
    }

    @Override
    public void updateharga(int harga, int status) {

        if (status==1){
            // jumlahbuku=jumlahbuku-jumlah;
            totalharga=totalharga-harga;
            txTotalharga.setText("Rp. "+String.valueOf(totalharga));

        }else{
            // jumlahbuku=jumlahbuku+jumlah;
            totalharga=totalharga+harga;
            txTotalharga.setText("Rp. "+String.valueOf(totalharga));

        }

    }
}

