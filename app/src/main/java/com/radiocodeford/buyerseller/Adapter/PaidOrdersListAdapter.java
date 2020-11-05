package com.radiocodeford.buyerseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.PaidOrdersListModel;
import com.radiocodeford.buyerseller.model.TicketListModel;

import java.util.ArrayList;

public class PaidOrdersListAdapter extends BaseAdapter {
    Context context;

    ArrayList<PaidOrdersListModel> paidorderlist;
    LayoutInflater inflater;

    public PaidOrdersListAdapter(Context applicationContext, ArrayList<PaidOrdersListModel> paidorderlist) {
        this.context = context;

        this.paidorderlist= paidorderlist;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return paidorderlist.size();
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
        view = inflater.inflate(R.layout.item_list_paid_orders, null);
        TextView orderCode = (TextView) view.findViewById(R.id.tv_item_paid_orders_code);
        TextView cellPhone = (TextView) view.findViewById(R.id.tv_item_paid_orders_cell_phone);
        TextView name = (TextView) view.findViewById(R.id.tv_item_paid_orders_client_name);
        TextView time = (TextView) view.findViewById(R.id.tv_item_paid_orders_payment_time);

        orderCode.setText(paidorderlist.get(i).getOrderCode());
        cellPhone.setText(paidorderlist.get(i).getCellPhone());
        name.setText(paidorderlist.get(i).getName());
        time.setText(paidorderlist.get(i).getTime());

        return view;
    }
}
