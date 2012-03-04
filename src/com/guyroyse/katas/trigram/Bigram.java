package com.guyroyse.katas.trigram;

public class Bigram {

	public static String fetchBigram(String[] words, int position) {
		StringBuilder bigram = new StringBuilder();
		if (words.length > 1)
			bigram.append(words[position - 2]).append(" ").append(words[position - 1]);
		return bigram.toString();
	}

}
