package com.radiocodeford.buyerseller.Adapter;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.radiocodeford.buyerseller.CodePhoneNumber;
import com.radiocodeford.buyerseller.LoginSellerBuyer;
import com.radiocodeford.buyerseller.MarketRegisteration;
import com.radiocodeford.buyerseller.Order.MakeOrderHomeScreen;
import com.radiocodeford.buyerseller.R;
import com.radiocodeford.buyerseller.model.CategoryModelVerticalList;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HomeScreenCategoryListAdapter extends Adapter<HomeScreenCategoryListAdapter.MyViewHolder> implements OnClickListener {
    public Context con;
    private ArrayList<CategoryModelVerticalList> dataSet;
    SharedPreferences pref;
    String screenCheck,loginChecker;


    public static class MyViewHolder extends ViewHolder {
        TextView TvNameLeaderBorad_horizontal;
        ImageView event_image;
        TextView event_title;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.event_title = (TextView) itemView.findViewById(R.id.category_name);
            this.event_image = (ImageView) itemView.findViewById(R.id.title_image_category);
        }
    }

    public void onClick(View v) {

    }

    public HomeScreenCategoryListAdapter(ArrayList<CategoryModelVerticalList> data, Context con) {
        this.dataSet = data;
        this.con = con;
    }

    public void ShowAlert() {
        Builder alertDialogBuilder = new Builder(this.con);
        alertDialogBuilder.setTitle("Notice");
        String str = "CANCEL";
        alertDialogBuilder.setMessage("BUYER FRIEND MUST REGISTER TO PALACE YOUR ORDER. THANK YOU!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                HomeScreenCategoryListAdapter.this.con.startActivity(new Intent(HomeScreenCategoryListAdapter.this.con, LoginSellerBuyer.class));
            }
        }).setNegativeButton(str, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_list_item_home_screen, parent, false);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                pref =con.getSharedPreferences("buyerSeller2", MODE_PRIVATE);

                loginChecker = pref.getString("LoginOneTime","null");
                if(loginChecker.contains("success")){
                    HomeScreenCategoryListAdapter.this.con.startActivity(new Intent(HomeScreenCategoryListAdapter.this.con, MakeOrderHomeScreen.class));
                }
                else {
                    HomeScreenCategoryListAdapter.this.ShowAlert();
                }
            }
        });
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(MyViewHolder holder, int listPosition) {
        TextView textViewName = holder.event_title;
        ImageView imageView = holder.event_image;
        textViewName.setText(((CategoryModelVerticalList) this.dataSet.get(listPosition)).getName());
        imageView.setImageResource(((CategoryModelVerticalList) this.dataSet.get(listPosition)).getImage());
    }

    public int getItemCount() {
        return this.dataSet.size();
    }
}
