package com.stquiz.service;

import com.stquiz.domain.QuizElement;

import java.util.Map;

public interface QuizAnswersChecker {
    void printCorrectAnswers(Map<QuizElement, Integer> userAnswerIndexesMap);

    void printResult(Map<QuizElement, Integer> userAnswerIndexesMap);
}
