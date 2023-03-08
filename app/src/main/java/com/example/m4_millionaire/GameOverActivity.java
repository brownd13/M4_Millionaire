package com.example.m4_millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        // Get haven earnings value if reached
        Intent intent = getIntent();
        int earned = intent.getIntExtra("EARNED", 0);

        if (earned > 0) {
            TextView tvOver = findViewById(R.id.tvGameOver);
            tvOver.setText(getText(R.string.gameOver).toString() + "\n" +
                    getText(R.string.haven) + earned + "!");
        }
    }

    public void exit(View view) {
        finish();
    }
}