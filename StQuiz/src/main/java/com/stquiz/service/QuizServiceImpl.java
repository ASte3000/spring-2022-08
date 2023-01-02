package com.stquiz.service;

import com.stquiz.domain.QuizElement;

import java.util.*;

public class QuizServiceImpl implements QuizService {
    private final QuizElementsService elementsService;
    private final QuizAnswersTaker answersTaker;
    private final QuizAnswersChecker answersChecker;

    public QuizServiceImpl(QuizElementsService elementsService, QuizAnswersTaker answersTaker, QuizAnswersChecker answersChecker) {
        this.elementsService = elementsService;
        this.answersTaker = answersTaker;
        this.answersChecker = answersChecker;
    }

    @Override
    public void runQuiz() {
        List<QuizElement> quizElements = elementsService.getPreparedQuizElements();
        Map<QuizElement, Integer> userAnswerIndexesMap = answersTaker.takeUserAnswers(quizElements);

        answersChecker.printCorrectAnswers(userAnswerIndexesMap);
        answersChecker.printResult(userAnswerIndexesMap);
    }
}
