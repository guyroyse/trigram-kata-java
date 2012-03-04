package com.guyroyse.katas.trigram;

import com.guyroyse.katas.trigram.collections.TrigramHashMap;
import com.guyroyse.katas.trigram.collections.TrigramMap;
import com.guyroyse.katas.trigram.collections.UnigramArrayList;
import com.guyroyse.katas.trigram.collections.UnigramList;

public class Analyzer {

	private static final int STARTING_WORD = 2;
	
	public TrigramMap analyze(String document) {
		String[] words = parseWords(document); 
		return analyzeWords(words);
	}
	
	private String[] parseWords(String document) {
		return document.split("\\s+");
	}
	
	private TrigramMap analyzeWords(String[] words) {
		TrigramMap map = new TrigramHashMap();
		for (int position = STARTING_WORD; position < words.length; position++) {
			String bigram = Bigram.fetchBigram(words, position);
			UnigramList unigrams = fetchUnigrams(map, bigram);
			unigrams.add(fetchUnigram(words, position));
		}
		return map;
	}
	
	private UnigramList fetchUnigrams(TrigramMap map, String bigram) {
		UnigramList unigrams = map.get(bigram);
		if (null == unigrams) {
			unigrams = new UnigramArrayList();
			map.put(bigram, unigrams);
		}
		return unigrams;
	}

	private String fetchUnigram(String[] words, int position) {
		return words[position];
	}

}
