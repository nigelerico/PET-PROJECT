package com.skripsi.nigel.esvira;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.Adpater.Adapterusertransaksi;
import com.skripsi.nigel.esvira.Adpater.Checkout;
import com.skripsi.nigel.esvira.Adpater.OngkirAdapter;
import com.skripsi.nigel.esvira.Model.Buku;
import com.skripsi.nigel.esvira.Model.Ongkir;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Chart extends AppCompatActivity implements Callbacks{

    private Button btPlacesAPI;
    private TextView tvPlaceAPI, txTotalharga,txOngkir,txTotal;
    private int PLACE_PICKER_REQUEST = 1;
    Button button_checkout;
    Intent intent;
    Button btndatepicker;
    Calendar cal,cal1;
    long maxDate;
    Date date;
    private ActionBar toolbar;
    private int temp = 0;
    TextView txtdatepicker;
    private SimpleDateFormat dateFormatter;
    Spinner Spinnerjam, spinner_metode;
    private String API_KERANJANG = Server.URL + "cart/list";
    private String API_TRANSAKSI = Server.URL + "transaksi";
    private String API_ONGKIR = Server.URL + "buku/ongkoskirim";
    private String API_GET_ONGKIR = Server.URL + "transaksi/getOngkir";
    int id_user,totalharga, totalbelanja;
    int ongkir;
    public final static String TAG_ID = "id";
    public static final String TAG_ALAMAT = "alamat_user";
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    ArrayList<Buku> listKeranjang = new ArrayList<Buku>();;
    Context mContext;
    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    private Checkout adapter;
    String tanggal_kirim, waktu, alamat,metode_p;
    private TextView note_alamat;
    int jumlahbuku , counter , multiplier = 1;
    Double des;




    private static final String TAG = Chart.class.getSimpleName();

    AutoCompleteTextView auto_kota;
    Spinner spinner_kota;
    ProgressDialog pDialog;
    OngkirAdapter adapterongkir;
    ArrayList<Ongkir> listkota = new ArrayList<Ongkir>();
    String kota_pengiriman;
    double berat,totberat;
    String part1,part2 ,temp1;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_layout);
        toolbar = getSupportActionBar();
        sharedpreferences = this.getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id_user = sharedpreferences.getInt(TAG_ID, 0);
        alamat = sharedpreferences.getString(TAG_ALAMAT,null);
        mContext = getApplicationContext();
        toolbar.setTitle("Checkout");
        adapter = new Checkout(mContext,listKeranjang,this);
        ambilkeranjang(API_KERANJANG,adapter);

        getOngkir();

        // ongkir=22000;

        String[] number = new String[]{
                "08.00 - 11.00 WIB",
                "12.00 - 15.00 WIB",
                "16.00 - 19.00 WIB"
        };

        String[] metodepembayaran = new String[]{
                "TRANSFER MANDIRI",
                "CASH ON DELIVERY"
        };

        cal = Calendar.getInstance();
        cal1 = Calendar.getInstance();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        btndatepicker = (Button) findViewById(R.id.buttondatepicker);
        button_checkout = (Button) findViewById(R.id.button_checkout);
        tvPlaceAPI = (TextView) findViewById(R.id.textView_alamat);
        txtdatepicker = (TextView) findViewById(R.id.textView_hari);
        btPlacesAPI = (Button) findViewById(R.id.buttonpick);
        txTotalharga = (TextView) findViewById(R.id.editText_harga_barang);
        txOngkir = (TextView) findViewById(R.id.editText_harga_pengiriman);
        txTotal = (TextView) findViewById(R.id.editText_total);
        txOngkir.setText("Rp. "+String.valueOf(ongkir));
        tvPlaceAPI.setText(alamat);

        // jumlahbuku = getIntent().getIntExtra("totJumlah",0);

        note_alamat = (TextView) findViewById(R.id.note_alamat);

        Spinnerjam = (Spinner) findViewById(R.id.spinner_jam);
        spinner_metode  = (Spinner) findViewById(R.id.spinner_pembayaran);
        //spinner_kota  = (Spinner) findViewById(R.id.spinner_kota);
        auto_kota  = (AutoCompleteTextView) findViewById(R.id.input_id);
        auto_kota.setThreshold(1);



        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(

                this,R.layout.spinner_item,number );

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        Spinnerjam.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,metodepembayaran );

        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        spinner_metode.setAdapter(spinnerArrayAdapter2);
        auto_kota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ongkir = listkota.get(position).getHarga_ongkir();
                ongkir = ongkir * Integer.parseInt(part1);
                txOngkir.setText("Rp. "+String.valueOf( Math.round(ongkir)));
                txTotalharga.setText("Rp. "+String.valueOf(totalharga));
                txTotal.setText("Rp. "+String.valueOf(totalharga+Math.round(ongkir)));
            }
        });




        callData();


