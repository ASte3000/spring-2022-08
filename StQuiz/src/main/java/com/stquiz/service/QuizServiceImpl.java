package com.stquiz.service;

import com.stquiz.dao.QuizDao;
import com.stquiz.domain.QuizElement;
import com.stquiz.output.QuizPrintService;

import java.util.Collection;

public class QuizServiceImpl implements QuizService {
    private final QuizDao dao;
    private final QuizPrintService printService;

    public QuizServiceImpl(QuizDao dao, QuizPrintService printService) {
        this.dao = dao;
        this.printService = printService;
    }

    @Override
    public void printElements() {
        Collection<QuizElement> quizElements = dao.getQuizElements();

        if (quizElements != null) {
            quizElements.forEach(this::printElement);
        }
    }

    private void printElement(QuizElement quizElement) {
        printService.println();
        printService.println("Question: " + quizElement.getQuestion());
        quizElement.getAnswers().forEach(answer ->
            printService.println("Answer: " + answer));
    }
}
