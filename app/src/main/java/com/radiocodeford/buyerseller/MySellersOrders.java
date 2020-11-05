package com.radiocodeford.buyerseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.radiocodeford.buyerseller.Adapter.MySellersOrdersAdapter;
import com.radiocodeford.buyerseller.Adapter.PaidOrdersListAdapter;
import com.radiocodeford.buyerseller.Order.MakeOrderHomeScreen;
import com.radiocodeford.buyerseller.model.MySellersOrdersModel;
import com.radiocodeford.buyerseller.model.PaidOrdersListModel;

import java.util.ArrayList;

public class MySellersOrders extends AppCompatActivity {
    ListView mySellersOrdersSimpleList;
    ArrayList<MySellersOrdersModel> mySellersOrdersList;
    Button goOut;

    public MySellersOrders() {
        mySellersOrdersList = new ArrayList<MySellersOrdersModel>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sellers_orders);
        this.goOut = (Button) findViewById(R.id.btn_my_sellers_goOut);
        this.goOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MySellersOrders.this.startActivity(new Intent (MySellersOrders.this, MyBuyersOrders.class));


            }
        });

        mySellersOrdersSimpleList = (ListView)findViewById(R.id.list_my_sellers_orders);
        mySellersOrdersList.add(new MySellersOrdersModel("1234 seller (Don Lucho)","medium yellow ","3.00","2 kilos","John Fred - 993748567","10:30 am"));
        mySellersOrdersList.add(new MySellersOrdersModel("1234 seller (Don Lucho)","medium yellow ","3.00","2 kilos","John Fred - 993748567","10:30 am"));
        MySellersOrdersAdapter customAdapter = new MySellersOrdersAdapter(getApplicationContext(), mySellersOrdersList);
        mySellersOrdersSimpleList.setAdapter(customAdapter);


    }
}