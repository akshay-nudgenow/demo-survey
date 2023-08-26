package com.nudgenow.nudgesurvey.models;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class QuestionEntity {
    @SerializedName("id")
    private String id;

    @SerializedName("questionType")
    private String questionType;

    @SerializedName("question")
    private String question;

    @SerializedName("description")
    private String description;

    @SerializedName("attachments")
    private List<AttachmentType> attachments;

    @SerializedName("options")
    private List<String> options;

    public QuestionEntity(String id, String questionType, String question, String description, List<AttachmentType> attachments, List<String> options) {
        this.id = id;
        this.questionType = questionType;
        this.question = question;
        this.description = description;
        this.attachments = attachments;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public List<AttachmentType> getAttachments() {
        return attachments;
    }

    public List<String> getOptions() {
        return options;
    }
}
