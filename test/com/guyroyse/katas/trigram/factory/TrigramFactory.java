package com.guyroyse.katas.trigram.factory;

import com.guyroyse.katas.trigram.collections.UnigramArrayList;
import com.guyroyse.katas.trigram.collections.UnigramList;

public class TrigramFactory {

	public static UnigramList makeUnigramList(String[] unigramText) {
		UnigramList unigrams = new UnigramArrayList();
		for (String text : unigramText) unigrams.add(text);
		return unigrams;		
	}
	
	public static UnigramList makeUnigramList(String text) {
		return makeUnigramList(new String[] {text});
	}
	
}
