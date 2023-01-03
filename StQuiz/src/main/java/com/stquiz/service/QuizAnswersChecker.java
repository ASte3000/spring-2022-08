package com.stquiz.service;

import java.util.List;

public interface QuizAnswersChecker {
    void printCorrectAnswers(List<QuizUserAnswer> userAnswers);

    void printResult(List<QuizUserAnswer> userAnswers);
}
