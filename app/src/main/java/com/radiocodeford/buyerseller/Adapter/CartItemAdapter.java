package com.radiocodeford.buyerseller.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.CartItemsModel;
import com.radiocodeford.buyerseller.model.CategoryModelVerticalList;
import com.radiocodeford.buyerseller.model.OrderDetailsShopTrayListModel;
import java.util.ArrayList;

public class CartItemAdapter extends BaseAdapter {
    Context context;

    ArrayList<CartItemsModel> list1;
    LayoutInflater inflter;

    public CartItemAdapter(Context applicationContext, ArrayList<CartItemsModel> list1) {
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
        view = inflter.inflate(R.layout.item_list_cart, null);
        TextView product = (TextView) view.findViewById(R.id.tv_product_item_cart);
        TextView price = (TextView) view.findViewById(R.id.tv_price_item_cart);
        TextView quantity = (TextView) view.findViewById(R.id.tv_quantity_item_cart);
        TextView subtotal= (TextView) view.findViewById(R.id.tv_subtotal_item_cart);
        ImageView image_cart= (ImageView) view.findViewById(R.id.image_cart_item);
        TextView seller= (TextView) view.findViewById(R.id.tv_seller_item_cart);
        price.setText(list1.get(i).getPrice());
        product.setText(list1.get(i).getProduct());
        quantity.setText(list1.get(i).getQuantity());
        subtotal.setText(list1.get(i).getSubtotal());
        seller.setText(list1.get(i).getSeller());
        image_cart.setImageResource(list1.get(i).getCart_item_image());
        return view;
    }
}
