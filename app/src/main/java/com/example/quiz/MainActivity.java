package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private TextView scoreTextView;
    private int score=0;
    private Question[] questions = new Question[] {
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_version, false)

    };
    private int currentIndex = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        scoreTextView= findViewById(R.id.score_text);

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                currentIndex=(currentIndex+1)%questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();

    }
    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessegeId=0;
        if(userAnswer==correctAnswer)
        {
            score++;
            resultMessegeId = R.string.correct_answer;
        }
        else
        {
            resultMessegeId=R.string.incorrect_answer;
        }
        Toast.makeText(this,resultMessegeId, Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion()
    {
        questionTextView.setText(questions[currentIndex].getQuestionId());
        scoreTextView.setText("Score: " + score);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart","wywołana została metoda cyklu życia: onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","wywołana została metoda cyklu życia: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy","wywołana została metoda cyklu życia: onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause","wywołana została metoda cyklu życia: onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","wywołana została metoda cyklu życia: onResume");
    }
}

