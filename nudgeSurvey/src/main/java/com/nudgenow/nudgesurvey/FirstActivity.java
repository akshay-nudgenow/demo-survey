package com.nudgenow.nudgesurvey;



import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.nudgenow.nudgesurvey.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FirstActivity extends AppCompatActivity {

    TextView radioButton1;
    TextView radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
         radioButton1 = findViewById(R.id.radioButton1);
         radioButton2 = findViewById(R.id.radioButton2);

         radioButton1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(FirstActivity.this, SecondActivity.class));
             }
         });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });


        ApiCall();


    }
   public void ApiCall()
   {
       SurveyUi surveyUi = new SurveyUi();


       surveyUi.setApiCallback(new SurveyUi.ApiCallback() {
           @Override
           public void onResponseReceived(String response) {
               System.out.println("Received API response in FirstActivity: " + response);

               try {

                   JSONObject rootJsonObject = new JSONObject(response);


                   JSONObject dataJsonObject = rootJsonObject.getJSONObject("data");


                   JSONObject innerDataJsonObject = dataJsonObject.getJSONObject("data");

                   JSONArray questionsJsonArray = innerDataJsonObject.getJSONArray("questions");


                   if(questionsJsonArray.length()!=0)
                   {
                       for (int i = 0; i < questionsJsonArray.length(); i++) {


                           JSONObject questionJsonObject = questionsJsonArray.getJSONObject(i);
                           String questionType = questionJsonObject.getString("questionType");
                           String question = questionJsonObject.getString("question");



                           Log.d("lc", "onResponseReceived: "+ question);
                           Log.d("lc", "onResponseReceived: "+ questionType);
                           System.out.println("Question Type: " + questionType);


                           System.out.println("Question: " + question);
                           if(questionType.equals("radiogroup"))
                           {
                               TextView myTextView = findViewById(R.id.question1);
                               myTextView.setText(question);
                               JSONArray options = questionJsonObject.getJSONArray("options");

                               for(int j=0; j<options.length(); j++) {
                                   String option = options.getString(0);
                                   String option2 = options.getString(1);

                                   radioButton1.setText(option);

                                   radioButton2.setText(option2);
                                   Log.d("lo", "onResponseReceived: "+ option+ ""+ option2);
                               }

                           }

                           else  if(questionType.equals("checkbox")){
                               startActivity(new Intent(FirstActivity.this, SecondActivity.class));
                           }
                       }
                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }


           }
       });

       String userStatId = "someUserStatId";
       String position = "somePosition";
       surveyUi.trigger(this, userStatId, position);

       // Call the second API
       String surveyId = "someSurveyId";
       surveyUi.triggerSecondApi(surveyId, userStatId,0);
   }

}