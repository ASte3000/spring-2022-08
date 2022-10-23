package com.stquiz.dao;

public class QuizDaoException extends RuntimeException {
    public QuizDaoException(Throwable cause) {
        super("QuizDao exception", cause);
    }
}
