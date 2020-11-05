package com.radiocodeford.buyerseller.Order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.radiocodeford.buyerseller.Adapter.OrderDetailsBuyerTrayListAdapter;
import com.radiocodeford.buyerseller.Adapter.OrderDetailsShopTrayListAdapter;
import com.radiocodeford.buyerseller.BuyerTray;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.ShopingCart;
import com.radiocodeford.buyerseller.model.OrderDetailsShopTrayListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuyerTrayOrderDetails extends AppCompatActivity {

    ListView simpleList;
    ArrayList<OrderDetailsShopTrayListModel> list = new ArrayList<>();
    TextView tv_customer_order_details;
    ArrayList<String> customeridarray;
    private RequestQueue requestQueue;
    ArrayAdapter<String> customeridarrayAdapter;
    String id, tray_buyer_id, total_observation, inquiry_product, price, total;
    String url_tray_buyer_detail = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/tray_buyer_detail";
    ArrayList<OrderDetailsShopTrayListModel> data = new ArrayList<>();
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buyer_tray_order_details);
        mContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(this);
        if(mContext!=null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        simpleList = (ListView) findViewById(R.id.list_buyer_tray);
        tv_customer_order_details = (TextView) findViewById(R.id.tv_Buyer_tray_order_no);
        tv_customer_order_details.setText("Customer : " + BuyerTray.seller_id);
        TraySellerDetailApi();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.toolbar_basket:   //this item has your app icon
                    BuyerTrayOrderDetails.this.startActivity(new Intent(BuyerTrayOrderDetails.this, ShopingCart.class));
                return true;

            case R.id.toolbar_bell:   //this item has your app icon
                BuyerTrayOrderDetails.this.startActivity(new Intent(BuyerTrayOrderDetails.this, ShopTray.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void TraySellerDetailApi() {
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    url_tray_buyer_detail, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String result = response;
                    try {
                        JSONArray jArray = new JSONArray(result);
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject objectPremium = (JSONObject) jArray.get(i);
                            //    JSONObject json = jsonObject.getJSONObject("address");
                            id = objectPremium.getString("id");
                            tray_buyer_id = objectPremium.getString("tray_buyer_id");
                            inquiry_product = objectPremium.getString("inquiry_product");
                            price = objectPremium.getString("price");
                            total = objectPremium.getString("total");
                            /*total_observation = objectPremium.getString("total_observation");*/

                            list.add(new OrderDetailsShopTrayListModel(inquiry_product,price, total));
                        }
                        OrderDetailsBuyerTrayListAdapter customeridarrayAdapter = new
                                OrderDetailsBuyerTrayListAdapter(getApplicationContext(), list);
                        simpleList.setAdapter(customeridarrayAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(BuyerTrayOrderDetails.this, "Try again later", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    // Creating Map String Params.
                    Map<String, String> params = new HashMap<>();
                    params.put("tray_buyer_id",  "1");

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}
