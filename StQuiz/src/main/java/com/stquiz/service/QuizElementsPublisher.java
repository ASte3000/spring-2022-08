package com.stquiz.service;

import com.stquiz.domain.QuizElement;

import java.util.List;

public interface QuizElementsPublisher {
    List<QuizElement> getPublishableQuizElements();
}
