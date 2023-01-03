package com.stquiz.service;

import com.stquiz.dao.TestQuizElementDao;
import com.stquiz.domain.QuizElement;
import com.stquiz.io.TestingIOService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizAnswersCheckerImplTest {

	@Test
	void printCorrectAnswers() {
		String expectedResult = "\n" +
			"Question: Question 1\n" +
			"User answer: Incorrect 1A\n" +
			"INCORRECT. Correct answer: Correct 1B\n" +
			"\n" +
			"Question: Question 2\n" +
			"User answer: Incorrect 2B\n" +
			"INCORRECT. Correct answer: Correct 2A\n" +
			"\n" +
			"Question: Question 3\n" +
			"User answer: Correct 3C\n" +
			"CORRECT\n";

		TestingIOService testingIOService = new TestingIOService();

		QuizAnswersCheckerImpl checker = new QuizAnswersCheckerImpl(testingIOService, 3);

		List<QuizElement> elements = new TestQuizElementDao().getQuizElements();

		List<QuizUserAnswer> userAnswers = new ArrayList<>();
		for (int i = 0; i < elements.size(); i++)
			userAnswers.add(new QuizUserAnswer(elements.get(i), i));

		checker.printCorrectAnswers(userAnswers);

		assertEquals(expectedResult, testingIOService.getResult());
	}

	@Test
	void printResult() {
		String expectedResult = "\n" +
			"Your result is 1 out of 3\n" +
			"Test failed. Minimum pass score is 2" +
			"\n";

		TestingIOService testingIOService = new TestingIOService();

		QuizAnswersCheckerImpl checker = new QuizAnswersCheckerImpl(testingIOService, 2);

		List<QuizElement> elements = new TestQuizElementDao().getQuizElements();

		List<QuizUserAnswer> userAnswers = new ArrayList<>();
		for (int i = 0; i < elements.size(); i++)
			userAnswers.add(new QuizUserAnswer(elements.get(i), i));

		checker.printResult(userAnswers);

		assertEquals(expectedResult, testingIOService.getResult());
	}
}