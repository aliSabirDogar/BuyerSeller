package com.radiocodeford.buyerseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.radiocodeford.buyerseller.Order.ShopTray;

public class Payments extends AppCompatActivity {
Button payNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        this.payNow = (Button) findViewById(R.id.btn__payment_pay_now);
        this.payNow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Payments.this.startActivity(new Intent(Payments.this, Survey.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.buyer_screens_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_basket:   //this item has your app icon
                Payments.this.startActivity(new Intent(Payments.this, ShopingCart.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}