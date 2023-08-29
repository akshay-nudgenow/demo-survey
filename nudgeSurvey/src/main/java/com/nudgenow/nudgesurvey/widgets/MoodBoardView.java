package com.nudgenow.nudgesurvey.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nudgenow.nudgesurvey.R;

public class MoodBoardView extends LinearLayout {
    private MoodBoardListener listener;
    private double selectedRating = 0;

    public MoodBoardView(Context context) {
        super(context);
        init();
    }

    public MoodBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoodBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.mood_board_view, this, true);
    }

    public void setMoodBoardListener(MoodBoardListener listener) {
        this.listener = listener;
    }

    public void setQuestion(String question) {
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(question);
    }

    public void setRating(double rating) {
        selectedRating = rating;
        updateRating();
    }

    private void updateRating() {
        LinearLayout ratingContainer = findViewById(R.id.ratingContainer);
        ratingContainer.removeAllViews();

        String[] text = {"Text 1", "Text 2", "Text 3", "Text 4", "Text 5"};

        for (int i = 0; i < 5; i++) {
            View ratingView = LayoutInflater.from(getContext()).inflate(R.layout.rating_item, ratingContainer, false);
            ImageView ratingImage = ratingView.findViewById(R.id.ratingImage);
            TextView ratingText = ratingView.findViewById(R.id.ratingText);

            if (i == selectedRating - 1) {
                ratingImage.setImageResource(R.drawable.s1);
            } else {
                ratingImage.setImageResource(R.drawable.u1);
            }

            ratingText.setText(text[i]);

            final int finalI = i;
            ratingView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedRating = finalI + 1;
                    updateRating();
                    if (listener != null) {
                        listener.onRatingChanged(selectedRating);
                    }
                }
            });

            ratingContainer.addView(ratingView);
        }
    }

    public interface MoodBoardListener {
        void onRatingChanged(double rating);
    }
}
