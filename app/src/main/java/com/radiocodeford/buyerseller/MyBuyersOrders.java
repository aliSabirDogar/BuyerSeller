package com.radiocodeford.buyerseller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.radiocodeford.buyerseller.Adapter.MyBuyersOrdersAdapter;
import com.radiocodeford.buyerseller.Adapter.MySellersOrdersAdapter;
import com.radiocodeford.buyerseller.model.MyBuyersOrdersModel;
import com.radiocodeford.buyerseller.model.MySellersOrdersModel;

import java.util.ArrayList;

public class MyBuyersOrders extends AppCompatActivity {
    ListView myBuyersOrdersSimpleList;
    ArrayList<MyBuyersOrdersModel> myBuyersOrdersList;
    Button goOut;

    public MyBuyersOrders() {
        myBuyersOrdersList = new ArrayList<MyBuyersOrdersModel>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_buyers_orders);
        this.goOut = (Button) findViewById(R.id.btn_my_buyers_goOut);
        this.goOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


            }
        });
        myBuyersOrdersSimpleList = (ListView)findViewById(R.id.list_my_buyers_orders);
        myBuyersOrdersList.add(new MyBuyersOrdersModel("1234 seller (Don Lucho)","medium yellow ","3.00","2 kilos","John Fred - 993748567","10:30 am"));
        myBuyersOrdersList.add(new MyBuyersOrdersModel("1234 seller (Don Lucho)","medium yellow ","3.00","2 kilos","John Fred - 993748567","10:30 am"));
        myBuyersOrdersList.add(new MyBuyersOrdersModel("1234 seller (Don Lucho)","medium yellow ","3.00","2 kilos","John Fred - 993748567","10:30 am"));
        myBuyersOrdersList.add(new MyBuyersOrdersModel("1234 seller (Don Lucho)","medium yellow ","3.00","2 kilos","John Fred - 993748567","10:30 am"));

        MyBuyersOrdersAdapter customAdapter = new MyBuyersOrdersAdapter(getApplicationContext(), myBuyersOrdersList);
        myBuyersOrdersSimpleList.setAdapter(customAdapter);



    }
}