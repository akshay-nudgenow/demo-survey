package com.nudgenow.nudgesurvey.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class PageEntity {

    @SerializedName("formId")
    private String formId;

    @SerializedName("pageNo")
    private int pageNo;

    @SerializedName("questions")
    private List<UserQuestionEntity> questions;

    public PageEntity(String formId, int pageNo, List<UserQuestionEntity> questions) {
        this.formId = formId;
        this.pageNo = pageNo;
        this.questions = questions;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<UserQuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<UserQuestionEntity> questions) {
        this.questions = questions;
    }

    public Map<String, Object> toMap() {
        // Implement the conversion to a Map<String, Object> here
        // You can use Gson or other methods to perform serialization
        return null;
    }

    public static PageEntity fromMap(Map<String, Object> map) {
        // Implement the conversion from a Map<String, Object> here
        // You can use Gson or other methods to perform deserialization
        return null;
    }

    public String toJson() {
        // Implement JSON serialization here
        return null;
    }

    public static PageEntity fromJson(String source) {
        // Implement JSON deserialization here
        return null;
    }
}
