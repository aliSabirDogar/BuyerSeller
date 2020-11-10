package com.radiocodeford.buyerseller.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.ModelOrderReview;
import com.radiocodeford.buyerseller.model.TicketListModel;

import java.util.ArrayList;
import java.util.List;

public class TicketListAdapter extends BaseAdapter {
    Context context;

    ArrayList<TicketListModel> ticketlist;
    LayoutInflater inflater;

    public TicketListAdapter(Context applicationContext, ArrayList<TicketListModel> ticketlist) {
        this.context = context;

        this.ticketlist= ticketlist;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return ticketlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_list_ticket, null);
        TextView quantity = (TextView) view.findViewById(R.id.tv_item_ticket_quantity);
        TextView description = (TextView) view.findViewById(R.id.tv_item_ticket_description);
        TextView price = (TextView) view.findViewById(R.id.tv_item_ticket_price);
        TextView amount = (TextView) view.findViewById(R.id.tv_item_ticket_amount);

        quantity.setText(ticketlist.get(i).getQuantity());
        description.setText(ticketlist.get(i).getDescription());
        price.setText(ticketlist.get(i).getPrice());
        amount.setText(ticketlist.get(i).getAmount());

        return view;
    }
}
