package com.example.m4_millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameWonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        // Get winnings value from passed through extra rather than hard coding winning amount.
        Intent intent = getIntent();
        int earned = intent.getIntExtra("EARNED", 0);
        TextView tvWin = findViewById(R.id.tvWonGame);
        tvWin.setText(getText(R.string.winner).toString() + earned + "!");
    }
    public void exit(View view) {
        finish();
    }
}