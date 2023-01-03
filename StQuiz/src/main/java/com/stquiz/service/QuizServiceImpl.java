package com.stquiz.service;

import com.stquiz.domain.QuizElement;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
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
        List<QuizUserAnswer> userAnswers = answersTaker.takeUserAnswers(quizElements);

        answersChecker.printCorrectAnswers(userAnswers);
        answersChecker.printResult(userAnswers);
    }
}
