package com.radiocodeford.buyerseller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.radiocodeford.buyerseller.Adapter.TicketListAdapter;
import com.radiocodeford.buyerseller.model.TicketListModel;

import java.util.ArrayList;

public class Ticket extends AppCompatActivity {
    ListView ticketSimpleList;
    Button close;
    ArrayList<TicketListModel> tickeList;

    public Ticket() {
        tickeList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ticket);
        ticketSimpleList = (ListView)findViewById(R.id.list_ticket);
        tickeList.add(new TicketListModel("122222","yellow potatooooooooooooo","333332","222222"));
        tickeList.add(new TicketListModel("3","Black potato","3","9"));
        tickeList.add(new TicketListModel("4","Yucca","2.50","10"));
        tickeList.add(new TicketListModel("1","yellow potato","2","2"));
        tickeList.add(new TicketListModel("3","Black potato","3","9"));
        tickeList.add(new TicketListModel("4","Yucca","2.50","10"));
        tickeList.add(new TicketListModel("1","yellow potato","2","2"));
        tickeList.add(new TicketListModel("3","Black potato","3","9"));
        tickeList.add(new TicketListModel("4","Yucca","2.50","10"));
        tickeList.add(new TicketListModel("1","yellow potato","2","2"));
        tickeList.add(new TicketListModel("3","Black potato","3","9"));
        tickeList.add(new TicketListModel("4","Yucca","2.50","10"));
        tickeList.add(new TicketListModel("1","yellow potato","2","2"));
        tickeList.add(new TicketListModel("3","Black potato","3","9"));
        tickeList.add(new TicketListModel("4","Yucca","2.50","10"));
        tickeList.add(new TicketListModel("1","yellow potato","2","2"));
        tickeList.add(new TicketListModel("3","Black potato","3","9"));
        tickeList.add(new TicketListModel("4","Yucca","2.50","10"));
        TicketListAdapter customAdapter = new TicketListAdapter(getApplicationContext(), tickeList);


        ticketSimpleList.setAdapter(customAdapter);
        this.close = (Button) findViewById(R.id.btn_ticket_close);
        this.close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Ticket.this.startActivity(new Intent(Ticket.this, PaidOrders.class));
            }
        });


    }
}
