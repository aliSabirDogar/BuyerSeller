package com.radiocodeford.buyerseller.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.OrderDetailsShopTrayListModel;

import java.util.ArrayList;

public class OrderDetailsShopTrayListAdapter extends BaseAdapter {
    Context context;
    public static  String[] valueList;

    ArrayList<OrderDetailsShopTrayListModel> list1;
    LayoutInflater inflter;
     int pos;

    public OrderDetailsShopTrayListAdapter(Context applicationContext, ArrayList<OrderDetailsShopTrayListModel> list1) {
        this.context = context;
        valueList = new String[list1.size()];
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
        view = inflter.inflate(R.layout.item_list_order_details_shop_tray, null);
        TextView customer_details= (TextView) view.findViewById(R.id.tv_customer_order_details_item);
        EditText price = (EditText) view.findViewById(R.id.tv_price_customer_order_details_item);
        EditText total = (EditText) view.findViewById(R.id.tv_total_customer_order_details_item);

         pos = i;
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valueList[pos] = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });
        customer_details.setText(list1.get(i).getProduct_details());
        price.setText(list1.get(i).getPrice());
        total.setText(list1.get(i).getTotal());
        return view;
    }


    public String[] getValueList(){
        return valueList;
    }
}
