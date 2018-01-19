package com.example.android.quiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MielcarekA on 12/18/2017.
 */

public class QuizQuestion implements Parcelable{

    /** Quiz question */
    private String mQuestion;

    /** Quiz question answer 1 */
    private String mAnswer1;

    /** Quiz question answer 2 */
    private String mAnswer2;

    /** Quiz question answer 3 */
    private String mAnswer3;

    /** Quiz question correct answer */
    private int mCorrectAnswer;

    /** Quiz question selected answer is correct */
    boolean selectedAnswerOK = false;

    /** Quiz question submit button clicked */
    boolean submitClicked = false;

    int currentSelection = NONE;
    public static final int NONE = -1;
    public static final int ANSWER_ONE_SELECTED = 0;
    public static final int ANSWER_TWO_SELECTED = 1;
    public static final int ANSWER_THREE_SELECTED = 2;

    public QuizQuestion(){

    }
    /**
     * Constructs a new QuizQuestion with initial values for texts.
     */
    public QuizQuestion(String question, String answer1, String answer2, String answer3, int correctAnswer){
        mQuestion = question;
        mAnswer1 = answer1;
        mAnswer2 = answer2;
        mAnswer3 = answer3;
        mCorrectAnswer = correctAnswer;
    }

    // Constructor for parcel
    public QuizQuestion(Parcel in) {
        mQuestion = in.readString();
        mAnswer1 = in.readString();
        mAnswer2 = in.readString();
        mAnswer3 = in.readString();
        mCorrectAnswer = in.readInt();
        currentSelection = in.readInt();
        selectedAnswerOK = in.readInt() == 1;
        submitClicked = in.readInt() == 1;
    }

    // Parcelable creator
    public static final Creator<QuizQuestion> CREATOR = new Creator<QuizQuestion>() {
        @Override
        public QuizQuestion createFromParcel(Parcel in) {
            return new QuizQuestion(in);
        }

        @Override
        public QuizQuestion[] newArray(int size) {
            return new QuizQuestion[size];
        }
    };


   // Getters and setters
    /**
     * Gets the question text.
     *
     * @return question text.
     */
    public String getQuestion() {
        return mQuestion;
    }

    /**
     * Gets the answer 1 text.
     *
     * @return answer 1 text.
     */
    public String getAnswer1() {
        return mAnswer1;
    }

    /**
     * Gets the answer 2 text.
     *
     * @return answer 2 text.
     */
    public String getAnswer2() {
        return mAnswer2;
    }

    /**
     * Gets the answer 3 text.
     *
     * @return answer 3 text.
     */
    public String getAnswer3() {
        return mAnswer3;
    }

    /**
     * Gets the correct answer number.
     *
     * @return correct answer number.
     */
    public int getCorrectAnswer() {
        return mCorrectAnswer;
    }

    /**
     * Gets the selected radio button id as integer.
     *
     * @return selected radio button id as integer.
     */
    public int getCurrentSelection() {
        return currentSelection;
    }

    /**
     * Gets the selected radio button id as integer.
     *
     * @return selected radio button id as integer.
     */
    public boolean getSelectedAnswerOK() {
        return selectedAnswerOK;
    }

    /**
     * Gets the submit button clicked value.
     *
     * @return submit button clicked value.
     */
    public boolean getSubmitClicked() {
        return submitClicked;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(mQuestion);
        out.writeString(mAnswer1);
        out.writeString(mAnswer2);
        out.writeString(mAnswer3);
        out.writeInt(mCorrectAnswer);
        out.writeInt(currentSelection);
        out.writeInt(selectedAnswerOK ? 1 : 0);
        out.writeInt(submitClicked ? 1 : 0);
    }
}
