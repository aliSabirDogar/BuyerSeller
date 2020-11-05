package com.radiocodeford.buyerseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.ModelOrderReview;
import com.radiocodeford.buyerseller.model.ShopTrayModel;

import java.util.List;

public class ShopTrayListAdapter extends Adapter<ShopTrayListAdapter.MyViewHolder> {
    public Context con;
    private List<ShopTrayModel> list;
    private OnClickListener mOnItemClickListener;
    private ShopTrayModel review;

    public class MyViewHolder extends ViewHolder {
        TextView id;
        TextView time,cell;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.id = (TextView) itemView.findViewById(R.id.tv_shoptray_id);
            this.cell = (TextView) itemView.findViewById(R.id.tv_shoptray_cell);
            this.time = (TextView) itemView.findViewById(R.id.tv_shoptray_time);

            itemView.setTag(this);
            itemView.setOnClickListener(ShopTrayListAdapter.this.mOnItemClickListener);
        }
    }

    public ShopTrayListAdapter(List<ShopTrayModel> eventsList, Context con) {
        this.list = eventsList;
        this.con = con;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_shoptray, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopTrayListAdapter.MyViewHolder holder, int position) {
        this.review = (ShopTrayModel) this.list.get(position);
        holder.id.setText(this.review.getName());
        holder.cell.setText(this.review.getCell());
        holder.time.setText(this.review.getShippingTime());

    }



    public int getItemCount() {
        return this.list.size();
    }

    public void setOnItemClickListener(OnClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }
}
