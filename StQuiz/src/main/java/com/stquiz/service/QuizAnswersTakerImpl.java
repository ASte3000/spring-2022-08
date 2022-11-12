package com.stquiz.service;

import com.stquiz.domain.QuizElement;
import com.stquiz.io.IOService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizAnswersTakerImpl implements QuizAnswersTaker {
    private final IOService ioService;

    public QuizAnswersTakerImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Map<QuizElement, Integer> takeUserAnswers(List<QuizElement> quizElements) {
        Map<QuizElement, Integer> userAnswerIndexesMap = new HashMap<>();
        quizElements.forEach(quizElement -> {
            int userAnswerIndex = runQuestion(quizElement);
            userAnswerIndexesMap.put(quizElement, userAnswerIndex);
        });

        return userAnswerIndexesMap;
    }

    private int runQuestion(QuizElement quizElement) {
        printElement(quizElement);
        return getUserAnswerNumber(quizElement.getAnswers().size()) - 1;
    }

    private void printElement(QuizElement quizElement) {
        ioService.println();
        ioService.println("Question: " + quizElement.getQuestion());

        for (int i = 0; i < quizElement.getAnswers().size(); i++) {
            ioService.println(String.format("%d: " + quizElement.getAnswers().get(i), i + 1));
        }
    }

    private int getUserAnswerNumber(int userMaxIndex) {
        while (true) {
            try {
                ioService.print("Please enter correct answer number: ");
                String userAnswerString = ioService.readln();
                int userAnswerNumber = Integer.parseInt(userAnswerString);
                if (userAnswerNumber >= 1 && userAnswerNumber <= userMaxIndex) {
                    return userAnswerNumber;
                } else {
                    ioService.println(
                            String.format("Incorrect answer, should be more or equal to 1 and less or equal to %d", userMaxIndex));
                }
            } catch (NumberFormatException e) {
                ioService.println("Incorrect answer format, please try again");
            }
        }
    }
}
