package com.skripsi.nigel.esvira.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.skripsi.nigel.esvira.Adpater.AdminInprogressAdapter;
import com.skripsi.nigel.esvira.Model.AdminInprogress;
import com.skripsi.nigel.esvira.R;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentAdminHistoryInprogress extends Fragment {
    ArrayList<AdminInprogress> adminInprogress = new ArrayList<>();
    private String API_URL_T = Server.URL + "transaksi/list";
    private RecyclerView mList;
    public final static String TAG_ID = "id";
    SharedPreferences sharedpreferences;
    private AdminInprogressAdapter adapter;
    int id_user;
    String status;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_admin_history_inprogress, container, false);
        mContext = view.getContext();
        adapter = new AdminInprogressAdapter(mContext,adminInprogress);
        getTransaksi(API_URL_T,view,adapter);

        return view;
    }
    public void getTransaksi(String url, final View view, final AdminInprogressAdapter keranjang){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String json = response.toString();
                Log.e("Response:", response.toString());
                try{
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (jsonObject.getString("status_transaksi").equals("0")|| jsonObject.getString("status_transaksi").equals("1")){
                            if (jsonObject.getString("status_transaksi").equals("0")){
                                status="On Progress";
                            }else {
                                status="On Delivery";
                            }
                            adminInprogress.add(new AdminInprogress(jsonObject.getString("id"),jsonObject.getString("kode_transaksi"),status,
                                    jsonObject.getString("created_at"),jsonObject.getString("metode_transaksi")));
                            mList = (RecyclerView) view.findViewById(R.id.recycler_admin_history_inporgress);
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mList.setLayoutManager(mLayoutManager);
                            mList.setAdapter(keranjang);;
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
        });
        Singleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);
    }
}