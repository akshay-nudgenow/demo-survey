package com.nudgenow.nudgesurvey.nudgeCore.model;




import java.util.List;
import java.util.Map;

public class User {
    private final String status;
    public final Data data;

    public User(String status, Data data) {
        this.status = status;
        this.data = data;
    }
    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        private final String id;
        private final String deviceId;
        private final String name;
        private final String email;
        private final String phoneNumber;
        private final String referredBy;
        private final String referrerCode;
        private final int usersReferred;
        private final String referralCode;
        private final String referralCodeExpiry;
        private final String externalId;
        private final int score;
        private final String token;
        private final String clientId;
        private final String shareId;

        private final List<Object> tags;
        private final List<Object> tasks;
        private final List<Object> rewards;
        private final String photoUrl;
        private final String createdAt;
        private final String updatedAt;
        private final int v;

        public Data(String id, String deviceId, String name, String email, String phoneNumber, String referredBy,
                    String referrerCode, int usersReferred, String referralCode, String referralCodeExpiry,
                    String externalId, int score, String clientId, String shareId, String token,
                    List<Object> tags, List<Object> tasks, List<Object> rewards, String photoUrl,
                    String createdAt, String updatedAt, int v) {
            this.id = id;
            this.deviceId = deviceId;
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.referredBy = referredBy;
            this.referrerCode = referrerCode;
            this.usersReferred = usersReferred;
            this.referralCode = referralCode;
            this.referralCodeExpiry = referralCodeExpiry;
            this.externalId = externalId;
            this.score = score;
            this.clientId = clientId;
            this.shareId = shareId;
            this.token = token;
            this.tags = tags;
            this.tasks = tasks;
            this.rewards = rewards;
            this.photoUrl = photoUrl;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.v = v;
        }



        public static Data fromJson(Map<String, Object> json) {
            String id = (String) json.get("_id");
            String deviceId = (String) json.get("deviceId");
            String name = (String) json.get("name");
            String email = (String) json.get("email");
            String phoneNumber = (String) json.get("phoneNumber");
            String referredBy = (String) json.get("referredBy");
            String referrerCode = (String) json.get("referrerCode");
            int usersReferred = (int) json.get("usersReferred");
            String referralCode = (String) json.get("referralCode");
            String referralCodeExpiry = (String) json.get("referralCodeExpiry");
            String externalId = (String) json.get("externalId");
            int score = (int) json.get("score");
            String clientId = (String) json.get("clientId");
            String shareId = (String) json.get("shareId");
            String token = (String) json.get("token");
            List<Object> tags = json.containsKey("tags") ? (List<Object>) json.get("tags") : null;
            List<Object> tasks = json.containsKey("tasks") ? (List<Object>) json.get("tasks") : null;
            List<Object> rewards = json.containsKey("rewards") ? (List<Object>) json.get("rewards") : null;
            String photoUrl = (String) json.get("photoUrl");
            String createdAt = (String) json.get("createdAt");
            String updatedAt = (String) json.get("updatedAt");
            int v = (int) json.get("__v");

            return new Data(id, deviceId, name, email, phoneNumber, referredBy, referrerCode, usersReferred,
                    referralCode, referralCodeExpiry, externalId, score, clientId, shareId, token,
                    tags, tasks, rewards, photoUrl, createdAt, updatedAt, v);
        }
        public String getToken() {
            return token;
        }
        public String getId() {
            return id;
        }
    }

    public static User fromJson(Map<String, Object> json) {
        String status = (String) json.get("status");
        Data data = json.containsKey("data") ? Data.fromJson((Map<String, Object>) json.get("data")) : null;
        return new User(status, data);
    }
}
/*
This Java code defines a model class "User" with nested class "Data" to represent user-related data fetched from JSON objects.
The code provides methods to convert JSON data into User objects
and their attributes, like ID, name, email, and token, using static factory methods and constructors.
 */
