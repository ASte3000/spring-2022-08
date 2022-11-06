package com.stquiz.output;

import java.io.PrintStream;

public class QuizPrintServiceImpl implements QuizPrintService {
    private final PrintStream printStream;

    public QuizPrintServiceImpl(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void println() {
        printStream.println();
    }

    @Override
    public void println(String text) {
        printStream.println(text);
    }
}