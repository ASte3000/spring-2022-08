package com.stquiz.dao;

import com.stquiz.domain.QuizElement;
import org.springframework.lang.NonNull;

import java.util.List;

public interface QuizElementDao {
    @NonNull
    List<QuizElement> getQuizElements();
}
