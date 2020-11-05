package com.radiocodeford.buyerseller.Order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;
import com.radiocodeford.buyerseller.Adapter.OrderDetailsShopTrayListAdapter;
import com.radiocodeford.buyerseller.CategoriesHomeScreen;
import com.radiocodeford.buyerseller.Order.ShopTray;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.ShopingCart;
import com.radiocodeford.buyerseller.model.OrderDetailsShopTrayListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class order_details_shop_tray extends AppCompatActivity {

    ListView simpleList;
    ArrayList<OrderDetailsShopTrayListModel> list = new ArrayList<>();
    TextView tv_customer_order_details;
    ArrayList<String> customeridarray;
    private RequestQueue requestQueue;
    ArrayAdapter<String> customeridarrayAdapter;
    String id, tray_seller_id, total_observation, inquiry_product, price, total;
    String url_tray_seller_detail = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/tray_seller_detail";
    ArrayList<OrderDetailsShopTrayListModel> data = new ArrayList<>();
    Context mContext;
    Button shop_tray;

    EditText editText_price,editText_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_details_seller_shop_tray);
       mContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(this);
        if(mContext!=null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        simpleList = (ListView) findViewById(R.id.list_customer_order_details);
        tv_customer_order_details = (TextView) findViewById(R.id.tv_customer_order_details);

        EditText comment = (EditText) findViewById(R.id.shop_try_comment);


        shop_tray = (Button) findViewById(R.id.shop_try_send);

        shop_tray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder sb = new StringBuilder();

                String message;


                String send_notification;
                for(int i=0;i<list.size();i++)
                {

                    String details=list.get(i).product_details;
                    String price=list.get(i).price;
                    String total=list.get(i).total;
                   // value=value+"-"+details+"-"+price+"-"+total;

                    sb.append(details+"-"+price+"-"+total);


                }

                message=sb.toString();

                message="New message from seller"+message;

                message="{'contents': {'en':"+message;
                String message2="'},";
                String message3="'include_player_ids': ['" + "84e4a7ce-0133-4edc-aa64-83d9264d9b6a" + "']}";
                String finalMessage=message+message2+message3;




                try {
                    OneSignal.postNotification(new JSONObject(finalMessage),
                            new OneSignal.PostNotificationResponseHandler() {
                                @Override
                                public void onSuccess(JSONObject response) {
                                    Log.i("OneSignalExample", "postNotification Success: " + response.toString());
                                }
                                @Override
                                public void onFailure(JSONObject response) {
                                    Log.e("OneSignalExample", "postNotification Failure: " + response.toString());
                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        tv_customer_order_details.setText("Customer : " + ShopTray.customer_id);
        TraySellerDetailApi();


        /*list.add(new OrderDetailsShopTrayListModel("yellow potato", "20$", "200"));
        list.add(new OrderDetailsShopTrayListModel("white potato", "20$", "200"));
        list.add(new OrderDetailsShopTrayListModel("Tomato", "20$", "200"));
        list.add(new OrderDetailsShopTrayListModel("carrot", "200$", "200"));
        list.add(new OrderDetailsShopTrayListModel("juice", "20$", "200"));
        list.add(new OrderDetailsShopTrayListModel("apple", "20$", "200"));
        list.add(new OrderDetailsShopTrayListModel("orange", "20$", "200"));
        list.add(new OrderDetailsShopTrayListModel("banana", "20$", "200"));
        list.add(new OrderDetailsShopTrayListModel("apple", "30$", "200"));*/


     /* OrderDetailsShopTrayListAdapter customeridarrayAdapter = new
                OrderDetailsShopTrayListAdapter(getApplicationContext(), list);
        simpleList.setAdapter(customeridarrayAdapter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_basket:   //this item has your app icon
                order_details_shop_tray.this.startActivity(new Intent(order_details_shop_tray.this, ShopingCart.class));
                return true;

            case R.id.toolbar_bell:   //this item has your app icon
                order_details_shop_tray.this.startActivity(new Intent(order_details_shop_tray.this, ShopTray.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void TraySellerDetailApi() {
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    url_tray_seller_detail, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String result = response;
                    try {
                        JSONArray jArray = new JSONArray(result);
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject objectPremium = (JSONObject) jArray.get(i);
                            //    JSONObject json = jsonObject.getJSONObject("address");
                            id = objectPremium.getString("id");
                            tray_seller_id = objectPremium.getString("tray_seller_id");
                            inquiry_product = objectPremium.getString("inquiry_product");
                            price = objectPremium.getString("price");
                            total = objectPremium.getString("total");
                            total_observation = objectPremium.getString("total_observation");

                            list.add(new OrderDetailsShopTrayListModel(inquiry_product,price, total));
                        }
                        OrderDetailsShopTrayListAdapter customeridarrayAdapter = new
                                OrderDetailsShopTrayListAdapter(getApplicationContext(), list);
                        simpleList.setAdapter(customeridarrayAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(order_details_shop_tray.this, "Try again later", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    // Creating Map String Params.
                    Map<String, String> params = new HashMap<>();
            params.put("tray_seller_id",  "1");

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}