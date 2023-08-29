package com.nudgenow.nudgesurvey.screens;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nudgenow.nudgesurvey.R;
import com.nudgenow.nudgesurvey.models.ScreenParamsEntity;

public class EndScreenActivity extends AppCompatActivity {
    private ScreenParamsEntity screendata;
    private int primaryColor;
    private int secondaryColor;
    private View.OnClickListener callback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        screendata = getIntent().getParcelableExtra("screendata");
        primaryColor = getIntent().getIntExtra("primaryColor", Color.BLACK);
        secondaryColor = getIntent().getIntExtra("secondaryColor", Color.BLACK);
        callback = (View.OnClickListener) getIntent().getSerializableExtra("callback");

        setupViews();
    }

    private void setupViews() {
        LinearLayout rootLayout = findViewById(R.id.root_layout);
        TextView headingTextView = findViewById(R.id.heading_text_view);
        TextView descriptionTextView = findViewById(R.id.description_text_view);
        Button ctaButton = findViewById(R.id.cta_button);
        Button closeButton = findViewById(R.id.close_button);

        rootLayout.setBackgroundColor(primaryColor);
        headingTextView.setText(screendata.getHeading());
        headingTextView.setTextColor(secondaryColor);
        descriptionTextView.setText(screendata.getDescription());
        descriptionTextView.setTextColor(secondaryColor);

        if (screendata.getHasCta()) {
            ctaButton.setVisibility(View.VISIBLE);
            ctaButton.setText(screendata.getCta());
            ctaButton.setTextColor(secondaryColor);
            ctaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.onClick(v);
                    }
                    String link = screendata.getCtaDeepLink();
                    if (link != null) {

                    }
                }
            });
        } else {
            ctaButton.setVisibility(View.GONE);
        }

        closeButton.setText("Close");
        closeButton.setTextColor(secondaryColor);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClick(v);
                }
                finish();
            }
        });
    }
}
