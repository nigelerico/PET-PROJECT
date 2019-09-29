package com.skripsi.nigel.esvira;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Adpater.BukuAdapter;
import com.skripsi.nigel.esvira.Adpater.CartAdapter;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SdFragment extends Fragment implements  Callbacks {


    Context mContext;
    int position, harga, total, jumlahsay = 1;
    BottomSheetDialog dialog;
    private String API_URL = Server.URL + "buku/dijual";
    private String API_URL_Search = Server.URL + "buku/search";
    private String API_URL_cart = Server.URL + "cart";
    ArrayList<Buku> listBuku = new ArrayList<Buku>();
    ArrayList<Buku> cartList;
    ArrayList<Integer> keranjang;
    ArrayList<Integer> jumlahbuku;
    private RecyclerView bottomSheetRecyclerview, recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior behavior;
    private View persistentbottomSheet;
    private TextView cart, totalHarga, opencart;
    private CartAdapter CartAdapter;
    // private Callbacks callbacks;
    private ScrollView scroll;
    SharedPreferences shared;
    SharedPreferences prefcart;
    int id;
    public static final String MyPREFERENCES = "myprefs";
    int id_user;
    public final static String TAG_ID = "id";
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    ;
    private BukuAdapter bukuAdapter;
    final Context context = this.getContext();
    public static final String session_status = "session_status";
    Boolean session = false;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        final View view = inflater.inflate(R.layout.fragment_sd, container, false);
        mContext = view.getContext();
        bukuAdapter = new BukuAdapter(mContext, listBuku, this);
        getBukuApi(view, bukuAdapter);

        //bottomsheet
        keranjang = new ArrayList<>();
        jumlahbuku = new ArrayList<>();
        cartList = new ArrayList<>();
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator);
        persistentbottomSheet = coordinatorLayout.findViewById(R.id.bottomsheet_buku);
        scroll = coordinatorLayout.findViewById(R.id.framehome);
        bottomSheetRecyclerview = coordinatorLayout.findViewById(R.id.recyclerview_bottom_sheet);
        bottomSheetRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        CartAdapter = new CartAdapter(mContext, cartList, this);
        bottomSheetRecyclerview.setAdapter(CartAdapter);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(persistentbottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        persistentbottomSheet.setVisibility(View.GONE);


        cart = (TextView) coordinatorLayout.findViewById(R.id.jumlah_cart);

        totalHarga = (TextView) coordinatorLayout.findViewById(R.id.Jumlah_total);
        opencart = (TextView) coordinatorLayout.findViewById(R.id.pay);
        sharedpreferences = this.getActivity().getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id_user = sharedpreferences.getInt(TAG_ID, 0);


        //open cart
        opencart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!session) {
                    startActivity(new Intent(getActivity(), Login.class));
                    Toast.makeText(getContext(), "Anda Harus Login Terlebih Dahulu", Toast.LENGTH_LONG).show();

                } else {
                    postKeranjang(API_URL_cart);

                }
            }
        });

        return view;
    }

    public void getBukuApi(final View view, final BukuAdapter buku) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                bukuAdapter.notifyDataSetChanged();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (jsonObject.getString("kategori").equals("SD/SMP/SMA")) {
                        listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
                                jsonObject.getInt("id"),
                                jsonObject.getString("nama"),
                                jsonObject.getInt("harga"),
                                jsonObject.getString("penulis"),
                                jsonObject.getString("kategori"),
                                jsonObject.getInt("jumlah")));
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview1);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        recyclerView.setAdapter(buku);
                    }
                        if (jsonObject.getString("kategori").equals("SD/SMP")) {
                            listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("nama"),
                                    jsonObject.getInt("harga"),
                                    jsonObject.getString("penulis"),
                                    jsonObject.getString("kategori"),
                                    jsonObject.getInt("jumlah")));
                            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview1);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(buku);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
    }

    public void getBukuApiAgama() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    listBuku.clear();
                    bukuAdapter.notifyDataSetChanged();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (jsonObject.getString("kategori").equals("agama")) {
                            listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("nama"),
                                    jsonObject.getInt("harga"),
                                    jsonObject.getString("penulis"),
                                    jsonObject.getString("kategori"),
                                    jsonObject.getInt("jumlah")));
                            bukuAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
    }

    public void getBukuApiSosial() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    listBuku.clear();
                    bukuAdapter.notifyDataSetChanged();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (jsonObject.getString("kategori").equals("sosial")) {
                            listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("nama"),
                                    jsonObject.getInt("harga"),
                                    jsonObject.getString("penulis"),
                                    jsonObject.getString("kategori"),
                                    jsonObject.getInt("jumlah")));
                            bukuAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
    }


    public void getBukuApiAll() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    listBuku.clear();
                    bukuAdapter.notifyDataSetChanged();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
                                jsonObject.getInt("id"),
                                jsonObject.getString("nama"),
                                jsonObject.getInt("harga"),
                                jsonObject.getString("penulis"),
                                jsonObject.getString("kategori"),
                                jsonObject.getInt("jumlah")));
                        bukuAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
    }

    public void postKeranjang(final String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {

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
                for (int i = 0; i < cartList.size(); i++) {
                    params.put("buku_id[" + i + "]", String.valueOf(cartList.get(i).getId()));
                    params.put("jumlah[" + i + "]", String.valueOf(cartList.get(i).getJumlah()));
                }
                return params;
            }

        };

        Singleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.drawer, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        super.onCreateOptionsMenu(menu,inflater);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                cariBuku(newText,context);
