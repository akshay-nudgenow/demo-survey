package com.nudgenow.nudgesurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nudgenow.nudgesurvey.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SecondActivity extends AppCompatActivity {
    TextView question1;
    TextView question2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

         question1 = findViewById(R.id.tv1);
         question2 = findViewById(R.id.tv2);


        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          startActivity(new Intent(SecondActivity.this, ThankYouActivity.class));
              //  Toast.makeText(SecondActivity.this, "statusss", Toast.LENGTH_LONG).show();
               ApiCall2();
            }

        });

        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          startActivity(new Intent(SecondActivity.this, ThankYouActivity.class));

                ApiCall2();
            }
        });


        ApiCall();

    }
    public void ApiCall2()
    {
        SurveyUi surveyUi = new SurveyUi();


        surveyUi.setApiCallback(new SurveyUi.ApiCallback() {
            @Override
            public void onResponseReceived(String response) {
                System.out.println("Received API response in FirstActivity: " + response);


                try {
                    // Parse the root JSON object
                    JSONObject rootObject = new JSONObject(response);


                    JSONObject dataObject = rootObject.getJSONObject("data");
                    JSONObject innerDataObject = dataObject.getJSONObject("data");
                    JSONObject userResponsesObject = innerDataObject.getJSONObject("userResponses");
                    JSONArray pageResponsesArray = userResponsesObject.getJSONArray("pageResponses");


                    for (int i = 0; i < pageResponsesArray.length(); i++) {
                        JSONObject pageResponseObject = pageResponsesArray.getJSONObject(i);



                        JSONArray responsesArray = pageResponseObject.getJSONArray("responses");


                        for (int j = 0; j < responsesArray.length(); j++) {
                            JSONObject responseObject = responsesArray.getJSONObject(j);
                            String type = responseObject.getString("type");


                            Log.d("Type", type);
                            if(type.equals("RESPONDED")){
                                Log.d("loso", "onResponseReceived: "+ response);
                                startActivity(new Intent(SecondActivity.this, ThankYouActivity.class));

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


        String surveyId = "someSurveyId";
        surveyUi.triggerSecondApi(surveyId, userStatId, 1);
    }
    public void ApiCall()
    {
        SurveyUi surveyUi = new SurveyUi();


        surveyUi.setApiCallback(new SurveyUi.ApiCallback() {
            @Override
            public void onResponseReceived(String response) {
                System.out.println("Received API response in FirstActivity: " + response);

                try {
                    // Parse the root JSON object
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
                            if(questionType.equals("checkbox"))
                            {
                                TextView myQuestion = findViewById(R.id.myQuestion);
                                myQuestion.setText(question);

                                JSONArray options = questionJsonObject.getJSONArray("options");


                                for(int j=0; j<options.length(); j++) {
                                    String option = options.getString(0);
                                    String option2 = options.getString(1);

                                    question1.setText(option);

                                    question2.setText(option2);
                                    Log.d("lo", "onResponseReceived: "+ option+ ""+ option2);
                                }

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
        surveyUi.triggerSecondApi(surveyId, userStatId, 1);
    }

}





