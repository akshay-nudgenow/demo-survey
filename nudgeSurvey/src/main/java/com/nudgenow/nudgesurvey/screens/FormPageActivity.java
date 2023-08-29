package com.nudgenow.nudgesurvey.screens;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.nudgenow.nudgesurvey.R;
import com.nudgenow.nudgesurvey.models.ScreenParamsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormPageActivity extends AppCompatActivity {
    private String id;
    private String userStatId;
    private int pageNo;
    private boolean showSkip;
    private int primaryColor;
    private int secondaryColor;
    private String position;
    private Object questions;
    private String type;
    private List<Map<String, Object>> responses;
    private Map<String, Object> responseBody;
    private ScreenParamsEntity screen;
    private int pageType;
    private boolean isLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_page);

        id = getIntent().getStringExtra("id");
        userStatId = getIntent().getStringExtra("userStatId");
        pageNo = getIntent().getIntExtra("pageNo", 0);
        showSkip = getIntent().getBooleanExtra("showSkip", false);
        primaryColor = getIntent().getIntExtra("primaryColor", Color.BLACK);
        secondaryColor = getIntent().getIntExtra("secondaryColor", Color.WHITE);
        position = getIntent().getStringExtra("position");
        questions = getIntent().getSerializableExtra("questions");
        type = getIntent().getStringExtra("type");

        pageType = getPageType(type);

        responseBody = new HashMap<>();
        responseBody.put("formId", id);
        responseBody.put("pageNo", pageNo);
        responseBody.put("gameId", "abc");
        responseBody.put("_id", id);
        responseBody.put("responses", new ArrayList<>());

        responses = new ArrayList<>();

        Button nextButton = findViewById(R.id.nextButton);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoading = true;
                progressBar.setVisibility(View.VISIBLE);

                Map<String, Object> newElement = new HashMap<>();
                newElement.put("formId", id);
                newElement.put("pageNo", pageNo);
                newElement.put("gameId", "abc");
                newElement.put("_id", id);
                newElement.put("responses", responseBody.get("responses"));

                responses.add(newElement);



                isLoading = false;
                progressBar.setVisibility(View.GONE);


            }
        });


    }

    private int getPageType(String type) {
        Map<String, Integer> pageTypeMap = new HashMap<>();
        pageTypeMap.put("STARTSCREEN", 0);
        pageTypeMap.put("PAGE", 1);
        pageTypeMap.put("NEXTPAGE", 1);
        pageTypeMap.put("MIDSCREEN", 2);
        pageTypeMap.put("ENDSCREEN", 3);

        return pageTypeMap.getOrDefault(type, 1);
    }
}
