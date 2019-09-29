package com.skripsi.nigel.esvira;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class UbahPasswordActivity extends AppCompatActivity {

    Button btn_submit;
    EditText et_input_email,et_input_token,et_input_password,et_input_confirmpass;
    private String API_URL = Server.URLRESET + "password/reset";
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        btn_submit = (Button) findViewById(R.id.btn_ubah_password);
        et_input_email = (EditText) findViewById(R.id.txt_emailubah_ubah);
        et_input_token = (EditText) findViewById(R.id.txt_token_ubah);
        et_input_password = (EditText) findViewById(R.id.txt_password_ubah);
        et_input_confirmpass = (EditText) findViewById(R.id.txt_confirm_password_ubah);




        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPassword();
            }
        });
    }


    private void postPassword() {
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

                    if(jsonObject.getString("message").equals("success ubah")){
                        Toast.makeText(getApplicationContext(),"Berhasil Mengubah Password", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }else {
                        if (jsonObject.getString("message").equals("error ubah")) {
                            Toast.makeText(getApplicationContext(),"Email atau Token Salah", Toast.LENGTH_SHORT).show();
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
                params.put("token", et_input_token.getText().toString());
                params.put("password", et_input_password.getText().toString());
                params.put("password_confirmation", et_input_confirmpass.getText().toString());
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
