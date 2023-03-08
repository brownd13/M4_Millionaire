package com.example.m4_millionaire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    private String[] questions;
    private int[] answerKey;
    private int[] prizeValue;
    private int earned = 0;
    private int havenEarned = 0;
    private int questionTotal;

    // Radio button resource IDs, and answer submission vars
    final private int[] rbResID = { R.id.A1, R.id.A2, R.id.A3, R.id.A4};
    final private int numAnswers = 4;
    private int questionCurrent = 0;
    private int submittedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load question/answer/prize info from string.xml
        questions = getResources().getStringArray(R.array.questions);
        answerKey = getResources().getIntArray(R.array.answerKey);
        prizeValue = getResources().getIntArray(R.array.prizeValue);
        questionTotal = questions.length;

        // Restore state, current question, and winnings
        if(savedInstanceState != null){
            questionCurrent = savedInstanceState.getInt("Q_CUR",0);
            earned = savedInstanceState.getInt("EARNED",0);
        }
        displayQuestion();
    }

    // Save state, current question, and winnings
    @Override
    protected void onSaveInstanceState(@NonNull Bundle state){
        super.onSaveInstanceState(state);
        state.putInt("Q_CUR", questionCurrent);
        state.putInt("EARNED", earned);
    }

    private void displayQuestion(){
        submittedAnswer = -1;   // reset - no answer selected
        setContentView(R.layout.question);

        // Populate TextView with Question and Answer content from strings.xml resource
        String[] QA = questions[questionCurrent].split(";");
        TextView tvEarned = findViewById(R.id.tvEarned);
        TextView tvQuestion =  findViewById(R.id.tvQuestion);
        TextView Ans1 = findViewById(R.id.A1);
        TextView Ans2 = findViewById(R.id.A2);
        TextView Ans3 = findViewById(R.id.A3);
        TextView Ans4 = findViewById(R.id.A4);

        tvEarned.setText(getText(R.string.earned).toString() + earned + ".");
        tvQuestion.setText(QA[0]);
        Ans1.setText(QA[1]);
        Ans2.setText(QA[2]);
        Ans3.setText(QA[3]);
        Ans4.setText(QA[4]);
    }

    public void confirmAnswer(View view){
        if(submittedAnswer == -1) {
            // Handle an unanswered question
            Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
            displayQuestion();
        } else if(submittedAnswer == answerKey[questionCurrent]) {
            // Handle correct answer submission
            earned = prizeValue[questionCurrent];
            Toast.makeText(this, "Correct! You earned $" + earned + "!", Toast.LENGTH_SHORT).show();

            // check if a safe haven level is reached
            if ( questionCurrent == 4 || questionCurrent == 9) {
                havenEarned = earned;
                Intent intent = new Intent(this, HavenActivity.class);
                intent.putExtra("HAVEN", havenEarned);
                startActivity(intent);
            }
            // Continue if there are more questions remaining
            if (questionCurrent < questionTotal - 1) {
                questionCurrent++;
                displayQuestion();
            } else {
                // Final question reached. Game won
                Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, GameWonActivity.class);
                intent.putExtra("EARNED", earned);
                startActivity(intent);
                finish();
            }
        } else {
            // Wrong answer, game over
            Toast.makeText(this, "Incorrect! Game over!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("EARNED", havenEarned);
            startActivity(intent);
            finish();
        }
    }

    // Handle radio button answer selection
    public void rbAnswerSelect(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        for(int a = 0; a < numAnswers; a++){
            if (view.getId() == rbResID[a] ) {
                if (checked) { submittedAnswer = a; }
            }
        }
    }

}