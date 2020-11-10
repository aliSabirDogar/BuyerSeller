package com.radiocodeford.buyerseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.radiocodeford.buyerseller.Adapter.CartItemAdapter;
import com.radiocodeford.buyerseller.Order.BuyerTrayOrderDetails;
import com.radiocodeford.buyerseller.model.CartItemsModel;

import java.util.ArrayList;

public class ShopingCart extends AppCompatActivity {

    ListView simpleList;
    Button pay;
    ArrayList<CartItemsModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_cart);
        simpleList = (ListView) findViewById(R.id.list_cart);
   /*     tv_buyer_tray_details = (TextView) findViewById(R.id.tv_customer_order_details);
        tv_buyer_tray_details.setText("Customer : "+ShopTray.customer_id);*/
        list.add(new CartItemsModel("20$", "12", "yellow potato","john","1000$",R.drawable.grocery));
        list.add(new CartItemsModel("20$", "12", "black potato","peter","1000$",R.drawable.grocery2));
        list.add(new CartItemsModel("10$", "12", "yucca","michel","1000$",R.drawable.grocery));





        CartItemAdapter customAdapter = new CartItemAdapter(getApplicationContext(), list);
        simpleList.setAdapter(customAdapter);
        this.pay = (Button) findViewById(R.id.btn_shopping_cart_pay);
        this.pay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShopingCart.this.startActivity(new Intent(ShopingCart.this, ShipInformation.class));
            }
        });
    }
  /*  @Override
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
                ShopingCart.this.startActivity(new Intent(ShopingCart.this, ShopingCart.class));
                return true;

            case R.id.toolbar_bell:   //this item has your app icon
                ShopingCart.this.startActivity(new Intent(ShopingCart.this, BuyerTrayOrderDetails.class));
                return true;
            default: return super.onOptionsItemSelected(item);
        }
  */
}


