package com.radiocodeford.buyerseller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.radiocodeford.buyerseller.Order.MakeOrderHomeScreen;

import java.util.HashMap;
import java.util.Map;

public class ClientRegisteration extends AppCompatActivity {
    Button signup;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private RequestQueue requestQueue;
    Context mContext = null;
    String URL = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/signup_client";
    String URL_insert_player_id = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/insert_player_id_client";
    String strpassword, strConfirmPassword;
    String player_id;
    EditText name, surname, address, district, province, department, mail, password, phone, confirmPassword;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registeration);
        mContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(this);
        if (mContext != null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        name = (EditText) findViewById(R.id.tf_clientRegisteration_name);
        surname = (EditText) findViewById(R.id.tf_clientRegisteration_surName);
        phone = (EditText) findViewById(R.id.tf_clientRegisteration_cellular);
        address = (EditText) findViewById(R.id.tf_clientRegisteration_address);
        province = (EditText) findViewById(R.id.tf_clientRegisteration_province);
        department = (EditText) findViewById(R.id.tf_clientRegisteration_department);
        mail = (EditText) findViewById(R.id.tf_clientRegisteration_mail);
        district = (EditText) findViewById(R.id.tf_clientRegisteration_district);

        password = (EditText) findViewById(R.id.tf_clientRegisteration_password);
        confirmPassword = (EditText) findViewById(R.id.tf_clientRegisteration_confirmPassword);

        pref = this.getSharedPreferences("buyerSeller", Context.MODE_PRIVATE);
        player_id = pref.getString("player_id", player_id);
        this.signup = (Button) findViewById(R.id.btn_client_SignUp);
        this.signup.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                strpassword = password.getText().toString();
                strConfirmPassword = confirmPassword.getText().toString();
                if (strpassword.matches(strConfirmPassword)) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //pDialog.dismiss();
                            PostPlayerid();
                            Toast.makeText(ClientRegisteration.this, "Registered Successfully as a client", Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ClientRegisteration.this, "Try again later", Toast.LENGTH_LONG).show();
                            Toast.makeText(ClientRegisteration.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<>();

                            params.put("name", name.getText().toString());
                            params.put("surname", address.getText().toString());
                            params.put("phone", phone.getText().toString());
                            params.put("address", address.getText().toString());
                            params.put("province", province.getText().toString());
                            params.put("department", department.getText().toString());
                            params.put("mail", mail.getText().toString());
                            params.put("district", district.getText().toString());
                            params.put("password", password.getText().toString());
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                    ClientRegisteration.this.startActivity(new Intent(ClientRegisteration.this, LoginSellerBuyer.class));
                } else {
                    Toast.makeText(ClientRegisteration.this, "Confirm Password should be same ", Toast.LENGTH_LONG).show();
                }
            }


        });
    }

    //onesignal playerid insert
    public void PostPlayerid() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_insert_player_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ClientRegisteration.this, "Try again later", Toast.LENGTH_LONG).show();
                Toast.makeText(ClientRegisteration.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

                params.put("cell", phone.getText().toString());
                params.put("player_id", player_id);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}

