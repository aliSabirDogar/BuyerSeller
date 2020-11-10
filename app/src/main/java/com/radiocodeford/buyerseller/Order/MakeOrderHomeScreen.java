package com.radiocodeford.buyerseller.Order;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.onesignal.OneSignal;
import com.radiocodeford.buyerseller.Adapter.OrderReviewScreenListAdapter;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.MasterProductsModel;
import com.radiocodeford.buyerseller.model.SelectQueryOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MakeOrderHomeScreen extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences pref;
    OrderReviewScreenListAdapter adapter;
    Button add;
    int api_call_counter=0;
    Context mContext=null;
    String urlmasterproducts = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/master_products";
    String urlselectedproducts = "http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/get_selected_products";
    String text,product,state,photo,code;
    String weightUnit,quantityOfProduct;
    EditText quantity;
    private RequestQueue requestQueue;
    //private FusedLocationProviderClient client;
    private ArrayList<SelectQueryOrder> data;
    private LayoutManager layoutManager_HORIZONTAL;
    private LayoutManager layoutManager_VERTICAL;
    TextView textCartItemCount;
    int mCartItemCount = 10;
    RecyclerView list;
    Button manual_order;
    String phone,password;
    Spinner product_spinner;
    String screenChecker;
    CheckBox select;
    Button send, buyerTray, makeOrder,Add;
    Spinner spinner_store;
    Spinner spinner_weight_product;
    CheckBox store;
    TextView title, store_label;
    CheckBox write;
    EditText write_order_manual;
    LinearLayout write_layout, store_layout;
    ArrayList<String> arrayList = new ArrayList();
    ArrayList<MasterProductsModel> arrayResponse = new ArrayList();

    ArrayAdapter<String> arrayAdapter;
    @Override
    /* Access modifiers changed, original: protected */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_make_order_home_screen);
        mContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(this);
        if (mContext != null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        //requestPermission();
        //client = LocationServices.getFusedLocationProviderClient(this);
        pref = this.getSharedPreferences("buyerSeller", Context.MODE_PRIVATE);
        phone = pref.getString("phone", "null");
        password = pref.getString("password", "null");
        screenChecker = pref.getString("screenCheckKey", "null");
        if (screenChecker.contains("buyer")) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = findViewById(R.id.drawer_layout_home_screen);
            NavigationView navigationView = findViewById(R.id.nav_view_make_order_HomeScreen);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_myAccount, R.id.nav_myCart,
                    R.id.nav_myOrders, R.id.nav_loginLogout, R.id.nav_send)
                    .setDrawerLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_make_order_screen);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            this.write_order_manual = (EditText) findViewById(R.id.write_order_manual);
            this.quantity = (EditText) findViewById(R.id.tf_product_quantity);

            this.write_layout = (LinearLayout) findViewById(R.id.write_order_layout);
            this.store_layout = (LinearLayout) findViewById(R.id.store_layout);


            this.select = (CheckBox) findViewById(R.id.RB_select_order);
            //this.noti = (ImageView) findViewById(R.id.notification_id_bell);
            this.title = (TextView) findViewById(R.id.tv_order_screen);
            this.store_label = (TextView) findViewById(R.id.tv_store_label);
            this.add = (Button) findViewById(R.id.btn_add_order);
            this.buyerTray = (Button) findViewById(R.id.buyerTray_btn);
            this.makeOrder = (Button) findViewById(R.id.btn_make_your_order);
            this.send = (Button) findViewById(R.id.btn_send_order);
            this.Add = (Button) findViewById(R.id.btn_add_order);
            this.manual_order = (Button) findViewById(R.id.btn_manual_order);
            this.store = (CheckBox) findViewById(R.id.RB_search_store);
            this.write = (CheckBox) findViewById(R.id.RB_write_order);
            MakeOrderHomeScreen.this.select.setChecked(true);
            this.product_spinner = (Spinner) findViewById(R.id.spinner_product);
            this.list = (RecyclerView) findViewById(R.id.list_orders_review);
            data = new ArrayList<SelectQueryOrder>();
            buyerTray.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    MakeOrderHomeScreen.this.startActivity(new Intent(MakeOrderHomeScreen.this, BuyerTray.class));
                }
            });
            /*makeOrder.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    if (ActivityCompat.checkSelfPermission(MakeOrderHomeScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    client.getLastLocation().addOnSuccessListener(MakeOrderHomeScreen.this, new OnSuccessListener<Location>() {

                        public void onSuccess(Location location) {
                            if (location != null) {


                                TextView textView = findViewById(R.id.tv_Location);
                                textView.setText(String.valueOf(getCompleteAddressString(location.getLatitude(), location.getLongitude())));


                            }

                        }
                    });
                }
            });*/


            MasterProductsApi();

            this.select.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {


                    MakeOrderHomeScreen.this.spinner_store.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.spinner_weight_product.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.product_spinner.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.quantity.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.write_order_manual.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.store.setChecked(false);
                    MakeOrderHomeScreen.this.manual_order.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.write.setChecked(false);
                    MakeOrderHomeScreen.this.write_order_manual.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.spinner_store.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.add.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.send.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.title.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.list.setVisibility(View.VISIBLE);
                    write_layout.setVisibility(View.GONE);
                    store_layout.setVisibility(View.GONE);
                    store_label.setVisibility(View.GONE);



                }
            });
            this.store.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {


                    store_layout.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.spinner_store.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.spinner_weight_product.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.product_spinner.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.quantity.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.write_order_manual.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.send.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.manual_order.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.select.setChecked(false);
                    MakeOrderHomeScreen.this.write.setChecked(false);
                    MakeOrderHomeScreen.this.write_order_manual.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.spinner_store.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.add.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.title.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.list.setVisibility(View.VISIBLE);
                    store_label.setVisibility(View.VISIBLE);
                }
            });
            this.write.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {

                    write_layout.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.spinner_store.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.spinner_weight_product.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.product_spinner.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.quantity.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.add.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.send.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.title.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.list.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.manual_order.setVisibility(View.VISIBLE);
                    MakeOrderHomeScreen.this.store.setChecked(false);
                    MakeOrderHomeScreen.this.select.setChecked(false);
                    store_label.setVisibility(View.GONE);
                    MakeOrderHomeScreen.this.write_order_manual.setVisibility(View.VISIBLE);
                }
            });


            this.product_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    code = arrayResponse.get(position).getCode();
                    state = arrayResponse.get(position).getState();
                    text = parent.getItemAtPosition(position).toString();
                    Context context = parent.getContext();
                    product_spinner.getSelectedItem().toString();


                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });


            this.spinner_weight_product = (Spinner) findViewById(R.id.spinner_weight_product);
            ArrayList<String> arrayList_spinner_weight_product = new ArrayList();
            arrayList_spinner_weight_product.add("kg");
            arrayList_spinner_weight_product.add("gram ");
            arrayList_spinner_weight_product.add("pound");
            arrayList_spinner_weight_product.add("milligram");
            ArrayAdapter<String> arrayAdapter_arrayList_spinner_weight_product = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList_spinner_weight_product);
            arrayAdapter_arrayList_spinner_weight_product.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spinner_weight_product.setAdapter(arrayAdapter_arrayList_spinner_weight_product);
            this.spinner_weight_product.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String tutorialsName = parent.getItemAtPosition(position).toString();
                    Context context = parent.getContext();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Selected: ");
                    stringBuilder.append(tutorialsName);
                    weightUnit = spinner_weight_product.getSelectedItem().toString();
                    //Toast.makeText(context, stringBuilder.toString(), 1).show();
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            this.spinner_store = (Spinner) findViewById(R.id.spinner_store);
            ArrayList<String> arrayList_spinner_store = new ArrayList();
            arrayList_spinner_store.add("Don carlos-open");
            arrayList_spinner_store.add("John fresh-closed");
            arrayList_spinner_store.add("Pipy veges-open");
            ArrayAdapter<String> arrayAdapter_spinner_store = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList_spinner_store);
            arrayAdapter_spinner_store.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spinner_store.setAdapter(arrayAdapter_spinner_store);
            this.spinner_store.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String tutorialsName = parent.getItemAtPosition(position).toString();
                    Context context = parent.getContext();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Selected: ");
                    stringBuilder.append(tutorialsName);
                    // Toast.makeText(context, stringBuilder.toString(), 1).show();
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            Add.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    quantityOfProduct = quantity.getText().toString();
                    list.setHasFixedSize(false);


                    layoutManager_VERTICAL = new LinearLayoutManager(MakeOrderHomeScreen.this);
                    list.setLayoutManager(layoutManager_VERTICAL);
                    list.setItemAnimator(new DefaultItemAnimator());
                    adapter = new OrderReviewScreenListAdapter(data, MakeOrderHomeScreen.this);

                    if (!quantity.getText().toString().isEmpty()) {
                        data.add(new SelectQueryOrder(code, state, text, quantityOfProduct + weightUnit, ""));
                        list.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MakeOrderHomeScreen.this, "Please Enter the Quantity", Toast.LENGTH_SHORT);
                    }


                }
            });



        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_make_order_screen);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
                MakeOrderHomeScreen.this.startActivity(new Intent(MakeOrderHomeScreen.this, SellerTrayOrderDetails.class));
                return true;


            default: return super.onOptionsItemSelected(item);
        }
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
               // Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
              //  Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
           // Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }
    //REQUEST FOR PERMISSSION
   /* private  void requestPermission()
    {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

    }
*/

    public void MasterProductsApi()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlmasterproducts, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String result=response;

                try {

                    JSONArray jArray = new JSONArray(result);

                    for(int i=0;i<jArray.length();i++) {

                        JSONObject objectPremium = (JSONObject) jArray.get(i);

                        //    JSONObject json = jsonObject.getJSONObject("address");

                        product = objectPremium.getString("product_name");
                        photo = objectPremium.getString("photo");
                        state = objectPremium.getString("state");
                        code = objectPremium.getString("code");
                        arrayResponse.add(new MasterProductsModel(photo,code,state,product));

                        arrayList.add(product);
                    }


                    arrayAdapter = new ArrayAdapter(MakeOrderHomeScreen.this, android.R.layout.simple_spinner_item, arrayList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    arrayAdapter.setNotifyOnChange(true);
                    product_spinner.setAdapter(arrayAdapter);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MakeOrderHomeScreen.this, "Try again later", Toast.LENGTH_LONG).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

                params.put("phone",  phone);
                params.put("password",password);

                return params;
            }
        };


        requestQueue.add(stringRequest);
    }

    public void getApi_call_counter() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlselectedproducts, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.contains("success"))
                {
                    api_call_counter=api_call_counter+1;
                    if(api_call_counter<data.size())
                    {
                        getApi_call_counter();
                    }
                    Toast.makeText(MakeOrderHomeScreen.this, "your query has been send", Toast.LENGTH_LONG).show();
                }
                else
                { Toast.makeText(getApplicationContext(), "please select product", Toast.LENGTH_SHORT).show(); }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MakeOrderHomeScreen.this, "Try again later", Toast.LENGTH_LONG).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Creating Map String Params.

                Map<String, String> params = new HashMap<>();
                params.put("consultation_code", data.get(api_call_counter).getCode());
                params.put("query", data.get(api_call_counter).getProduct());
                params.put("quantity", data.get(api_call_counter).getQuantity());
                params.put("unit", data.get(api_call_counter).getUnit());
                return params;



            }

        };


        requestQueue.add(stringRequest);
    }
}

