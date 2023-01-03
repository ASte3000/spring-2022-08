package com.stquiz.service;

import com.stquiz.domain.QuizElement;

public class QuizUserAnswer {
	private final QuizElement quizElement;
	private final int userAnswerIndex;

	public QuizUserAnswer(QuizElement quizElement, int userAnswerIndex) {
		this.quizElement = quizElement;
		this.userAnswerIndex = userAnswerIndex;
	}

	public QuizElement getQuizElement() {
		return quizElement;
	}

	public int getUserAnswerIndex() {
		return userAnswerIndex;
	}
}
