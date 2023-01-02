package com.stquiz.dao;

public class QuizElementDaoException extends RuntimeException {
    public QuizElementDaoException(Throwable cause) {
        super("QuizDao exception", cause);
    }
}
