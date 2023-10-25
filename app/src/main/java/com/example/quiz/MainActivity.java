package com.example.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_ANSWER="com.example.quiz.correctAnswer";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;
    private TextView scoreTextView;
    private int score=0;
    private boolean answerWasShown;
    private static final int REQUEST_CODE_PROMPT=0;
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
        Log.d("onCreate", "wywołana została metoda: onCreate");
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null)
        {

            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
            score = savedInstanceState.getInt("score",0);
        }
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        scoreTextView= findViewById(R.id.score_text);
        promptButton=findViewById(R.id.prompt_button);

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
                answerWasShown=false;
                setNextQuestion();
            }
        });
        setNextQuestion();

        promptButton.setOnClickListener((v) ->{
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER,correctAnswer);
            startActivityForResult(intent,REQUEST_CODE_PROMPT);
        } );

    }
    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessegeId=0;
        if(answerWasShown) {
            resultMessegeId = R.string.answer_was_shown;
        }
            else{
                if (userAnswer == correctAnswer) {
                score++;
                resultMessegeId = R.string.correct_answer;
            } else {
                resultMessegeId = R.string.incorrect_answer;
            }
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

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.d("onSaveInstanceState", "wywołana została metoda: onSaveInstanceState ");
        outState.putInt(KEY_CURRENT_INDEX,currentIndex);
        outState.putInt("score",score);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK){return;}
        if(requestCode==REQUEST_CODE_PROMPT){
            if(data==null){return;}
            answerWasShown=data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN,false);
        }
    }
}

