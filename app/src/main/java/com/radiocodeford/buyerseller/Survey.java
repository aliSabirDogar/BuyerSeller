package com.radiocodeford.buyerseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Survey extends AppCompatActivity {
    Button send;
    Button goOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        this.send = (Button) findViewById(R.id.btn_survey_send);
        this.send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Survey.this.startActivity(new Intent(Survey.this, Ticket.class));
            }
        });
        this.goOut = (Button) findViewById(R.id.btn_survey_goOut);
        this.goOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Survey.this.startActivity(new Intent(Survey.this, Ticket.class));
            }
        });
    }
}