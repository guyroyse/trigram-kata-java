package com.guyroyse.katas.trigram.collections;

import static com.guyroyse.katas.trigram.factory.TrigramFactory.*;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;


public class UnigramListTest {

	private final UnigramList unigrams = makeUnigramList(new String[] {"foo", "bar", "baz"}); 

	@Test
	public void itReturnsOnlyUnigramWhenListHasASingleItem() {
		assertEquals("foo", makeUnigramList("foo").getRandomUnigram());		
	}
	
	@Test
	public void itReturnsRandomUnigramWhenThereAreMultipleUnigrams() {
		unigrams.setRandom(new Random() {
			public int nextInt(int n) { return n; }
		});
		assertEquals("baz", unigrams.getRandomUnigram());
	}

	@Test
	public void itHandsTheMaximumIndexOfTheUnigramListToRandom() {
		unigrams.setRandom(new Random() {
			public int nextInt(int n) {
				assertEquals(unigrams.size() - 1, n);
				return n; 
			}
		});		
		unigrams.getRandomUnigram();
	}

}