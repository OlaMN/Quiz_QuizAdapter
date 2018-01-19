package com.example.android.quiz;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by MielcarekA on 1/13/2018.
 */

public class ViewHolder {
    TextView tvQuestion;
    RadioGroup rgQuestion;
    RadioButton rbAnswer1;
    RadioButton rbAnswer2;
    RadioButton rbAnswer3;
    Button btnSubmit;

    ViewHolder(View v){
        tvQuestion = (TextView) v.findViewById(R.id.tv_question);
        rgQuestion = (RadioGroup) v.findViewById(R.id.rg_question);
        rbAnswer1 = (RadioButton) v.findViewById(R.id.rb_answer1);
        rbAnswer2 = (RadioButton) v.findViewById(R.id.rb_answer2);
        rbAnswer3 = (RadioButton) v.findViewById(R.id.rb_answer3);
        btnSubmit = (Button) v.findViewById(R.id.btn_submit);
    }
}
