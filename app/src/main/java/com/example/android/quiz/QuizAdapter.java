package com.example.android.quiz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;


/**
 * Created by MielcarekA on 1/7/2018.
 */

public class QuizAdapter extends ArrayAdapter<QuizQuestion> {

    private static final String LOG_TAG = QuizAdapter.class.getSimpleName();
    private final ArrayList<QuizQuestion> quiz;

    public QuizAdapter(Activity context, ArrayList<QuizQuestion> quiz) {
        super(context, 0, quiz);
        this.quiz = quiz;
        }


    /**
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        View listItemView = convertView;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.quiz_item, parent, false);
            holder = new ViewHolder(listItemView);
            listItemView.setTag(holder);
        } else
            holder = (ViewHolder) listItemView.getTag();

        // Save clicked item's position
        holder.rgQuestion.setTag(new Integer(position));
        holder.btnSubmit.setTag(new Integer(position));

        // On radio button selection save selected radio id
        holder.rgQuestion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Integer pos = (Integer) radioGroup.getTag();
                QuizQuestion element = quiz.get(pos);
                // Save selected radio button id
                switch(checkedId){
                    case R.id.rb_answer1:
                        element.currentSelection = QuizQuestion.ANSWER_ONE_SELECTED;
                        break;
                    case R.id.rb_answer2:
                        element.currentSelection = QuizQuestion.ANSWER_TWO_SELECTED;
                        break;
                    case R.id.rb_answer3:
                        element.currentSelection = QuizQuestion.ANSWER_THREE_SELECTED;
                        break;
                    default: element.currentSelection = QuizQuestion.NONE;
                }
            }
        });

        // On submit button save selected answer
        holder.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) view.getTag();
                QuizQuestion element = quiz.get(pos);
                if (element.currentSelection == QuizQuestion.NONE){
                    // No answer is selected
                    Toast.makeText(getContext(), R.string.onSubmitButton,Toast.LENGTH_SHORT).show();
                } else {
                    // Answer selected
                    // Check if it's correct
                    if (element.currentSelection == element.getCorrectAnswer()) {
                        element.selectedAnswerOK = true;
                    } else
                        element.selectedAnswerOK = false;
                    // Save submitClicked state

                    element.submitClicked = true;
                    // Disable button and and change its color
                    view.setEnabled(false);
                    view.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray));
                    // Disable radio buttons clicking
                    RadioGroup rg = (RadioGroup) ((View) view.getParent()).findViewById(R.id.rg_question);
                    for (int i = 0; i < rg.getChildCount(); i++) {
                        rg.getChildAt(i).setEnabled(false);
                    }
                }
            }
        });

        QuizQuestion currentQuestion = getItem(position);

        holder.tvQuestion.setText(currentQuestion.getQuestion());
        holder.rbAnswer1.setText(currentQuestion.getAnswer1());
        holder.rbAnswer2.setText(currentQuestion.getAnswer2());
        holder.rbAnswer3.setText(currentQuestion.getAnswer3());

        // Screen scrolling - memorize state of radio group and button
        if (quiz.get(position).currentSelection != QuizQuestion.NONE){
            int selected = quiz.get(position).getCurrentSelection();
            RadioButton rb = (RadioButton) holder.rgQuestion.getChildAt(selected);
            rb.setChecked(true);
        } else
            holder.rgQuestion.clearCheck();

        if (quiz.get(position).submitClicked){
            for (int i = 0; i < holder.rgQuestion.getChildCount(); i++) {
                holder.rgQuestion.getChildAt(i).setEnabled(false);
                holder.btnSubmit.setEnabled(false);
                holder.btnSubmit.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray));
            }
        } else {
            for (int i = 0; i < holder.rgQuestion.getChildCount(); i++) {
                holder.rgQuestion.getChildAt(i).setEnabled(true);
                holder.btnSubmit.setEnabled(true);
                holder.btnSubmit.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
            }
        }
        return listItemView;
    }
}



