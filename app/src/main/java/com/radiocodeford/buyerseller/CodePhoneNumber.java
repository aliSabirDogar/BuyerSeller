package com.radiocodeford.buyerseller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.radiocodeford.buyerseller.Order.MakeOrderHomeScreen;

import java.security.Key;
import java.util.prefs.PreferenceChangeEvent;

public class CodePhoneNumber extends AppCompatActivity {

    String stringNumber;
    Button proceed;
    TextView pincode;
    int state;
    String codePhoneNumber,randomPincode;
    EditText digit1,digit2,digit3,digit4;
    boolean isPinCodeValid;
    SharedPreferences pref;
    //int i;
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_phone_number);
        this.proceed = (Button) findViewById(R.id.btn_code_proceed);
        //this.phoneNumber = (TextView) findViewById(R.id.tv_code_phoneNumber);
        digit1 = (EditText) findViewById(R.id.codephone1);
        digit2 = (EditText) findViewById(R.id.codephone2);
        digit3 = (EditText) findViewById(R.id.codephone3);
        digit4 = (EditText) findViewById(R.id.codephone4);

        randomPincode = getIntent().getExtras().getString("pincode");


          pref = this.getSharedPreferences("buyerSeller", Context.MODE_PRIVATE);

          state= pref.getInt("directedkey",0);
          this.proceed.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int n1= Integer.parseInt(digit1.getText().toString());
                int n2= Integer.parseInt(digit2.getText().toString());
                int n3= Integer.parseInt(digit3.getText().toString());
                int n4= Integer.parseInt(digit4.getText().toString());
                int number = Integer.valueOf(String.valueOf(n1) + String.valueOf(n2)+String.valueOf(n3) + String.valueOf(n4));
                stringNumber = Integer.toString(number);


                SetValidation();
            }
            public void SetValidation() {
                // Check for a valid pincode.
                if (stringNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "enter the pin Code", Toast.LENGTH_SHORT).show();
                    isPinCodeValid = false;
                } else if (stringNumber.matches(randomPincode)) {
                    Toast.makeText(getApplicationContext(), "Pin Code is  correct", Toast.LENGTH_SHORT).show();
                    isPinCodeValid = true;
                } else {
                    Toast.makeText(getApplicationContext(), "Pin Code is not correct", Toast.LENGTH_SHORT).show();
                    isPinCodeValid = false;
                }



                if (isPinCodeValid)
                {
                    if (state==0)
                    { CodePhoneNumber.this.startActivity(new Intent(CodePhoneNumber.this, ClientRegisteration.class)); }
                    else {
                        CodePhoneNumber.this.startActivity(new Intent(CodePhoneNumber.this, MarketRegisteration.class));
                    }


                }
                else
                    {  }
                 }



        });
    }
}