//
//        spinner_metode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int a, long l) {
//                if(spinner_metode.getSelectedItem().toString().equals("CASH ON DELIVERY")){
//                    ongkir = 17000;
//                    counter = 0;
//                    for (int i = 1; i<=jumlahbuku; i++){
//                        if (counter == 1){
//                            multiplier++;
//                            counter = 0;
//                        }
//                        if(i>0 && i%5==0){
//                            counter++;
//                        }
//                    }
//                    ongkir=ongkir*multiplier;
//                    txOngkir.setText("Rp. "+String.valueOf(ongkir));
//                    multiplier = 1;
//
//                } if (spinner_metode.getSelectedItem().toString().equals("TRANSFER MANDIRI")){
//                    ongkir = 18000;
//                    counter = 0;
//                    for (int i = 1; i<=jumlahbuku; i++){
//                        if (counter == 1){
//                            multiplier++;
//                            counter = 0;
//                        }
//                        if(i>0 && i%5==0){
//                            counter++;
//                        }
//                    }
//                    ongkir=ongkir*multiplier;
//                    txOngkir.setText("Rp. "+String.valueOf(ongkir));
//                    multiplier = 1;
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        btPlacesAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(Chart.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        btndatepicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(Chart.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                        // TODO Auto-generated method stub
                        Calendar newDate = Calendar.getInstance();
                        newDate.set( arg1,arg2,arg3 );
                        txtdatepicker.setText(dateFormatter.format(newDate.getTime()));
                        tanggal_kirim = dateFormatter.format(newDate.getTime());
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMinDate(System.currentTimeMillis()+24*60*60*1000);
                if(temp == 0) {
                    cal1.add(Calendar.DAY_OF_MONTH, 3);
                    temp = 1 ;
                }
                dialog.getDatePicker().setMaxDate(cal1.getTimeInMillis());
                dialog.show();
            }
        });


        button_checkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                metode_p = spinner_metode.getSelectedItem().toString();
                waktu = Spinnerjam.getSelectedItem().toString();
                if( TextUtils.isEmpty(auto_kota.getText())){
                    auto_kota.setError( "Tujuan Kota Harus di isi");
                } else {
                    postTransaksi(API_TRANSAKSI);
                }

            }
        });

    }
    private void callData() {
        listkota.clear();

        JsonArrayRequest jArr = new JsonArrayRequest(API_ONGKIR,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Ongkir item = new Ongkir();
                                item.setId(obj.getInt("id"));
                                item.setKota(obj.getString("kota"));
                                item.setHarga_ongkir(obj.getInt("harga_ongkir"));
                                listkota.add(item);
                                adapterongkir = new OngkirAdapter(Chart.this,R.layout.list_ongkir ,listkota);
                                auto_kota.setAdapter(adapterongkir);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterongkir.notifyDataSetChanged();


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(Chart.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        Singleton.getInstance(this).addToRequestQueue(jArr);
    }


    private void getOngkir (){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_GET_ONGKIR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                Log.e("Response:", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        ongkir = jsonobject.getInt("harga_ongkir");
                        kota_pengiriman = jsonobject.getString("kota");
                    }
                    auto_kota.setText(kota_pengiriman);

                    ongkir = ongkir * Integer.parseInt(part1);
                    txOngkir.setText("Rp. "+String.valueOf(Math.round(ongkir)));

                    txTotalharga.setText("Rp. "+String.valueOf(totalharga));
                    txTotal.setText("Rp. "+String.valueOf(totalharga+Math.round(ongkir)));
                    note_alamat.setText(""+part1);
                   // Toast.makeText(getApplicationContext(), "desimal : "+ berat, Toast.LENGTH_SHORT).show();


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
                params.put("id",String.valueOf(id_user));
                return params;
            }
        };

        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void ambilkeranjang (String url, final Checkout keranjang){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                Log.e("Response:", response.toString());
                listKeranjang.clear();
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        listKeranjang.add(new Buku(Server.URLIMAGE+ jsonobject.getString("foto"),
                                jsonobject.getInt("buku_id"),
                                jsonobject.getString("nama"),jsonobject.getInt("harga"),
                                jsonobject.getInt("jumlah_buku"),
                                jsonobject.getInt("jumlah")));
                        mList = (RecyclerView) findViewById(R.id.recyclerview_checkout);
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        mList.setLayoutManager(mLayoutManager);
                        mList.setAdapter(keranjang);
                        totalharga = totalharga+jsonobject.getInt("total_harga");
                       // jumlahbuku =
                        berat =  (jsonobject.getInt("jumlah_buku")*jsonobject.getDouble("berat"));
                        totberat = totberat+berat;
                    }

                    temp1 = String.valueOf(totberat);
                    String[] parts = temp1.split("\\.");
                    part1 = parts[0];
                    part2 = parts[1];
                    des = Double.parseDouble(part2);

                    if((int)(Math.round(des)) > 3){
                        part1 = String.valueOf(Integer.parseInt(part1)+ 1);
                    }




////                    totberat = Math.round(totberat * 1) / 1;
                    ongkir = ongkir * Integer.parseInt(part1);
//                    counter = 0;
//                    for (int i = 1; i<=jumlahbuku; i++){
//                        if (counter == 1){
//                            multiplier++;
//                            counter = 0;
//                        }
//                        if(i>0 && i%5==0){
//                            counter++;
//                        }
//                    }
//                    ongkir=ongkir*multiplier;
                    txOngkir.setText("Rp. "+String.valueOf( Math.round(ongkir)));
//                    multiplier = 1;
               //     Toast.makeText(getApplicationContext(), "desimal : "+ berat, Toast.LENGTH_SHORT).show();
                    note_alamat.setText(""+part1);
                    txTotalharga.setText("Rp. "+String.valueOf(totalharga));
                    txTotal.setText("Rp. "+String.valueOf(totalharga+Math.round(ongkir)));
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

        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void postTransaksi(String url){

        StringRequest stringRequest=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.toString();
                try {
                    startActivity(new Intent(getApplicationContext(), Main.class));
                    Toast.makeText(getApplicationContext(),"Transaksi berhasil silahkan cek di menu pesanan",Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if(error!=null && error.getMessage() !=null){
                    Toast.makeText(getApplicationContext(),"error VOLLEY "+error.getMessage(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Silahkan lengkapi dulu data pengiriman",Toast.LENGTH_LONG).show();

                }
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", String.valueOf(id_user));
                params.put("alamat",alamat);
                params.put("metode_transaksi",metode_p);
                params.put("waktu_pengiriman",tanggal_kirim+", "+waktu);
                params.put("ongkir", String.valueOf(ongkir));
                for(int i = 0; i < listKeranjang.size(); i++) {
                    params.put("buku_id["+i+"]",String.valueOf(listKeranjang.get(i).getId()));
                    params.put("jumlah["+i+"]",String.valueOf(listKeranjang.get(i).getJumlah()));
                }
                params.put("note_alamat", note_alamat.getText().toString());


                return params;
            }

        };

        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format(
                        "%s "
                        , place.getAddress());
                tvPlaceAPI.setText(toastMsg);
                alamat=tvPlaceAPI.getText().toString();
            }
        }
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
//                    ongkir=22000*multiplier;
            //Toast.makeText(mContext,"j :" + jumlahbuku +"m :" + multiplier +"ongkir :" + ongkir,Toast.LENGTH_LONG).show();

            txOngkir.setText("Rp. "+String.valueOf( Math.round(ongkir)));
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
//            ongkir=22000*multiplier;
            //Toast.makeText(mContext,"j :" + jumlahbuku +"m :" + multiplier +"ongkir :" + ongkir,Toast.LENGTH_LONG).show();
            txOngkir.setText("Rp. "+String.valueOf(ongkir));
            multiplier = 1;
        }
    }

    @Override
    public void updateharga(int harga,int status) {

        if (status==1){
            // jumlahbuku=jumlahbuku-jumlah;
            totalharga=totalharga-harga;
            txTotalharga.setText("Rp. "+String.valueOf(totalharga));
            txTotal.setText("Rp. "+String.valueOf(totalharga+Math.round(ongkir)));
        }else{
            // jumlahbuku=jumlahbuku+jumlah;
            totalharga=totalharga+harga;
            txTotalharga.setText("Rp. "+String.valueOf(totalharga));
            txTotal.setText("Rp. "+String.valueOf(totalharga+Math.round(ongkir)));
        }

    }
}
