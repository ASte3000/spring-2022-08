package com.stquiz.domain;

import java.util.Collection;

public class QuizElement {
    private final String question;
    private final Collection<String> answers;
    private final String correctAnswer;

    public QuizElement(String question, Collection<String> answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public Collection<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
