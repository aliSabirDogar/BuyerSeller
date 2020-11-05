package com.radiocodeford.buyerseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShipInformation extends AppCompatActivity {
Button ShippingPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_information);
        this.ShippingPay = (Button) findViewById(R.id.btn_ship_info_continue_and_pay);
        this.ShippingPay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShipInformation.this.startActivity(new Intent(ShipInformation.this, Payments.class));
            }
        });
    }
}