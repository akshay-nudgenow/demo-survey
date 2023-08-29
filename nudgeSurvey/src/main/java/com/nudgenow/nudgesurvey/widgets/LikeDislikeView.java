package com.nudgenow.nudgesurvey.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nudgenow.nudgesurvey.R;

public class LikeDislikeView extends LinearLayout {
    private boolean isLiked = false;
    private boolean isDisliked = false;
    private LikeDislikeListener listener;

    public LikeDislikeView(Context context) {
        super(context);
        init();
    }

    public LikeDislikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LikeDislikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.like_dislike_view, this, true);

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        TextView questionTextView = findViewById(R.id.questionTextView);
        ImageButton likeButton = findViewById(R.id.likeButton);
        ImageButton dislikeButton = findViewById(R.id.dislikeButton);

        descriptionTextView.setTextColor(Color.GRAY);
        questionTextView.setTextSize(26);

        likeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isLiked = !isLiked;
                isDisliked = false;
                updateSelection();
                if (listener != null) {
                    listener.onSelectionChanged(isLiked);
                }
            }
        });

        dislikeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isDisliked = !isDisliked;
                isLiked = false;
                updateSelection();
                if (listener != null) {
                    listener.onSelectionChanged(!isDisliked);
                }
            }
        });
    }

    public void setLikeDislikeListener(LikeDislikeListener listener) {
        this.listener = listener;
    }

    public void setDescription(String description) {
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(description);
    }

    public void setQuestion(String question) {
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(question);
    }

    private void updateSelection() {
        ImageButton likeButton = findViewById(R.id.likeButton);
        ImageButton dislikeButton = findViewById(R.id.dislikeButton);

        if (isLiked) {
            likeButton.setImageResource(R.drawable.thumbsup_);
        } else {
            likeButton.setImageResource(R.drawable.thumbsup_);
        }

        if (isDisliked) {
            dislikeButton.setImageResource(R.drawable.thumbdown);
        } else {
            dislikeButton.setImageResource(R.drawable.thumbdown);
        }
    }

    public interface LikeDislikeListener {
        void onSelectionChanged(boolean isLiked);
    }
}
