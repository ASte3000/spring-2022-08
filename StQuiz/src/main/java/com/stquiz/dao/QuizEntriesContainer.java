package com.stquiz.dao;

import com.stquiz.domain.QuizElement;

import java.util.*;
import java.util.stream.Collectors;

public class QuizEntriesContainer {
    private final Map<Integer, String> questionsMap = new HashMap<>();
    private final Map<Integer, List<String>> allAnswersMap = new HashMap<>();
    private final Map<Integer, Integer> correctAnswerIndexesMap = new HashMap<>();

    public void addEntry(QuizEntry entry) {
        int id = entry.getId();
        String text = entry.getText();

        switch (entry.getType()) {
            case QUESTION:
                addQuestion(id, text);
                return;
            case CORRECT_ANSWER:
                addCorrectAnswer(id, text);
                return;
            case INCORRECT_ANSWER:
                addIncorrectAnswer(id, text);
                return;
            default:
                throw new IllegalArgumentException("Unexpected type: " + entry.getType());
        }
    }

    public List<QuizElement> getQuizElements() {
        return questionsMap.entrySet().stream()
                .map(this::getQuizElement)
                .collect(Collectors.toList());
    }

    private QuizElement getQuizElement(Map.Entry<Integer, String> questionsMapEntry) {
        int id = questionsMapEntry.getKey();
        String question = questionsMapEntry.getValue();
        List<String> allAnswers = allAnswersMap.get(id);
        int correctAnswerIndex = correctAnswerIndexesMap.get(id);

        return new QuizElement(question, allAnswers, correctAnswerIndex);
    }

    private void addQuestion(int id, String text) {
        questionsMap.put(id, text);
    }

    private void addCorrectAnswer(int id, String text) {
        addAnswer(id, text);

        int correctIndex = allAnswersMap.get(id).indexOf(text);
        correctAnswerIndexesMap.put(id, correctIndex);
    }

    private void addIncorrectAnswer(int id, String text) {
        addAnswer(id, text);
    }

    private void addAnswer(int id, String text) {
        List<String> answersList = allAnswersMap.computeIfAbsent(id, i -> new ArrayList<>());
        answersList.add(text);
    }

}
