package com.nudgenow.nudgesurvey.model;

import java.util.ArrayList;

public class Data{
    public String type;
    public Data data;
    public int pageNo;
    public boolean skip;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public Object getSkipTo() {
        return skipTo;
    }

    public void setSkipTo(Object skipTo) {
        this.skipTo = skipTo;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Object> getNextLogic() {
        return nextLogic;
    }

    public void setNextLogic(ArrayList<Object> nextLogic) {
        this.nextLogic = nextLogic;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Object skipTo;
    public ArrayList<Question> questions;
    public String title;
    public String description;
    public ArrayList<Object> nextLogic;
    public String _id;
    public String position;
    public String primaryColor;
    public String secondaryColor;
}