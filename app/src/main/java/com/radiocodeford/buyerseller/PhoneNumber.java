package com.radiocodeford.buyerseller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PhoneNumber extends AppCompatActivity {
    Button proceed;
    TextView tvCountryCode;
    EditText phoneNumber;
    CheckBox termsConditions;
    String stringPhoneNumber,randomPincode,sms;
    boolean isphoneNumberValid, ischeckboxValid;
    private static final int PERMISSION_REQUEST_CODE = 1;
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        phoneNumber= (EditText) findViewById(R.id.tf_phoneNumber_cell);
        termsConditions=(CheckBox) findViewById(R.id.cb_phone_number_terms);
        tvCountryCode = (TextView)findViewById(R.id.tv_phoneNumber_countryCode);
        tvCountryCode.setText("+"+GetCountryZipCode() );
        this.proceed = (Button) findViewById(R.id.btn_phone_number_proceed);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
        }
        //Intent myIntent;
        proceed.setOnClickListener(
                new View.OnClickListener(  ) {

            @Override
            public void onClick(View v) {
                SetValidation(); }
        });

    }
    public void SetValidation() {
        // Check for the empty textfield of phone number.
        if (phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter Phone Number", Toast.LENGTH_SHORT).show();
            isphoneNumberValid = false;
        }
        else if (phoneNumber.getText().length()<10) {
            Toast.makeText(getApplicationContext(), "Phone Number is too short", Toast.LENGTH_SHORT).show();
            isphoneNumberValid = false;

        }
        else {
            isphoneNumberValid = true;
        }


        // Check for the checkbox of terms and conditions.
        if (termsConditions.isChecked()) {
            ischeckboxValid = true;
        } else {
            Toast.makeText(getApplicationContext(), "Please agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            ischeckboxValid = false;

        }

        if (isphoneNumberValid && ischeckboxValid) {
            final Random pincode= new Random();
            int value = pincode.nextInt(10000);
            randomPincode = Integer.toString(value);
            stringPhoneNumber=phoneNumber.getText().toString();
            stringPhoneNumber = stringPhoneNumber.startsWith("0") ? stringPhoneNumber.substring(1) : stringPhoneNumber;


            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+" +GetCountryZipCode() +stringPhoneNumber, null, randomPincode, null, null);
                Toast.makeText(getApplicationContext(), "SMS Sent!",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "SMS faild, please try again later!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            Intent myIntent = new Intent( PhoneNumber.this, CodePhoneNumber.class);
            myIntent.putExtra("pincode", randomPincode);

            startActivity(myIntent);
           PhoneNumber.this.startActivity(new Intent(PhoneNumber.this, CodePhoneNumber.class));
        }
    }
    String GetCountryZipCode(){

        String CountryID="";
        String CountryZipCode="";
        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        CountryID= manager.getSimCountryIso().toUpperCase();
        String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(CountryID.trim())){
                CountryZipCode=g[0];break;  }
        }
return  CountryZipCode;
}}