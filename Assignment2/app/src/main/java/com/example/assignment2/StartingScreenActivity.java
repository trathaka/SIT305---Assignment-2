package com.example.assignment2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StartingScreenActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharePrefs";
    public static final String KEY_HIGH_SCORE = "keyHighScore";

    private TextView textViewHighScore;

    private int highScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        textViewHighScore = findViewById(R.id.text_view_highscore);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(StartingScreenActivity.this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if (score > highScore){
                    updateHighScore(score);
                }
            }
        }
    }

    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGH_SCORE,0);
        textViewHighScore.setText("Highscore: " + highScore);
    }


    private void updateHighScore(int highScoreNew){
        highScore = highScoreNew;
        textViewHighScore.setText("Highscore: " + highScore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGH_SCORE, highScore);
        editor.apply();
    }
}

