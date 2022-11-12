package com.stquiz.service;

import com.stquiz.domain.QuizElement;

import java.util.List;
import java.util.Map;

public interface QuizAnswersTaker {
    Map<QuizElement, Integer> takeUserAnswers(List<QuizElement> quizElements);
}
