package com.example.android.quiz;

        import android.app.Activity;
        import android.content.res.ColorStateList;
        import android.support.v7.widget.AppCompatRadioButton;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.RadioButton;
        import android.widget.Toast;

        import java.util.ArrayList;


/**
 * Created by MielcarekA on 1/7/2018.
 */

public class ResultAdapter extends ArrayAdapter<QuizQuestion> {

    private static final String LOG_TAG = QuizAdapter.class.getSimpleName();
    private final ArrayList<QuizQuestion> quiz;

    public ResultAdapter(Activity context, ArrayList<QuizQuestion> quiz) {
        super(context, 0, quiz);
        this.quiz = quiz;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
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

        // Hide all submit button
        holder.btnSubmit.setVisibility(View.GONE);

        QuizQuestion currentQuestion = getItem(position);
        // Dispay questions and answers
        holder.tvQuestion.setText(currentQuestion.getQuestion());
        holder.rbAnswer1.setText(currentQuestion.getAnswer1());
        holder.rbAnswer2.setText(currentQuestion.getAnswer2());
        holder.rbAnswer3.setText(currentQuestion.getAnswer3());
        // Radio buttons not clickable
        for (int i = 0; i < holder.rgQuestion.getChildCount(); i++) {
            holder.rgQuestion.getChildAt(i).setClickable(false);
        }

        // Display correct and wrong answers icons
        int selected = quiz.get(position).getCurrentSelection();
        int correct = quiz.get(position).getCorrectAnswer();
        for (int i = 0; i < holder.rgQuestion.getChildCount(); i++){
            RadioButton rb = (RadioButton) holder.rgQuestion.getChildAt(i);
            if (i == correct){
                rb.setButtonDrawable(getContext().getResources().getDrawable(R.drawable.ic_correct));
                rb.setPadding(16, 8, 0, 8);
            } else if (i == selected & i != correct ){
                rb.setButtonDrawable(getContext().getResources().getDrawable(R.drawable.ic_wrong));
                rb.setPadding(16, 8, 0, 8);
            } else {
                rb.setButtonDrawable(getContext().getResources().getDrawable(R.drawable.ic_notchecked));
                rb.setPadding(16, 8, 0, 8);
            }
        }

        // Display selection
        if (quiz.get(position).currentSelection != QuizQuestion.NONE){
            int select = quiz.get(position).getCurrentSelection();
            RadioButton rb_s = (RadioButton) holder.rgQuestion.getChildAt(select);
            rb_s.setChecked(true);
        } else
        holder.rgQuestion.clearCheck();

        return listItemView;
    }
}



