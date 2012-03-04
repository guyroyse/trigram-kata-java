package com.guyroyse.katas.trigram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TrigramFile {

	public static void writeFile(String filename, String text) {
		try {
			BufferedWriter writer = openFileForWrite(filename);
			writeEntireText(writer, text);
			writer.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String readFile(String filename) {
		String text;
		try {
			BufferedReader reader = openFileForRead(filename);
			text = readEntireFile(reader);
			reader.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return text;
	}

	private static BufferedWriter openFileForWrite(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		return writer;
	}

	private static BufferedReader openFileForRead(String filename) throws FileNotFoundException {
		return new BufferedReader(new FileReader(filename));
	}

	private static void writeEntireText(BufferedWriter writer, String text) throws IOException {
		writer.write(text);
	}

	private static String readEntireFile(BufferedReader reader) throws IOException {
		StringBuilder text = new StringBuilder();
		for (String line = reader.readLine(); line != null; line = reader.readLine())
			text.append(line).append("\n");
		return chompTrailingReturn(text).toString();
	}

	private static StringBuilder chompTrailingReturn(StringBuilder text) {
		return text.deleteCharAt(text.length() - 1);
	}

}

