package com.example.assignment2.KoreanLesson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.assignment2.R;

public class Korean1Activity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    public static final String SHARED_PREFS = "sharePrefs";
    public static final String KEY_HIGH_SCORE = "keyHighScore";

    private TextView textViewHighScore;
    private Spinner spinnerDifficulty;

    private int highScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean1_activity);

        textViewHighScore = findViewById(R.id.text_view_highscore);
        loadHighScore();
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);

        String[] difficultyLevels = Korean1Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);

        Button buttonStartQuiz = findViewById(R.id.button_start);
        // Button onClickListener to open a new activity
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExercise();
            }
        });
    }

    // Intent to QuestionActivity with startActivityForResult
    private void startExercise() {
        String difficulty = spinnerDifficulty.getSelectedItem().toString();

        Intent intent = new Intent(Korean1Activity.this, Korean1QuestionActivity.class);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    // Deal with REQUEST_CODE and data to intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(Korean1QuestionActivity.EXTRA_SCORE,0);
                if (score > highScore){
                    updateHighScore(score);
                }
            }
        }
    }

    // Load the score from sharePreferences
    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGH_SCORE,0);
        textViewHighScore.setText("Highscore: " + highScore);
    }


    // Method to set current high score to the new score and save it in sharePreferences
    private void updateHighScore(int highScoreNew){
        highScore = highScoreNew;
        textViewHighScore.setText("Highscore: " + highScore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGH_SCORE, highScore);
        editor.apply();
    }
}

