package com.nudgenow.nudgesurvey.model;

import com.google.gson.annotations.SerializedName;

public class AttachmentType {
    @SerializedName("type")
    private String type;

    @SerializedName("link")
    private String link;

    public AttachmentType(String type, String link) {
        this.type = type;
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }
}