package com.example.android.quiz;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    float result = 0;
    float ansCorrect = 0;
    float ansWrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Arrow in the menu bar to go back to parent activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Copy question ArrayList from main activity
        Intent resultIntent = getIntent();
        ArrayList<QuizQuestion> question = resultIntent.getParcelableArrayListExtra("question");

        // Find views
        TextView tvResult = (TextView) findViewById(R.id.tv_result);
        TextView tvOk = (TextView) findViewById(R.id.tv_resultOk);
        TextView tvNOk = (TextView) findViewById(R.id.tv_resultNOk);

        // Calculate correct and wrong anwers
        for (int i = 0; i <question.size(); i++) {
            if (question.get(i).getSelectedAnswerOK())
                ansCorrect++;
            else
                ansWrong++;
        }
        // Calculate result in %
        result = ansCorrect/question.size() * 100;

        // Display results
        tvResult.setText(getString(R.string.yourResult) + (int) result + "%");
        tvOk.setText(getString(R.string.nmbCorrect) + (int) ansCorrect);
        tvNOk.setText(getString(R.string.nmbWrong) + (int) ansWrong);

        // Result adapter - custom ArrayAdapter
        ResultAdapter itemsAdapter = new ResultAdapter(this, question);
        ListView listView = (ListView) findViewById(R.id.listview_quiz);
        listView.setAdapter(itemsAdapter);

    }
}
