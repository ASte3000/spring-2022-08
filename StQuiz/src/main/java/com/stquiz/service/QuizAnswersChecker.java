package com.stquiz.service;

import java.util.List;

public interface QuizAnswersChecker {
    void printCorrectAnswers(List<QuizUserAnswer> userAnswers);

    void printResult(String userName, List<QuizUserAnswer> userAnswers);
}
