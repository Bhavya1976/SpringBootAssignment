package com.stackroute.Model;

public class Wiki {
    private String published;
    private String summary;
    private String content;


    // Getter Methods

    public String getPublished() {
        return published;
    }

    public String getSummary() {
        return summary;
    }

    public String getContent() {
        return content;
    }

    // Setter Methods

    public void setPublished(String published) {
        this.published = published;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String content) {
        this.content = content;
    }
}