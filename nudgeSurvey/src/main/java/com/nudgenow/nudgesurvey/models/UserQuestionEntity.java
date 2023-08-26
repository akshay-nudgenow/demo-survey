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
        // Implement this method to convert the object to a map
        // Similar to the Dart code
        // ...
        // Return a map with the appropriate key-value pairs
        return null; // Replace this with your implementation
    }

    public static UserQuestionEntity fromMap(Map<String, Object> map) {
        // Implement this method to create an object from a map
        // Similar to the Dart code
        // ...
        // Return a UserQuestionEntity object
        return null; // Replace this with your implementation
    }

    public String toJson() {
        // Implement this method to convert the object to JSON
        // Similar to the Dart code
        // ...
        // Return a JSON representation of the object
        return null; // Replace this with your implementation
    }

    public static UserQuestionEntity fromJson(String source) {
        // Implement this method to create an object from JSON
        // Similar to the Dart code
        // ...
        // Return a UserQuestionEntity object
        return null; // Replace this with your implementation
    }
}
