package com.nudgenow.nudgesurvey.screens;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nudgenow.nudgesurvey.R;

public class StartScreenActivity extends AppCompatActivity {

    private TextView headingTextView;
    private TextView descriptionTextView;
    private Button startSurveyButton;
    private Button skipSurveyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        headingTextView = findViewById(R.id.headingTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        startSurveyButton = findViewById(R.id.startSurveyButton);
        skipSurveyButton = findViewById(R.id.skipSurveyButton);

        startSurveyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        skipSurveyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        String heading = getIntent().getStringExtra("heading");
        String description = getIntent().getStringExtra("description");


        headingTextView.setText(heading != null ? heading : "Heading");
        descriptionTextView.setText(description != null ? description : "fake fake fake fake fake fake fake fake\nfake fake fake fake fake fake fake fake\nfake fake fake fake fake fake fake ");
    }
}
