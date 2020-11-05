package com.radiocodeford.buyerseller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.radiocodeford.buyerseller.Order.MakeOrderHomeScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MarketRegisteration extends AppCompatActivity {
    Button signup;
    private RequestQueue requestQueue;
    Context mContext=null;
    String URL = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/signup_market";
    String strpassword,strConfirmPassword;



    EditText marketname, address, manager, cellular, telephone, mail, district, shippingCost, user, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_market_registeration);
         mContext = getApplicationContext();
         requestQueue = Volley.newRequestQueue(this);
        if(mContext!=null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        marketname = (EditText) findViewById(R.id.tf_marketRegisteration_name);
        address = (EditText) findViewById(R.id.tf_marketRegisteration_address);
        manager = (EditText) findViewById(R.id.tf_marketRegisteration_manager);
        cellular = (EditText) findViewById(R.id.tf_marketRegisteration_cellular);
        telephone = (EditText) findViewById(R.id.tf_marketRegisteration_telephone);
        mail = (EditText) findViewById(R.id.tf_marketRegisteration_mail);
        district = (EditText) findViewById(R.id.tf_marketRegisteration_district);
        shippingCost = (EditText) findViewById(R.id.tf_marketRegisteration_cost);
        user = (EditText) findViewById(R.id.tf_marketRegisteration_username);
        password = (EditText) findViewById(R.id.tf_marketRegisteration_password);
        confirmPassword = (EditText) findViewById(R.id.tf_marketRegisteration_confirmPassword);
        this.signup = (Button) findViewById(R.id.btn_market_registration_register);
        this.signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                strpassword = password.getText().toString();
                strConfirmPassword = confirmPassword.getText().toString();
                if (strpassword.matches(strConfirmPassword)) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //pDialog.dismiss();
                            Toast.makeText(MarketRegisteration.this, "Registered Successfully as a Business", Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MarketRegisteration.this, "Try again later", Toast.LENGTH_LONG).show();
                            Toast.makeText(MarketRegisteration.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<>();

                            params.put("name", marketname.getText().toString());
                            params.put("address", address.getText().toString());
                            params.put("admin", manager.getText().toString());
                            params.put("cell", cellular.getText().toString());
                            params.put("telephone", telephone.getText().toString());
                            params.put("mail", mail.getText().toString());
                            params.put("district", district.getText().toString());
                            params.put("shipping_cost", shippingCost.getText().toString());
                            params.put("user", user.getText().toString());
                            params.put("password", password.getText().toString());
                            params.put("confirm_password", confirmPassword.getText().toString());
                            return params;
                        }
                    };


                    requestQueue.add(stringRequest);

                    MarketRegisteration.this.startActivity(new Intent(MarketRegisteration.this, LoginSellerBuyer.class));
                }
                else {
                    Toast.makeText(MarketRegisteration.this, "Confirm Password should be same ", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}