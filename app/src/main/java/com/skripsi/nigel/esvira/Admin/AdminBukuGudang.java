package com.skripsi.nigel.esvira.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Adpater.BukuGudangAdapter;
import com.skripsi.nigel.esvira.Login;
import com.skripsi.nigel.esvira.Model.BukuGudangModel;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminBukuGudang extends Fragment {
    private String API_URL = Server.URL + "bukushow";
    private String API_URL_Search = Server.URL + "buku/gudang/search";;
    ArrayList<BukuGudangModel> bukuGudangList = new ArrayList<BukuGudangModel>();
    SwipeRefreshLayout swipeRefreshLayout;
    final Context context = this.getContext();
    private BukuGudangAdapter bukuGudangAdapter;
    String token;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View view = inflater.inflate(R.layout.activity_buku_gudang, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipecontainer);
        bukuGudangAdapter = new BukuGudangAdapter(view.getContext(), bukuGudangList);
        getBukuApi(API_URL, view, bukuGudangAdapter);
        SharedPreferences sharedpreferencesAdmnin = this.getActivity().getSharedPreferences(Login.my_shared_preferences2, Context.MODE_PRIVATE);
        token = "Bearer "+sharedpreferencesAdmnin.getString("token", null);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        bukuGudangAdapter.clear();
                        getBukuApi(API_URL, view, bukuGudangAdapter);
                    }
                }, 1000);
            }
        });
        return view;
    }

    public void getBukuApi(String url, final View view, final BukuGudangAdapter buku){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               try{
                   for (int i = 0; i < response.length(); i++){
                       JSONObject jsonObject = response.getJSONObject(i);
                       bukuGudangList.add(new BukuGudangModel(jsonObject.getInt("id") ,Server.URLIMAGE+jsonObject.getString("foto") ,jsonObject.getString("nama"), jsonObject.getInt("harga"), jsonObject.getInt("jumlah"), jsonObject.getString("penulis"), jsonObject.getString("kategori")));
                       RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewbukugudang);
                       GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                       recyclerView.setLayoutManager(gridLayoutManager);
                       recyclerView.setAdapter(buku);
                   }
               }catch (JSONException e){
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", token);
                return headers;
            }

        };
        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.admin_action_bar_gudang, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setIconified(true);

        super.onCreateOptionsMenu(menu,inflater);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cariData(newText, context);
                return false;
            }
        });
    }

    private void cariData(final String keyword, final Context context ) {
        StringRequest SearchReq = new StringRequest(Request.Method.POST, API_URL_Search,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        String json = response.toString();
                        Log.e("Response: ", response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            bukuGudangList.clear();
                            bukuGudangAdapter.notifyDataSetChanged();

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObjectArray = jsonArray.getJSONObject(i);
                                bukuGudangList.add(new BukuGudangModel(jsonObjectArray.getInt("id") ,Server.URLIMAGE+jsonObjectArray.getString("foto") ,jsonObjectArray.getString("nama"), jsonObjectArray.getInt("harga"), jsonObjectArray.getInt("jumlah"), jsonObjectArray.getString("penulis"), jsonObjectArray.getString("kategori")));
                                bukuGudangAdapter.notifyDataSetChanged();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", token);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("keyword",keyword);
                return map;
            }
        };
        Singleton.getInstance(context).addToRequestQueue(SearchReq);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
