package com.skripsi.nigel.esvira;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.nigel.esvira.ServerAPI.Server;
import com.skripsi.nigel.esvira.Singleton.Singleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InputEmailActivity extends AppCompatActivity {

    Button btn_submit;
    EditText et_input_email;
    private String API_URL = Server.URLRESET + "password/email";
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_email);

        btn_submit = (Button) findViewById(R.id.btn_submit_reset);
        et_input_email = (EditText) findViewById(R.id.txt_emailubah);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postEmail();
            }
        });
    }


    private void postEmail() {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Wait ...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                String json = response.toString();
//                Log.e("Response: ", response.toString());
                try{
                    JSONObject jsonObject = new JSONObject(json);

                    if(jsonObject.getString("message").equals("success sent")){
                        Toast.makeText(getApplicationContext(),"Cek Email dan Ubah Password", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }else {
                        if (jsonObject.getString("message").equals("error sent")) {
                            Toast.makeText(getApplicationContext(),"Email Salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (Exception e){

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", et_input_email.getText().toString());
                return params;
            }
        };
        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
