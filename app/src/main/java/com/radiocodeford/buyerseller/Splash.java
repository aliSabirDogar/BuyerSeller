package com.radiocodeford.buyerseller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.radiocodeford.buyerseller.Order.SellerTray;

public class Splash extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String  loginChecker,screenCheck;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        pref =getSharedPreferences("buyerSeller", MODE_PRIVATE);

        screenCheck = pref.getString("screenCheckKey", "null");
        loginChecker = pref.getString("LoginOneTime","null");

        if(loginChecker.contains("success")) {
            if (screenCheck.contains("seller")){
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Splash.this.startActivity(new Intent(Splash.this, SellerTray.class));
                        Splash.this.finish();
                    }
                }, 2500);
            }
            else{
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Splash.this.startActivity(new Intent(Splash.this, CategoriesHomeScreen.class));
                        Splash.this.finish();
                    }
                }, 2500);
            }


        }
        else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Splash.this.startActivity(new Intent(Splash.this, CategoriesHomeScreen.class));
                    Splash.this.finish();
                }
            }, 2500);
        }



    }
}
