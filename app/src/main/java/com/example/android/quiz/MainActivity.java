package com.example.android.quiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int nmbAnswers = 0;
    private boolean isBackBtnPressed = false;
    ArrayList<QuizQuestion> question = new ArrayList<QuizQuestion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question.add(new QuizQuestion(getString(R.string.question1), getString(R.string.answer1_1), getString(R.string.answer1_2), getString(R.string.answer1_3), Integer.parseInt(getString(R.string.correct1))));
        question.add(new QuizQuestion(getString(R.string.question2), getString(R.string.answer2_1), getString(R.string.answer2_2), getString(R.string.answer2_3), Integer.parseInt(getString(R.string.correct2))));
        question.add(new QuizQuestion(getString(R.string.question3), getString(R.string.answer3_1), getString(R.string.answer3_2), getString(R.string.answer3_3), Integer.parseInt(getString(R.string.correct3))));
        question.add(new QuizQuestion(getString(R.string.question4), getString(R.string.answer4_1), getString(R.string.answer4_2), getString(R.string.answer4_3), Integer.parseInt(getString(R.string.correct4))));
        question.add(new QuizQuestion(getString(R.string.question5), getString(R.string.answer5_1), getString(R.string.answer5_2), getString(R.string.answer5_3), Integer.parseInt(getString(R.string.correct5))));
        question.add(new QuizQuestion(getString(R.string.question6), getString(R.string.answer6_1), getString(R.string.answer6_2), getString(R.string.answer6_3), Integer.parseInt(getString(R.string.correct6))));
        question.add(new QuizQuestion(getString(R.string.question7), getString(R.string.answer7_1), getString(R.string.answer7_2), getString(R.string.answer7_3), Integer.parseInt(getString(R.string.correct7))));
        question.add(new QuizQuestion(getString(R.string.question8), getString(R.string.answer8_1), getString(R.string.answer8_2), getString(R.string.answer8_3), Integer.parseInt(getString(R.string.correct8))));
        question.add(new QuizQuestion(getString(R.string.question9), getString(R.string.answer9_1), getString(R.string.answer9_2), getString(R.string.answer9_3), Integer.parseInt(getString(R.string.correct9))));
        question.add(new QuizQuestion(getString(R.string.question10), getString(R.string.answer10_1), getString(R.string.answer10_2), getString(R.string.answer10_3), Integer.parseInt(getString(R.string.correct10))));

        // QuizAdapter - custom ArrayAdapter
        QuizAdapter itemsAdapter = new QuizAdapter(this, question);
        ListView listView = (ListView) findViewById(R.id.listview_quiz);
        listView.setAdapter(itemsAdapter);

        Button btnResult = (Button) findViewById(R.id.btn_result);

        // On result button check if all questions have answer
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nmbAnswers = 0;
                for (int i = 0; i < question.size(); i++){
                    if (question.get(i).submitClicked){
                        nmbAnswers ++;
                    }
                }
                if (nmbAnswers != question.size()){
                    Toast.makeText(getApplicationContext(), R.string.onResultButton,Toast.LENGTH_SHORT).show();
                } else {
                    // Open new activity
                    Intent resultIntent = new Intent (MainActivity.this, ResultActivity.class);
                    resultIntent.putParcelableArrayListExtra("question", question );
                    startActivity(resultIntent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(isBackBtnPressed){
            finish();
        }else {
            isBackBtnPressed = true;
            Toast.makeText(this, R.string.backButton, Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackBtnPressed = false;
                }
            }, 2000);
        }
    }

    /**
     * Save state on rotation
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("questionArrayList", question);
        super.onSaveInstanceState(outState);
    }

    /**
     * Restore state after rotation
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        question = savedInstanceState.getParcelableArrayList("questionArrayList");

        // QuizAdapter - custom ArrayAdapter
        QuizAdapter itemsAdapter = new QuizAdapter(this, question);
        ListView listView = (ListView) findViewById(R.id.listview_quiz);
        listView.setAdapter(itemsAdapter);

        }
}
