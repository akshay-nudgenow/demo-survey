package com.nudgenow.nudgesurvey.model;

import com.nudgenow.nudgesurvey.model.Data;

public class Root{
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String status;
    public Data data;
}
