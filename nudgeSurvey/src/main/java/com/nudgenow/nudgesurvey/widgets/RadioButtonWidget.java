package com.nudgenow.nudgesurvey.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nudgenow.nudgesurvey.R;

import java.util.ArrayList;
import java.util.List;
public class RadioButtonWidget extends LinearLayout {
    private TextView questionTextView;
    private LinearLayout optionsLayout;


    private String selectedChoice = "";
    private RadioButtonListener radioButtonListener;

    public RadioButtonWidget(Context context) {
        super(context);
        init();
    }

    public RadioButtonWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadioButtonWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        inflate(getContext(), R.layout.radio_button_widget_layout, this);

        questionTextView = findViewById(R.id.questionTextView);
        optionsLayout = findViewById(R.id.optionsLayout);
    }

    public void setQuestion(String question) {
        questionTextView.setText(question);
    }

    public void setOptions(List<String> options) {
        optionsLayout.removeAllViews();

        for (String option : options) {
            TextView optionTextView = new TextView(getContext());
            optionTextView.setText(option);
            optionTextView.setTextColor(Color.BLACK);
            optionTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectChoice(option);
                }
            });

            optionsLayout.addView(optionTextView);
        }
    }

    public void setRadioButtonListener(RadioButtonListener listener) {
        this.radioButtonListener = listener;
    }

    private void selectChoice(String choice) {
        selectedChoice = choice;
        if (radioButtonListener != null) {
            radioButtonListener.onSelectionChanged(choice, new Runnable() {
                @Override
                public void run() {
                    selectedChoice = "";
                }
            });
        }
    }

    public interface RadioButtonListener {
        void onSelectionChanged(String selectedChoice, Runnable clearSelection);
    }
}