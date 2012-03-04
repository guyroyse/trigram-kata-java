package com.guyroyse.katas.trigram;

import com.guyroyse.katas.trigram.collections.TrigramMap;
import com.guyroyse.katas.trigram.collections.UnigramList;

public class Generator {

	public String generate(TrigramMap map, String startingBigram) {
		StringBuilder document = new StringBuilder(startingBigram);
		generateDocument(map, document);
		return document.toString();
	}

	private void generateDocument(TrigramMap map, StringBuilder document) {
		for (UnigramList unigrams = fetchUnigrams(map, document); null != unigrams; unigrams = fetchUnigrams(map, document))
			appendUnigram(document, unigrams);
	}

	private UnigramList fetchUnigrams(TrigramMap map, StringBuilder document) {
		String currentBigram = fetchCurrentBigram(document);
		return map.get(currentBigram);
	}

	private void appendUnigram(StringBuilder document, UnigramList unigrams) {
		if (null != unigrams)
			document.append(" ").append(unigrams.getRandomUnigram());
	}

	private String fetchCurrentBigram(StringBuilder document) {
		String[] words = document.toString().split(" ");
		return Bigram.fetchBigram(words, words.length);
	}

}