package com.stquiz.service;

import com.stquiz.dao.QuizDao;
import com.stquiz.domain.QuizElement;

import java.io.IOException;
import java.util.Collection;

public class QuizServiceImpl implements QuizService {
    private final QuizDao dao;

    public QuizServiceImpl(QuizDao dao) {
        this.dao = dao;
    }

    @Override
    public void printElements() {
        Collection<QuizElement> quizElements = null;
        try {
            quizElements = dao.getQuizElements();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (quizElements != null)
            quizElements.forEach(this::printElement);
    }

    private void printElement(QuizElement quizElement) {
        System.out.println();
        System.out.println("Question: " + quizElement.getQuestion());
        quizElement.getAnswers().forEach(answer ->
                System.out.println("Answer: " + answer));
    }
}
