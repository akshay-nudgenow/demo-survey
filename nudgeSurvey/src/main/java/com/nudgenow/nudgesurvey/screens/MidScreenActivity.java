package com.nudgenow.nudgesurvey.screens;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MidScreenActivity extends AppCompatActivity {

    private TextView headingTextView;
    private TextView descriptionTextView;
    private Button startSurveyButton;
    private Button skipSurveyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setLayoutParams(layoutParams);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.CENTER);

        headingTextView = new TextView(this);
        headingTextView.setLayoutParams(layoutParams);
        headingTextView.setTextSize(30);
        headingTextView.setTypeface(null, Typeface.BOLD);
        headingTextView.setMaxLines(2);
        headingTextView.setEllipsize(TextUtils.TruncateAt.END);
        headingTextView.setGravity(Gravity.CENTER);
        headingTextView.setText(getIntent().getStringExtra("heading"));

        descriptionTextView = new TextView(this);
        descriptionTextView.setLayoutParams(layoutParams);
        descriptionTextView.setTextSize(20);
        descriptionTextView.setMaxLines(4);
        descriptionTextView.setEllipsize(TextUtils.TruncateAt.END);
        descriptionTextView.setGravity(Gravity.CENTER);
        descriptionTextView.setText(getIntent().getStringExtra("description"));

        startSurveyButton = new Button(this);
        startSurveyButton.setLayoutParams(layoutParams);
        startSurveyButton.setText("Start Survey");

        skipSurveyButton = new Button(this);
        skipSurveyButton.setLayoutParams(layoutParams);
        skipSurveyButton.setText("Skip Survey");

        mainLayout.addView(headingTextView);
        mainLayout.addView(descriptionTextView);
        mainLayout.addView(startSurveyButton);
        mainLayout.addView(skipSurveyButton);

        setContentView(mainLayout);
    }
}
