package com.nudgenow.nudgesurvey.models;

import java.util.HashMap;
import java.util.Map;

public class ScreenParamsEntity {
    private String heading;
    private String description;
    private Boolean hasCta;
    private String cta;
    private String ctaDeepLink;
    private String sId;

    public ScreenParamsEntity(String heading, String description, Boolean hasCta, String cta, String ctaDeepLink, String sId) {
        this.heading = heading;
        this.description = description;
        this.hasCta = hasCta;
        this.cta = cta;
        this.ctaDeepLink = ctaDeepLink;
        this.sId = sId;
    }

    public ScreenParamsEntity(Map<String, Object> json) {
        this.heading = (String) json.get("heading");
        this.description = (String) json.get("description");
        this.hasCta = (Boolean) json.get("hasCta");
        this.cta = (String) json.get("cta");
        this.ctaDeepLink = (String) json.get("ctaDeepLink");
        this.sId = (String) json.get("_id");
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getHasCta() {
        return hasCta;
    }

    public String getCta() {
        return cta;
    }

    public String getCtaDeepLink() {
        return ctaDeepLink;
    }

    public String getsId() {
        return sId;
    }

    public Map<String, Object> toJson() {
        Map<String, Object> data = new HashMap<>();
        data.put("heading", heading);
        data.put("description", description);
        data.put("hasCta", hasCta);
        data.put("cta", cta);
        data.put("ctaDeepLink", ctaDeepLink);
        data.put("_id", sId);
        return data;
    }
}
