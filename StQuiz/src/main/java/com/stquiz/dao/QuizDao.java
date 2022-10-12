package com.stquiz.dao;

import com.stquiz.domain.QuizElement;

import java.io.IOException;
import java.util.Collection;

public interface QuizDao {
    Collection<QuizElement> getQuizElements() throws IOException;
}
