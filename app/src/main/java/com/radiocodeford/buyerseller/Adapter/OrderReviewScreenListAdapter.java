package com.radiocodeford.buyerseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.ModelOrderReview;
import com.radiocodeford.buyerseller.model.SelectQueryOrder;

import java.util.List;

public class OrderReviewScreenListAdapter extends Adapter<OrderReviewScreenListAdapter.MyViewHolder> {
    public Context con;
    private List<SelectQueryOrder> list;
    private OnClickListener mOnItemClickListener;
    private SelectQueryOrder review;

    public class MyViewHolder extends ViewHolder {
        TextView product;
        TextView quantity;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.product = (TextView) itemView.findViewById(R.id.item_list_product_name);
            this.quantity = (TextView) itemView.findViewById(R.id.item_list_product_quantity);
            itemView.setTag(this);
            itemView.setOnClickListener(OrderReviewScreenListAdapter.this.mOnItemClickListener);
        }
    }

    public OrderReviewScreenListAdapter(List<SelectQueryOrder> eventsList, Context con) {
        this.list = eventsList;
        this.con = con;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_review_order, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        this.review = (SelectQueryOrder) this.list.get(position);
        holder.product.setText(this.review.getProduct());
        holder.quantity.setText(this.review.getQuantity());
    }

    public int getItemCount() {
        return this.list.size();
    }

    public void setOnItemClickListener(OnClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }
}
