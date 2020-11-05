package com.radiocodeford.buyerseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.radiocodeford.buyerseller.Adapter.PaidOrdersListAdapter;
import com.radiocodeford.buyerseller.Adapter.TicketListAdapter;
import com.radiocodeford.buyerseller.model.PaidOrdersListModel;
import com.radiocodeford.buyerseller.model.TicketListModel;

import java.util.ArrayList;

public class PaidOrders extends AppCompatActivity {
    ListView paidOrdersSimpleList;
    ArrayList<PaidOrdersListModel> paidOrdersList;
    Button goOut;

    public PaidOrders() {
        paidOrdersList = new ArrayList<PaidOrdersListModel>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_orders);
        this.goOut = (Button) findViewById(R.id.btn_paid_orders_goOut);
        this.goOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PaidOrders.this.startActivity(new Intent(PaidOrders.this, MySellersOrders.class));
            }
        });

            paidOrdersSimpleList = (ListView)findViewById(R.id.list_paid_orders);
            paidOrdersList.add(new PaidOrdersListModel("1580","9172635","John","JUNE 10,10:30 am"));
            paidOrdersList.add(new PaidOrdersListModel("1581","9172635","Maria","10:40 am"));
            paidOrdersList.add(new PaidOrdersListModel("1582","9172635","Jose","10:50 am"));
            paidOrdersList.add(new PaidOrdersListModel("1580","9172635","John","10:30 am"));
            paidOrdersList.add(new PaidOrdersListModel("1581","9172635","Maria","10:40 am"));
            paidOrdersList.add(new PaidOrdersListModel("1582","9172635","Jose","10:50 am"));
        paidOrdersList.add(new PaidOrdersListModel("1580","9172635","John","10:30 am"));
        paidOrdersList.add(new PaidOrdersListModel("1581","9172635","Maria","10:40 am"));
        paidOrdersList.add(new PaidOrdersListModel("1582","9172635","Jose","10:50 am"));
        paidOrdersList.add(new PaidOrdersListModel("1580","9172635","John","10:30 am"));
        paidOrdersList.add(new PaidOrdersListModel("1581","9172635","Maria","10:40 am"));
        paidOrdersList.add(new PaidOrdersListModel("1582","9172635","Jose","10:50 am"));
        paidOrdersList.add(new PaidOrdersListModel("1580","9172635","John","10:30 am"));
        paidOrdersList.add(new PaidOrdersListModel("1581","9172635","Maria","10:40 am"));
        paidOrdersList.add(new PaidOrdersListModel("1582","9172635","Jose","10:50 am"));
        paidOrdersList.add(new PaidOrdersListModel("1580","9172635","John","10:30 am"));
        paidOrdersList.add(new PaidOrdersListModel("1581","9172635","Maria","10:40 am"));
        paidOrdersList.add(new PaidOrdersListModel("1582","9172635","Jose","10:50 am"));
        paidOrdersList.add(new PaidOrdersListModel("1580","9172635","John","10:30 am"));
        paidOrdersList.add(new PaidOrdersListModel("1581","9172635","Maria","10:40 am"));
        paidOrdersList.add(new PaidOrdersListModel("1582","9172635","Jose","10:50 am"));
        paidOrdersList.add(new PaidOrdersListModel("1580","9172635","John","10:30 am"));
        paidOrdersList.add(new PaidOrdersListModel("1581","9172635","Maria","10:40 am"));
        paidOrdersList.add(new PaidOrdersListModel("1582","9172635","Jose","10:50 am"));

        PaidOrdersListAdapter customAdapter = new PaidOrdersListAdapter(getApplicationContext(), paidOrdersList);


            paidOrdersSimpleList.setAdapter(customAdapter);


        }
}