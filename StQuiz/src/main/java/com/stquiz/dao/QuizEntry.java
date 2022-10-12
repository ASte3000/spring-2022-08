package com.stquiz.dao;

public class QuizEntry {
    private int id;
    private QuizEntryType type;
    private String text;

    public QuizEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuizEntryType getType() {
        return type;
    }

    public void setType(QuizEntryType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
