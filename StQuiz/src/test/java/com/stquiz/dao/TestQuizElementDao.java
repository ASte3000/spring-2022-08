package com.stquiz.dao;

import com.stquiz.domain.QuizElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestQuizElementDao implements QuizElementDao {
	@Override
	public List<QuizElement> getQuizElements() {
		List<QuizElement> result = new ArrayList<>();

		QuizElement element1 = new QuizElement(
				"Question 1",
				Arrays.asList("Incorrect 1A", "Correct 1B", "Incorrect 1C"),
				1);
		result.add(element1);

		QuizElement element2 = new QuizElement(
				"Question 2",
				Arrays.asList("Correct 2A", "Incorrect 2B", "Incorrect 2C"),
				0);
		result.add(element2);

		QuizElement element3 = new QuizElement(
				"Question 3",
				Arrays.asList("Incorrect 3A", "Incorrect 3B", "Correct 3C"),
				2);
		result.add(element3);

		return result;
	}
}