//                return false;
//            }
//        });
//    }

    private void cariBuku(final String keyword, final Context context) {

        StringRequest SearchReq = new StringRequest(Request.Method.POST, API_URL_Search,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String json = response.toString();
                        Log.e("Response: ", response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            listBuku.clear();
                            bukuAdapter.notifyDataSetChanged();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObjectArray = jsonArray.getJSONObject(i);
                                listBuku.add(new Buku(Server.URLIMAGE + jsonObjectArray.getString("foto"),
                                        jsonObjectArray.getInt("id"),
                                        jsonObjectArray.getString("nama"),
                                        jsonObjectArray.getInt("harga"),
                                        jsonObjectArray.getString("penulis"),
                                        jsonObjectArray.getString("kategori"),
                                        jsonObjectArray.getInt("jumlah")));
                                bukuAdapter.notifyDataSetChanged();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("keyword", keyword);
                return map;
            }
        };

        Singleton.getInstance(context).addToRequestQueue(SearchReq);

    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_sort) {
//            dialog();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void dialog() {
        String[] options = {"All Data", "Agama", "Sosial"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sort By");
        builder.setIcon(R.drawable.ic_sort_black_24dp);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (which == 0) {
                    getBukuApiAll();
                }
                if (which == 1) {
                    getBukuApiAgama();
                }
                if (which == 2) {
                    getBukuApiSosial();
                }
            }
        });

        builder.create().show();
    }


    public void setMarginNaik() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) scroll.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 129);
        scroll.setLayoutParams(layoutParams);

    }

    public void setMarginNol() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) scroll.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        scroll.setLayoutParams(layoutParams);

    }

    public void updateCart(Buku cartlist, int status, int jumlah) {
        if(status == 1){
            cartList.add(cartlist);
        }
        setMarginNaik();
        CartAdapter.addCartItems(cartlist.getId(), jumlah);
        cart.setText(String.valueOf(CartAdapter.getItemCount()));
        persistentbottomSheet.setVisibility(View.VISIBLE);
        if (CartAdapter.getItemCount()<1){
            setMarginNol();
            persistentbottomSheet.setVisibility(View.GONE);
        }
    }

    @Override
    public void hapuscart(int Position) {
        CartAdapter.removeAt(position);
        if (cartList.size()==0){
            persistentbottomSheet.setVisibility(View.GONE);
        }
        cart.setText(String.valueOf(cartList.size()));
    }

    @Override
    public void updateJumlah(int i, int jumlah) {
    }


    @Override
    public void updateharga(int harga, int status) {
        if(harga==0){
            persistentbottomSheet.setVisibility(View.GONE);
        }
        totalHarga.setText(String.valueOf(harga));
    }

}



