package com.radiocodeford.buyerseller.Order;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.radiocodeford.buyerseller.Adapter.SellerTrayListAdapter;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.BuyerTrayModel;

import java.util.ArrayList;

public class SellerTray extends AppCompatActivity {
    SellerTrayListAdapter adapter;
    private ArrayList<BuyerTrayModel> data = new ArrayList();
    ArrayList<String> arrayList = new ArrayList();
    private LayoutManager layoutManager_HORIZONTAL;
    private LayoutManager layoutManager_VERTICAL;
   // String urlTrayBuyer = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/tray_buyer";
    RecyclerView list;
    Context mContext;
    RequestQueue requestQueue;
    public static String buyer_id;
    String name, storeNumber, shippingTime,id;


    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            buyer_id = data.get(position).getName() + " - " + data.get(position).getCell() + " - " + data.get(position).getShippingTime();
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
            Intent intent = new Intent(SellerTray.this, SellerTrayOrderDetails.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SellerTray.this.startActivity(intent);
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;

        }
    };

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_tray);
        mContext = getApplicationContext();
      //  requestQueue = Volley.newRequestQueue(this);
        if (mContext != null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }



        this.list = (RecyclerView) findViewById(R.id.list_orders_sellertray);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.list.getContext(), 1);
        ShapeDrawable shapeDrawableForDivider = new ShapeDrawable(new RectShape());
        shapeDrawableForDivider.setIntrinsicHeight(3);
        shapeDrawableForDivider.setAlpha(0);
        dividerItemDecoration.setDrawable(shapeDrawableForDivider);
        this.list.addItemDecoration(dividerItemDecoration);
        this.list.setHasFixedSize(true);
        //call to api
        //sellerTrayApi();

        data.add(new BuyerTrayModel("Micheal", "0300000000", "11:00am"));
        data.add(new BuyerTrayModel("Nickel", "0300000000", "11:00am"));
        data.add(new BuyerTrayModel("Milley", "0300000000", "11:00am"));
        data.add(new BuyerTrayModel("Jackson", "0300000000", "11:00am"));
        data.add(new BuyerTrayModel("John", "0300000000", "11:00am"));

        this.layoutManager_VERTICAL = new LinearLayoutManager(this);
        this.list.setLayoutManager(this.layoutManager_VERTICAL);
        this.list.setItemAnimator(new DefaultItemAnimator());
        this.adapter = new SellerTrayListAdapter(this.data, this);
        this.list.setAdapter(this.adapter);
        adapter.setOnItemClickListener(onItemClickListener);
    }




 /*   public void sellerTrayApi(){
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
*/
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
                SellerTray.this.startActivity(new Intent(SellerTray.this, ShopTray.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
