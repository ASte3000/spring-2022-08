package com.stquiz.service;

import com.stquiz.dao.QuizDao;
import com.stquiz.domain.QuizElement;
import com.stquiz.output.QuizPrintService;

import java.util.*;
import java.util.stream.Collectors;

public class QuizServiceImpl implements QuizService {
    private final QuizDao dao;
    private final QuizPrintService printService;
    private final int minPassScore;

    public QuizServiceImpl(QuizDao dao, QuizPrintService printService, int minPassScore) {
        this.dao = dao;
        this.printService = printService;
        this.minPassScore = minPassScore;
    }

    @Override
    public void runQuiz() {
        Collection<QuizElement> quizElements = getShuffledQuizElements();

        Map<QuizElement, Integer> userAnswerIndexesMap = new HashMap<>();
        quizElements.forEach(quizElement -> {
            int userAnswerIndex = runQuestion(quizElement);
            userAnswerIndexesMap.put(quizElement, userAnswerIndex);
        });

        quizElements.forEach(quizElement -> printCorrectAnswer(quizElement, userAnswerIndexesMap.get(quizElement)));

        printResult(userAnswerIndexesMap);
    }

    private Collection<QuizElement> getShuffledQuizElements() {
        return dao.getQuizElements()
                .stream()
                .map(this::shuffleQuizElement)
                .collect(Collectors.toList());
    }

    private QuizElement shuffleQuizElement(QuizElement daoQuizElement) {
        List<String> answers = new ArrayList<>(daoQuizElement.getAnswers());

        for (int i=0; i < 10; i++) {
            int index1 = (int)Math.round(Math.random() * (answers.size() - 1));
            int index2 = (int)Math.round(Math.random() * (answers.size() - 1));
            Collections.swap(answers, index1, index2);
        }

        String correctAnswer = daoQuizElement.getAnswers().get(daoQuizElement.getCorrectAnswerIndex());
        int correctAnswerIndex = answers.indexOf(correctAnswer);

        return new QuizElement(daoQuizElement.getQuestion(), answers, correctAnswerIndex);
    }

    private void printElement(QuizElement quizElement) {
        printService.println();
        printService.println("Question: " + quizElement.getQuestion());

        for (int i = 0; i < quizElement.getAnswers().size(); i++) {
            printService.println(String.format("%d: " + quizElement.getAnswers().get(i), i + 1));
        }
    }

    private int getUserAnswerNumber(int userMaxIndex) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                printService.print("Please enter correct answer number: ");
                String userAnswerString = scanner.nextLine();
                int userAnswerNumber = Integer.parseInt(userAnswerString);
                if (userAnswerNumber >= 1 && userAnswerNumber <= userMaxIndex) {
                    return userAnswerNumber;
                } else {
                    printService.println(
                            String.format("Incorrect answer, should be more or equal to 1 and less or equal to %d", userMaxIndex));
                }
            } catch (NumberFormatException e) {
                printService.println("Incorrect answer format, please try again");
            }
        }
    }

    private int runQuestion(QuizElement quizElement) {
        printElement(quizElement);
        return getUserAnswerNumber(quizElement.getAnswers().size()) - 1;
    }

    private void printCorrectAnswer(QuizElement quizElement, int userAnswerIndex) {
        printService.println();
        printService.println("Question: " + quizElement.getQuestion());
        printService.println("User answer: " + quizElement.getAnswers().get(userAnswerIndex));
        if (quizElement.getCorrectAnswerIndex() == userAnswerIndex) {
            printService.println("CORRECT");
        } else {
            printService.println("INCORRECT. Correct answer: " +
                    quizElement.getAnswers().get(quizElement.getCorrectAnswerIndex()));
        }
    }

    private void printResult(Map<QuizElement, Integer> userAnswerIndexesMap) {
        int correctAnswersCount =
            userAnswerIndexesMap.entrySet()
                .stream()
                .map(entry -> entry.getKey().getCorrectAnswerIndex() == entry.getValue() ? 1 : 0)
                .reduce(0, Integer::sum);

        printService.println();
        printService.println(
                String.format("Your result is %d out of %d", correctAnswersCount, userAnswerIndexesMap.size()));

        if (correctAnswersCount >= minPassScore) {
            printService.println("Test passed");
        } else {
            printService.println(String.format("Test failed. Minimum pass score is %d", minPassScore));
        }
    }
}
