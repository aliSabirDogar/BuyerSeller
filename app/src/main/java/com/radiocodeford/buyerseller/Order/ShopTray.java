package com.radiocodeford.buyerseller.Order;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.radiocodeford.buyerseller.Adapter.ShopTrayListAdapter;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.ShopingCart;
import com.radiocodeford.buyerseller.model.MasterProductsModel;
import com.radiocodeford.buyerseller.model.ModelOrderReview;
import com.radiocodeford.buyerseller.model.ShopTrayModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopTray extends AppCompatActivity implements OneSignal.NotificationOpenedHandler {
    ShopTrayListAdapter adapter;
    private ArrayList<ShopTrayModel> data = new ArrayList();
    ArrayList<String> arrayList = new ArrayList();
    private LayoutManager layoutManager_HORIZONTAL;
    private LayoutManager layoutManager_VERTICAL;
    String urlTraySeller = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/tray_seller";
    RecyclerView list;
    Context mContext;
    RequestQueue requestQueue;
    public static String customer_id;
    String name, cell, shippingTime;
    Button shop_tray;


    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            customer_id = data.get(position).getName() + " - " + data.get(position).getCell() + " - " + data.get(position).getShippingTime();
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
            Intent intent = new Intent(ShopTray.this, order_details_shop_tray.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ShopTray.this.startActivity(intent);
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;

        }
    };

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_tray);
        mContext = getApplicationContext();
        //volly requiest volly
        requestQueue = Volley.newRequestQueue(this);
        if (mContext != null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        status.getPermissionStatus().getEnabled();//Boolean: true - device subscribed in app settings, false - device unsubscribed in app settings
        status.getSubscriptionStatus().getSubscribed();//Boolean: true - device can receive push from OS, false - device not subscribed from OneSignal or FCM
        status.getSubscriptionStatus().getUserSubscriptionSetting();//Boolean: true unless you have called setSubscription false
        status.getSubscriptionStatus().getUserId();//String: the OS Player Id or null if device has not registered with OS Servers
        status.getSubscriptionStatus().getPushToken();//The FCM Push Token or null if device has not registered with Google's servers




        this.list = (RecyclerView) findViewById(R.id.list_orders_shoptray);


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


    }
    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String customKey;
        Log.i("OSNotificationPayload", "result.notification.payload.toJSONObject().toString(): " + result.notification.payload.toJSONObject().toString());
        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }
        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);





    }




    public void sellerTrayApi(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlTraySeller, new Response.Listener<String>() {
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

                        name = objectPremium.getString("name");
                        cell = objectPremium.getString("cell");
                        shippingTime = objectPremium.getString("shipping_time");

                        data.add(new ShopTrayModel(name, cell, shippingTime));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new ShopTrayListAdapter(data, ShopTray.this);
                list.setAdapter(adapter);
                adapter.setOnItemClickListener(onItemClickListener);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShopTray.this, "Try again later", Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

            /*params.put("phone",  null);
            params.put("password",null);
*/
                return params;
            }
        };

        requestQueue.add(stringRequest);
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
                ShopTray.this.startActivity(new Intent(ShopTray.this, ShopingCart.class));
                return true;

            case R.id.toolbar_bell:   //this item has your app icon
                ShopTray.this.startActivity(new Intent(ShopTray.this, ShopTray.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
