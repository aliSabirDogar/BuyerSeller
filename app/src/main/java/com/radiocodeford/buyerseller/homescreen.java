package com.radiocodeford.buyerseller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.radiocodeford.buyerseller.Adapter.HomeScreenCategoryListAdapter;
import com.radiocodeford.buyerseller.Adapter.HomeScreenSliderListAdapter;
import com.radiocodeford.buyerseller.model.CategoryModelVerticalList;
import com.radiocodeford.buyerseller.model.SliderModelHorizontalList;
import com.radiocodeford.buyerseller.model.testData;
import com.radiocodeford.buyerseller.model.testData2;

import java.util.ArrayList;

public class homescreen extends AppCompatActivity {
    private static HomeScreenSliderListAdapter Slideradapter;
    private static HomeScreenCategoryListAdapter Categoryadapter;
    private static ArrayList<CategoryModelVerticalList> data;
    private static ArrayList<SliderModelHorizontalList> data2;
    static OnClickListener myOnClickListener;
    private static RecyclerView recyclerView_HORIZONTAL;
    private static RecyclerView recyclerView_VERTICAL;
    private static ArrayList<Integer> removedItems;
    TextView create_event;
    private RecyclerView.LayoutManager layoutManager_HORIZONTAL;
    private RecyclerView.LayoutManager layoutManager_VERTICAL;
    ImageView basket2;
    FloatingActionButton cart;


    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_homescreen);
        recyclerView_VERTICAL = (RecyclerView) findViewById(R.id.my_recycler_view_hot_vertical);
        recyclerView_HORIZONTAL = (RecyclerView) findViewById(R.id.my_recycler_view_hot_horizontal);
       // basket2 = (ImageView) findViewById(R.id.basket);

        recyclerView_VERTICAL.setHasFixedSize(true);
        recyclerView_HORIZONTAL.setHasFixedSize(true);
        this.layoutManager_VERTICAL = new LinearLayoutManager(this);
        recyclerView_VERTICAL.setLayoutManager(this.layoutManager_VERTICAL);
        recyclerView_VERTICAL.setItemAnimator(new DefaultItemAnimator());


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_HORIZONTAL.setLayoutManager(layoutManager);
        data = new ArrayList();
        for (i = 1; i < testData2.NameEvent.length; i++) {
            data.add(new CategoryModelVerticalList(testData2.NameEvent[i], testData2.drawableArray[i].intValue()));
        }
        removedItems = new ArrayList();
        Categoryadapter = new HomeScreenCategoryListAdapter(data, this);
        recyclerView_VERTICAL.setAdapter(Categoryadapter);
        data2 = new ArrayList();
        for (i = 0; i < testData.drawableArray.length; i++) {
            data2.add(new SliderModelHorizontalList(testData.drawableArray[i].intValue()));
        }
        removedItems = new ArrayList();
        Slideradapter = new HomeScreenSliderListAdapter(data2, this);
        recyclerView_HORIZONTAL.setAdapter(Slideradapter);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        basket2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                homescreen.this.startActivity(new Intent(homescreen.this, ShopingCart.class));
            }
        });

        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("Market and Groceries");
        arrayList.add("Food");
        arrayList.add("Clothes");
        arrayList.add("Others");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Context context = parent.getContext();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Selected: ");
                stringBuilder.append(tutorialsName);

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
/*    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buyer_screens_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.basket) {
          startActivity(new Intent(homescreen.this, ShopingCart.class));
            // add your action here that you want
            return true;
        }




        return super.onOptionsItemSelected(item);
    }*/
}
