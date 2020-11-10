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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;
import com.radiocodeford.buyerseller.Adapter.OrderDetailsBuyerTrayListAdapter;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.ShopingCart;
import com.radiocodeford.buyerseller.model.OrderDetailsShopTrayListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BuyerTrayOrderDetails extends AppCompatActivity {

    ListView simpleList;
    ArrayList<OrderDetailsShopTrayListModel> list = new ArrayList<>();
    TextView tv_seller_order_details;
    ArrayList<String> customeridarray;
    private RequestQueue requestQueue;
    ArrayAdapter<String> customeridarrayAdapter;
    String id, tray_buyer_id, total_observation, inquiry_product, price, total;
    String url_tray_buyer_detail = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/tray_buyer_detail";
    ArrayList<OrderDetailsShopTrayListModel> data = new ArrayList<>();
    Context mContext;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buyer_tray_order_details);
        mContext = getApplicationContext();
        send=(Button)findViewById(R.id.btn_buyer_tray_send);
        requestQueue = Volley.newRequestQueue(this);
        if(mContext!=null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        simpleList = (ListView) findViewById(R.id.list_buyer_tray);
        tv_seller_order_details = (TextView) findViewById(R.id.tv_Buyer_tray_order_no);
        tv_seller_order_details.setText("Customer : " + BuyerTray.seller_id);
        TraySellerDetailApi();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            SendNotification();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }
        });



    }
    public void SendNotification()
    {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con;
            con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestMethod("POST");

            String strJsonBody = "{"
                    +   "\"app_id\": \"c5f7d7c2-7957-4c05-8a5d-f4eca9be3c8e\","
                    +   "\"include_player_ids\": [\"0361eb27-5435-4fe1-b415-3772461a6543\"],"
                    +   "\"data\": {\"foo\": \"bar\"},"
                    +   "\"contents\": {\"en\": \"Message from buyer\"}"
                    + "}";


            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (  httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch(Throwable t) {
            t.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.buyer_screens_menu, menu);
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
