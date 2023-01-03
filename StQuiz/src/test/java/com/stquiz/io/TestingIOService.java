package com.stquiz.io;

public class TestingIOService implements IOService {

	private final StringBuilder sb = new StringBuilder();

	@Override
	public void print(String text) {
		sb.append(text);
	}

	@Override
	public void println() {
		sb.append("\n");
	}

	@Override
	public void println(String text) {
		print(text);
		println();
	}

	@Override
	public String readln() {
		return null;
	}

	public String getResult() {
		return sb.toString();
	}
}
