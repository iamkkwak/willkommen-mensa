package com.example.kkwak.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btn_offer;
    private Button btn_location;
    private Button btn_time;
    private Button btn_qna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // OnClickLister for each button in main.

        btn_offer = (Button)findViewById(R.id.button_offer);
        btn_offer.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OfferActivity.class);
                startActivity(intent);
            }
        });

        btn_location = (Button)findViewById(R.id.button_location);
        btn_location.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        btn_time = (Button)findViewById(R.id.button_time);
        btn_time.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimeActivity.class);
                startActivity(intent);
            }
        });

        btn_qna = (Button)findViewById(R.id.button_qna);
        btn_qna.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QnaActivity.class);
                startActivity(intent);
            }
        });
    }
}
