package com.nudgenow.nudgesurvey.model;

import java.util.List;

public class PageDetails {
    private int pageNo;
    private List<Question> questions;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}