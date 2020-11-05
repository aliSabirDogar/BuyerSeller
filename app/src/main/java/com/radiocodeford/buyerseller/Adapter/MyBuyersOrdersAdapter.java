package com.radiocodeford.buyerseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.MyBuyersOrdersModel;

import java.util.ArrayList;

public class MyBuyersOrdersAdapter extends BaseAdapter {
    Context context;

    ArrayList<MyBuyersOrdersModel> mybuyersorderlist;
    LayoutInflater inflater;

    public MyBuyersOrdersAdapter(Context applicationContext, ArrayList<MyBuyersOrdersModel> mybuyersorderlist) {
        this.context = context;

        this.mybuyersorderlist= mybuyersorderlist;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mybuyersorderlist.size();
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
        view = inflater.inflate(R.layout.item_list_my_buyers_orders, null);
        TextView nOrders = (TextView) view.findViewById(R.id.tv_my_buyers_orders_nOrders);
        TextView product = (TextView) view.findViewById(R.id.tv_my_buyers_orders_product);
        TextView price = (TextView) view.findViewById(R.id.tv_my_buyers_orders_price);
        TextView quantity = (TextView) view.findViewById(R.id.tv_my_buyers_orders_quantity);
        TextView client = (TextView) view.findViewById(R.id.tv_my_buyers_orders_client);
        TextView paymentTime = (TextView) view.findViewById(R.id.tv_my_buyers_orders_paymentTime);
        nOrders.setText(mybuyersorderlist.get(i).getBuyersnOrders());
        product.setText(mybuyersorderlist.get(i).getBuyersproduct());
        price.setText(mybuyersorderlist.get(i).getBuyersprice());
        quantity.setText(mybuyersorderlist.get(i).getBuyersquantity());
        client.setText(mybuyersorderlist.get(i).getBuyersclient());
        paymentTime.setText(mybuyersorderlist.get(i).getBuyerspaymentTime());

        return view;
    }
}
