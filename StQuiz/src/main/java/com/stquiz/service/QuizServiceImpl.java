package com.stquiz.service;

import com.stquiz.domain.QuizElement;

import java.util.*;

public class QuizServiceImpl implements QuizService {
    private final QuizElementsPublisher elementsPublisher;
    private final QuizAnswersTaker answersTaker;
    private final QuizAnswersChecker answersChecker;

    public QuizServiceImpl(QuizElementsPublisher elementsPublisher, QuizAnswersTaker answersTaker, QuizAnswersChecker answersChecker) {
        this.elementsPublisher = elementsPublisher;
        this.answersTaker = answersTaker;
        this.answersChecker = answersChecker;
    }

    @Override
    public void runQuiz() {
        List<QuizElement> quizElements = elementsPublisher.getPublishableQuizElements();
        Map<QuizElement, Integer> userAnswerIndexesMap = answersTaker.takeUserAnswers(quizElements);

        answersChecker.printCorrectAnswers(userAnswerIndexesMap);
        answersChecker.printResult(userAnswerIndexesMap);
    }
}
