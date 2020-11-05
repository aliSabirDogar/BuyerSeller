package com.radiocodeford.buyerseller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.location.FusedLocationProviderClient;
import com.onesignal.OneSignal;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.radiocodeford.buyerseller.Adapter.HomeScreenCategoryListAdapter;
import com.radiocodeford.buyerseller.Adapter.HomeScreenSliderListAdapter;
import com.radiocodeford.buyerseller.Order.ShopTray;
import com.radiocodeford.buyerseller.model.CategoryModelVerticalList;
import com.radiocodeford.buyerseller.model.SliderModelHorizontalList;
import com.radiocodeford.buyerseller.model.testData;
import com.radiocodeford.buyerseller.model.testData2;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class CategoriesHomeScreen extends AppCompatActivity  {


    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences pref;
    private static HomeScreenSliderListAdapter Slideradapter;
    private static HomeScreenCategoryListAdapter Categoryadapter;
    private static ArrayList<CategoryModelVerticalList> data;
    private static ArrayList<SliderModelHorizontalList> data2;
    static View.OnClickListener myOnClickListener;
    private static RecyclerView recyclerView_HORIZONTAL;
    private static RecyclerView recyclerView_VERTICAL;
    private static ArrayList<Integer> removedItems;
    TextView create_event;
    String loginChecker;
    String screenCheck;
    //private FusedLocationProviderClient client;
    private RecyclerView.LayoutManager layoutManager_HORIZONTAL;
    private RecyclerView.LayoutManager layoutManager_VERTICAL;
    int i;
    private RequestQueue requestQueue, caterequestQueue;
    Spinner spinner, catespinner;
    ArrayList<String> arrayList;
    ArrayList<String> categoryarraylist;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> categoryarrayAdapter;
    String URLmain_item ="http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/main_items";
    String url_get_category="http://tuscomprasfacil.com/mpew/mpew/restapi/index.php/get_categories";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        setContentView(R.layout.activity_categories_home_screen);
        requestQueue = Volley.newRequestQueue(this);
        caterequestQueue = Volley.newRequestQueue(this);
        arrayList = new ArrayList();
        categoryarraylist = new ArrayList();
        pref = this.getSharedPreferences("buyerSeller", Context.MODE_PRIVATE);
         screenCheck= pref.getString("screenCheckKey","null");
        loginChecker = pref.getString("LoginOneTime","null");

       /* if (ActivityCompat.checkSelfPermission(CategoriesHomeScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CategoriesHomeScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CategoriesHomeScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            // Write you code here if permission already given.
        }
*/


     if(loginChecker.contains("success")){

         if(screenCheck.contains("seller")){
                 SellerScreens();
         }
         else {
             BuyerScreens();
         }
     }
     else {

     }

      // Passing each menu ID as a set of Ids because each
      // menu should be considered as top level destinations.

        recyclerView_VERTICAL = (RecyclerView) findViewById(R.id.my_recycler_view_hot_vertical);
        recyclerView_HORIZONTAL = (RecyclerView) findViewById(R.id.my_recycler_view_hot_horizontal);
        recyclerView_VERTICAL.setHasFixedSize(true);
        recyclerView_HORIZONTAL.setHasFixedSize(true);
        this.layoutManager_VERTICAL = new LinearLayoutManager(this);
        recyclerView_VERTICAL.setLayoutManager(this.layoutManager_VERTICAL);
        recyclerView_VERTICAL.setItemAnimator(new DefaultItemAnimator());


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_HORIZONTAL.setLayoutManager(layoutManager);
        data = new ArrayList();
        for (i = 1; i < testData2.NameEvent.length; i++) {
            data.add(new CategoryModelVerticalList(testData2.NameEvent[i], testData2.drawableArray[i].intValue()));
        }
        removedItems = new ArrayList();
        Categoryadapter = new HomeScreenCategoryListAdapter(data, this);
        recyclerView_VERTICAL.setAdapter(Categoryadapter);
        data2 = new ArrayList();
        for (i = 0; i < testData.drawableArray.length; i++) {
            data2.add(new SliderModelHorizontalList(testData.drawableArray[i].intValue()));
        }
        removedItems = new ArrayList();
        Slideradapter = new HomeScreenSliderListAdapter(data2, this);
        recyclerView_HORIZONTAL.setAdapter(Slideradapter);

        //spoinner main_item
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Context context = parent.getContext();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Selected: ");
                stringBuilder.append(tutorialsName);

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        setURLspinner_main_item();
    }
   /* public static class CheckPermission {

        //  CHECK FOR LOCATION PERMISSION
        public static boolean checkPermission(Activity activity){
            int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
            if (result == PackageManager.PERMISSION_GRANTED){

                return true;

            }
            else
                {
                return false;

            }
        }

        //REQUEST FOR PERMISSSION
        public static void requestPermission(Activity activity, final int code){

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.ACCESS_FINE_LOCATION)){

                Toast.makeText(activity,"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.",Toast.LENGTH_LONG).show();

            } else {

                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},code);
            }
        }

    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories_home_screen, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (loginChecker.contains("success")) {
            if (screenCheck.contains("buyer")) {
                switch (item.getItemId()) {

                    case R.id.toolbar_basket:   //this item has your app icon
                        CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, ShopingCart.class));
                        return true;


                    default:
                        return super.onOptionsItemSelected(item);
                }

            }
            else
            {
                switch (item.getItemId()) {

                     case R.id.toolbar_bell:   //this item has your app icon
                        CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, ShopTray.class));
                        return true;

                    default:
                        return super.onOptionsItemSelected(item);
                }

            }
        }

        else { }
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();


    }
    public void SellerScreens(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_myAccount,
                R.id.nav_myOrders, R.id.nav_loginLogout, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        // navigation drawer
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, CategoriesHomeScreen.class));
                            return true;
                        case R.id.myaccount:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, Survey.class));
                            return true;

                        case R.id.myorders:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, MySellersOrders.class));
                            return true;
                        case R.id.LoginLogout:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, ClientRegisteration.class));
                            return true;
                    }


                return true;
            }
        });


    }
    public void BuyerScreens(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_myAccount, R.id.nav_myCart,
                R.id.nav_myOrders, R.id.nav_loginLogout, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        // navigation drawer
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, CategoriesHomeScreen.class));
                            return true;
                        case R.id.myaccount:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, Survey.class));
                            return true;
                        case R.id.mycart:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, ShopingCart.class));
                            return true;
                        case R.id.myorders:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, MyBuyersOrders.class));
                            return true;
                        case R.id.LoginLogout:
                            CategoriesHomeScreen.this.startActivity(new Intent(CategoriesHomeScreen.this, LoginSellerBuyer.class));
                            return true;
                    }


                return true;
            }
        });

    }

    //spinner url data fetch
    public void setURLspinner_main_item() {
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLmain_item, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // if(response.contains("success"))
                    {
                        String result = response;
                        try {
                            JSONArray jsnArray = new JSONArray(result);
                            for (int i = 0; i < jsnArray.length(); i++) {
                                JSONObject objectPremium = (JSONObject) jsnArray.get(i);
                                //    JSONObject json = jsonObject.getJSONObject("address");
                                String code = objectPremium.getString("code");
                                String name = objectPremium.getString("name");
                                String state = objectPremium.getString("state");
                                arrayList.add(name);
                            }
                            arrayAdapter = new ArrayAdapter(CategoriesHomeScreen.this, android.R.layout.simple_spinner_item, arrayList);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(arrayAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        /*editor.putString("sellerkey","seller");
                        editor.putString("phone",strPhone);
                        editor.putString("password",strPassword);
                        editor.commit();
                        LoginSellerBuyer.this.startActivity(new Intent(LoginSellerBuyer.this, MakeOrderHomeScreen.class));
*/
                    }
                 /*   else
                    {
                        Toast.makeText(getApplicationContext(), "username or password is not correct", Toast.LENGTH_SHORT).show();
                    }*/
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CategoriesHomeScreen.this, "Try again later", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    // Creating Map String Params.
                   /* Map<String, String> params = new HashMap<>();
                    params.put("phone",  phone.getText().toString());
                    params.put("password", password.getText().toString());*/
                    return null;
                }
            };
            requestQueue.add(stringRequest);
        }
        //category spinner
        catespinner = findViewById(R.id.categoryspinner);
        catespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Context context = parent.getContext();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Selected: ");
                stringBuilder.append(tutorialsName);
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
     setURL_category_spinner();
    }
    public void setURL_category_spinner() {
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    url_get_category, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // if(response.contains("success"))
                    {
                        String result = response;
                        try {
                            JSONArray jArray = new JSONArray(result);
                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject objectPremium = (JSONObject) jArray.get(i);
                                //    JSONObject json = jsonObject.getJSONObject("address");
                                String code = objectPremium.getString("code");
                                String market_code = objectPremium.getString("market_code");
                                String name = objectPremium.getString("name");
                                categoryarraylist.add(name);
                            }
                            categoryarrayAdapter = new ArrayAdapter(CategoriesHomeScreen.this,
                                    android.R.layout.simple_spinner_item, categoryarraylist);
                            categoryarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            catespinner.setAdapter(categoryarrayAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CategoriesHomeScreen.this, "Try again later", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return null;
                }
            };
            caterequestQueue.add(stringRequest);
        }
    }
}