package com.radiocodeford.buyerseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.OrderDetailsShopTrayListModel;

import java.util.ArrayList;

public class OrderDetailsBuyerTrayListAdapter extends BaseAdapter {
    Context context;

    ArrayList<OrderDetailsShopTrayListModel> list1;
    LayoutInflater inflter;

    public OrderDetailsBuyerTrayListAdapter(Context applicationContext, ArrayList<OrderDetailsShopTrayListModel> list1) {
        this.context = context;

        this.list1= list1;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return list1.size();
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
        view = inflter.inflate(R.layout.item_list_order_details_buyer_tray, null);
        TextView customer_details= (TextView) view.findViewById(R.id.tv_buyer_order_details_item);
        TextView price = (TextView) view.findViewById(R.id.tv_price_buyer_order_details_item);
        TextView total = (TextView) view.findViewById(R.id.tv_total_buyer_order_details_item);

        customer_details.setText(list1.get(i).getProduct_details());
        price.setText(list1.get(i).getPrice());
        total.setText(list1.get(i).getTotal());
        return view;
    }
}
