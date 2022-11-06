package com.stquiz.domain;

import java.util.Collections;
import java.util.List;

public class QuizElement {
    private final String question;
    private final List<String> answers;
    private final int correctAnswerIndex;

    public QuizElement(String question, List<String> answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = Collections.unmodifiableList(answers);
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
