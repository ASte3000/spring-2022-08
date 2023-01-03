package com.stquiz.service;

import com.stquiz.domain.QuizElement;
import com.stquiz.io.IOService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizInputServiceImpl implements QuizInputService {
    private final IOService ioService;

    public QuizInputServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public String takeUserName() {
        ioService.print("Please enter your first name: ");
        String firstName = ioService.readln();

        ioService.print("Please enter your last name: ");
        String lastName = ioService.readln();

        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public List<QuizUserAnswer> takeUserAnswers(List<QuizElement> quizElements) {
        List<QuizUserAnswer> userAnswers = new ArrayList<>();
        quizElements.forEach(quizElement -> {
            int userAnswerIndex = runQuestion(quizElement);
            userAnswers.add(new QuizUserAnswer(quizElement, userAnswerIndex));
        });

        return userAnswers;
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
