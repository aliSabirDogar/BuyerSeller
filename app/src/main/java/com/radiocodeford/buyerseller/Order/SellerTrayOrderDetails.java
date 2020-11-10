package com.radiocodeford.buyerseller.Order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;
import com.radiocodeford.buyerseller.Adapter.OrderDetailsSellerTrayListAdapter;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.ShopingCart;
import com.radiocodeford.buyerseller.model.OrderDetailsShopTrayListModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SellerTrayOrderDetails extends AppCompatActivity {
    ListView simpleList;
    ArrayList<OrderDetailsShopTrayListModel> list = new ArrayList<>();
    TextView tv_buyer_order_details;
    ArrayList<String> customeridarray;
    private RequestQueue requestQueue;
    ArrayAdapter<String> customeridarrayAdapter;
    //String id, tray_buyer_id, total_observation, inquiry_product, price, total;
    //String url_tray_buyer_detail = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/tray_buyer_detail";

    Context mContext;
    Button send;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seller_tray_order_details);
        mContext = getApplicationContext();
        send=(Button) findViewById(R.id.btn_Seller_tray_send);
        requestQueue = Volley.newRequestQueue(this);
        if(mContext!=null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        simpleList = (ListView) findViewById(R.id.list_seller_tray_order_details);
        tv_buyer_order_details = (TextView) findViewById(R.id.tv_Seller_tray_order_no);
        tv_buyer_order_details.setText("Customer : " + SellerTray.buyer_id);
        list.add(new OrderDetailsShopTrayListModel("2kg Yellow potato","0$","0$"));
        list.add(new OrderDetailsShopTrayListModel("2kg Cabbage","0$","0$"));
        OrderDetailsSellerTrayListAdapter customeridarrayAdapter = new
                OrderDetailsSellerTrayListAdapter(getApplicationContext(), list);
        simpleList.setAdapter(customeridarrayAdapter);

        //TraySellerDetailApi();
        send.setOnClickListener(new View.OnClickListener() {
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

                message="New message from seller";

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



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seller_screens_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {

            case R.id.toolbar_bell:   //this item has your app icon
                SellerTrayOrderDetails.this.startActivity(new Intent(SellerTrayOrderDetails.this, ShopTray.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

   /* public void TraySellerDetailApi() {
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
                            *//*total_observation = objectPremium.getString("total_observation");*//*

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
    }*/
}