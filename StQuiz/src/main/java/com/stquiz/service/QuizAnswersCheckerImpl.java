package com.stquiz.service;

import com.stquiz.domain.QuizElement;
import com.stquiz.io.IOService;

import java.util.List;

public class QuizAnswersCheckerImpl implements QuizAnswersChecker {
    private final IOService ioService;
    private final int minPassScore;

    public QuizAnswersCheckerImpl(IOService ioService, int minPassScore) {
        this.ioService = ioService;
        this.minPassScore = minPassScore;
    }

    @Override
    public void printCorrectAnswers(List<QuizUserAnswer> userAnswers) {
        userAnswers.forEach(ua -> printCorrectAnswer(ua.getQuizElement(), ua.getUserAnswerIndex()));
    }

    @Override
    public void printResult(String userName, List<QuizUserAnswer> userAnswers) {
        ioService.println();
        ioService.println(String.format("%s,", userName));

        int correctAnswersCount =
            userAnswers.stream()
                .map(ua -> ua.getQuizElement().getCorrectAnswerIndex() == ua.getUserAnswerIndex() ? 1 : 0)
                .reduce(0, Integer::sum);

        ioService.println(
                String.format("Your result is %d out of %d", correctAnswersCount, userAnswers.size()));

        if (correctAnswersCount >= minPassScore) {
            ioService.println("Test passed");
        } else {
            ioService.println(String.format("Test failed. Minimum pass score is %d", minPassScore));
        }
    }

    private void printCorrectAnswer(QuizElement quizElement, int userAnswerIndex) {
        ioService.println();
        ioService.println("Question: " + quizElement.getQuestion());
        ioService.println("User answer: " + quizElement.getAnswers().get(userAnswerIndex));
        if (quizElement.getCorrectAnswerIndex() == userAnswerIndex) {
            ioService.println("CORRECT");
        } else {
            ioService.println("INCORRECT. Correct answer: " +
                quizElement.getAnswers().get(quizElement.getCorrectAnswerIndex()));
        }
    }
}
