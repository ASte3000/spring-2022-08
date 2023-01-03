package com.stquiz.service;

import com.stquiz.dao.QuizElementDao;
import com.stquiz.domain.QuizElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizElementsServiceImpl implements QuizElementsService {
    private static final int SWAP_ROUNDS_COUNT = 10;

    private final QuizElementDao dao;

    public QuizElementsServiceImpl(QuizElementDao dao) {
        this.dao = dao;
    }

    @Override
    public List<QuizElement> getPreparedQuizElements() {
        Collection<QuizElement> rawElements = dao.getQuizElements();

        return rawElements
            .stream()
            .map(this::shuffleAnswers)
            .collect(Collectors.toList());
    }

    private QuizElement shuffleAnswers(QuizElement daoQuizElement) {
        List<String> answers = new ArrayList<>(daoQuizElement.getAnswers());

        int maxValue = answers.size() - 1;
        for (int i = 0; i < SWAP_ROUNDS_COUNT; i++) {
            int index1 = getRandomSwapIndex(maxValue);
            int index2 = getRandomSwapIndex(maxValue);
            Collections.swap(answers, index1, index2);
        }

        String correctAnswer = daoQuizElement.getAnswers().get(daoQuizElement.getCorrectAnswerIndex());
        int correctAnswerIndex = answers.indexOf(correctAnswer);

        return new QuizElement(daoQuizElement.getQuestion(), answers, correctAnswerIndex);
    }

    private int getRandomSwapIndex(int maxValue) {
        return (int) Math.round(Math.random() * maxValue);
    }
}
