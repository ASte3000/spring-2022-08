package com.stquiz.service;

import com.stquiz.domain.QuizElement;

import java.util.List;

public interface QuizInputService {
    String takeUserName();
    List<QuizUserAnswer> takeUserAnswers(List<QuizElement> quizElements);
}
