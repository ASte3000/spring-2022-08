package com.stquiz.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIOServiceImpl implements IOService {
    private final Scanner userInput;
    private final PrintStream userOutput;

    public ConsoleIOServiceImpl(InputStream inputStream, PrintStream userOutput) {
        userInput = new Scanner(inputStream);
        this.userOutput = userOutput;
    }

    @Override
    public void print(String text) {
        userOutput.print(text);
    }

    @Override
    public void println() {
        userOutput.println();
    }

    @Override
    public void println(String text) {
        userOutput.println(text);
    }

    @Override
    public String readln() {
        return userInput.nextLine();
    }
}