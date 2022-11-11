package com.stquiz.io;

import java.util.Scanner;

public class ConsoleIOServiceImpl implements IOService {
    private final Scanner userInput;

    public ConsoleIOServiceImpl() {
        userInput = new Scanner(System.in);
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public String readln() {
        return userInput.nextLine();
    }
}