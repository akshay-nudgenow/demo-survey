package com.nudgenow.nudgesurvey.models;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class UserQuestionEntity {
    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("question")
    private QuestionEntity question;

    @SerializedName("response")
    private String response;

    @SerializedName("status")
    private String status;

    public UserQuestionEntity(String id, String type, QuestionEntity question, String response, String status) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.response = response;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public String getResponse() {
        return response;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, Object> toMap() {

        return null;
    }

    public static UserQuestionEntity fromMap(Map<String, Object> map) {

        return null;
    }

    public String toJson() {

        return null;
    }

    public static UserQuestionEntity fromJson(String source) {

        return null;
    }
}
