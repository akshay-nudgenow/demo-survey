package com.nudgenow.nudgesurvey.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nudgenow.nudgesurvey.R;
import com.nudgenow.nudgesurvey.models.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxWidget extends LinearLayout {

    private CheckBox checkBox;
    private List<String> selectedChoices;
    private OnSelectionChangedListener onSelectionChangedListener;

    public CheckBoxWidget(Context context) {
        super(context);
        init(context);
    }

    public CheckBoxWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CheckBoxWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.checkbox_widget_layout, this, true);

        selectedChoices = new ArrayList<>();

        TextView descriptionTextView = rootView.findViewById(R.id.descriptionTextView);
        TextView questionTextView = rootView.findViewById(R.id.questionTextView);
        LinearLayout choicesLayout = rootView.findViewById(R.id.choicesLayout);
        if (checkBox != null) {
            descriptionTextView.setText(checkBox.getDescription());
            questionTextView.setText(checkBox.getQuestion());

            List<String> options = checkBox.getOptions();
            if (options != null) {
                for (String choice : options) {
                    View choiceView = inflater.inflate(R.layout.choice_chip_layout, choicesLayout, false);
                    TextView choiceTextView = choiceView.findViewById(R.id.choiceTextView);
                    choiceTextView.setText(choice);

                    choiceView.setOnClickListener(v -> {
                        toggleChoice(choice);
                        if (onSelectionChangedListener != null) {
                            onSelectionChangedListener.onSelectionChanged(selectedChoices);
                        }
                    });

                    choicesLayout.addView(choiceView);
                }
            }
        }
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
        init(getContext());
    }

    public void setOnSelectionChangedListener(OnSelectionChangedListener listener) {
        this.onSelectionChangedListener = listener;
    }

    private void toggleChoice(String choice) {
        if (selectedChoices.contains(choice)) {
            selectedChoices.remove(choice);
        } else {
            selectedChoices.add(choice);
        }
    }

    public interface OnSelectionChangedListener {
        void onSelectionChanged(List<String> selectedChoices);
    }
}
