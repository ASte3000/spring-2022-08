package com.stquiz.output;

public class QuizPrintServiceImpl implements QuizPrintService {
    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }
}