package com.stquiz.service;

import com.stquiz.dao.TestQuizElementDao;
import com.stquiz.domain.QuizElement;
import com.stquiz.io.TestingIOService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuizAnswersCheckerImplTest {

	@Test
	void printCorrectAnswers() {
		String expectedResult = "\n" +
				"Question: Question 2\n" +
				"User answer: Incorrect 2B\n" +
				"INCORRECT. Correct answer: Correct 2A\n" +
				"\n" +
				"Question: Question 1\n" +
				"User answer: Incorrect 1A\n" +
				"INCORRECT. Correct answer: Correct 1B\n" +
				"\n" +
				"Question: Question 3\n" +
				"User answer: Correct 3C\n" +
				"CORRECT\n";

		TestingIOService testingIOService = new TestingIOService();

		QuizAnswersCheckerImpl checker = new QuizAnswersCheckerImpl(testingIOService, 3);

		List<QuizElement> elements = new TestQuizElementDao().getQuizElements();

		Map<QuizElement, Integer> userAnswerIndexesMap = new HashMap<>();
		for (int i = 0; i < elements.size(); i++)
			userAnswerIndexesMap.put(elements.get(i), i);

		checker.printCorrectAnswers(userAnswerIndexesMap);

		assertEquals(expectedResult, testingIOService.getResult());
	}

/*	@Test
	void printResult() {
		//TODO later
	}*/
}