package com.example.m4_millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HavenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int havenEarned = intent.getIntExtra("HAVEN", 0);
        setContentView(R.layout.activity_haven);
        TextView tvHaven = findViewById(R.id.tvHaven);
        tvHaven.setText(getText(R.string.haven).toString()  + havenEarned + " guaranteed!");
    }

    public void nextQ(View view){
        finish();
    }
}