package com.radiocodeford.buyerseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.MySellersOrdersModel;
import com.radiocodeford.buyerseller.model.PaidOrdersListModel;

import java.util.ArrayList;

public class MySellersOrdersAdapter extends BaseAdapter {
    Context context;

    ArrayList<MySellersOrdersModel> mysellersorderlist;
    LayoutInflater inflater;

    public MySellersOrdersAdapter(Context applicationContext, ArrayList<MySellersOrdersModel> mysellersorderlist) {
        this.context = context;

        this.mysellersorderlist= mysellersorderlist;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mysellersorderlist.size();
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
        view = inflater.inflate(R.layout.item_list_my_sellers_orders, null);
        TextView nOrders = (TextView) view.findViewById(R.id.tv_my_sellers_orders_nOrders);
        TextView product = (TextView) view.findViewById(R.id.tv_my_sellers_orders_product);
        TextView price = (TextView) view.findViewById(R.id.tv_my_sellers_orders_price);
        TextView quantity = (TextView) view.findViewById(R.id.tv_my_sellers_orders_quantity);
        TextView client = (TextView) view.findViewById(R.id.tv_my_sellers_orders_client);
        TextView paymentTime = (TextView) view.findViewById(R.id.tv_my_sellers_orders_paymentTime);
        nOrders.setText(mysellersorderlist.get(i).getnOrders());
        product.setText(mysellersorderlist.get(i).getProduct());
        price.setText(mysellersorderlist.get(i).getPrice());
        quantity.setText(mysellersorderlist.get(i).getQuantity());
        client.setText(mysellersorderlist.get(i).getClient());
        paymentTime.setText(mysellersorderlist.get(i).getPaymentTime());

        return view;
    }
}
