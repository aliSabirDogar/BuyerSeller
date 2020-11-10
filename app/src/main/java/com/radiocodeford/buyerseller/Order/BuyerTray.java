package com.radiocodeford.buyerseller.Order;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.radiocodeford.buyerseller.Adapter.BuyerTrayAdapter;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.ShopingCart;
import com.radiocodeford.buyerseller.model.BuyerTrayModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuyerTray extends AppCompatActivity {
    BuyerTrayAdapter adapter;
    private ArrayList<BuyerTrayModel> data = new ArrayList();
    ArrayList<String> arrayList = new ArrayList();
    private LayoutManager layoutManager_HORIZONTAL;
    private LayoutManager layoutManager_VERTICAL;
    String urlTrayBuyer = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/tray_buyer";
    RecyclerView list;
    Context mContext;
    RequestQueue requestQueue;
    public static String seller_id;
    String name, storeNumber, shippingTime,id;


    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            seller_id = data.get(position).getName() + " - " + data.get(position).getCell() + " - " + data.get(position).getShippingTime();
       /*     person_name=EventsList.get(position).getFullName();
            party=EventsList.get(position).getFullName();
            ticket=String.valueOf(EventsList.get(position).getTicketPrice());
            no_people=String.valueOf(EventsList.get(position).getNoOfPeople());
            date=EventsList.get(position).getEventDate();
            desc=EventsList.get(position).getDescription();
            banner=EventsList.get(position).getEventBanner();
            email=EventsList.get(position).getEmail();
            id=EventsList.get(position).getId().toString();
            Toast.makeText(getApplicationContext(),EventsList.get(position).getFullName(),Toast.LENGTH_SHORT).show();*/
            Intent intent = new Intent(BuyerTray.this, BuyerTrayOrderDetails.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BuyerTray.this.startActivity(intent);
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;

        }
    };

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_tray);
        mContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(this);
        if (mContext != null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }



        this.list = (RecyclerView) findViewById(R.id.list_orders_buyertray);
       /* DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.list.getContext(), 1);
        ShapeDrawable shapeDrawableForDivider = new ShapeDrawable(new RectShape());
        shapeDrawableForDivider.setIntrinsicHeight(3);
        shapeDrawableForDivider.setAlpha(0);
        dividerItemDecoration.setDrawable(shapeDrawableForDivider);
        this.list.addItemDecoration(dividerItemDecoration);
        this.list.setHasFixedSize(true);*/
        //call to api
        sellerTrayApi();


        this.layoutManager_VERTICAL = new LinearLayoutManager(this);
        this.list.setLayoutManager(this.layoutManager_VERTICAL);
        this.list.setItemAnimator(new DefaultItemAnimator());
      /*  this.adapter = new BuyerTrayAdapter(this.data, this);
        this.list.setAdapter(this.adapter);*/
      // adapter.setOnItemClickListener(onItemClickListener);
    }




    public void sellerTrayApi(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlTrayBuyer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String result = response;
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(), 1);
                ShapeDrawable shapeDrawableForDivider = new ShapeDrawable(new RectShape());
                shapeDrawableForDivider.setIntrinsicHeight(3);
                shapeDrawableForDivider.setAlpha(0);
                dividerItemDecoration.setDrawable(shapeDrawableForDivider);
                list.addItemDecoration(dividerItemDecoration);
                list.setHasFixedSize(true);

                try {

                    JSONArray jArray = new JSONArray(result);

                    for (int i = 0; i < jArray.length(); i++) {

                        JSONObject objectPremium = (JSONObject) jArray.get(i);

                        //    JSONObject json = jsonObject.getJSONObject("address");
                        id= objectPremium.getString("id");
                        name = objectPremium.getString("name");
                        storeNumber = objectPremium.getString("cell");
                        shippingTime = objectPremium.getString("shipping_time");




                        data.add(new BuyerTrayModel(name, storeNumber, shippingTime));

                    }
        adapter = new BuyerTrayAdapter(data, BuyerTray.this);
        list.setAdapter(adapter);
                     adapter.setOnItemClickListener(onItemClickListener);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuyerTray.this, "Try again later", Toast.LENGTH_LONG).show();


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
                BuyerTray.this.startActivity(new Intent(BuyerTray.this, ShopingCart.class));
                return true;


            default: return super.onOptionsItemSelected(item);
        }
    }
}
