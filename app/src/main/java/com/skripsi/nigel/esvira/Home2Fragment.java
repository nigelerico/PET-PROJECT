package com.skripsi.nigel.esvira;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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
import com.skripsi.nigel.esvira.Adpater.PageAdapter;
import com.skripsi.nigel.esvira.Adpater.PageAdapterHome;
import com.skripsi.nigel.esvira.Fragment.FragmentHelp;
import com.skripsi.nigel.esvira.Fragment.HomeFragment;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Home2Fragment extends Fragment {

    Context mContext;
    private String API_URL_Search = Server.URL + "buku/search";
    ArrayList<Buku> listBuku = new ArrayList<Buku>();
    private BukuAdapter bukuAdapter;
    final Context context = this.getContext();
    private View persistentbottomSheet;
    int position,harga,total,jumlahsay=1;
    BottomSheetDialog dialog;
    private String API_URL = Server.URL + "buku/dijual";
    private String API_URL_cart = Server.URL + "cart";
    ArrayList<Buku> cartList;
    ArrayList<Integer> keranjang;
    ArrayList<Integer> jumlahbuku;
    private RecyclerView bottomSheetRecyclerview, recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior behavior;
    private TextView cart,totalHarga, opencart;
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
    public static final String session_status = "session_status";
    Boolean session = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);



        View view = inflater.inflate(R.layout.fragment_home3, container, false);
        mContext = view.getContext();
     //   bukuAdapter = new BukuAdapter(mContext, listBuku, this);

        //bottomsheet
        keranjang = new ArrayList<>();
        jumlahbuku = new ArrayList<>();
        cartList = new ArrayList<>();
        coordinatorLayout = (CoordinatorLayout)view.findViewById(R.id.coordinator);
        persistentbottomSheet = coordinatorLayout.findViewById(R.id.bottomsheet_buku);
        scroll = coordinatorLayout.findViewById(R.id.framehome);
        bottomSheetRecyclerview = coordinatorLayout.findViewById(R.id.recyclerview_bottom_sheet);
        bottomSheetRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
       // CartAdapter = new CartAdapter(mContext,cartList, this);
        bottomSheetRecyclerview.setAdapter(CartAdapter);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(persistentbottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        persistentbottomSheet.setVisibility(View.GONE);


        cart = (TextView) coordinatorLayout.findViewById(R.id.jumlah_cart);

        totalHarga=(TextView) coordinatorLayout.findViewById(R.id.Jumlah_total);
        opencart= (TextView)coordinatorLayout.findViewById(R.id.pay);
        sharedpreferences = this.getActivity().getSharedPreferences(my_shared_preferences,Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id_user = sharedpreferences.getInt(TAG_ID, 0);


        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_home);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("SD"));
        tabLayout.addTab(tabLayout.newTab().setText("SMP"));
        tabLayout.addTab(tabLayout.newTab().setText("SMA"));
        tabLayout.addTab(tabLayout.newTab().setText("UMUM"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final PageAdapterHome adapter = new PageAdapterHome
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

//        opencart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!session) {
//                    startActivity(new Intent(getActivity(), Login.class));
//                    Toast.makeText(getContext(), "Anda Harus Login Terlebih Dahulu", Toast.LENGTH_LONG).show();
//
//                } else {
//                    postKeranjang(API_URL_cart);
//
//                }
//            }
//        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {
            Fragment fragment = new HomeFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutDrawer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


            return true;
        }

        if (id == R.id.action_help) {
            startActivity(new Intent(getActivity(), FragmentHelp.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.drawer, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


//    public void setMarginNaik(){
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) scroll.getLayoutParams();
//        layoutParams.setMargins(0,0,0,129);
//        scroll.setLayoutParams(layoutParams);
//
//    }
//    public void setMarginNol(){
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) scroll.getLayoutParams();
//        layoutParams.setMargins(0,0,0,0);
//        scroll.setLayoutParams(layoutParams);
//
//    }
//
//
//    @Override
//    public void updateCart(Buku cartlist, int status, int jumlah) {
//        if(status == 1){
//            cartList.add(cartlist);
//        }
//        setMarginNaik();
//        CartAdapter.addCartItems(cartlist.getId(), jumlah);
//        cart.setText(String.valueOf(CartAdapter.getItemCount()));
//        persistentbottomSheet.setVisibility(View.VISIBLE);
//        if (CartAdapter.getItemCount()<1){
//            setMarginNol();
//            persistentbottomSheet.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void hapuscart(int Position) {
//        CartAdapter.removeAt(position);
//        if (cartList.size()==0){
//            persistentbottomSheet.setVisibility(View.GONE);
//        }
//        cart.setText(String.valueOf(cartList.size()));
//    }
//
//    @Override
//    public void updateJumlah(int jumlah, int status) {
//
//    }
//
//    @Override
//    public void updateharga(int harga, int status) {
//        if(harga==0){
//            persistentbottomSheet.setVisibility(View.GONE);
//        }
//        totalHarga.setText(String.valueOf(harga));
//    }
//
//
//    public void getBukuApi(final View view, final BukuAdapter buku) {
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                bukuAdapter.notifyDataSetChanged();
//                try {
//                    for (int i = 0; i < response.length(); i++) {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
//                                jsonObject.getInt("id"),
//                                jsonObject.getString("nama"),
//                                jsonObject.getInt("harga"),
//                                jsonObject.getString("penulis"),
//                                jsonObject.getString("kategori"),
//                                jsonObject.getInt("jumlah")));
//                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview1);
//                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//                        recyclerView.setLayoutManager(gridLayoutManager);
//                        recyclerView.setAdapter(buku);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
//    }
//
//    public void getBukuApiAgama() {
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    listBuku.clear();
//                    bukuAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < response.length(); i++) {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        if (jsonObject.getString("kategori").equals("agama")) {
//                            listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
//                                    jsonObject.getInt("id"),
//                                    jsonObject.getString("nama"),
//                                    jsonObject.getInt("harga"),
//                                    jsonObject.getString("penulis"),
//                                    jsonObject.getString("kategori"),
//                                    jsonObject.getInt("jumlah")));
//                            bukuAdapter.notifyDataSetChanged();
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
//    }
//
//    public void getBukuApiSosial() {
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    listBuku.clear();
//                    bukuAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < response.length(); i++) {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        if (jsonObject.getString("kategori").equals("sosial")) {
//                            listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
//                                    jsonObject.getInt("id"),
//                                    jsonObject.getString("nama"),
//                                    jsonObject.getInt("harga"),
//                                    jsonObject.getString("penulis"),
//                                    jsonObject.getString("kategori"),
//                                    jsonObject.getInt("jumlah")));
//                            bukuAdapter.notifyDataSetChanged();
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
//    }
//
//
//    public void getBukuApiAll() {
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    listBuku.clear();
//                    bukuAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < response.length(); i++) {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        listBuku.add(new Buku(Server.URLIMAGE + jsonObject.getString("foto"),
//                                jsonObject.getInt("id"),
//                                jsonObject.getString("nama"),
//                                jsonObject.getInt("harga"),
//                                jsonObject.getString("penulis"),
//                                jsonObject.getString("kategori"),
//                                jsonObject.getInt("jumlah")));
//                        bukuAdapter.notifyDataSetChanged();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
//    }
//
//    public void postKeranjang(final String url) {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                String json = response.toString();
//                try {
//
//                    startActivity(new Intent(getActivity(), Chart.class));
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                error.printStackTrace();
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting parameters to login url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("user_id", String.valueOf(id_user));
//                for (int i = 0; i < cartList.size(); i++) {
//                    params.put("buku_id[" + i + "]", String.valueOf(cartList.get(i).getId()));
//                    params.put("jumlah[" + i + "]", String.valueOf(cartList.get(i).getJumlah()));
//                }
//                return params;
//            }
//
//        };
//
//        Singleton.getInstance(mContext).addToRequestQueue(stringRequest);
//    }


}








