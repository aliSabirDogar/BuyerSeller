package com.radiocodeford.buyerseller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.SliderModelHorizontalList;

import java.util.ArrayList;

public class HomeScreenSliderListAdapter extends Adapter<HomeScreenSliderListAdapter.MyViewHolder> {
    public Context con;
    private ArrayList<SliderModelHorizontalList> dataSet;

    public static class MyViewHolder extends ViewHolder {
        ImageView event_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.event_image = (ImageView) itemView.findViewById(R.id.title_image);
        }
    }

    public HomeScreenSliderListAdapter(ArrayList<SliderModelHorizontalList> data, Context con) {
        this.dataSet = data;
        this.con = con;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slidert_list_item_horizontal, parent, false);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.event_image.setImageResource(((SliderModelHorizontalList) this.dataSet.get(position)).getImage());
    }




    public int getItemCount() {
        return this.dataSet.size();
    }
}
