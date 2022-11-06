package com.stquiz.dao;

import com.stquiz.domain.QuizElement;
import org.springframework.lang.NonNull;

import java.util.Collection;

public interface QuizDao {
    @NonNull
    Collection<QuizElement> getQuizElements();
}
