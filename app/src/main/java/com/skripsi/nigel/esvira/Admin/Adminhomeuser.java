package com.skripsi.nigel.esvira.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.skripsi.nigel.esvira.Adpater.AdminuserAdapter;
import com.skripsi.nigel.esvira.Login;
import com.skripsi.nigel.esvira.Model.Userdata;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adminhomeuser extends Fragment {
//modelnya Userdata
//adapternya adminuseradapater
    private String API_URL = Server.URL + "user";
    private String API_URL_Search = Server.URL + "user/search";;
    ArrayList<Userdata> userdata = new ArrayList<>();
    String token;
    final Context context = this.getContext();
    private AdminuserAdapter adminuserAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View view = inflater.inflate(R.layout.adminhomeuser, container, false);
        adminuserAdapter = new AdminuserAdapter(view.getContext(),userdata);
        SharedPreferences sharedpreferencesAdmnin = this.getActivity().getSharedPreferences(Login.my_shared_preferences2, Context.MODE_PRIVATE);
        token = "Bearer "+sharedpreferencesAdmnin.getString("token", null);
        getUserApi(API_URL, view, adminuserAdapter);
        return view;
    }


    public void getUserApi(String url, final View view, final AdminuserAdapter buku){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        if(jsonObject.getString("tipe").equals("user")){
                            userdata.add(new Userdata(jsonObject.getInt("id"),Server.URLIMAGE+jsonObject.getString("foto"), jsonObject.getString("name") ,jsonObject.getString("email"), jsonObject.getString("nomor_telepon")));
                            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewadminhomeuser);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(buku);
                        }
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
        inflater.inflate(R.menu.admin_action_bar_user, menu);
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
                            userdata.clear();
                            adminuserAdapter.notifyDataSetChanged();

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObjectArray = jsonArray.getJSONObject(i);
                                userdata.add(new Userdata(jsonObjectArray.getInt("id"),Server.URLIMAGE+jsonObjectArray.getString("foto"), jsonObjectArray.getString("name") ,jsonObjectArray.getString("email"), jsonObjectArray.getString("nomor_telepon")));
                                adminuserAdapter.notifyDataSetChanged();
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
