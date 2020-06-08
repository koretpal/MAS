package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvclockin, tvclockout, tvregisterhere;
   // private CardView cdin, cdout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvclockin = findViewById(R.id.tvclockin);
        tvclockout = findViewById(R.id.tvclockout);
        tvregisterhere = findViewById(R.id.tvregisterhere);
//        cdin = findViewById(R.id.cdin);
//        cdout = findViewById(R.id.cdout);
//
//        cdin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity( new Intent(MainActivity.this, Clockin.class));
//            }
//        });
//        cdout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity( new Intent(MainActivity.this, Clockout.class));
//            }
//        });

        tvclockin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, Clockin.class));
            }
        });
        tvclockout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, Clockout.class));
            }
        });

        tvregisterhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, Registration.class));
            }
        });
    }
}
