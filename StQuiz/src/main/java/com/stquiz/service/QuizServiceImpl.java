package com.stquiz.service;

import com.stquiz.domain.QuizElement;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizElementsService elementsService;
    private final QuizInputService inputService;
    private final QuizAnswersChecker answersChecker;

    public QuizServiceImpl(QuizElementsService elementsService, QuizInputService inputService, QuizAnswersChecker answersChecker) {
        this.elementsService = elementsService;
        this.inputService = inputService;
        this.answersChecker = answersChecker;
    }

    @Override
    public void runQuiz() {
        String userName = inputService.takeUserName();

        List<QuizElement> quizElements = elementsService.getPreparedQuizElements();
        List<QuizUserAnswer> userAnswers = inputService.takeUserAnswers(quizElements);

        answersChecker.printCorrectAnswers(userAnswers);
        answersChecker.printResult(userName, userAnswers);
    }
}
