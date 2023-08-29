package com.nudgenow.nudgesurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nudgenow.nudgesurvey.widgets.LikeDislikeView;

public class NudgeActivity extends AppCompatActivity {

    private LikeDislikeView likeDislikeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nudge);

        likeDislikeView = findViewById(R.id.likeDislikeView);
        likeDislikeView.setDescription("Description");
        likeDislikeView.setQuestion("Question");
        likeDislikeView.setLikeDislikeListener(new LikeDislikeView.LikeDislikeListener() {
            @Override
            public void onSelectionChanged(boolean isLiked) {
                // Handle selection change
            }
        });
    }
}