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
import com.radiocodeford.buyerseller.model.BuyerTrayModel;

import java.util.List;

public class SellerTrayListAdapter extends Adapter<SellerTrayListAdapter.MyViewHolder> {

    public Context cont;
    private List<BuyerTrayModel> list;
    private OnClickListener mOnItemClickListener;
    private BuyerTrayModel review;

    public class MyViewHolder extends ViewHolder {
        TextView id;
        TextView time,cell;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.id = (TextView) itemView.findViewById(R.id.tv_sellertray_id);
            this.cell = (TextView) itemView.findViewById(R.id.tv_sellertray_buyercell);
            this.time = (TextView) itemView.findViewById(R.id.tv_sellertray_time);

            itemView.setTag(this);
            itemView.setOnClickListener(SellerTrayListAdapter.this.mOnItemClickListener);
        }
    }

    public SellerTrayListAdapter(List<BuyerTrayModel> eventsList, Context con) {
        this.list = eventsList;
        this.cont = con;
    }

    public SellerTrayListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SellerTrayListAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_sellertray, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SellerTrayListAdapter.MyViewHolder holder, int position) {
        this.review = (BuyerTrayModel) this.list.get(position);
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
