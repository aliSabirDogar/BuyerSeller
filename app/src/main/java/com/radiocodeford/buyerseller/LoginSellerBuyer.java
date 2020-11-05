package com.radiocodeford.buyerseller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.radiocodeford.buyerseller.Order.MakeOrderHomeScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginSellerBuyer extends AppCompatActivity {
    Button login;

    EditText phone, password;
    TextView client, business;
    boolean isusernameValid, isPasswordValid,isResponseValid;
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    private RequestQueue requestQueue;
    Context mContext=null;
    String URL_buyer = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/seller_login";
    String URL_seller = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/market_login";
    String strPhone,strPassword ;

    /* Access modifiers changed, original: protected */



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_seller_buyer);
        mContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(this);
        if(mContext!=null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
   pref =getSharedPreferences("buyerSeller", MODE_PRIVATE);

        editor = pref.edit();
      phone = (EditText) findViewById(R.id.tf_login_cellular);
        password = (EditText) findViewById(R.id.tf_login_password);
        login = (Button) findViewById(R.id.btn_login);
        client = (TextView) findViewById(R.id.tv_login_client);
        business = (TextView) findViewById(R.id.tv_login_business);


        business.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // 1 for market and 0 for client
                // redirect to marketRegistraionForm

                editor.putInt("directedkey",1);


                editor.commit();

                LoginSellerBuyer.this.startActivity(new Intent(LoginSellerBuyer.this, PhoneNumber.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  SetValidation();




            }


        });


        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to clientRegistraionForm
               // SharedPreferences.Editor editor=pref.edit();
                editor.putInt("directedkey",0);
                editor.commit();

                LoginSellerBuyer.this.startActivity(new Intent(LoginSellerBuyer.this, PhoneNumber.class));
            }
        });

    }

    public void SetValidation() {
        // Check for empty editTexts.
        if (phone.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Username is not entered", Toast.LENGTH_SHORT).show();
            isusernameValid = false;
        }
        else if (password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "password is not entered", Toast.LENGTH_SHORT).show();
            isPasswordValid = false;

        }
        else
            {
                isPasswordValid=true;
                isusernameValid=true;

            }

        if (isusernameValid && isPasswordValid) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginSellerBuyer.this);
            View mView=getLayoutInflater().inflate(R.layout.custom_dialogue,null);
            Button buyer =(Button)mView.findViewById(R.id.btn_customDialogue_buyer);
            Button seller =(Button)mView.findViewById(R.id.btn_customDialogue_seller);
            builder.setView(mView);
            final AlertDialog alertDialog= builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            buyer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login_buyer();


                    }
            });
            seller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login_seller();
                         }
            });

            alertDialog.show();


        }
    }
public  void login_buyer(){
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_buyer, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            if(response.contains("success"))
            {   strPhone=phone.getText().toString();
                strPassword=password.getText().toString();
                Toast.makeText(LoginSellerBuyer.this, "Login Successfully as a buyer", Toast.LENGTH_LONG).show();
                editor.putString("screenCheckKey","buyer");
                editor.putString("phone",strPhone);
                editor.putString("password",strPassword);
                editor.putString("LoginOneTime","success");
                editor.commit();

                LoginSellerBuyer.this.startActivity(new Intent(LoginSellerBuyer.this, MakeOrderHomeScreen.class));



            }
            else
            {
                Toast.makeText(getApplicationContext(), "username or password is not correct", Toast.LENGTH_SHORT).show();

            }



        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            // Toast.makeText(LoginSellerBuyer.this, "Try again later", Toast.LENGTH_LONG).show();


        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            // Creating Map String Params.
            Map<String, String> params = new HashMap<>();

            params.put("phone",  phone.getText().toString());
            params.put("password", password.getText().toString());

            return params;
    }
    };


    requestQueue.add(stringRequest);
}
    public  void login_seller(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_seller, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.contains("success"))
                {
                    Toast.makeText(LoginSellerBuyer.this, "Login Successfully as a seller", Toast.LENGTH_LONG).show();

                    editor.putString("screenCheckKey","seller");
                    editor.putString("phone",strPhone);
                    editor.putString("password",strPassword);
                    editor.putString("LoginOneTime","success");
                    editor.commit();
                    LoginSellerBuyer.this.startActivity(new Intent(LoginSellerBuyer.this, MakeOrderHomeScreen.class));


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "username or password is not correct", Toast.LENGTH_SHORT).show();

                      }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(LoginSellerBuyer.this, "Try again later", Toast.LENGTH_LONG).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

                params.put("phone",  "phone.getText().toString()");
                params.put("password", password.getText().toString());

                return params;
            }
        };


        requestQueue.add(stringRequest);
    }

}
